package TDD;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class RomanNumeralCalculatorTest {
    RomanNumeralCalculator rnc = new RomanNumeralCalculator();

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100})
    public void negativeTest(int testValue) {
        illegalArgumentTest(testValue);
    }

    @Test
    public void zeroTest() {
        illegalArgumentTest(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {4000, 5000, 10000})
    public void tooBigTest(int testValue) {
        illegalArgumentTest(testValue);
    }

    @ParameterizedTest
    @MethodSource({"oneToTenProvider", "tenToFiftyProvider",
                   "fiftyToOneHundredProvider", "oneHundredToFiveHundredProvider",
                   "fiveHundredToOneThousandProvider", "oneThousandToMaxProvider"})
    public void intToRomanTest(String expectedOutput, int input) {
        assertEquals(expectedOutput, rnc.intToRoman(input));
    }

    private void illegalArgumentTest(int value) {
        assertThrows(IllegalArgumentException.class, () -> {
            rnc.intToRoman(value);
        });
    }

    private static Stream<Arguments> oneToTenProvider() {
        return Stream.of(
            Arguments.of("I", 1),
            Arguments.of("II", 2),
            Arguments.of("III", 3),
            Arguments.of("IV", 4),
            Arguments.of("V", 5),
            Arguments.of("VI", 6),
            Arguments.of("VII", 7),
            Arguments.of("VIII", 8),
            Arguments.of("IX", 9),
            Arguments.of("X", 10)
        );
    }

    private static Stream<Arguments> tenToFiftyProvider() {
        return Stream.of(
           Arguments.of("XIV", 14),
           Arguments.of("XXVI", 26),
           Arguments.of("XXXVIII", 38),
           Arguments.of("XL", 40),
           Arguments.of("XLIX", 49),
           Arguments.of("L", 50)
        );
    }

    private static Stream<Arguments> fiftyToOneHundredProvider() {
        return Stream.of(
            Arguments.of("LXIII", 63),
            Arguments.of("LXXV", 75),
            Arguments.of("LXXXVII", 87),
            Arguments.of("XC", 90),
            Arguments.of("XCIX", 99),
            Arguments.of("C", 100)
        );
    }

    private static Stream<Arguments> oneHundredToFiveHundredProvider() {
        return Stream.of(
            Arguments.of("CXL", 140),
            Arguments.of("CCXIII", 213),
            Arguments.of("CCCLXXXVII", 387),
            Arguments.of("CD", 400),
            Arguments.of("CDLXVI", 466),
            Arguments.of("D", 500)
        );
    }

    private static Stream<Arguments> fiveHundredToOneThousandProvider() {
        return Stream.of(
            Arguments.of("DXLIV", 544),
            Arguments.of("DCLXIX", 669),
            Arguments.of("DCCXCII", 792),
            Arguments.of("DCCCLXXI", 871),
            Arguments.of("CM", 900),
            Arguments.of("CMLXVIII", 968),
            Arguments.of("M", 1000)
        );
    }

    private static Stream<Arguments> oneThousandToMaxProvider() {
        return Stream.of(
            Arguments.of("MDCLXVIII", 1668),
            Arguments.of("MMCCCXCI", 2391),
            Arguments.of("MMMDCCCLXXXVIII", 3888),
            Arguments.of("MMMCMXCIX", 3999)
        );
    }
}
