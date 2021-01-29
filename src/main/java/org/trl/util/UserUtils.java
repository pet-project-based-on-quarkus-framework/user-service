package org.trl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.trl.core.dto.UserDto;
import org.trl.exception.IllegalValueException;

import static java.lang.String.format;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * This class contains methods utils for {@literal UserDTO}.
 *
 * @author Tsyupryk Roman
 */
@ApplicationScoped
public class UserUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";

    private StringUtils stringUtils;

    public UserUtils() {
    }

    @Inject
    public UserUtils(StringUtils stringUtils) {
        this.stringUtils = stringUtils;
    }

    /**
     * Check the user fields for the correct value.
     *
     * @param user must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given {@code user} is equals to {@literal null}.
     * @throws IllegalValueException    If some field of {@literal UserDTO} contains illegal value.
     */
    public void checkFieldsOfUser(UserDto user) {
        String logHeaderMessage = "************ checkFieldsOfUser() ---> ";
        String messageNullValue = "Field %s not be equals to null, check the field that it has the 'user' parameter.";
        String messageIsEmpty = "Field %s is empty, check the field that it has the 'user' parameter.";

        if (user == null) {
            LOG.error(logHeaderMessage + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        if (user.getFirstName() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'firstName'"));
            throw new IllegalValueException(format(messageNullValue, "'firstName'"));
        } else if (stringUtils.isEmpty(user.getFirstName())) {
            LOG.error(logHeaderMessage + format(messageIsEmpty, "'firstName'"));
            throw new IllegalValueException(format(messageIsEmpty, "'firstName'"));
        }

        if (user.getLastName() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'lastName'"));
            throw new IllegalValueException(format(messageNullValue, "'lastName'"));
        } else if (stringUtils.isEmpty(user.getLastName())) {
            LOG.error(logHeaderMessage + format(messageIsEmpty, "'lastName'"));
            throw new IllegalValueException(format(messageIsEmpty, "'lastName' is empty"));
        }

        if (user.getUsername() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'userName'"));
            throw new IllegalValueException(format(messageNullValue, "'userName'"));
        } else if (stringUtils.isEmpty(user.getUsername())) {
            LOG.error(logHeaderMessage + format(messageIsEmpty, "'userName'"));
            throw new IllegalValueException(format(messageIsEmpty, "'userName'"));
        }

        if (user.getEmail() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'email'"));
            throw new IllegalValueException(format(messageNullValue, "'email'"));
        } else if (stringUtils.isEmpty(user.getEmail())) {
            LOG.error(logHeaderMessage + format(messageIsEmpty, "'email'"));
            throw new IllegalValueException(format(messageIsEmpty, "'email'"));
        }

        if (user.getPassword() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'password'"));
            throw new IllegalValueException(format(messageNullValue, "'password'"));
        } else if (stringUtils.isEmpty(user.getPassword())) {
            LOG.error(logHeaderMessage + format(messageIsEmpty, "'password'"));
            throw new IllegalValueException(format(messageIsEmpty, "'password'"));
        }

        if (user.getBirthday() == null) {
            LOG.error(logHeaderMessage + format(messageNullValue, "'birthday'"));
            throw new IllegalValueException(format(messageNullValue, "'birthday'"));
        }
    }
}
