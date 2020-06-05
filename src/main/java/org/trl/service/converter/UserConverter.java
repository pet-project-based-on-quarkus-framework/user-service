package org.trl.service.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.trl.controller.dto.UserDTO;
import org.trl.repository.entity.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * This class is designed to convert {@literal UserEntity} to {@literal UserDTO} and vice versa.
 * And also, this class is designed to convert {@literal Collection<UserEntity>} to {@literal Collection<UserDTO>} and vice versa.
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
     * Convert {@literal UserEntity} to {@literal UserDTO}.
     *
     * @param entity must not be {@literal null}.
     * @return the {@literal UserDTO}.
     * @throws IllegalArgumentException in case the given {@code entity} is {@literal null}.
     */
    public UserDTO mapEntityToDTO(UserEntity entity) {
        UserDTO result = null;

        if (entity == null) {
            LOG.debug("************ mapEntityToDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapEntityToDTO() ---> userEntity = " + entity
                + " ---> userEntity.getClass().getSimpleName() = " + entity.getClass().getSimpleName());

        result = new UserDTO();
        result.setId(entity.getId());
        result.setFirstName(entity.getFirstName());
        result.setLastName(entity.getLastName());
        result.setUsername(entity.getUsername());
        result.setEmail(entity.getEmail());
        result.setPassword(entity.getPassword());
        result.setBirthday(entity.getBirthday());

        LOG.debug("************ mapEntityToDTO() ---> result = " + result
                + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Collection<UserEntity>} to {@literal Collection<UserDTO>}.
     *
     * @param collection must not be {@literal null}.
     * @return the {@literal Collection<UserDTO>}.
     * @throws IllegalArgumentException in case the given {@code collection} is {@literal null}.
     */
    public Collection<UserDTO> mapCollectionEntityToCollectionDTO(Collection<UserEntity> collection) {
        Collection<UserDTO> result = null;

        if (collection == null) {
            LOG.debug("************ mapCollectionEntityToCollectionDTO() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        LOG.debug("************ mapCollectionEntityToCollectionDTO() ---> userEntityCollection = " + collection);

        result = collection.parallelStream()
                .map(userEntity -> new UserConverter().mapEntityToDTO(userEntity))
                .collect(Collectors.toCollection(LinkedList::new));

        LOG.debug("************ mapCollectionEntityToCollectionDTO() ---> result = " + result);

        return result;
    }

    /**
     * Convert {@literal UserDTO} to {@literal UserEntity}.
     *
     * @param dto must not be {@literal null}.
     * @return the {@literal UserEntity}.
     * @throws IllegalArgumentException in case the given {@code dto} is {@literal null}.
     */
    public UserEntity mapDTOToEntity(UserDTO dto) {
        UserEntity result = null;

        if (dto == null) {
            LOG.debug("************ mapDTOToEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        LOG.debug("************ mapDTOToEntity() ---> userDTO = " + dto
                + " ---> userDTO.getClass().getSimpleName() = " + dto.getClass().getSimpleName());

        result = new UserEntity();
        result.setId(dto.getId());
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setUsername(dto.getUsername());
        result.setEmail(dto.getEmail());
        result.setPassword(dto.getPassword());
        result.setBirthday(dto.getBirthday());

        LOG.debug("************ mapDTOToEntity() ---> result = "
                + result + " ---> result.getClass().getSimpleName() = " + result.getClass().getSimpleName());

        return result;
    }

    /**
     * Convert {@literal Collection<UserDTO>} to {@literal Collection<UserEntity>}.
     *
     * @param collection must not be {@literal null}.
     * @return the {@literal Collection<UserEntity>}.
     * @throws IllegalArgumentException in case the given {@code collection} is {@literal null}.
     */
    public Collection<UserEntity> mapCollectionDTOToCollectionEntity(Collection<UserDTO> collection) {
        Collection<UserEntity> result = null;

        if (collection == null) {
            LOG.debug("************ mapCollectionDTOToCollectionEntity() ---> " + EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        LOG.debug("************ mapCollectionDTOToCollectionEntity() ---> userDTOCollection = " + collection);

        result = collection.parallelStream()
                .map(userDTO -> new UserConverter().mapDTOToEntity(userDTO))
                .collect(Collectors.toCollection(LinkedList::new));

        LOG.debug("************ mapCollectionDTOToCollectionEntity() ---> result = " + result);

        return result;
    }
}
