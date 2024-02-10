package org.example;
import io.restassured.builder.ResponseBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.response.Response;

public class ModifyRequestResponseFilter implements Filter {

    private final String customHeader;
    private final String responsePrefix;

    public ModifyRequestResponseFilter(String customHeader, String responsePrefix) {
        this.customHeader = customHeader;
        this.responsePrefix = responsePrefix;
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        // Изменить запрос, добавив пользовательский заголовок
        requestSpec.header("X-Custom-Header", customHeader);

        // Продолжить выполнение запроса
        Response response = ctx.next(requestSpec, responseSpec);

        // Изменить тело ответа, добавив префикс
        String modifiedResponseBody = response.getBody().asString();
        modifiedResponseBody = responsePrefix + modifiedResponseBody;

        // Создать новый ответ с измененным телом
        return new ResponseBuilder().clone(response).setBody(modifiedResponseBody).build();
    }
}
