import Utils.TestDataHelper;
import io.restassured.http.ContentType;
import org.example.AuthenticationFilter;
import org.example.NewUser;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.TOKEN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NegativeTest extends BaseHomeWorkTest {

    @Test
    public void patchEmptyNameUsers() {
        int userId = getId("endPointUsers", "id");

        String nameCheckedField = "name";
        String valueCheckedField = " ";

        try {
            given()
                    .pathParams("id", userId)
                    .filter(new AuthenticationFilter(TOKEN))
                    .body("{ \"" + nameCheckedField + "\": \"" + valueCheckedField + "\" }")
                    .contentType(ContentType.JSON)
                    .when()
                    .patch(getConfig("objectPathV2")
                            + getConfig("objectIdPath"))
                    .then()
                    .statusCode(422)
                    .contentType(ContentType.JSON)
                    .log().ifValidationFails()
                    .body("[0].field", equalTo("name"))
                    .body("[0].message", equalTo("can't be blank"));

        } catch (Exception e) {
            assertEquals(e.getClass(), com.fasterxml.jackson.databind.exc.MismatchedInputException.class);
            assertEquals(e.getMessage(), "Cannot deserialize value of type `org.example.NewUser` from Array value (token `JsonToken.START_ARRAY`)");
        }
    }

    @Test
    public void patchNegativeEmailUsers() {
        int userId = getId("endPointUsers", "id");

        String nameCheckedField = "email";
        String valueCheckedField = "boris.gmail.com";

        given()
                .pathParams("id", userId)
                .filter(new AuthenticationFilter(TOKEN))
                .body("{ \"" + nameCheckedField + "\": \"" + valueCheckedField + "\" }")
                .contentType(ContentType.JSON)
                .when()
                .patch(getConfig("objectPathV2")
                        + getConfig("objectIdPath"))
                .then()
                .log().all()
                .statusCode(422)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body("[0].field", equalTo("email"))
                .body("[0].message", equalTo("is invalid"));
    }


    @Test
    public void newUserCreationWithoutToken() {
        NewUser newUser = TestDataHelper.createUser();

        given()
                .contentType(ContentType.JSON)
                .body(newUser)
                .when()
                .post(getConfig("objectPathV2")
                        + getConfig("endPointUsers"))
                .then()
                .assertThat()
                .statusCode(401)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body("message", equalTo("Authentication failed"));
    }
}