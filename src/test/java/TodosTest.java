import Utils.ApiWrapper;
import Utils.TestDataHelper;
import org.example.Todos;
import org.junit.jupiter.api.Test;

import static Utils.ApiWrapper.deleteRequest;
import static Utils.ApiWrapper.sendGetRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest extends BaseHomeWorkTest {


    @Test
    public void schemeTodosValidationTest() {
        //int userId = getToDoUserId();
        int userId = getId("objectToDosPostPath", "user_id");

        sendGetRequest(
                given().pathParams("id", userId),
                getConfig("objectPathV2")
                        + getConfig("objectIdPath")
                        + getConfig("objectToDosPostPath"))
                .assertThat()
                .body(matchesJsonSchemaInClasspath("todos-schema.json"));
    }

    @Test
    public void getListParamObjectsToDos() {
        String page = "5";
        String perPage = "50";

        sendGetRequest(given().pathParams("page",
                        page, "perPage", perPage),
                (getConfig("objectPathV2")
                        + getConfig("objectToDosPostPath")
                        + "?page={page}&per_page={perPage}"))
                .assertThat()
                .body("$", hasSize(Integer.parseInt(perPage)));
    }


    @Test
    public void newTodoUsersCreation() {

        //int userId = getUserId();
        int userId = getId("endPointUsers", "id");
        System.out.println();

        Todos newUsersTodo = TestDataHelper.createTodos(userId);

        Todos actualUsersTodo =
                ApiWrapper.sendPostRequest(
                        given().pathParams("id", userId),
                        (getConfig("objectPathV2")
                                + getConfig("objectIdPath")
                                + getConfig("objectToDosPostPath")),
                        newUsersTodo,
                        Todos.class,
                        getConfig("objectToken"));

        assertEquals(actualUsersTodo, newUsersTodo);
    }

    @Test
    public void deleteToDo() {

//        int userId = getToDoId();
        int userId = getId("objectToDosPostPath", "id");

        deleteRequest(given().pathParams("id", userId),
                getConfig("objectPathV2")
                        + getConfig("objectToDosPostIdPath"),
                getConfig("objectToken")
        );
    }

}
