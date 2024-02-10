import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HeadOptionsExampleTest extends BookerBaseTest {
    @Test
    public void headTest() {
        given()
                .pathParam("id", 1)
                .when().head("/booking/{id}")
                //.when().get("/booking/{id}")
                .then()
                .log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void optionsTest() {
        given()
                .when()
                .options("/booking/1")
                .then().log().all()
                .statusCode(200);
    }
}
