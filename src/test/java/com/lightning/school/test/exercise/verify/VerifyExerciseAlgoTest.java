package com.lightning.school.test.exercise.verify;

import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperandException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.MissingOperatorException;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.NpiEmptyEception;
import com.lightning.school.mvc.facade.ControllerException.verrify.exo.OperatorNotSupportedException;
import com.lightning.school.mvc.facade.ExerciseVerifyController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VerifyExerciseAlgoTest {

    private ExerciseVerifyController controller;

    @Before
    public void init(){
        controller = new ExerciseVerifyController(null);
    }


    // Exception

    @Test(expected = NpiEmptyEception.class)
    public void should_do_nothing_with_empty_string(){
        controller.verifExo("");
    }

    @Test(expected = NpiEmptyEception.class)
    public void should_do_nothing_with_string_equal_null(){
        controller.verifExo(null);
    }

    @Test(expected = OperatorNotSupportedException.class)
    public void should_do_nothing_with_operator_not_suported(){
        controller.verifExo("10t");
    }

    @Test(expected = MissingOperandException.class)
    public void should_do_nothing_with_no_operand_with_or(){
        controller.verifExo("|");
    }

    @Test(expected = MissingOperandException.class)
    public void should_do_nothing_with_no_operand_with_and(){
        controller.verifExo("&");
    }

    @Test(expected = MissingOperandException.class)
    public void should_do_nothing_with_no_operand_with_not(){
        controller.verifExo("!");
    }

    @Test(expected = MissingOperatorException.class)
    public void should_do_nothing_with_no_operator(){
        controller.verifExo("11");
    }

    @Test(expected = MissingOperatorException.class)
    public void should_do_nothing_with_missing_operator(){
        controller.verifExo("11&1");
    }

    // OR TEST

    @Test
    public void should_verify_with_simple_operator_or_11(){
        Assert.assertTrue(controller.verifExo("11|"));
    }

    @Test
    public void should_verify_with_simple_operator_or_10(){
        Assert.assertTrue(controller.verifExo("10|"));
    }

    @Test
    public void should_verify_with_simple_operator_or_01(){
        Assert.assertTrue(controller.verifExo("01|"));
    }

    @Test
    public void should_verify_with_simple_operator_or_00(){
        Assert.assertFalse(controller.verifExo("00|"));
    }

    // AND TEST

    @Test
    public void should_verify_with_simple_operator_and_11(){
        Assert.assertTrue(controller.verifExo("11&"));
    }

    @Test
    public void should_verify_with_simple_operator_and_10(){
        Assert.assertFalse(controller.verifExo("10&"));
    }

    @Test
    public void should_verify_with_simple_operator_and_01(){
        Assert.assertFalse(controller.verifExo("01&"));
    }

    @Test
    public void should_verify_with_simple_operator_and_00(){
        Assert.assertFalse(controller.verifExo("00&"));
    }


    // NOT Test

    @Test
    public void should_verify_with_simple_operator_not_1(){
        Assert.assertFalse(controller.verifExo("1!"));
    }

    @Test
    public void should_verify_with_simple_operator_not_0(){
        Assert.assertTrue(controller.verifExo("0!"));
    }


    // Test complexe

    @Test
    public void should_verify_with_complexe_npi_1(){
        Assert.assertTrue(controller.verifExo("10|00&|"));
    }

    @Test
    public void should_verify_with_complexe_npi_2(){
        Assert.assertFalse(controller.verifExo("1!10|&"));
    }

    @Test
    public void should_verify_with_complexe_npi_3(){
        Assert.assertTrue(controller.verifExo("0!01|&11&01|&|"));
    }

    @Test
    public void should_verify_with_complexe_npi_4(){
        Assert.assertFalse(controller.verifExo("0!01|&11&01|&|!"));
    }

}
