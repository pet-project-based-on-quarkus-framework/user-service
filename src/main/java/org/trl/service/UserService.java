package org.trl.service;


import org.trl.model.dto.UserDto;
import org.trl.repository.entity.UserEntity;

/**
 * This interface is designed to support service for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface UserService {

    void create(UserDto user);

    UserEntity get(Long id);

    UserDto update(Long id, UserDto user);

    void delete(Long id);
}
