package org.trl.core.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class UserEntityBuilderTest {

    @Test
    void testBuilder() {
        UserEntity expected = new UserEntity();
        expected.setId(1L);
        expected.setFirstName("Roman");
        expected.setLastName("Tsyupryk");
        expected.setUsername("TRL");
        expected.setEmail("tsyupryk.roman@gmail.com");
        expected.setPassword("strong_password");
        expected.setBirthday(LocalDate.of(1988, 6, 26));

        UserEntity result = new UserEntity.Builder()
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
