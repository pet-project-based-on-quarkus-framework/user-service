package org.trl.service;


import org.trl.controller.dto.UserDTO;

/**
 * This interface is designed to support service for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
public interface UserService {

    void create(UserDTO user);

    UserDTO get(Long id);

    UserDTO update(Long id, UserDTO user);

    void delete(Long id);
}
