import Utils.ApiWrapper;
import Utils.TestDataHelper;
import org.example.Comments;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.deleteRequest;
import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentsTest extends BaseHomeWorkTest {


    @Test
    public void schemeCommentsValidationTest() {
        sendGetRequest(
                getConfig("objectPathV2")
                        + getConfig("objectCommentsPostPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("comments-schema.json"));
    }

    @Test
    public void getListParamObjectsComments() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(given().pathParams("page",
                        page, "perPage", perPage),
                (getConfig("objectPathV2")
                        + getConfig("objectCommentsPostPath")
                        + "?page={page}&per_page={perPage}"))
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }


    @Test
    public void newCommentPostsCreation() {
        int postId = getPostId();

        Comments newPCommentsPost = TestDataHelper.createComments(postId);
        System.out.println(newPCommentsPost);
        Comments actualComments =
                ApiWrapper.sendPostRequest(

                        given().pathParams("id", postId),
                        (getConfig("objectPathV2")
                                + getConfig("objectPostIdPath")
                                + getConfig("objectCommentsPostPath")),
                        newPCommentsPost,
                        Comments.class,
                        getConfig("objectToken"));

        assertEquals(actualComments, newPCommentsPost);
    }

    @Test
    public void deleteComment() {

        int postId = getIdPost();

        deleteRequest(given().pathParams("id", postId),
                getConfig("objectPathV2")
                        + getConfig("objectCommentsIdPath"),
                getConfig("objectToken")
        );
    }

}
