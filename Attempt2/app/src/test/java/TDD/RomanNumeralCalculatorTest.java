package TDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralCalculatorTest {
    RomanNumeralCalculator rnc = new RomanNumeralCalculator();

    @Test
    public void negativeTest() {
        illegalArgumentTest(-1);
        illegalArgumentTest(-10);
        illegalArgumentTest(-1000);
    }

    @Test
    public void zeroTest() {
        illegalArgumentTest(0);
    }

    @Test
    public void tooBigTest() {
        illegalArgumentTest(4000);
    }

    @Test
    public void oneTest() {
        assertEquals("I", rnc.intToRoman(1));
    }

    @Test
    public void fiveTest() {
        assertEquals("V", rnc.intToRoman(5));
    }

    @Test
    public void tenTest() {
        assertEquals("X", rnc.intToRoman(10));
    }

    private void illegalArgumentTest(int value) {
        assertThrows(IllegalArgumentException.class, () -> {
            rnc.intToRoman(value);
        });
    }
}
