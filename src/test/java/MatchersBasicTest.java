import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MatchersBasicTest extends ReqresBaseTest {
    @Test
    public void matchersTest() {
        String userId = "1";
        given()
                .pathParam("userId", userId)
                .when()
                .get("/api/users/{userId}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.log().all()
                .body("data.id", equalTo(1))
                .body("support.text", containsString("server costs"))
                .body("data.id", greaterThan(0))
                .body("data.id", lessThan(2))
                .body("data.email", matchesRegex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"));
    }
}
