import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;


public class AuthorizationService {

    private String BASE_URI = "http://localhost:8080";

    public AuthorizationService(String baseUri) {
        this.BASE_URI = baseUri;
    }

    public AuthorizationService() {

    }

    public void authorize(String username, String password) {
        String accessToken = given()
                .baseUri(BASE_URI)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .auth().none()
                .auth().preemptive().basic(username, password)
                .contentType(ContentType.URLENC)
                .expect()
                .statusCode(SC_OK)
                .body("access_token", not(nullValue()))
                .when()
                .post("/oauth/token")
                .body().jsonPath().getString("access_token");
        AuthorizationContext.setAuthToken(accessToken);
    }

}
