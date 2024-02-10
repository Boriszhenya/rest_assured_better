import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GivenExampleTest extends BookerBaseTest {
    @Test
    public void pathParamTest() {
        given()
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void queryParamTest() {
        given()
                .queryParams("firstname", "John", "lastname", "Smith")
                .when().log().all()
                .get("/booking")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void headerParamTest() {
        given()
                .pathParam("id", 1)
                .header("Authorization", "Bearer abc123")
                .when().log().all()
                .get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void cookieParamTest() {
        given()
                .pathParam("id", 1)
                .cookies("token", "abc123")
                .when().log().all()
                .get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }
}
