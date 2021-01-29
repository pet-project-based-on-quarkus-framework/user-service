package org.trl.service.converter;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trl.model.dto.UserDto;
import org.trl.repository.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;

@QuarkusTest
class UserConverterTest {

    @Inject
    UserConverter userConverter;

    private static UserDto userDto_Resource;
    private static UserEntity userEntity_Resource;

    private static List<UserDto> dtoList_Resource;
    private static List<UserEntity> entityList_Resource;

    @BeforeEach
    void setUp() {

        userDto_Resource = new UserDto();
        userDto_Resource.setId(1L);
        userDto_Resource.setFirstName("Roman");
        userDto_Resource.setLastName("Tsyupryk");
        userDto_Resource.setUsername("TRL");
        userDto_Resource.setEmail("tsyupryk.roman@gmail.com");
        userDto_Resource.setEmail("strong_password");
        userDto_Resource.setBirthday(LocalDate.of(1988, 6, 26));

        userEntity_Resource = new UserEntity();
        userEntity_Resource.setId(1L);
        userEntity_Resource.setFirstName("Roman");
        userEntity_Resource.setLastName("Tsyupryk");
        userEntity_Resource.setUsername("TRL");
        userEntity_Resource.setEmail("tsyupryk.roman@gmail.com");
        userEntity_Resource.setEmail("strong_password");
        userEntity_Resource.setBirthday(LocalDate.of(1988, 6, 26));

        UserDto userDto_1 = new UserDto();
        userDto_1.setId(1L);
        userDto_1.setFirstName("Roman");
        userDto_1.setLastName("Tsyupryk");
        userDto_1.setUsername("TRL");
        userDto_1.setEmail("tsyupryk.roman@gmail.com");
        userDto_1.setEmail("strong_password");
        userDto_1.setBirthday(LocalDate.of(1988, 6, 26));
        UserDto userDto_2 = new UserDto();
        userDto_2.setId(2L);
        userDto_2.setFirstName("AAA");
        userDto_2.setLastName("BBB");
        userDto_2.setUsername("AB");
        userDto_2.setEmail("AB@gmail.com");
        userDto_2.setEmail("strong_password");
        userDto_2.setBirthday(LocalDate.of(1900, 1, 1));

        UserEntity userEntity_1 = new UserEntity();
        userEntity_1.setId(1L);
        userEntity_1.setFirstName("Roman");
        userEntity_1.setLastName("Tsyupryk");
        userEntity_1.setUsername("TRL");
        userEntity_1.setEmail("tsyupryk.roman@gmail.com");
        userEntity_1.setEmail("strong_password");
        userEntity_1.setBirthday(LocalDate.of(1988, 6, 26));
        UserEntity userEntity_2 = new UserEntity();
        userEntity_2.setId(2L);
        userEntity_2.setFirstName("AAA");
        userEntity_2.setLastName("BBB");
        userEntity_2.setUsername("AB");
        userEntity_2.setEmail("AB@gmail.com");
        userEntity_2.setEmail("strong_password");
        userEntity_2.setBirthday(LocalDate.of(1900, 1, 1));

        dtoList_Resource = List.of(userDto_1, userDto_2);
        entityList_Resource = List.of(userEntity_1, userEntity_2);
    }

    @Test
    void parameterUserEntityIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapEntityToDto(null));
    }

    @Test
    void shouldReturnUserDto() {
        UserDto result = userConverter.mapEntityToDto(userEntity_Resource);
        assertEquals(userDto_Resource, result);
    }

    @Test
    void parameterCollectionOfUserEntityIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapCollectionEntityToCollectionDto(null));
    }

    @Test
    void shouldReturnEmptyCollectionOfUserDto() {
        Collection<UserDto> result = userConverter.mapCollectionEntityToCollectionDto(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCollectionWithUserDto() {
        Collection<UserDto> result = userConverter.mapCollectionEntityToCollectionDto(entityList_Resource);
        assertEquals(dtoList_Resource, result);
    }

    @Test
    void parameterUserDtoIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapDtoToEntity(null));
    }

    @Test
    void shouldReturnUserEntity() {
        UserEntity result = userConverter.mapDtoToEntity(userDto_Resource);
        assertEquals(userEntity_Resource, result);
    }

    @Test
    void parameterCollectionOfUserDtoIsNull_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapCollectionDtoToCollectionEntity(null));
    }

    @Test
    void shouldReturnEmptyCollectionOfUserEntity() {
        Collection<UserEntity> result = userConverter.mapCollectionDtoToCollectionEntity(Collections.emptyList());
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCollectionWithUserEntities() {
        Collection<UserEntity> result = userConverter.mapCollectionDtoToCollectionEntity(dtoList_Resource);
        assertEquals(entityList_Resource, result);
    }

}
