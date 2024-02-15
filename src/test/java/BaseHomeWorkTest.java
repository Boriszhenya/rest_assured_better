import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseHomeWorkTest {
    protected static Properties properties;

    @BeforeAll
    public static void globalSetUp() {
        properties = new Properties();
        //RestAssured.filters(new AllureRestAssured());
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config_homework.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }
}
