import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PlainPostRequestsTest extends ReqresBaseTest {
    @Test
    public void plainPostRequestTest() {
        given().contentType(ContentType.JSON).log().all()
                .when()
                .post("api/logout")
                .then().log().all().assertThat()
                .statusCode(200);
    }

    @Test
    public void simplePostRequestTest() {
        String requestBody = """
                {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
                }""";
        given().contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("api/register")
                .then().assertThat()
                .statusCode(200)
                .and().body("id", equalTo(4))
                .body("token", containsString("QpwL5tke4Pnpja7X4"));
    }
}
