package com.example.pixel_request.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LuhnaAlgorithmTestSuite {

    @Test
    public void testLuhnaGenerateControlSumTest() {
        // Given
        String digits1 = "92480";
        String digits2 = "1234567";
        String digits3 = "12_3-45:67";

        // When
        int control1 = Luhna.generate(digits1, false);
        int control2 = Luhna.generate(digits2, false);
        int control3 = Luhna.generate(digits3, false);

        // Then
        assertEquals(3, control1);
        assertEquals(4, control2);
        assertEquals(4, control3);
    }

    @Test
    public void testLuhnaCheckControlSumTest() {
        // Given
        String digits1 = "924803";
        String digits2 = "12345674";
        String digits3 = "12_3-45:674";

        // When
        int control1 = Luhna.generate(digits1, true);
        int control2 = Luhna.generate(digits2, true);
        int control3 = Luhna.generate(digits3, true);

        // Then
        assertEquals(0, control1);
        assertEquals(0, control2);
        assertEquals(0, control3);
    }

    @Test
    public void testLuhnaCheckControlSumNotCorrectTest() {
        // Given
        String digits1 = "924802";
        String digits2 = "12345671";
        String digits3 = "12_4-45:674";

        // When
        int control1 = Luhna.generate(digits1, true);
        int control2 = Luhna.generate(digits2, true);
        int control3 = Luhna.generate(digits3, true);

        // Then
        assertNotEquals(0, control1);
        assertNotEquals(0, control2);
        assertNotEquals(0, control3);
    }
}
