package org.example;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthenticationFilter implements Filter {

    private final String authToken;

    public AuthenticationFilter(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        // Добавить токен в заголовок авторизации
        requestSpec.header("Authorization", "Bearer " + authToken);

        // Продолжить выполнение запроса
        return ctx.next(requestSpec, responseSpec);
    }
}

