import io.restassured.http.ContentType;
import org.example.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostRequestInputClassExampleTest extends TypicodeBaseTest {
    @Test
    public void entityPostRequestTest() {
        Post postEntity = new Post();
        postEntity.setUserId(1);
        postEntity.setTitle("Title of the post");
        postEntity.setBody("Body of the post with text");

        given()
                .contentType(ContentType.JSON)
                .body(postEntity)
                .log().all()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .log().all();
    }
}
