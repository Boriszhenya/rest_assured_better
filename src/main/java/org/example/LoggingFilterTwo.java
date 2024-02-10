package org.example;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilterTwo implements OrderedFilter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        System.out.println("Request 2: " + requestSpec.getMethod() + " " + requestSpec.getURI());
        Response response = ctx.next(requestSpec, responseSpec);
        System.out.println("Response 2: " + response.getStatusCode());
        return response;
    }

    @Override
    public int getOrder() {
        return 100; // Order in which the filter should be executed
    }
}

