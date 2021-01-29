package org.trl.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserDtoBuilderTest {

    @Test
    void testBuilder() {
        UserDto expected = new UserDto();
        expected.setId(1L);
        expected.setFirstName("Roman");
        expected.setLastName("Tsyupryk");
        expected.setUsername("TRL");
        expected.setEmail("tsyupryk.roman@gmail.com");
        expected.setPassword("strong_password");
        expected.setBirthday(LocalDate.of(1988, 6, 26));

        UserDto result = new UserDto.Builder()
                .withId(1L)
                .withFirstName("Roman")
                .withLastName("Tsyupryk")
                .withUsername("TRL")
                .withEmail("tsyupryk.roman@gmail.com")
                .withPassword("strong_password")
                .withBirthday(LocalDate.of(1988, 6, 26))
                .build();

        assertEquals(expected, result);
    }

}
