package functional1;

import base.FunctionalBaseTest;
import client.PetStoreTestClass;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import request.User;
import response.ApiResponse;
import util.TestHelper;

import java.io.IOException;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CreateUpdateUser extends FunctionalBaseTest {

    private static String username;
    private static long userId;
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String password;
    private static String phone;
    private static int userStatus;
    private static String userSession;
    private static String userSessionExpiry;
    private static int userRateLimit;
    private String updated = "_updated";
    private String updatedUserName = null;

    @Override
    public void setupData() throws Exception {
    }

    @DataProvider
    public Iterator<Object[]> getNewUserData() throws IOException {
        List<Object[]> data = Lists.newLinkedList();


        User validUser1 =  User.builder()
                .id(100)
                .username("username1")
                .firstName("firstname1")
                .lastName("lastname1")
                .email("firstname1@email1.com")
                .password("password1")
                .phone(TestHelper.getRandomPhoneNumber())
                .userStatus(1000)
                .build();

        User validUser2 =  User.builder()
                .id(200)
                .username("username2")
                .firstName("firstname2")
                .lastName("lastname2")
                .email("firstname2@email2.com")
                .password("password2")
                .phone(TestHelper.getRandomPhoneNumber())
                .userStatus(2000)
                .build();

        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(TestHelper.getJsonString(validUser1));
        jsonArrayPayload.put(TestHelper.getJsonString(validUser2));

        data.add(new Object[] {jsonArrayPayload, 200, "unknown", "ok"});

        return  data.iterator()  ;
    }

    @Test(dataProvider = "getNewUserData" , testName = "create new users")
    public void createValidUserArrayTest(JSONArray userArray, int statusCode, String type, String message) {
        List<String> existingUsers = new ArrayList<>();
        ApiResponse response = new PetStoreTestClass().createUserArray(getHostname(),userArray);
        Assert.assertEquals(response.getHttpStatusCode(), statusCode);
        if (statusCode != 200) {
            response.assertErrorData(type,statusCode,message);
        } else {
            response.assertSuccessData(type);
        }
        if (statusCode == 200) {
            for(int i=0;i<userArray.length();i++){
                existingUsers.add(userArray.getJSONObject(i).get("username").toString());
            }
        }
        for(String user:existingUsers ) {
            validUserNames(user);
        }
    }

    @Test(testName = "Get existing user details" , dependsOnMethods = "createValidUserArrayTest")
    public void getExistingUserDetails() throws IOException {
        //Fetch Existing UserName
        Random random = new Random();
        int randomUserIndex = random.nextInt(allValidUserNames.size());
        username = allValidUserNames.get(randomUserIndex);
        System.out.println("Username ::: " + username);

        //Fetch User details
        Response response = new PetStoreTestClass().getUserDetails(getHostname(), username);
        User userDetails = (User) TestHelper.getResponseObject(response.getBody().asString(), User.class);
        userId = userDetails.getId();
        firstName = userDetails.getFirstName();
        lastName = userDetails.getLastName();
        email = userDetails.getEmail();
        password = userDetails.getPassword();
        phone = userDetails.getPhone();
        userStatus = userDetails.getUserStatus();
    }

    @DataProvider
    public Iterator<Object[]> userLoginData() {
        List<Object[]> data = Lists.newLinkedList();
        Map<String, Object> validUserDetails = ImmutableMap.of("username", username, "password", password);
        data.add(new Object[]{validUserDetails, 200, "unknown", "logged in user session:"});
        return data.iterator();
    }

    @Test(testName = "Login User Test" , dependsOnMethods = "getExistingUserDetails", dataProvider = "userLoginData")
    public void loginUserTest(Map<String, Object> params, int statusCode, String type, String message){
        Response response = new PetStoreTestClass().loginUser(getHostname(), params);

        Assert.assertEquals(response.getStatusCode(), statusCode);
        Assert.assertEquals(response.jsonPath().get("type").toString(), type);
        Assert.assertTrue(response.jsonPath().get("message").toString().startsWith(message));

        //Capture user session
        List<String> sr = Arrays.asList(response.jsonPath().get("message").toString().split(":"));
        userSession = sr.get(1);
        System.out.print("user logged in as " + username + " with session "+userSession);

        //verify response headers
        userSessionExpiry = response.getHeader("x-expires-after");
        userRateLimit = Integer.parseInt(response.getHeader("x-rate-limit"));

        Assert.assertNotNull(userSessionExpiry);
        Assert.assertEquals(userRateLimit, 5000);
    }

    @DataProvider
    public Iterator<Object[]> updateUserData(){
        List<Object[]> data = Lists.newLinkedList();
        int message = 0;

        User updateId =  User.builder()
                .id(12321)
                .build();

        User updateUserName =  User.builder()
                .username(username+updated)
                .build();

        User updateFirstName =  User.builder()
                .firstName(firstName+updated)
                .build();

        User updateLastName =  User.builder()
                .firstName(lastName+updated)
                .build();

        User updateEmail =  User.builder()
                .firstName(updated+email)
                .build();

        User updatePassword =  User.builder()
                .firstName(password+updated)
                .build();

        User updatePhone =  User.builder()
                .firstName(TestHelper.getRandomPhoneNumber())
                .build();

        User updateUserStatus =  User.builder()
                .userStatus(TestHelper.getRandomNumberInt())
                .build();

        data.add(new Object[]{updateId, 200, "unknown", updateId.getId()});
        data.add(new Object[]{updateFirstName, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateLastName, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateEmail, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updatePassword, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updatePhone, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateUserStatus, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateUserName, 200, "unknown", ""});

        return  data.iterator();
    }

    @Test(testName = "update user details", dependsOnMethods = "loginUserTest",dataProvider = "updateUserData")
    public void updateUserDetailsTest(Map<String, Object> params, int statusCode, String type, String message) throws ParseException {
        //fetch user session expiry details
        ZonedDateTime expiry = TestHelper.formatDate(userSessionExpiry);
        OffsetDateTime current = TestHelper.formatDateToUTC();
        if(!current.isAfter(OffsetDateTime.from(expiry))){

            //start updating user details
            String updateRequestBody = TestHelper.getJsonString(params);
            ApiResponse response = new PetStoreTestClass().updateUser(getHostname(), username, updateRequestBody);
            Assert.assertEquals(response.getHttpStatusCode(), statusCode);
            if (statusCode != 200) {
                response.assertErrorData(type, statusCode, message);
            } else {
                Assert.assertEquals(response.getHttpStatusCode(), statusCode);
                Assert.assertEquals(response.getType(), type);
                if (!message.equals("")) {
                    Assert.assertEquals(response.getMessage(), message);
                } else {
                    Assert.assertTrue(TestHelper.isInClosedRange(Long.valueOf(response.getMessage()), -9223372036854775808L, 9223372036854775807L));
                }
            }
            updatedUserName = username + updated;
        } else{
            System.out.println("User session expired!");
        }
    }

    @Test(testName = "verify updated user details",dependsOnMethods = "updateUserDetailsTest")
    public void getUpdatedUserDetailsTest() throws IOException {
        Response response = new PetStoreTestClass().getUserDetails(getHostname(), updatedUserName);
        Assert.assertEquals(response.getStatusCode(),200);

        User userDetails = (User) TestHelper.getResponseObject(response.getBody().asString(), User.class);
        userId = userDetails.getId();
        firstName = userDetails.getFirstName();
        lastName = userDetails.getLastName();
        email = userDetails.getEmail();
        password = userDetails.getPassword();
        phone = userDetails.getPhone();
        userStatus = userDetails.getUserStatus();

        //verify updated user name
        Assert.assertEquals(updatedUserName,userDetails.getUsername());
    }

}
