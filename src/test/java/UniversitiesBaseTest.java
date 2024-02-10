import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

public class UniversitiesBaseTest extends BaseTest {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "http://universities.hipolabs.com/";
    }
}
