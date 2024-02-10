import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationExampleTest extends BookerBaseTest {
        @Test
        public void reqSpecTest() {
            RequestSpecification requestSpecification =
                    given()
                            .pathParam("id", 1)
                            .contentType(ContentType.JSON)
                            .accept(ContentType.ANY)
                            .headers("FirstCustomHeader", "firstValue", "SecondCustomHeader", "secondValue");

            given(requestSpecification).log().all()
                    //.spec(requestSpecification)
                    .when()
                    //.spec(requestSpecification)
                    .get("/booking/{id}")
                    .then().log().all()
                    .assertThat()
                    .contentType(ContentType.JSON)
                    .statusCode(200);
        }
}
