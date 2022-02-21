package TDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RomanNumeralsTest {
    RomanNumerals rn = new RomanNumerals();

    @Test
    void oneDigitTest() {
        int testNumbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        String testNumerals[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        batchTest(testNumbers, testNumerals);
    }

    @Test
    void twoDigitTest() {
        int testNumbers[] = {10, 12, 39, 50, 66, 98};
        String testNumerals[] = {"X", "XII", "XXXIX", "L", "LXVI", "XCVIII"};

        batchTest(testNumbers, testNumerals);
    }

    @Test
    void threeDigitTest() {
        int testNumbers[] = {100, 173, 246, 468, 500, 672, 732, 925};
        String testNumerals[] = {"C", "CLXXIII", "CCXLVI", "CDLXVIII", "D", "DCLXXII", "DCCXXXII", "CMXXV"};

        batchTest(testNumbers, testNumerals);
    }

    @Test
    void fourDigitTest() {
        int testNumbers[] = {1000, 1066, 1377, 2014, 3888, 3999};
        String testNumerals[] = {"M", "MLXVI", "MCCCLXXVII", "MMXIV", "MMMDCCCLXXXVIII", "MMMCMXCIX"};

        batchTest(testNumbers, testNumerals);
    }

    private void batchTest(int[] testValues, String[] expectedValues) {
        for (int i = 0; i < testValues.length; i++) {
            assertEquals(expectedValues[i], rn.convertToRoman(testValues[i]), testValues[i] + "");
        }
    }
}
