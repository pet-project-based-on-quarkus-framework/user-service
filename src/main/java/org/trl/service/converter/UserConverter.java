package org.trl.service.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.model.dto.UserDto;
import org.trl.repository.entity.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal UserEntity} to {@literal UserDto} and vice versa.
 * And also, this class is designed to convert {@literal Collection<UserEntity>} to {@literal Collection<UserDto>} and vice versa.
 *
 * @author Tsyupryk Roman
 */
@ApplicationScoped
public class UserConverter {

    private static final Logger LOG = LoggerFactory.getLogger(UserConverter.class);
    private static final String EXCEPTION_MESSAGE = "Parameter is illegal, check the parameter that are passed to the method.";

    public UserConverter() {
    }

    /**
     * Convert {@literal UserEntity} to {@literal UserDto}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal UserDto}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public UserDto mapEntityToDto(UserEntity entity) {
        UserDto result;

        if (entity == null) {
            LOG.debug("In mapEntityToDto() - {}", EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        result = new UserDto.Builder()
                .withId(entity.getId())
                .withFirstName(entity.getFirstName())
                .withLastName(entity.getLastName())
                .withUsername(entity.getUsername())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withBirthday(entity.getBirthday())
                .build();

        LOG.debug("In mapEntityToDto() - Result [{}]", result);

        return result;
    }

    /**
     * Convert {@literal Collection<UserEntity>} to {@literal Collection<UserDto>}.
     *
     * @param collection must not be {@literal null}.
     * @return the {@literal Collection<UserDto>}.
     * @throws IllegalArgumentException in case the given {@code collection} is {@literal null}.
     */
    public Collection<UserDto> mapCollectionEntityToCollectionDto(Collection<UserEntity> collection) {
        Collection<UserDto> result;

        if (collection == null) {
            LOG.debug("In mapCollectionEntityToCollectionDto() - {}", EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        result = collection.parallelStream()
                .map(userEntity -> new UserConverter().mapEntityToDto(userEntity))
                .collect(Collectors.toCollection(LinkedList::new));

        LOG.debug("In mapCollectionEntityToCollectionDto() - Result [{}]", result);

        return result;
    }

    /**
     * Convert {@literal UserDto} to {@literal UserEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal UserEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public UserEntity mapDtoToEntity(UserDto dto) {
        UserEntity result;

        if (dto == null) {
            LOG.debug("In mapDtoToEntity() - {}", EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        result = new UserEntity.Builder()
                .withId(dto.getId())
                .withFirstName(dto.getFirstName())
                .withLastName(dto.getLastName())
                .withUsername(dto.getUsername())
                .withEmail(dto.getEmail())
                .withPassword(dto.getPassword())
                .withBirthday(dto.getBirthday())
                .build();

        LOG.debug("In mapDtoToEntity() - Result [{}]", result);

        return result;
    }

    /**
     * Convert {@literal Collection<UserDto>} to {@literal Collection<UserEntity>}.
     *
     * @param collection must not be {@literal null}.
     * @return the {@literal Collection<UserEntity>}.
     * @throws IllegalArgumentException in case the given {@code collection} is {@literal null}.
     */
    public Collection<UserEntity> mapCollectionDtoToCollectionEntity(Collection<UserDto> collection) {
        Collection<UserEntity> result;

        if (collection == null) {
            LOG.debug("In mapCollectionDtoToCollectionEntity() - {}", EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        result = collection.parallelStream()
                .map(userDto -> new UserConverter().mapDtoToEntity(userDto))
                .collect(Collectors.toCollection(LinkedList::new));

        LOG.debug("In mapCollectionDtoToCollectionEntity() - Result [{}]", result);

        return result;
    }

}
