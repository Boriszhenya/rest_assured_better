import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

public class MatchersDoubleTest {
    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://api.coindesk.com/v1/bpi/currentprice.json";
    }

    @Test
    public void closeToTest() {
        Response response = given().when().get();

        float expected = 42300f;
        float error = 1000f;

        response.then().log().all()
                .assertThat()
                .statusCode(200);
                //.body("bpi.USD.rate_float", closeTo(expected, error));

        double actualValue = response.jsonPath().getFloat("bpi.USD.rate_float");

        assertThat(actualValue, closeTo(expected, error));
    }


    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }
}
