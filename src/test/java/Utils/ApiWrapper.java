package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.AuthenticationFilter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiWrapper {
    private final static int DEFAULT_STATUS_CODE_GET = 200;
    private final static int DEFAULT_STATUS_CODE_PATCH = 200;
    private final static int DEFAULT_STATUS_CODE_POST = 201;
    private final static int DEFAULT_STATUS_CODE_PUT = 200;
    private final static int DEFAULT_STATUS_CODE_DELETE = 204;

    private final static String TOKEN = "e2d891897138eafe9cddfe43eeebf508ae3b2ddfbb8920fdc3088e1a757d7368";


    public static <T> T sendPostRequest(RequestSpecification requestSpecification,
                                        String endpoint,
                                        T requestBody,
                                        Class<T> responseType) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                //.log().all()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(DEFAULT_STATUS_CODE_POST)
                .contentType(ContentType.JSON)
                //.log().all()
                .log().ifValidationFails()
                .extract().as(responseType);
    }

    public static <T> T sendPostRequest(String endpoint, T requestBody, Class<T> responseType) {

        return sendPostRequest(given(), endpoint, requestBody, responseType);
    }

    public static <T> T sendPutRequest(RequestSpecification requestSpecification,
                                       String endpoint,
                                       T requestBody,
                                       Class<T> responseType) {
        return given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                //.log().all()
                .put(endpoint)
                .then()
                .assertThat()
                .statusCode(DEFAULT_STATUS_CODE_PUT)
                .contentType(ContentType.JSON)
                //.log().all()
                .log().ifValidationFails()
                .extract().as(responseType);
    }


    public static void sendPatchRequest(RequestSpecification requestSpecification,
                                        String nameCheckedField,
                                        String valueCheckedField,
                                        String callPath) {
        given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpecification)
                .body("{ \"" + nameCheckedField + "\": \"" + valueCheckedField + "\" }")
                .contentType(ContentType.JSON)
                //.log().all()
                .when()
                .patch(callPath)
                .then()
                //.log().all()
                .statusCode(DEFAULT_STATUS_CODE_PATCH)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(nameCheckedField, equalTo(valueCheckedField));
    }


    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpecification,
                                                     String callPath,
                                                     int statusCode) {
        return given()
                .spec(requestSpecification)
                .when()
                //.log().all()
                .get(callPath)
                .then()
                .statusCode(statusCode)
                .contentType(ContentType.JSON)
                //.log().all()
                .log().ifValidationFails();
    }

    public static ValidatableResponse sendGetRequest(String callPath, int statusCode) {
        return sendGetRequest(given(), callPath, statusCode);
    }

    public static ValidatableResponse sendGetRequest(RequestSpecification requestSpecification, String callPath) {
        return sendGetRequest(requestSpecification, callPath, DEFAULT_STATUS_CODE_GET);
    }

    public static ValidatableResponse sendGetRequest(String callPath) {
        return sendGetRequest(given(), callPath, DEFAULT_STATUS_CODE_GET);
    }

    public static void deleteRequest(RequestSpecification requestSpecification,
                                     String callPath,
                                     int statusCode) {
        given()
                .filter(new AuthenticationFilter(TOKEN))
                .spec(requestSpecification)
                .when()
                //.log().all()
                .delete(callPath)
                .then()
                .log().ifValidationFails()
                .statusCode(statusCode);
    }

    public static void deleteRequest(RequestSpecification requestSpecification, String callPath) {
        deleteRequest(requestSpecification, callPath, DEFAULT_STATUS_CODE_DELETE);
    }
}
