import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class ReqresBaseTest extends BaseTest {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "https://reqres.in/";
    }
}
