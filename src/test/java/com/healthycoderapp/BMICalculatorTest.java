package test.java.com.healthycoderapp;

import main.java.com.healthycoderapp.BMICalculator;
import main.java.com.healthycoderapp.Coder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BMICalculatorTest {
    @Test
    void should_Return_True_When_DietRecommended() {
        // given
        double weight = 89.0;
        double height = 1.72;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertTrue(recommended);
    }

    @Test
    void should_Return_False_When_DietRecommended() {
        // given
        double weight = 50.0;
        double height = 1.92;
        // when
        boolean recommended = BMICalculator.isDietRecommended(weight, height);
        // then
        assertFalse(recommended);
    }

    @Test
    void should_ThrowArithmeticException_When_DietRecommended() {
        // given
        double weight = 50.0;
        double height = 0.0;
        // when
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
        // then
        assertThrows(ArithmeticException.class, executable);
    }

    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty(){
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98.0, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNullWorstBMI_When_CoderListNotEmpty(){
        // given
        List<Coder> coders = new ArrayList<>();
        // when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
        // then
        assertNull(coderWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMI_When_CoderListNotEmpty(){
        // given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60.0));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};
        // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);
        // then
        assertArrayEquals(expected, bmiScores);
    }

}
