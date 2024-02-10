import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class PostRequestFileUploadTest {
    @Test
    public void fileUploadTest() throws IOException {
        RestAssured.baseURI = "https://httpbin.org";

        File fileToUpload = new File("src/main/resources/The_face.jpg");
        InputStream is = new FileInputStream(fileToUpload);
        byte[] bytes = is.readAllBytes();

        given()
                //.multiPart("file", fileToUpload) // "file" это имя поля в форме для файла
                //.multiPart("file", is) // Отправка как InputStream
                .multiPart("file", bytes) // Отправка как byteArray
                .log().all()
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().all();
    }
}
