import Utils.ApiWrapper;
import Utils.TestDataHelper;
import org.example.Comments;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentsTest extends BaseHomeWorkTest {




    @Test
    public void schemeCommentsValidationTest() {
        sendGetRequest(
                getConfig("objectPathV2")
                        + getConfig("objectCommentsPostPath")
        )
                .assertThat()
                .body(matchesJsonSchemaInClasspath("comments-schema.json"));
    }

    @Test
    public void getListParamObjectsComments() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(
                given().pathParams("page",
                        page, "perPage", perPage),
                getConfig("objectPathV2")
                        + getConfig("objectCommentsPostPath")
                        + "?page={page}&per_page={perPage}"
        )
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }

    @Test
    public void newCommentPostsCreation() {
        int postId = getId("objectCommentsPostPath", "post_id");

        Comments newPCommentsPost = TestDataHelper.createComments(postId);

        Comments actualComments =
                ApiWrapper.sendPostRequest(
                        given().pathParams("id", postId),
                        getConfig("objectPathV2")
                                + getConfig("objectPostIdPath")
                                + getConfig("objectCommentsPostPath"),
                        newPCommentsPost,
                        Comments.class
                );
        assertEquals(actualComments, newPCommentsPost);
    }

    @Test
    public void deleteComment() {

        int postId = getId("objectCommentsPostPath", "id");

        deleteRequest(given().pathParams("id", postId),
                getConfig("objectPathV2")
                        + getConfig("objectCommentsIdPath")
        );
    }

    @Test
    public void patchNameComment() {
        int postId = getId("objectCommentsPostPath", "id");

        String nameCheckedField = "name";
        String valueCheckedField = "BORISZ";

        sendPatchRequest(
                given().pathParams("id", postId),
                nameCheckedField,
                valueCheckedField,
                getConfig("objectPathV2")
                        + getConfig("objectCommentsIdPath")
        );
    }

    @Test
    public void putNameComment() {

        int id = getId("objectCommentsPostPath", "id");
        int postId = getId("objectCommentsPostPath", "post_id");

        Comments comments = TestDataHelper.createComments(id);
        comments.setName("BORISZ");
        comments.setPostID(postId);

        Comments actualComments =
                ApiWrapper.sendPutRequest(
                        given().pathParams("id", id),
                        getConfig("objectPathV2")
                                + getConfig("objectCommentsIdPath"),
                        comments,
                        Comments.class
                );
        assertEquals(actualComments, comments);
    }
}
