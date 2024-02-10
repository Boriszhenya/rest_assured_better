import io.restassured.http.ContentType;
import org.example.ModifyRequestResponseFilter;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ModifyFilterExampleTest extends BookerBaseTest {
    @Test
    public void modifyTest() {
        given()
                .filter(new ModifyRequestResponseFilter("Abra-cadabra", "{ \"prefix\" : \"BODY STARTS\" }, "))
                .pathParam("id", 1).log().all()
                .when().get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusLine("HTTP/1.1 200 OK")
                .body("prefix", equalTo("BODY STARTS"));
    }
}
