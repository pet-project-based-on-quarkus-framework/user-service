package org.trl.service.converter;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.trl.controller.dto.UserDTO;
import org.trl.repository.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;

@QuarkusTest
class UserConverterTest {

    @Inject
    UserConverter userConverter;

    private static UserDTO userDTO_Resource;
    private static UserEntity userEntity_Resource;

    private static List<UserDTO> dtoList_Resource;
    private static List<UserEntity> entityList_Resource;
    private static List<UserEntity> emptyList_Resource;

    @BeforeEach
    void setUp() {

        userDTO_Resource = new UserDTO();
        userDTO_Resource.setId(1L);
        userDTO_Resource.setFirstName("Roman");
        userDTO_Resource.setLastName("Tsyupryk");
        userDTO_Resource.setUsername("TRL");
        userDTO_Resource.setEmail("tsyupryk.roman@gmail.com");
        userDTO_Resource.setEmail("strong_password");
        userDTO_Resource.setBirthday(LocalDate.of(1988, 6, 26));

        userEntity_Resource = new UserEntity();
        userEntity_Resource.setId(1L);
        userEntity_Resource.setFirstName("Roman");
        userEntity_Resource.setLastName("Tsyupryk");
        userEntity_Resource.setUsername("TRL");
        userEntity_Resource.setEmail("tsyupryk.roman@gmail.com");
        userEntity_Resource.setEmail("strong_password");
        userEntity_Resource.setBirthday(LocalDate.of(1988, 6, 26));

        UserDTO userDTO_1 = new UserDTO();
        userDTO_1.setId(1L);
        userDTO_1.setFirstName("Roman");
        userDTO_1.setLastName("Tsyupryk");
        userDTO_1.setUsername("TRL");
        userDTO_1.setEmail("tsyupryk.roman@gmail.com");
        userDTO_1.setEmail("strong_password");
        userDTO_1.setBirthday(LocalDate.of(1988, 6, 26));
        UserDTO userDTO_2 = new UserDTO();
        userDTO_2.setId(2L);
        userDTO_2.setFirstName("AAA");
        userDTO_2.setLastName("BBB");
        userDTO_2.setUsername("AB");
        userDTO_2.setEmail("AB@gmail.com");
        userDTO_2.setEmail("strong_password");
        userDTO_2.setBirthday(LocalDate.of(1900, 1, 1));

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

        dtoList_Resource = Collections.unmodifiableList(Arrays.asList(userDTO_1, userDTO_2));
        entityList_Resource = Collections.unmodifiableList(Arrays.asList(userEntity_1, userEntity_2));
        emptyList_Resource = Collections.unmodifiableList(new ArrayList<>());
    }

    // ================================================================================================== mapEntityToDTO
    @Test
    void mapEntityToDTO() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        assertEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_NullParameter() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapEntityToDTO(null));
    }

    @Test
    void mapEntityToDTO_Incorrect_Id() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setId(100L);
        assertNotEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_Incorrect_FirstName() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setFirstName("--------------");
        assertNotEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_Incorrect_LastName() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setLastName("--------------");
        assertNotEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_Incorrect_Username() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setUsername("--------------");
        assertNotEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_Incorrect_Email() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setEmail("--------------");
        assertNotEquals(userDTO_Resource, result);
    }

    @Test
    void mapEntityToDTO_Incorrect_Password() {
        // TODO: Terminate this test.
    }

    @Test
    void mapEntityToDTO_Incorrect_Birthday() {
        UserDTO result = userConverter.mapEntityToDTO(userEntity_Resource);
        result.setBirthday(LocalDate.of(2000, 1, 1));
        assertNotEquals(userDTO_Resource, result);
    }

    // ============================================================================== mapCollectionEntityToCollectionDTO
    @Test
    void mapCollectionEntityToCollectionDTO() {
        Collection<UserDTO> result = userConverter.mapCollectionEntityToCollectionDTO(entityList_Resource);
        assertEquals(dtoList_Resource, result);
    }

    @Test
    void mapCollectionEntityToCollectionDTO_NullParameter() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapCollectionEntityToCollectionDTO(null));
    }

    @Test
    void mapCollectionEntityToCollectionDTO_Check_Failure_Of_Test() {
        Collection<UserDTO> result = userConverter.mapCollectionEntityToCollectionDTO(entityList_Resource);
        result.add(new UserDTO());
        assertNotEquals(dtoList_Resource, result);
    }

    @Test
    void mapCollectionEntityToCollectionDTO_Empty_Collection() {
        Collection<UserDTO> result = userConverter.mapCollectionEntityToCollectionDTO(emptyList_Resource);
        assertEquals(emptyList_Resource, result);
    }

    // ================================================================================================== mapDTOToEntity
    @Test
    void mapDTOToEntity() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        assertEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_NullParameter() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapDTOToEntity(null));
    }

    @Test
    void mapDTOToEntity_Incorrect_Id() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setId(100L);
        assertNotEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_Incorrect_FirstName() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setFirstName("--------------");
        assertNotEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_Incorrect_LastName() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setLastName("--------------");
        assertNotEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_Incorrect_Username() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setUsername("--------------");
        assertNotEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_Incorrect_Email() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setEmail("--------------");
        assertNotEquals(userEntity_Resource, result);
    }

    @Test
    void mapDTOToEntity_Incorrect_Password() {
        // TODO: Terminate this test...
    }

    @Test
    void mapDTOToEntity_Incorrect_Birthday() {
        UserEntity result = userConverter.mapDTOToEntity(userDTO_Resource);
        result.setBirthday(LocalDate.of(2000, 1, 1));
        assertNotEquals(userEntity_Resource, result);
    }

    // ============================================================================== mapCollectionDTOToCollectionEntity
    @Test
    void mapCollectionDTOToCollectionEntity() {
        Collection<UserEntity> result = userConverter.mapCollectionDTOToCollectionEntity(dtoList_Resource);
        assertEquals(entityList_Resource, result);
    }

    @Test
    void mapCollectionDTOToCollectionEntity_NullParameter() {
        assertThrows(IllegalArgumentException.class, () -> userConverter.mapCollectionDTOToCollectionEntity(null));
    }

    @Test
    void mapCollectionDTOToCollectionEntity_Check_Failure_Of_Test() {
        Collection<UserEntity> result = userConverter.mapCollectionDTOToCollectionEntity(dtoList_Resource);
        result.add(new UserEntity());
        assertNotEquals(entityList_Resource, result);
    }
}
