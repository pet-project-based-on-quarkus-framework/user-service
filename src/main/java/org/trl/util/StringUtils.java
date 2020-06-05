package org.trl.util;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StringUtils {

    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null. Check the parameter that are passed to the method.";

    /**
     * Check the {@code str} for an empty value.
     *
     * @param str must not be equal to {@literal null}.
     * @throws IllegalArgumentException in case the given {@code str} is {@literal null}.
     * @return If {@code str} is empty return {@literal true}. If {@code str} is not empty return {@literal false}.
     */
    public boolean isEmpty(String str) {

        if (str == null) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        String replaceAll = str.replaceAll("\\s+", "");
        return replaceAll.isEmpty();
    }
}
