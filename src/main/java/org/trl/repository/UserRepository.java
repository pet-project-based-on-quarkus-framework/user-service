package org.trl.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.exception.UserNotFoundException;
import org.trl.repository.entity.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static java.lang.String.format;

/**
 * This interface is designed to support JPA for {@literal UserEntity}.
 *
 * @author Tsyupryk Roman
 */
@ApplicationScoped
public class UserRepository {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);

    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST = "";

    private static final String createLogHeaderMessage = "************ create() ---> ";
    private static final String updateLogHeaderMessage = "************ update () ---> ";
    private static final String deleteLogHeaderMessage = "************ delete () ---> ";

    private EntityManager entityManager;

    public UserRepository() {
    }

    @Inject
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Create the {@literal UserEntity}.
     *
     * @param user must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given user is equals to {@literal null}.
     */
    @Transactional
    public void create(UserEntity user) {

        if (user == null) {
            LOG.error(createLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(createLogHeaderMessage + "user = " + user);

        entityManager.persist(user);

        LOG.debug(createLogHeaderMessage + "user = " + user + " added correctly.");
    }

    /**
     * Get {@literal UserEntity} by this id.
     *
     * @param id must not be equal to {@literal null}, and must be greater than zero.
     * @return the {@literal UserEntity} with the given id.
     * @throws IllegalArgumentException in case the given id is equals to {@literal null}
     *                                  or if id is equal or less zero.
     * @throws UserNotFoundException    in case if {@literal UserDTO} not exist with this id.
     */
    public UserEntity find(Long id) {
        UserEntity userResult;

        userResult = entityManager.find(UserEntity.class, id);

        if (userResult == null) {
            String message = format("User with this userId = %s not exist.", id);
            LOG.debug("In find() - {}", message);
            throw new UserNotFoundException(message);
        }

        LOG.debug("In find() - Found user: [{}].", userResult);

        return userResult;
    }

    /**
     * Update the {@literal UserEntity} by this id.
     *
     * @param id   must not be {@literal null}, and must be greater than zero.
     * @param user must not be {@literal null}.
     * @return the {@literal UserEntity}, this user to be updated.
     * @throws IllegalArgumentException in case the given id is equals to {@literal null}
     *                                  or if id is equal or less zero.
     *                                  Or user is equals to {@literal null}.
     * @throws UserNotFoundException    in case if {@literal UserEntity} not found by id.
     */
    @Transactional
    public UserEntity update(Long id, UserEntity user) {

        if ((user == null) || (id == null) || (id <= 0)) {
            LOG.error(updateLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(updateLogHeaderMessage + "id = " + id + " userSource = " + user);

        UserEntity entity = entityManager.find(UserEntity.class, id);

        if (entity == null) {
            String message = format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, id);
            LOG.error(updateLogHeaderMessage + message);
            throw new UserNotFoundException(message);
        }

        LOG.debug(updateLogHeaderMessage + " userFromRepository = " + entity);

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setBirthday(user.getBirthday());

        return entity;
    }

    /**
     * Delete {@literal UserEntity} by id.
     *
     * @param id must not be equal {@literal null} and must be greater than zero.
     * @throws IllegalArgumentException in case the given id is equals to {@literal null} or id is equal or less zero.
     * @throws UserNotFoundException    If user not found by this id.
     */
    @Transactional
    public void delete(Long id) {

        if ((id == null) || (id <= 0)) {
            LOG.error(deleteLogHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug(deleteLogHeaderMessage + "id = " + id);

        UserEntity entity = entityManager.find(UserEntity.class, id);

        if (entity == null) {
            String message = format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, id);
            LOG.error(deleteLogHeaderMessage + message);
            throw new UserNotFoundException(message);
        }

        entityManager.remove(entity);

        LOG.debug(deleteLogHeaderMessage + "user = " + entity + " is deleted correctly.");
    }
}
