package org.example;
import io.restassured.filter.FilterContext;
import io.restassured.filter.OrderedFilter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilterThree implements OrderedFilter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        System.out.println("Request 3: " + requestSpec.getMethod() + " " + requestSpec.getURI());
        Response response = ctx.next(requestSpec, responseSpec);
        System.out.println("Response 3: " + response.getStatusCode());
        return response;
    }

    @Override
    public int getOrder() {
        return 10; // Order in which the filter should be executed
    }
}

