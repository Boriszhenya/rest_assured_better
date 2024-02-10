import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemeValidationTest extends TypicodeBaseTest {
    @Test
    public void postSchemeValidationTest() {
        String postId = "1";
        given()
                .pathParam("id", postId)
                .when()
                .get("/posts/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("post-scheme.json"));
    }
}
