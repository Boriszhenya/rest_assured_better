package org.example;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class PerformanceMonitoringFilter implements Filter {

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        long startTime = System.currentTimeMillis();

        // Proceed with the request
        Response response = ctx.next(requestSpec, responseSpec);

        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        System.out.println("Request took: " + responseTime + " milliseconds");

        return response;
    }
}

