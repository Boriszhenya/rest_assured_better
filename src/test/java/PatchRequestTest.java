import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.example.PatchPost;
import org.example.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchRequestTest extends TypicodeBaseTest {
    @Test
    public void patchRequestTest() { // Это как вам может хотеться это сделать
        String postId = "1";
        Post newPost = new Post();
        newPost.setTitle("New title");

        Post actualResponse = given()
                .pathParam("id", postId)
                .body(newPost)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .patch("/posts/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .extract().as(Post.class);

        newPost.setUserId(1);
        newPost.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        assertEquals(newPost, actualResponse);
    }

    @Test
    public void patchProperRequestTest() { // А делать придётся к сожалению так
        String postId = "1";
        String newTitle = "New title";

        //String partialPayload = String.format("{ \"title\": \"%s\" }", newTitle);
        String partialPayload = "{ \"title\": \"" + newTitle + "\" }";
        //String partialPayload =
        //        new JsonPath(String.format("{ \"title\": \"%s\" }", newTitle)).prettyPrint();

        given()
                .pathParam("id", postId)
                .body(partialPayload)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .patch("/posts/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .body("title", equalTo(newTitle));
    }

    @Test
    public void patchProperRequestMinorClassTest() { // А делать придётся к сожалению так
        String postId = "1";
        String newTitle = "New title";

        PatchPost patchPost = new PatchPost(newTitle);

        given()
                .pathParam("id", postId)
                .body(patchPost)
                .contentType(ContentType.JSON)
                .when()
                .patch("/posts/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .assertThat()
                .body("title", equalTo(newTitle));
    }
}
