import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;

public class BasicAuthExampleTest extends BaseTest {
    @Test
    public void basicAuthTest() {
        RestAssured.baseURI = "https://authenticationtest.com/HTTPAuth/";
        given()
                .auth().basic("user", "pass")
                .when().get()
                .then().log().all()
                .statusCode(200)
                .body(containsString("Success!"), containsString("You are now logged in!"));
    }
}
