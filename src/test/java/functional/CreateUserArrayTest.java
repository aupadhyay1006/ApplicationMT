package functional;

import base.FunctionalBaseTest;
import client.PetStoreTestClass;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import response.ApiResponse;
import util.TestHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Slf4j
public class CreateUserArrayTest extends FunctionalBaseTest {
    @Override
    public void setupData() throws Exception {
    }

    @DataProvider
    public Iterator<Object[]> getValidEmptyParamData() {
        List<Object[]> data = Lists.newLinkedList();
        List<String> randomAppender=new ArrayList<String>();
        int i=0;
        while(i < 8){
            randomAppender.add(TestHelper.getRandomNumber());
            i++;
        }

        JSONObject emptyIdParam = new JSONObject();
        emptyIdParam.put("id", "" );
        emptyIdParam.put("username",  randomAppender.get(0) + "_" + "TestUserName");
        emptyIdParam.put("firstName",  randomAppender.get(0) + "_" + "TestUserFirstName");
        emptyIdParam.put("lastName", randomAppender.get(0) + "_" + "TestUserLastName");
        emptyIdParam.put("email", randomAppender.get(0) + "testuseremail@test.com");
        emptyIdParam.put("password",  randomAppender.get(0) + "TestUserPassword");
        emptyIdParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyIdParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyUserNameParam = new JSONObject();
        emptyUserNameParam.put("id", TestHelper.getRandomNumberInt());
        emptyUserNameParam.put("username", "");
        emptyUserNameParam.put("firstName",  randomAppender.get(1) + "_" + "TestUserFirstName");
        emptyUserNameParam.put("lastName",randomAppender.get(1) + "_" + "TestUserLastName");
        emptyUserNameParam.put("email",randomAppender.get(1) + "testuseremail@test.com");
        emptyUserNameParam.put("password", randomAppender.get(1) + "TestUserPassword");
        emptyUserNameParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyUserNameParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyFirstNameParam = new JSONObject();
        emptyFirstNameParam.put("id", TestHelper.getRandomNumberInt());
        emptyFirstNameParam.put("username",randomAppender.get(2) + "_" + "TestUserName");
        emptyFirstNameParam.put("firstName", "");
        emptyFirstNameParam.put("lastName",randomAppender.get(2) + "_" + "TestUserLastName");
        emptyFirstNameParam.put("email",randomAppender.get(2) + "testuseremail@test.com");
        emptyFirstNameParam.put("password", randomAppender.get(2) + "TestUserPassword");
        emptyFirstNameParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyFirstNameParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyLastNameParam = new JSONObject();
        emptyLastNameParam.put("id", TestHelper.getRandomNumberInt());
        emptyLastNameParam.put("username", randomAppender.get(3) + "_" + "TestUserName");
        emptyLastNameParam.put("firstName", randomAppender.get(3) + "_" + "TestUserFirstName");
        emptyLastNameParam.put("lastName","");
        emptyLastNameParam.put("email",randomAppender.get(3) + "testuseremail@test.com");
        emptyLastNameParam.put("password", randomAppender.get(3) + "TestUserPassword");
        emptyLastNameParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyLastNameParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyEmailParam = new JSONObject();
        emptyEmailParam.put("id", TestHelper.getRandomNumberInt());
        emptyEmailParam.put("username",randomAppender.get(4) + "_" + "TestUserName");
        emptyEmailParam.put("firstName", randomAppender.get(4) + "_" + "TestUserFirstName");
        emptyEmailParam.put("lastName",randomAppender.get(4) + "_" + "TestUserLastName");
        emptyEmailParam.put("email","");
        emptyEmailParam.put("password", randomAppender.get(4) + "TestUserPassword");
        emptyEmailParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyEmailParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyPasswordParam = new JSONObject();
        emptyPasswordParam.put("id", TestHelper.getRandomNumberInt());
        emptyPasswordParam.put("username",randomAppender.get(5) + "_" + "TestUserName");
        emptyPasswordParam.put("firstName",randomAppender.get(5) + "_" + "TestUserFirstName");
        emptyPasswordParam.put("lastName",randomAppender.get(5) + "_" + "TestUserLastName");
        emptyPasswordParam.put("email",randomAppender.get(5) + "testuseremail@test.com");
        emptyPasswordParam.put("password", "");
        emptyPasswordParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyPasswordParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyPhoneParam = new JSONObject();
        emptyPhoneParam.put("id", TestHelper.getRandomNumberInt());
        emptyPhoneParam.put("username", randomAppender.get(6) + "_" + "TestUserName");
        emptyPhoneParam.put("firstName",randomAppender.get(6) + "_" + "TestUserFirstName");
        emptyPhoneParam.put("lastName",randomAppender.get(6) + "_" + "TestUserLastName");
        emptyPhoneParam.put("email",randomAppender.get(6) + "testuseremail@test.com");
        emptyPhoneParam.put("password", TestHelper.getRandomNumber() + "TestUserPassword");
        emptyPhoneParam.put("phone","");
        emptyPhoneParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject emptyUserStatusParam = new JSONObject();
        emptyUserStatusParam.put("id", TestHelper.getRandomNumberInt());
        emptyUserStatusParam.put("username",randomAppender.get(7) + "_" + "TestUserName");
        emptyUserStatusParam.put("firstName", randomAppender.get(7)  + "_" + "TestUserFirstName");
        emptyUserStatusParam.put("lastName",randomAppender.get(7)  + "_" + "TestUserLastName");
        emptyUserStatusParam.put("email",randomAppender.get(7)  + "testuseremail@test.com");
        emptyUserStatusParam.put("password", randomAppender.get(7)  + "TestUserPassword");
        emptyUserStatusParam.put("phone",TestHelper.getRandomPhoneNumber());
        emptyUserStatusParam.put("userStatus", "" );

        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(emptyIdParam);
        jsonArrayPayload.put(emptyUserNameParam);
        jsonArrayPayload.put(emptyFirstNameParam);
        jsonArrayPayload.put(emptyLastNameParam);
        jsonArrayPayload.put(emptyEmailParam);
        jsonArrayPayload.put(emptyPasswordParam);
        jsonArrayPayload.put(emptyPhoneParam);
        jsonArrayPayload.put(emptyUserStatusParam);

        data.add(new Object[] {jsonArrayPayload,200, "unknown", "ok"});

        return data.iterator();
    }

