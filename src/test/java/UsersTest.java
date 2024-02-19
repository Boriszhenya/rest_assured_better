import Utils.ApiWrapper;
import Utils.TestDataHelper;
import org.example.NewUser;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.deleteRequest;
import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest extends BaseHomeWorkTest {

    @Test
    public void getListOfObjects() {
        sendGetRequest(
                getConfig("objectPathV2")
                        + getConfig("endPointUsers"))
                .assertThat()
                .body("$", hasSize(10)
                );
    }

    @Test
    public void getListParamObjectsUsers() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(
                given().pathParams("page", page,
                        "perPage", perPage),
                (getConfig("objectPathV2")
                        + getConfig("endPointUsers") + "?page={page}&per_page={perPage}")
        )
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }


    @Test
    public void schemeUserValidationTest() {
        int userId = getId("endPointUsers", "id");
        sendGetRequest(
                given().pathParams("id", userId),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath")
        )
                .assertThat()
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    public void newUserCreation() {

        NewUser newUser = TestDataHelper.createUser();

        NewUser actualClient =
                ApiWrapper.sendPostRequest(
                        (getConfig("objectPathV2")
                                + getConfig("endPointUsers")),
                        newUser,
                        NewUser.class,
                        getConfig("objectToken")
                );

        assertEquals(actualClient, newUser);
    }

    @Test
    public void deleteUser() {

        int userId = getId("endPointUsers", "id");

        deleteRequest(
                given().pathParams("id", userId),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath"),
                getConfig("objectToken")
        );
    }
}


