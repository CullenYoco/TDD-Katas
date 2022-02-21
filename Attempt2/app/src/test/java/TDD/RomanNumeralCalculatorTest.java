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
        illegalArgumentTest(5000);
        illegalArgumentTest(10000);
    }

    @Test
    public void oneToTenTest() {
        assertEquals("I", rnc.intToRoman(1));
        assertEquals("II", rnc.intToRoman(2));
        assertEquals("III", rnc.intToRoman(3));
        assertEquals("IV", rnc.intToRoman(4));
        assertEquals("V", rnc.intToRoman(5));
        assertEquals("VI", rnc.intToRoman(6));
        assertEquals("VII", rnc.intToRoman(7));
        assertEquals("VIII", rnc.intToRoman(8));
        assertEquals("IX", rnc.intToRoman(9));
        assertEquals("X", rnc.intToRoman(10));
    }

    @Test
    public void tenToFiftyTest() {
        assertEquals("XIV", rnc.intToRoman(14));
        assertEquals("XXVI", rnc.intToRoman(26));
        assertEquals("XXXVIII", rnc.intToRoman(38));
        assertEquals("XL", rnc.intToRoman(40));
        assertEquals("XLIX", rnc.intToRoman(49));
        assertEquals("L", rnc.intToRoman(50));
    }

    @Test
    public void fiftyToOneHundredTest() {
        assertEquals("LXIII", rnc.intToRoman(63));
        assertEquals("LXXV", rnc.intToRoman(75));
        assertEquals("LXXXVII", rnc.intToRoman(87));
        assertEquals("XC", rnc.intToRoman(90));
        assertEquals("XCIX", rnc.intToRoman(99));
        assertEquals("C", rnc.intToRoman(100));
    }

    @Test
    public void oneHundredToFiveHundredTest() {
        assertEquals("CXL", rnc.intToRoman(140));
        assertEquals("CCXIII", rnc.intToRoman(213));
        assertEquals("CCCLXXXVII", rnc.intToRoman(387));
        assertEquals("CD", rnc.intToRoman(400));
        assertEquals("CDLXVI", rnc.intToRoman(466));
        assertEquals("D", rnc.intToRoman(500));
    }

    @Test
    public void fiveHundredToOneThousandTest() {
        assertEquals("DXLIV", rnc.intToRoman(544));
        assertEquals("DCLXIX", rnc.intToRoman(669));
        assertEquals("DCCXCII", rnc.intToRoman(792));
        assertEquals("DCCCLXXI", rnc.intToRoman(871));
        assertEquals("CM", rnc.intToRoman(900));
        assertEquals("CMLXVIII", rnc.intToRoman(968));
        assertEquals("M", rnc.intToRoman(1000));
    }

    @Test
    public void oneThousandToMax() {
        assertEquals("MDCLXVIII", rnc.intToRoman(1668));
        assertEquals("MMCCCXCI", rnc.intToRoman(2391));
        assertEquals("MMMDCCCLXXXVIII", rnc.intToRoman(3888));
        assertEquals("MMMCMXCIX", rnc.intToRoman(3999));
    }

    private void illegalArgumentTest(int value) {
        assertThrows(IllegalArgumentException.class, () -> {
            rnc.intToRoman(value);
        });
    }
}
