import Utils.ApiWrapper;
import Utils.TestDataHelper;
import org.example.Post;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.deleteRequest;
import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest extends BaseHomeWorkTest {

    @Test
    public void schemePostValidationTest() {

        sendGetRequest(
                given().pathParams("id", getPostUserId()),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath")
                        + getConfig("objectPostPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    public void getListParamObjectsPosts() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(given().pathParams("page",
                        page, "perPage", perPage),
                (getConfig("objectPathV2")
                        + getConfig("objectPostPath")
                        + "?page={page}&per_page={perPage}"))
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }


    @Test
    public void newPostCreation() {
        int postUserId = getPostUserId();

        Post newPost = TestDataHelper.createPost(postUserId);
        System.out.println(newPost);
        Post actualPost =
                ApiWrapper.sendPostRequest(

                        given().pathParams("id", postUserId),
                        (getConfig("objectPathV2")
                                + getConfig("objectIdPath")
                                + getConfig("objectPostPath")),
                        newPost,
                        Post.class,
                        getConfig("objectToken"));

        assertEquals(actualPost, newPost);
    }

    @Test
    public void deletePost() {

        int postId = getPostId();

        deleteRequest(given().pathParams("id", postId),
                getConfig("objectPathV2")
                        + getConfig("objectPostIdPath"),
                getConfig("objectToken")
        );
    }
}