    @Test(dataProvider = "getValidEmptyParamData",testName = "verify empty params")
    public void createValidEmptyParamArrayTest(JSONArray userArray, int statusCode, String type, String message) {
        ApiResponse response = new PetStoreTestClass().createUserArray(getHostname(),userArray);
        Assert.assertEquals(response.getHttpStatusCode(), statusCode);
        if (statusCode != 200) {
            response.assertErrorData(type,statusCode,message);
        } else {
            response.assertSuccessData(type);
        }
    }

    @DataProvider
    public Iterator<Object[]> getValidData() {
        List<Object[]> data = Lists.newLinkedList();
        List<String> randomAppender=new ArrayList<String>();
        int i=0;
        while(i < 4){
            randomAppender.add(TestHelper.getRandomNumber());
            i++;
        }

        JSONObject allValidParams_1 = new JSONObject();
        allValidParams_1.put("id", TestHelper.getRandomNumberInt());
        allValidParams_1.put("username",randomAppender.get(0) + "_" + "TestUserName");
        allValidParams_1.put("firstName",randomAppender.get(0) + "_" + "TestUserFirstName");
        allValidParams_1.put("lastName",randomAppender.get(0) + "_" + "TestUserLastName");
        allValidParams_1.put("email",randomAppender.get(0) + "testuseremail@test.com");
        allValidParams_1.put("password", randomAppender.get(0) + "TestUserPassword");
        allValidParams_1.put("phone",TestHelper.getRandomPhoneNumber());
        allValidParams_1.put("userStatus",  TestHelper.getRandomNumberInt() );

        JSONObject allValidParams_2 = new JSONObject();
        allValidParams_2.put("id", TestHelper.getRandomNumberInt());
        allValidParams_2.put("username",randomAppender.get(1) + "_" + "TestUserName");
        allValidParams_2.put("firstName",randomAppender.get(1) + "_" + "TestUserFirstName");
        allValidParams_2.put("lastName",randomAppender.get(1) + "_" + "TestUserLastName");
        allValidParams_2.put("email",randomAppender.get(1) + "testuseremail@test.com");
        allValidParams_2.put("password", randomAppender.get(1) + "TestUserPassword");
        allValidParams_2.put("phone",TestHelper.getRandomPhoneNumber());
        allValidParams_2.put("userStatus",  TestHelper.getRandomNumberInt() );

        JSONObject nullUserStatusParam = new JSONObject();
        nullUserStatusParam.put("id", TestHelper.getRandomNumberInt());
        nullUserStatusParam.put("username", randomAppender.get(2) + "_" + "TestUserName");
        nullUserStatusParam.put("firstName", randomAppender.get(2) + "_" + "TestUserFirstName");
        nullUserStatusParam.put("lastName",randomAppender.get(2) + "_" + "TestUserLastName");
        nullUserStatusParam.put("email",randomAppender.get(2) + "testuseremail@test.com");
        nullUserStatusParam.put("password", randomAppender.get(2) + "TestUserPassword");
        nullUserStatusParam.put("phone",TestHelper.getRandomPhoneNumber());
        nullUserStatusParam.put("userStatus",  JSONObject.NULL);

        JSONObject nullIdParam = new JSONObject();
        nullIdParam.put("id", JSONObject.NULL);
        nullIdParam.put("username", randomAppender.get(3) + "_" + "TestUserName");
        nullIdParam.put("firstName", randomAppender.get(3) + "_" + "TestUserFirstName");
        nullIdParam.put("lastName",randomAppender.get(3) + "_" + "TestUserLastName");
        nullIdParam.put("email",randomAppender.get(3) + "testuseremail@test.com");
        nullIdParam.put("password", randomAppender.get(3) + "TestUserPassword");
        nullIdParam.put("phone",TestHelper.getRandomPhoneNumber());
        nullIdParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(allValidParams_1);
        jsonArrayPayload.put(allValidParams_2);
        jsonArrayPayload.put(nullUserStatusParam);
        jsonArrayPayload.put(nullIdParam);

        data.add(new Object[] {jsonArrayPayload, 200, "unknown", "ok"});
        return data.iterator();
    }

