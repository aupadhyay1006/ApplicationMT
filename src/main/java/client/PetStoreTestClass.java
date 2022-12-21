package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import request.Pet;
import response.ApiResponse;
import util.PetStoreProperties;
import util.TestHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

    @Slf4j
    public class PetStoreTestClass {

        public ApiResponse createUserArray(String uri, JSONArray jsonArrayPayLoad) {
            log.info("createUserArray URI: {}{}", uri, PetStoreProperties.PETSTORE_USER_CREATE_ARRAY_BASE_PATH);
            System.out.println("createUserArray URI: {}{}"+  uri + jsonArrayPayLoad);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .contentType(ContentType.JSON)
                    .header("accept","application/json")
                    .body(jsonArrayPayLoad.toString())
                    .when()
                    .post(PetStoreProperties.PETSTORE_USER_CREATE_ARRAY_BASE_PATH);

            log.info("createUserArray response: {}", response.asString());

            ApiResponse apiResponse = null;
            try {
                apiResponse = (ApiResponse) TestHelper.getResponseObject(response.asString(), ApiResponse.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            apiResponse.setHttpStatusCode(response.statusCode());
            return apiResponse;
        }

        public Response loginUser(String uri,Map<String, Object> queryParams ){
            log.info("Login User URI: {}", uri);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .header("accept", "application/json")
                    .queryParams(queryParams)
                    .when()
                    .get(PetStoreProperties.PETSTORE_USER_LOGIN_BASE_PATH);

            log.info("Login User response: {}", response.asString());

            return response;

        }

        public ApiResponse createANewUser(String uri,String body ){
            log.info("Create new user URI: {}", uri);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .header("accept", "application/json")
                    .body(body)
                    .when()
                    .post(PetStoreProperties.PETSTORE_USER_BASE_PATH);

            log.info("Create new user response: {}", response.asString());

            ApiResponse apiResponse = null;
            try {
                apiResponse = (ApiResponse) TestHelper.getResponseObject(response.asString(), ApiResponse.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            apiResponse.setHttpStatusCode(response.statusCode());
            return apiResponse;

        }

        public Response getUserDetails (String uri, String username){
            log.info("getUserDetails URI: {}{}", uri , username);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .header("accept", "application/json")
                    .when()
                    .get(PetStoreProperties.PETSTORE_USER_BASE_PATH+"/{username}",username);

            log.info("getUserDetails response: {}", response.asString());

            return response;
        }

        public ApiResponse updateUser(String uri, String username,String body) {
            log.info("updateUser URI: {}{}", uri, username);

            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .contentType(ContentType.JSON)
                    .header("accept","application/json")
                    .body(body)
                    .when()
                    .put(PetStoreProperties.PETSTORE_USER_BASE_PATH+"/{username}",username);

            log.info("updateUser response: {}", response.asString());

            ApiResponse apiResponse = null;
            try {
                apiResponse = (ApiResponse) TestHelper.getResponseObject(response.asString(), ApiResponse.class);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            apiResponse.setHttpStatusCode(response.statusCode());
            return apiResponse;
        }

        public Response createPet (String uri, String body){
            log.info("createPet URI: {}", uri  , body);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .contentType(ContentType.JSON)
                    .header("accept", "application/json")
                    .when()
                    .body(body)
                    .post(PetStoreProperties.PETSTORE_USER_CREATE_PET_BASE_PATH);

            log.info("createPet response: {}", response.asString());

            return response;
        }

        public Response updatePet (String uri, String body){
            log.info("updatePet URI: {}", uri  , body);
            Response response = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .contentType(ContentType.JSON)
                    .header("accept", "application/json")
                    .when()
                    .body(body)
                    .put(PetStoreProperties.PETSTORE_USER_CREATE_PET_BASE_PATH);

            log.info("updatePet response: {}", response.asString());

            return response;
        }

        public List<Pet> getPetDetails (String uri, String status){
            log.info("getPetDetails URI: {}{}", uri , status);
            List<Pet> petResponse = given()
                    .baseUri(uri)
                    .basePath(PetStoreProperties.PETSTORE_BASE_PATH)
                    .header("accept", "application/json")
                    .queryParam("status",status)
                    .when()
                    .get(PetStoreProperties.PETSTORE_USER_PET_STATUS_BASE_PATH)
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath().getList(".", Pet.class);;

            log.info("getPetDetails response: {}", petResponse.toString());

            return petResponse;
        }

}
