import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class BookerBaseTest extends BaseTest {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
    }
}
