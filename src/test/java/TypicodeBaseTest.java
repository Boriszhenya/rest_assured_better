import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class TypicodeBaseTest extends BaseTest {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }
}
