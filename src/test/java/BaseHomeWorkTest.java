import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseHomeWorkTest {
    protected static Properties properties;


    @BeforeAll
    public static void globalSetUp() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config_homework.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = getConfig("baseURIGorest");
        RestAssured.filters(new AllureRestAssured());
    }

    public static int getPostUserId() {
        return
                given()
                        .when()
                        .get(getConfig("objectPathV2") +
                                getConfig("objectPostPath"))
                        .then()
                        //.log().all()
                        .statusCode(200)
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt("[0].user_id");
    }

    public static int getPostId() {
        return
                given()
                        .when()
                        .get(getConfig("objectPathV2") +
                                getConfig("objectCommentsPostPath"))
                        .then()
                        //.log().all()
                        .statusCode(200)
                        // .log().all()
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt("[0].post_id");
    }

    public static int getIdPost() {
        return
                given()
                        .when()
                        .get(getConfig("objectPathV2") +
                                getConfig("objectCommentsPostPath"))
                        .then()
                        //.log().all()
                        .statusCode(200)
                        // .log().all()
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt("[0].id");
    }


    public static int getUserId() {
        return
                given()
                        .when()
                        .get(getConfig("objectPathV2") +
                                getConfig("endPointUsers"))
                        .then()
                        .log().all()
                        .statusCode(200)
                        .log().all()
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt("[0].id");
    }

//    public static int getToDoUserId() {
//        return
//                given()
//                        .when()
//                        .get(getConfig("objectPathV2") +
//                                getConfig("objectToDosPostPath"))
//                        .then()
//                        //.log().all()
//                        .statusCode(200)
//                        // .log().all()
//                        .log().ifValidationFails()
//                        .extract()
//                        .jsonPath()
//                        .getInt("[0].user_id");
//    }

    public static int getId(String endpoint, String nameId) {
        String path = "[0]." + nameId;
        System.out.println("Путь - " + path);
        return
                given()
                        .when()
                        .get(getConfig("objectPathV2") +
                                getConfig(endpoint))
                        .then()
                        .log().all()
                        .statusCode(200)
                        .log().all()
                        .log().ifValidationFails()
                        .extract()
                        .jsonPath()
                        .getInt(path);

    }


//    public static int getToDoId() {
//        return
//                given()
//                        .when()
//                        .get(getConfig("objectPathV2") +
//                                getConfig("objectToDosPostPath"))
//                        .then()
//                        //.log().all()
//                        .statusCode(200)
//                        // .log().all()
//                        .log().ifValidationFails()
//                        .extract()
//                        .jsonPath()
//                        .getInt("[0].id");
//    }


    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

    @AfterEach
    public void tearDown() {
        RestAssured.reset();
    }
}
