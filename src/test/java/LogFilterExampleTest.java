import io.restassured.http.ContentType;
import org.example.LoggingFilter;
import org.example.LoggingFilterThree;
import org.example.LoggingFilterTwo;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LogFilterExampleTest extends BookerBaseTest {
    @Test
    public void logTest() {
        given()
                //.filter(new LoggingFilter())
                .filter(new LoggingFilterTwo())
                .filter(new LoggingFilterThree())
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }
}
