package com.aimprosoft.sandbox.util.validator;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * @author BaLiK on 28.03.19
 */
public class ValidatorTest {
//todo:validate name and email test
    @Test
    public void testCorrectLogin() {
        assertTrue(Validator.validateLogin("mkyong34"));
        assertTrue(Validator.validateLogin("mkyong_2002"));
        assertTrue(Validator.validateLogin("mk3-4_yong"));
        assertTrue(Validator.validateLogin("aaaaaaaaaaaaaaaaaaaa"));
    }


    @Test
    public void testIncorrectLogin() {
        assertFalse(Validator.validateLogin("mk"));
        assertFalse(Validator.validateLogin("mk@yong"));
        assertFalse(Validator.validateLogin("HALLO_WORLD"));
        assertFalse(Validator.validateLogin("HaLLoWoRlD"));
        assertFalse(Validator.validateLogin("aaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    public void testCorrectId() {
        assertTrue(Validator.validateId("1"));
        assertTrue(Validator.validateId("123"));
        assertTrue(Validator.validateId("999999"));
        assertTrue(Validator.validateId("1111111111"));
    }

    @Test
    public void testIncorrectId() {
        assertFalse(Validator.validateId("-1"));
        assertFalse(Validator.validateId("0"));
        assertFalse(Validator.validateId("-10000000000000000"));
        assertFalse(Validator.validateId("1abcdE222"));
        assertFalse(Validator.validateId("0123"));
    }

    @Test
    public void testCorrectRank() {
        for (int i = 1; i < 1000; ++i) {
            assertTrue(Validator.validateRank(String.valueOf(i)));
        }
    }

    @Test
    public void testIncorrectRank() {
        for (int i = 0; i > -1001; --i) {
            assertFalse(Validator.validateRank(String.valueOf(i)));
        }
        assertFalse(Validator.validateRank("-10000000000000000"));
        assertFalse(Validator.validateRank("1abcdE222"));
        assertFalse(Validator.validateRank("0123"));
    }

    @Test
    public void testCorrectDate() {
        LocalDate startDate = LocalDate.parse("1979-12-31");
        LocalDate finishDate = LocalDate.now();

        while (finishDate.compareTo(startDate) >= 0) {
            assertTrue(Validator.validateDate(startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            startDate = startDate.plusDays(1);
        }
    }

    @Test
    public void testIncorrectDate() {
        String date = "BaLiK";
        assertFalse(Validator.validateDate(date));
        date = "12-31-2018";
        assertFalse(Validator.validateDate(date));
        date = "31-12-2018";
        assertFalse(Validator.validateDate(date));
        date = "2000/12/04";
        assertFalse(Validator.validateDate(date));
        date = "2000-45-55";
        assertFalse(Validator.validateDate(date));
        date="2000-balik-29";
        assertFalse(Validator.validateDate(date));
    }
}