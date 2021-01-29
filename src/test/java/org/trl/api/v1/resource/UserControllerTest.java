package org.trl.api.v1.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.trl.model.dto.UserDto;
import org.trl.service.DateTimeService;

import javax.json.bind.JsonbBuilder;
import java.net.HttpURLConnection;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.trl.api.v1.resource.UserController.BASE_URL;

@QuarkusTest
class UserControllerTest {

    @Test
    void shouldReturnUser() {
        final long userId = 1L;

        UserDto userDto = new UserDto.Builder()
                .withId(1L)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();
        final String expectedJsonResponse = JsonbBuilder.create().toJson(userDto);

        given()
                .pathParam("id", userId)
                .when().get(BASE_URL + "/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body(is(expectedJsonResponse));
    }

    @Test
    void illegalPathParamUserId_shouldReturnHttpStatusCodeBadRequest() {
        final long userId = 0L;
        final String errorMessage = "userId must be greater than or equal to 1";

        given()
                .pathParam("id", userId)
                .when().get(BASE_URL + "/{id}")
                .then()
                .statusCode(400)
                .body(containsString(errorMessage));
    }

    @Test
    void whenUserIsNotFoundById_shouldThrowUserNotFoundException() {
        final long userId = 123456789L;

        given().pathParam("id", userId)
                .when().get(BASE_URL + "/{id}")
                .then()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }

}
