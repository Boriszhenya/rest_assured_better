import Utils.ApiWrapper;
import Utils.TestDataHelper;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.example.NewUser;
import org.example.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewHomeWorkTest extends BaseHomeWorkTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = getConfig("baseURIGorest");
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    public void getListOfObjects() {

        sendGetRequest(getConfig("objectPathV2")
                + getConfig("endPointUsers"))
                .assertThat()
                .body("$", hasSize(10));
    }


    @Test
    public void getListParamObjects() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(given().pathParams("page", page, "perPage", perPage), (getConfig("objectPathV2")
                + getConfig("endPointUsers") + "?page={page}&per_page={perPage}"))
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }

    @Test
    public void schemeUserValidationTest() {
        String objId = "6392611";

        sendGetRequest(
                given().pathParams("id", objId),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }

    @Test
    public void newUserCreation() {

        NewUser newUser = TestDataHelper.createUser();

        NewUser actualClient =
                ApiWrapper.sendPostRequest
                        ((getConfig("objectPathV2") + getConfig("endPointUsers")),
                        newUser,
                        NewUser.class,
                        getConfig("objectToken"));

        assertEquals(actualClient, newUser);
    }

    @Test
    public void schemePostValidationTest() {
        String objId = "6392612";

        sendGetRequest(
                given().pathParams("id", objId),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath")
                        + getConfig("objectPostPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    public void newPostCreation() {
        String objId = "6392612";

        Post newPost = TestDataHelper.createPost(objId);
        System.out.println(newPost);
            Post actualPost =
                ApiWrapper.sendPostRequest(

                        given().pathParams("id", objId),
                        (getConfig("objectPathV2")
                                + getConfig("objectIdPath")
                                + getConfig("objectPostPath")),
                        newPost,
                        Post.class,
                        getConfig("objectToken"));

        assertEquals(actualPost, newPost);
    }


}