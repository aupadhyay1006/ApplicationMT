package functional1;

import base.FunctionalBaseTest;
import client.PetStoreTestClass;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
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

@Slf4j
public class UpdateUserTest extends FunctionalBaseTest {
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

        List<String> randomAppender = new ArrayList<String>();
        randomAppender.add(TestHelper.getRandomNumber());
        List<String> existingUsers = new ArrayList<>();

        //create a valid user
        JSONObject validUser = new JSONObject();
        validUser.put("id", TestHelper.getRandomNumberInt());
        validUser.put("username", randomAppender.get(0) + "_" + "TestUserName");
        validUser.put("firstName", randomAppender.get(0) + "_" + "TestUserFirstName");
        validUser.put("lastName", randomAppender.get(0) + "_" + "TestUserLastName");
        validUser.put("email", randomAppender.get(0) + "testuseremail@test.com");
        validUser.put("password", randomAppender.get(0) + "TestUserPassword");
        validUser.put("phone", TestHelper.getRandomPhoneNumber());
        validUser.put("userStatus", TestHelper.getRandomNumberInt());
        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(validUser);
        ApiResponse createUserResponse = new PetStoreTestClass().createUserArray(getHostname(), jsonArrayPayload);
        if (createUserResponse.getHttpStatusCode() == 200) {
            for (int i = 0; i < jsonArrayPayload.length(); i++) {
                existingUsers.add(jsonArrayPayload.getJSONObject(i).get("username").toString());
            }
        }

        for (String user : existingUsers) {
            validUserNames(user);
        }

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
    public Iterator<Object[]> getValidData() {
        List<Object[]> data = Lists.newLinkedList();
        Map<String, Object> validUserDetails = ImmutableMap.of("username", username, "password", password);
        data.add(new Object[]{validUserDetails, 200, "unknown", "logged in user session:"});
        return data.iterator();
    }

    @Test(testName = "valid login user test", dataProvider = "getValidData")
    public void loginUserValidTest(Map<String, Object> params, int statusCode, String type, String message) {
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
    public Iterator<Object[]> updateUserData() {

        List<Object[]> data = Lists.newLinkedList();
        int message = 0;

        Map<String, Object> updateId = ImmutableMap.of("id", TestHelper.getRandomNumber());
        Map<String, Object> updateFirstName = ImmutableMap.of("firstName", firstName + updated);
        Map<String, Object> updateLastName = ImmutableMap.of("lastName", lastName + updated);
        Map<String, Object> updateEmail = ImmutableMap.of("email", updated + email);
        Map<String, Object> updatePassword = ImmutableMap.of("password", password + updated);
        Map<String, Object> updatePhone = ImmutableMap.of("phone", TestHelper.getRandomPhoneNumber());
        Map<String, Object> updateUserStatus = ImmutableMap.of("userStatus", TestHelper.getRandomNumber());
        Map<String, Object> updateUserName = ImmutableMap.of("username", username + updated);

        data.add(new Object[]{updateId, 200, "unknown", String.valueOf(updateId.get("id"))});
        data.add(new Object[]{updateFirstName, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateLastName, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateEmail, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updatePassword, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updatePhone, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateUserStatus, 200, "unknown", String.valueOf(message)});
        data.add(new Object[]{updateUserName, 200, "unknown", ""});

        return data.iterator();
    }

    @Test(testName = "update valid user", dataProvider = "updateUserData", dependsOnMethods = "loginUserValidTest")
    public void updateUserValidTest(Map<String, Object> params, int statusCode, String type, String message) throws ParseException {

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

    @Test(dependsOnMethods = "updateUserValidTest" )
    public void getUpdatedUserDetails() throws IOException {
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

    @Test (dependsOnMethods = "updateUserValidTest" )
    public void getInvalidUserDetails() throws IOException {
        Response response = new PetStoreTestClass().getUserDetails(getHostname(), updatedUserName+"invalid");
        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(response.getBody().jsonPath().getInt("code"),1);
        Assert.assertEquals(response.getBody().jsonPath().get("type"),"error");
        Assert.assertEquals(response.getBody().jsonPath().get("message"),"User not found");

    }

}