    @Test(dataProvider = "getValidData" , testName = "create new users")
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

    @DataProvider
    public Iterator<Object[]> getInvalidData() {
        List<Object[]> data = Lists.newLinkedList();
        List<String> randomAppender=new ArrayList<String>();
        int i=0;
        while(i < 2){
            randomAppender.add(TestHelper.getRandomNumber());
            i++;
        }

        JSONObject invalidIdParam = new JSONObject();
        invalidIdParam.put("id", "invalidId");
        invalidIdParam.put("username", randomAppender.get(0) + "_" + "TestUserName");
        invalidIdParam.put("firstName", randomAppender.get(0) + "_" + "TestUserFirstName");
        invalidIdParam.put("lastName",randomAppender.get(0) + "_" + "TestUserLastName");
        invalidIdParam.put("email",randomAppender.get(0) + "testuseremail@test.com");
        invalidIdParam.put("password", randomAppender.get(0) + "TestUserPassword");
        invalidIdParam.put("phone",TestHelper.getRandomPhoneNumber());
        invalidIdParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject invalidUserStatusParam = new JSONObject();
        invalidUserStatusParam.put("id", TestHelper.getRandomNumberInt());
        invalidUserStatusParam.put("username", randomAppender.get(1) + "_" + "TestUserName");
        invalidUserStatusParam.put("firstName", randomAppender.get(1) + "_" + "TestUserFirstName");
        invalidUserStatusParam.put("lastName",randomAppender.get(1) + "_" + "TestUserLastName");
        invalidUserStatusParam.put("email",randomAppender.get(1) + "testuseremail@test.com");
        invalidUserStatusParam.put("password", randomAppender.get(1) + "TestUserPassword");
        invalidUserStatusParam.put("phone",TestHelper.getRandomPhoneNumber());
        invalidUserStatusParam.put("userStatus", "invalidstatus");

        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(invalidIdParam);
        jsonArrayPayload.put(invalidUserStatusParam);

        data.add(new Object[] {jsonArrayPayload, 500,  "unknown", "something bad happened"});
        return data.iterator();
    }

    @Test(dataProvider = "getInvalidData",testName = "verify invalid user params")
    public void createInvalidUserArrayTest(JSONArray userArray, int statusCode, String type, String message) {
        ApiResponse response = new PetStoreTestClass().createUserArray(getHostname(),userArray);
        Assert.assertEquals(response.getHttpStatusCode(), statusCode);
        if (statusCode != 200) {
            response.assertErrorData(type,statusCode,message);
        } else {
            response.assertSuccessData(type);
        }
    }

