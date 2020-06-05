package org.trl.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashingTest {

    @Test
    void generateHash() {
        PasswordHashing passwordHashing = new PasswordHashing();
        String password = "strong_password";
        String hashedPassword = passwordHashing.generateHash(password);

        assertTrue(passwordHashing.validatePassword(password, hashedPassword));
    }

    @Test
    void generateHash_DifferentPasswords() {
        PasswordHashing passwordHashing = new PasswordHashing();
        String password = "strong_password";
        String hashedPassword = passwordHashing.generateHash(password);

        assertFalse(passwordHashing.validatePassword(password + " ", hashedPassword));
    }

    @Test
    void generateHash_PasswordIsNull() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.generateHash(null));
    }

    @Test
    void generateHash_PasswordIsEmpty() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.generateHash(""));
    }

    @Test
    void validatePassword_PasswordIsNull_HashedPasswordIsNull() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword(null, null));
    }

    @Test
    void validatePassword_PasswordIsNull() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword(null, "12345"));
    }

    @Test
    void validatePassword_HashedPasswordIsNull() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword("1234", null));
    }

    @Test
    void validatePassword_PasswordIsEmpty_HashedPasswordIsEmpty() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword("", ""));
    }

    @Test
    void validatePassword_PasswordIsEmpty() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword("", "12345"));
    }

    @Test
    void validatePassword_HashedPasswordIsEmpty() {
        PasswordHashing passwordHashing = new PasswordHashing();

        assertThrows(IllegalArgumentException.class, () -> passwordHashing.validatePassword("1234", ""));
    }
}
