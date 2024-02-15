package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiWrapper {
    private final static int DEFAULT_STATUS_CODE = 200;
    private final static int DEFAULT_STATUS_CODE_POST = 201;


    public static <T> T sendPostRequest(RequestSpecification requestSpecification,String endpoint, T requestBody, Class<T> responseType, String token) {
        return given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .header("Authorization", "Bearer "+ token)
                .when()
               .log().all()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(DEFAULT_STATUS_CODE_POST)
                .contentType(ContentType.JSON)
               .log().all()
                .log().ifValidationFails()
                .extract().as(responseType);
    }

    public static <T> T sendPostRequest(String endpoint, T requestBody, Class<T> responseType, String token) {

        return sendPostRequest(given(), endpoint, requestBody, responseType, token);
    }


    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpecification, String callPath, int statusCode){
        return given()
                .spec(requestSpecification)
                .when()
                .get(callPath)
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                .log().ifValidationFails();
    }

    public static ValidatableResponse sendGetRequest(String callPath, int statusCode){
        return sendGetRequest(given(), callPath, statusCode);
    }

    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpecification, String callPath){
        return sendGetRequest(requestSpecification, callPath, DEFAULT_STATUS_CODE);
    }

    public static ValidatableResponse sendGetRequest(String callPath){
        return sendGetRequest(given(), callPath, DEFAULT_STATUS_CODE);
    }
}
