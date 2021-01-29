package org.trl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.model.dto.UserDto;
import org.trl.repository.UserRepository;
import org.trl.repository.entity.UserEntity;
import org.trl.service.UserService;
import org.trl.service.converter.UserConverter;
import org.trl.util.UserUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * This class is designed to implementation methods of {@code UserService}.
 *
 * @author Tsyupryk Roman
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";

    private static final String createLogHeaderMessage = "************ create() ---> ";
    private static final String updateLogHeaderMessage = "************ update () ---> ";
    private static final String deleteLogHeaderMessage = "************ delete () ---> ";


    private UserRepository userRepository;
    private UserConverter userConverter;
    private UserUtils userUtils;

    public UserServiceImpl() {
    }

    @Inject
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, UserUtils userUtils) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userUtils = userUtils;
    }

    /**
     * Create the {@literal UserDTO}.
     *
     * @param user must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given {@code user} is equals to {@literal null}.
     */
    @Override
    public void create(UserDto user) {

        if (user == null) {
            LOG.error(createLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(createLogHeaderMessage + "user = " + user);

        userUtils.checkFieldsOfUser(user);

        userRepository.create(userConverter.mapDtoToEntity(user));

        LOG.debug(createLogHeaderMessage + "user = " + user + " added correctly.");
    }

    /**
     * Get {@literal UserDTO} by this id.
     *
     * @param id must not be equal to {@literal null}, and must be greater than zero.
     * @return the {@literal UserDTO} with the given id.
     * @throws IllegalArgumentException in case the given id is equal to {@literal null}
     *                                  or if id is equal or less zero.
     */
    @Override
    public UserEntity get(Long id) {
        UserEntity userResult;

        userResult = userRepository.find(id);

        LOG.debug("In get() - Found user: [{}].", userResult);

        return userResult;
    }

    /**
     * Update the {@literal UserDTO} by this {@code userId}.
     *
     * @param id   must not be {@literal null} and must be greater than zero.
     * @param user must not be {@literal null}.
     * @return the {@literal UserDTO}, this user to be updated.
     * @throws IllegalArgumentException in case the given id is equals to {@literal null},
     *                                  or id is equal or less zero.
     *                                  Or user is equals to {@literal null}.
     */
    @Override
    public UserDto update(Long id, UserDto user) {
        UserDto userResult = null;

        if ((user == null) || (id == null) || (id <= 0)) {
            LOG.error(updateLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(updateLogHeaderMessage + "id = " + id + " user = " + user);

        userResult = userConverter.mapEntityToDto(userRepository.update(id, userConverter.mapDtoToEntity(user)));

        LOG.debug(updateLogHeaderMessage + "the updated instance of a user = " + userResult);

        return userResult;
    }

    /**
     * Delete {@literal UserDTO} by id.
     *
     * @param id must not be equal {@literal null} and must be greater than zero.
     * @throws IllegalArgumentException in case the given id is equals to {@literal null} or id is equal or less zero.
     */
    @Override
    public void delete(Long id) {

        if ((id == null) || (id <= 0)) {
            LOG.error(deleteLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(deleteLogHeaderMessage + "id= " + id);

        userRepository.delete(id);

        LOG.debug(deleteLogHeaderMessage + "user by this " + id + " deleted correctly.");
    }
}
