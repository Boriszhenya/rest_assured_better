import io.restassured.http.ContentType;
import org.example.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassComparisonExampleTest extends TypicodeBaseTest {
    @Test
    public void compareClassesResponseTest() {
        String postId = "1";
        Post actualResponse = given()
                .pathParam("id", postId)
                .when()
                .get("/posts/{id}")
                .then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(Post.class);

        Post expectedResponse = new Post();
        expectedResponse.setUserId(1);
        expectedResponse.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedResponse.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        assertEquals(expectedResponse, actualResponse);
    }
}
