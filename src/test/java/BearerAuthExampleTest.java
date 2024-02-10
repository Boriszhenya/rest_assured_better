import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class BearerAuthExampleTest extends BaseTest{
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "https://authenticationtest.com/";
    }

    @Test
    public void withoutBearerTest(){
        given()
                .when().get()
                .then().statusCode(200)
                .body(containsString("Token Not Set"));
    }

    @Test
    public void withBearerTest(){
        given()
                .header("Authorization", "Bearer t0k3nId")
                .when().get()
                .then().statusCode(200)
                .body(containsString("Token Set"));
    }
}
