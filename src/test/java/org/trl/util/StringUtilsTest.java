package org.trl.util;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

@QuarkusTest
class StringUtilsTest {

    private String str_Empty;
    private String str_WithSpace;
    private String str_WithTab;
    private String str_WithNewLine_1;
    private String str_WithNewLine_2;
    private String str_WithNewLine_3;
    private String str_WithValue;

    @Inject
    StringUtils stringUtils;

    @BeforeEach
    void setUp() {
        str_Empty = "";
        str_WithSpace = " ";
        str_WithTab = "   ";
        str_WithNewLine_1 = "\n";
        str_WithNewLine_2 = "" + System.lineSeparator() + "";
        str_WithNewLine_3 = "\r\n";
        str_WithValue = "AAA";
    }

    @Test
    void isEmpty_CheckStringEmpty() {
        assertTrue(stringUtils.isEmpty(str_Empty));
    }

    @Test
    void isEmpty_CheckStringWithSpace() {
        assertTrue(stringUtils.isEmpty(str_WithSpace));
    }

    @Test
    void isEmpty_CheckStringWithTab() {
        assertTrue(stringUtils.isEmpty(str_WithTab));
    }

    @Test
    void isEmpty_CheckStringWithNewLine_1() {
        assertTrue(stringUtils.isEmpty(str_WithNewLine_1));
    }

    @Test
    void isEmpty_CheckStringWithNewLine_2() {
        assertTrue(stringUtils.isEmpty(str_WithNewLine_2));
    }

    @Test
    void isEmpty_CheckStringWithNewLine_3() {
        assertTrue(stringUtils.isEmpty(str_WithNewLine_3));
    }

    @Test
    void isEmpty_CheckStringWithValue() {
        assertFalse(stringUtils.isEmpty(str_WithValue));
    }
}
