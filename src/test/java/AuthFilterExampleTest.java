import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import org.example.AuthenticationFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthFilterExampleTest extends BookerBaseTest {
    private static Filter authFilter;
    @BeforeAll
    public static void globalSetUp() {
        String token = given()
                .contentType(ContentType.JSON)
                .body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
                .when().post("https://restful-booker.herokuapp.com/auth")
                .then().assertThat().statusCode(200)
                .extract().path("token");
        authFilter = new AuthenticationFilter(token);
    }
    @Test
    public void globalAuthTest() {
        given()
                .filter(authFilter)
                .pathParam("id", 1).log().all()
                .when().get("/booking/{id}")
                .then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);
    }
}
