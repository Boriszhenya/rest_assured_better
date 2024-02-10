import io.restassured.http.ContentType;
import org.example.LoggingFilter;
import org.example.PerformanceMonitoringFilter;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PerfFilterExampleTest extends BookerBaseTest {
    @Test
    public void perfTest() {
        given()
                .filter(new LoggingFilter())
                .filter(new PerformanceMonitoringFilter())
                .pathParam("id", 1)
                .when().get("/booking/{id}")
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }
}
