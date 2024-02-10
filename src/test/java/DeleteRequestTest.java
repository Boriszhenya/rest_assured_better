import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteRequestTest extends TypicodeBaseTest {
    @Test
    public void deleteTest() {
        given().pathParam("id", 1)
                .when()
                .delete("/posts/{id}")
                .then()
                .log().all()
                .statusCode(200);
    }
}