    @DataProvider
    public Iterator<Object[]> getValidMissingData() {
        List<Object[]> data = Lists.newLinkedList();
        List<String> randomAppender=new ArrayList<String>();
        int i=0;
        while(i < 7){
            randomAppender.add(TestHelper.getRandomNumber());
            i++;
        }
        JSONObject missingFirstNameParam = new JSONObject();
        missingFirstNameParam.put("id", TestHelper.getRandomNumberInt());
        missingFirstNameParam.put("username", randomAppender.get(0)+ "_" + "TestUserName");
        missingFirstNameParam.put("lastName",randomAppender.get(0) + "_" + "TestUserLastName");
        missingFirstNameParam.put("email",randomAppender.get(0) + "testuseremail@test.com");
        missingFirstNameParam.put("password", randomAppender.get(0) + "TestUserPassword");
        missingFirstNameParam.put("phone",TestHelper.getRandomPhoneNumber());
        missingFirstNameParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingIdParam = new JSONObject();
        missingIdParam.put("username", randomAppender.get(1) + "_" + "TestUserName");
        missingIdParam.put("firstName", randomAppender.get(1) + "_" + "TestUserFirstName");
        missingIdParam.put("lastName",randomAppender.get(1) + "_" + "TestUserLastName");
        missingIdParam.put("email",randomAppender.get(1) + "testuseremail@test.com");
        missingIdParam.put("password", randomAppender.get(1) + "TestUserPassword");
        missingIdParam.put("phone",TestHelper.getRandomPhoneNumber());
        missingIdParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingLastNameParam = new JSONObject();
        missingLastNameParam.put("id", TestHelper.getRandomNumberInt());
        missingLastNameParam.put("username",randomAppender.get(2) + "_" + "TestUserName");
        missingLastNameParam.put("firstName", randomAppender.get(2) + "_" + "TestUserFirstName");
        missingLastNameParam.put("email",randomAppender.get(2)+ "testuseremail@test.com");
        missingLastNameParam.put("password", randomAppender.get(2) + "TestUserPassword");
        missingLastNameParam.put("phone",TestHelper.getRandomPhoneNumber());
        missingLastNameParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingEmailParam = new JSONObject();
        missingEmailParam.put("id", TestHelper.getRandomNumberInt());
        missingEmailParam.put("username", randomAppender.get(3) + "_" + "TestUserName");
        missingEmailParam.put("firstName", randomAppender.get(3) + "_" + "TestUserFirstName");
        missingEmailParam.put("lastName",randomAppender.get(3) + "_" + "TestUserLastName");
        missingEmailParam.put("password", randomAppender.get(3) + "TestUserPassword");
        missingEmailParam.put("phone",TestHelper.getRandomPhoneNumber());
        missingEmailParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingPasswordParam = new JSONObject();
        missingPasswordParam.put("id", TestHelper.getRandomNumberInt());
        missingPasswordParam.put("username", randomAppender.get(4) + "_" + "TestUserName");
        missingPasswordParam.put("firstName", randomAppender.get(4) + "_" + "TestUserFirstName");
        missingPasswordParam.put("lastName",randomAppender.get(4) + "_" + "TestUserLastName");
        missingPasswordParam.put("email",randomAppender.get(4) + "testuseremail@test.com");
        missingPasswordParam.put("phone",TestHelper.getRandomPhoneNumber());
        missingPasswordParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingPhoneParam = new JSONObject();
        missingPhoneParam.put("id", TestHelper.getRandomNumberInt());
        missingPhoneParam.put("username", randomAppender.get(5) + "_" + "TestUserName");
        missingPhoneParam.put("firstName",randomAppender.get(5) + "_" + "TestUserFirstName");
        missingPhoneParam.put("lastName",randomAppender.get(5) + "_" + "TestUserLastName");
        missingPhoneParam.put("email",randomAppender.get(5) + "testuseremail@test.com");
        missingPhoneParam.put("password", TestHelper.getRandomNumber() + "TestUserPassword");
        missingPhoneParam.put("userStatus", TestHelper.getRandomNumberInt());

        JSONObject missingUserStatusParam = new JSONObject();
        missingUserStatusParam.put("id", TestHelper.getRandomNumberInt());
        missingUserStatusParam.put("username",randomAppender.get(6)  + "_" + "TestUserName");
        missingUserStatusParam.put("firstName", randomAppender.get(6) + "_" + "TestUserFirstName");
        missingUserStatusParam.put("lastName",randomAppender.get(6)+ "_" + "TestUserLastName");
        missingUserStatusParam.put("email",randomAppender.get(6) + "testuseremail@test.com");
        missingUserStatusParam.put("password", randomAppender.get(6) + "TestUserPassword");
        missingUserStatusParam.put("phone",TestHelper.getRandomPhoneNumber());

        JSONArray jsonArrayPayload = new JSONArray();
        jsonArrayPayload.put(missingFirstNameParam);
        jsonArrayPayload.put(missingLastNameParam);
        jsonArrayPayload.put(missingEmailParam);
        jsonArrayPayload.put(missingPasswordParam);
        jsonArrayPayload.put(missingIdParam);
        jsonArrayPayload.put(missingPhoneParam);
        jsonArrayPayload.put(missingUserStatusParam);

        data.add(new Object[] {jsonArrayPayload, 200, "unknown", "ok"});
        return data.iterator();
    }

    @Test(dataProvider = "getValidMissingData" ,testName = "verify missing params")
    public void createValidMissingParamUserArrayTest(JSONArray userArray, int statusCode, String type, String message) {
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

}