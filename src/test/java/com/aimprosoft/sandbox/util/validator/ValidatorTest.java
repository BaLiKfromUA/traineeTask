package com.aimprosoft.sandbox.util.validator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author BaLiK on 28.03.19
 */
public class ValidatorTest {

    @Test
    public void testCorrectLogin() {
        assertTrue(Validator.validateLogin("mkyong34"));
        assertTrue(Validator.validateLogin("mkyong_2002"));
        assertTrue(Validator.validateLogin("mk3-4_yong"));
        assertTrue(Validator.validateLogin("aaaaaaaaaaaaaaaaaaaa"));
    }


    @Test
    public void testIncorrectLogin(){
      assertFalse(Validator.validateLogin("mk"));
      assertFalse(Validator.validateLogin("mk@yong"));
      assertFalse(Validator.validateLogin("HALLO_WORLD"));
      assertFalse(Validator.validateLogin("HaLLoWoRlD"));
      assertFalse(Validator.validateLogin("aaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }


}