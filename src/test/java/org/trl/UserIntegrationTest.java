package org.trl;

//@QuarkusTest
public class UserIntegrationTest {

//    @InjectMock
//    DateTimeService dateTimeServiceMock;

//    @Test
//    void create() {
//        final String bodyJSONRequest = "{\"birthday\":\"2000-01-28\",\"email\":\"newUser@mail.com\",\"firstName\":\"NewUserFirstName\",\"lastName\":\"NewUserLastName\",\"password\":\"strong_password\",\"username\":\"ns\"}";
//        given()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().post("/users")
//                .then()
//                .statusCode(HttpURLConnection.HTTP_CREATED);
//    }

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

//    @Test
//    void update() {
//        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
//        long userId = 2L;
//        final String bodyJSONResponse = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"id\":2,\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
//        given()
//                .pathParam("id", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().patch("/users/{id}")
//                .then()
//                .statusCode(HttpURLConnection.HTTP_OK)
//                .body(is(bodyJSONResponse));
//    }

//    @Test
//    void update_Illegal_UserId() {
//        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
//        long userId = 0L;
//        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
//        given()
//                .pathParam("id", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().patch("/users/{id}")
//                .then()
//                .statusCode(422)
//                .body(is(bodyJSONResponse));
//    }

//    @Test
//    void update_UserById_NotFound() {
//        final String bodyJSONRequest = "{\"birthday\":\"2000-01-18\",\"email\":\"8l@mail.com\",\"firstName\":\"8Carlos\",\"lastName\":\"8Rodriguez\",\"password\":\"8strong_password\",\"username\":\"8cr\"}";
//        final long userId = 100L;
//        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 12, 10, 35, 53);
//        UserNotFoundException exception = new UserNotFoundException(format("User with this userId = %s not exist.", userId));
//        ApiErrorDto errorDto = new ApiErrorDto.Builder()
//                .withTimestamp(dateTime)
//                .withErrorMessage(exception.getMessage())
//                .withPath("")
//                .build();
//
//        final String expectedJsonResponse = JsonbBuilder.create().toJson(errorDto);
//
//        when(dateTimeServiceMock.now()).thenReturn(dateTime);
//
//        given()
//                .pathParam("id", userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(bodyJSONRequest)
//                .when().patch("/users/{id}")
//                .then()
//                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
//                .body(is(expectedJsonResponse));
//    }

//    @Test
//    void delete() {
//        long userId = 5L;
//        given()
//                .pathParam("id", userId)
//                .when().delete("/users/{id}")
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    void delete_Illegal_UserId() {
//        long userId = 0L;
//        final String bodyJSONResponse = "{\"exceptionType\":\"java.lang.IllegalArgumentException\",\"statusCode\":422,\"exceptionMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\"}";
//        given()
//                .pathParam("id", userId)
//                .when().delete("/users/{id}")
//                .then()
//                .statusCode(422)
//                .body(is(bodyJSONResponse));
//    }
//
//    @Test
//    void delete_UserById_NotFound() {
//        final long userId = 100L;
//        LocalDateTime dateTime = LocalDateTime.of(2020, 6, 12, 10, 35, 53);
//        UserNotFoundException exception = new UserNotFoundException(format("User with this userId = %s not exist.", userId));
//        ApiErrorDto errorDto = new ApiErrorDto.Builder()
//                .withTimestamp(dateTime)
//                .withErrorMessage(exception.getMessage())
//                .withPath("")
//                .build();
//
//        final String expectedJsonResponse = JsonbBuilder.create().toJson(errorDto);
//
//        when(dateTimeServiceMock.now()).thenReturn(dateTime);
//
//        given()
//                .pathParam("id", userId)
//                .when().delete("/users/{id}")
//                .then()
//                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
//                .body(is(expectedJsonResponse));
//    }

}
