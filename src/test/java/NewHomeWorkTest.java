import Utils.ApiWrapper;
import Utils.TestDataHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.example.ColorEntity;
import org.example.Data;
import org.example.GadgetPost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class NewHomeWorkTest extends BaseHomeWorkTest {
    static String token = "e2d891897138eafe9cddfe43eeebf508ae3b2ddfbb8920fdc3088e1a757d7368";

    @BeforeEach
    public void setUp() {
         RestAssured.baseURI = getConfig("baseURI");

    }

    // Ваша задача написать по одному тесту на каждый вызов:
    // 1. Для вызова GET LIST OF ALL OBJECTS проверьте,
    // что количество объектов в ответном массиве равно 13
    @Test
    public void getListOfAllObjects() {
        sendGetRequest(getConfig("objectPath"))
                .assertThat()
                .body("$", hasSize(6));
        System.out.println(token);
    }

    // 2. Для вызова GET LIST OF OBJECTS BY IDS проверьте,
    // что при вызове с параметрами id 10 и 12 (одновременно),
    // у обоих значение Capacity = 64 GB
    @Test
    public void getListByIds() {
        String id1 = "10";
        String id2 = "12";
        List<Objects> list =
                sendGetRequest(
                        given().queryParams("id",id1, "id", id2),
                        getConfig("objectPath")
                )
                .extract().jsonPath().getList("data.Capacity");

        assertThat(list, everyItem(equalTo("64 GB")));
    }

    // 3. Для вызова GET SINGLE OBJECT с любым id сделайте "контрактный" тест
    @Test
    public void validateListOfAllObjects() {
        sendGetRequest(getConfig("objectPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("gadgets-scheme.json"));
    }

    @Test
    public void springAppUpdateAvailability() {
        given()
                .pathParam("id", "4")
                // /product-availability/4
                .queryParam("amount", 5)
                // /product-availability/4?amount=5
                .when()
                .post("/product-availability/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .assertThat()
                .body(emptyOrNullString());
    }

    @Test
    public void validateOneObject() {
        String objId = "13";
        sendGetRequest(
                given().pathParam("id", objId),
                getConfig("objectIdPath")
        )
                .assertThat()
                .body(matchesJsonSchemaInClasspath("gadget-scheme.json"));
    }

    // 4. Для вызова POST ADD OBJECT сделайте добавление новой сущности
    // с использованием класса сущности (можете использовать пример вызова с сайта)
    // и проверьте, что переданные данные присутствуют в ответе (также используя класс)

    @Test
    public void entityPostRequestTest() {
        GadgetPost gadgetPost = new GadgetPost();
        Data data = new Data();
        data.setYear(2019);
        data.setPrice(1849.99);
        data.setCpuModel("Intel Core i9");
        data.setHardDiskSize("1 TB");
        gadgetPost.setName("Apple MacBook Pro 16");
        gadgetPost.setData(data);

        GadgetPost actualResponse = given()
                .contentType(ContentType.JSON)
                .body(gadgetPost)
                .when()
                .post("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().as(GadgetPost.class);

        assertEquals(actualResponse, gadgetPost);
    }

    @Test
    public void entityPostRequestImprovedTest() {
        GadgetPost gadgetPost = TestDataHelper.createSampleGadgetPost();
        // или
        gadgetPost.setName("BLA-BLA");
        // или
        //GadgetPost gadgetPost = TestDataHelper.createSampleGadgetPost("Bla-bla-bla");

        GadgetPost actualResponse =
                ApiWrapper.sendPostRequest(getConfig("objectPath"), gadgetPost);

        assertEquals(actualResponse, gadgetPost);
    }

    // 5. Для вызова PUT UPDATE OBJECT сделайте класс телефона,
    // обновите сущность с id 1 или 3 и проверьте ответ

    // 6. Для вызова PATCH PARTIALLY UPDATE OBJECT проверьте
    // обновление любого поля любого понравившегося вам объекта

    // 7. Для вызова DELETE DELETE OBJECT проверьте статус ответа,
    // сообщение и что в сообщении содержится переданный id объекта

    // Andrei Idt example
    @Test
    public void getElementsWithProperty() {
        List<Map<String, Object>> list = given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().jsonPath().getList("findAll { it.data.color != null }");

        System.out.println("DEBUG");

        assertTrue(list.stream().allMatch(item ->
                item.containsKey("data") &&
                        ((Map<String, Object>) item.get("data")).containsKey("price") &&
                        (Float) ((Map<String, Object>) item.get("data")).get("price") > 350f
        ));
    }

    @Test
    public void getElementsValuesWithProperty() {
        List<Float> list = given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().jsonPath().getList("findAll { it.data.color != null }.data.price");

        assertThat(list, everyItem(greaterThan(350f)));
    }

    @Test
    public void getElementsWithPropertySatisfyRequirements() {
        given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body(
                        "findAll " +
                        "{ it.data.color != null && it.data.price != null }.every " +
                        "{ it.data.price > 350 }",
                        is(true)
                );
    }

    @Test
    public void getElementsWithPropertySatisfyRequirementsAsClass() {
        List<ColorEntity> list = given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract()
                .jsonPath()
                .getList("findAll { it.data.color != null }", ColorEntity.class);
                                    // [?(@.data.color != null)]

        assertFalse(list.isEmpty());
        list.forEach(
                colorEntity -> assertTrue(
                    colorEntity.getData().getPrice() > 350.00
                )
        );
    }

    @Test
    public void testSize(){
        JsonPath jsonPath = given()
                .when()
                .get("/objects")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .extract().jsonPath();

        assertThat(jsonPath.getInt("size()"), equalTo(6));
    }
}
