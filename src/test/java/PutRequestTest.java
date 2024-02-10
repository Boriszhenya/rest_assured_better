import io.restassured.http.ContentType;
import org.example.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutRequestTest extends TypicodeBaseTest {
    @Test
    public void putRequestTest() {
        String postId = "1";
        Post newPost = new Post();
        newPost.setUserId(1);
        newPost.setTitle("New title");
        newPost.setBody("New body");

        Post actualResponse = given()
                .pathParam("id", postId)
                .body(newPost)
                .contentType(ContentType.JSON)
                .when()
                .put("/posts/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all()
                .extract().as(Post.class);

        assertEquals(newPost, actualResponse);
    }
}
