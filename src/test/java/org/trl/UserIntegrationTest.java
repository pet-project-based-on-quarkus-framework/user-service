package org.trl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;

@QuarkusTest
public class UserIntegrationTest {

    @Test
    public void create() {
        final String bodyJSONRequest = "{\"birthday\":\"2000-01-28\",\"email\":\"newUser@mail.com\",\"firstName\":\"NewUserFirstName\",\"lastName\":\"NewUserLastName\",\"password\":\"strong_password\",\"username\":\"ns\"}";
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyJSONRequest)
                .when().post("/users")
                .then()
                .statusCode(HttpURLConnection.HTTP_CREATED);
    }

//    @Test
//    public void create_FieldFirstNameIsEmpty() {
//        final String bodyJSONRequest = "{\"birthday\":\"2000-01-28\",\"email\":\"newUser@mail.com\",\"firstName\":\"\",\"lastName\":\"NewUserLastName\",\"password\":\"strong_password\",\"username\":\"ns\"}";
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().post("/users")
//                .then()
//                .statusCode(422);
//    }

    // TODO: Find information how to do pass null value via JSON. Terminate this test.
//    @Test
//    public void create_Illegal_UserId() {
//        final String bodyJSONRequest = "{}";
//        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().post("/users")
//                .then()
//                .statusCode(422)
//                .body(is(bodyJSONResponse));
//    }

    @Test
    public void get() {
        long userId = 1L;
        final String bodyJSONResponse = "{\"birthday\":\"1988-06-26\",\"email\":\"tsyupryk.roman@gmail.com\",\"firstName\":\"Roman\",\"id\":1,\"lastName\":\"Tsyupryk\",\"password\":\"strong_password\",\"username\":\"TRL\"}";
        given()
                .pathParam("id", userId)
                .when().get("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void get_Illegal_UserId() {
        long userId = 0L;
        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
        given()
                .pathParam("id", userId)
                .when().get("/users/{id}")
                .then()
                .statusCode(422)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void get_UserById_NotFound() {
        long userId = 100L;
        final String bodyJSONResponse = "{\"exceptionType\":\"org.trl.exception.UserNotFoundException\",\"statusCode\":404,\"exceptionMessage\":\"User with this userId = 100 not exist.\"}";
        given()
                .pathParam("id", userId)
                .when().get("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void update() {
        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
        long userId = 2L;
        final String bodyJSONResponse = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"id\":2,\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
        given()
                .pathParam("id", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyJSONRequest)
                .when().patch("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void update_Illegal_UserId() {
        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
        long userId = 0L;
        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
        given()
                .pathParam("id", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyJSONRequest)
                .when().patch("/users/{id}")
                .then()
                .statusCode(422)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void update_UserById_NotFound() {
        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
        long userId = 100L;
        final String bodyJSONResponse = "{\"exceptionType\":\"org.trl.exception.UserNotFoundException\",\"statusCode\":404,\"exceptionMessage\":\"User with this userId = 100 not exist.\"}";
        given()
                .pathParam("id", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bodyJSONRequest)
                .when().patch("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void delete() {
        long userId = 5L;
        given()
                .pathParam("id", userId)
                .when().delete("/users/{id}")
                .then()
                .statusCode(204);

        final String bodyJSONResponse = "{\"exceptionType\":\"org.trl.exception.UserNotFoundException\",\"statusCode\":404,\"exceptionMessage\":\"User with this userId = 5 not exist.\"}";
        given()
                .pathParam("id", userId)
                .when().delete("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void delete_Illegal_UserId() {
        long userId = 0L;
        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
        given()
                .pathParam("id", userId)
                .when().delete("/users/{id}")
                .then()
                .statusCode(422)
                .body(is(bodyJSONResponse));
    }

    @Test
    public void delete_UserById_NotFound() {
        long userId = 100L;
        final String bodyJSONResponse = "{\"exceptionType\":\"org.trl.exception.UserNotFoundException\",\"statusCode\":404,\"exceptionMessage\":\"User with this userId = 100 not exist.\"}";
        given()
                .pathParam("id", userId)
                .when().delete("/users/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .body(is(bodyJSONResponse));
    }
}
