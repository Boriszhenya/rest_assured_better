import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MatchersArraysTest extends UniversitiesBaseTest {
    @Test
    public void arraysMatchersTest() {
        String universityName = "Middlesbrough";
        given()
                .pathParam("name", universityName)
                .when()
                .get("/search?name={name}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                //.log().all()
                .body("[0].web_pages", hasItem("https://www.mbro.ac.uk/"))
                .body("[0].domains", hasItems("middlesbro.ac.uk", "mbro.ac.uk"))
                .body("[0].domains", hasSize(2));
    }
}
