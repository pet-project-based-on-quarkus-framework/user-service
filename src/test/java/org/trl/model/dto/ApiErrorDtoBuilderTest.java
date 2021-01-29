package org.trl.model.dto;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ApiErrorDtoBuilderTest {

    @Test
    void testBuilder() {
        ApiErrorDto expected = new ApiErrorDto();
        expected.setTimestamp(LocalDateTime.of(2021, 1, 1, 10, 23, 24, 25));
        expected.setErrorMessage("Some Error Message");
        expected.setPath("Some Path");

        ApiErrorDto result = new ApiErrorDto.Builder()
                .withTimestamp(LocalDateTime.of(2021, 1, 1, 10, 23, 24, 25))
                .withErrorMessage("Some Error Message")
                .withPath("Some Path")
                .build();

        assertEquals(expected, result);
    }

}
