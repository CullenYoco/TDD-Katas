package TDD;

public class RomanNumeralCalculator {
    public String intToRoman(int value) {
        if (isInvalidValue(value)) {
            throw new IllegalArgumentException();
        }

        if (value == 1) {
            return "I";
        }

        if (value == 5) {
            return "V";
        }

        if (value == 10) {
            return "X";
        }

        return null;
    }

    private boolean isInvalidValue(int value) {
        return value < 1 || value > 3999;
    }
}
