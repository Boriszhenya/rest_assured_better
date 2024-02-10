import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ThenExampleTest extends BookerBaseTest {
    @Test
    public void statusTest() {
        given()
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test
    public void headerTest() {
        given()
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .header("Server", "Cowboy")
                .headers("X-Powered-By", "Express", "Via", "1.1 vegur")
                .headers(Map.of("X-Powered-By", "Express", "Via", "1.1 vegur"));
    }

    @Test
    public void logIfFailsTest() {
        given()
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .header("Server", "Cowboy2").log().ifValidationFails();
    }

    @Test
    public void timeTest() {
        given()
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then().log().all()
                .time(lessThan(1500L))
                .assertThat()
                .contentType(ContentType.JSON);
    }
}
