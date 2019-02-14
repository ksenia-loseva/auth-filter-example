import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.spi.AuthFilter;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

public class AuthorizationFilter implements AuthFilter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        if (AuthorizationContext.getToken() != null) {
            requestSpec.replaceHeader(AUTHORIZATION, "Bearer " + AuthorizationContext.getToken());
        }
        return ctx.next(requestSpec, responseSpec);
    }
}
