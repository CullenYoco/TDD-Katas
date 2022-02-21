package TDD;

public class RomanNumeralCalculator {
    private final int keyNumbers[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private final String keyNumberTranslations[] = {"I", "IV", "V", "IX", "X","XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    public String intToRoman(int value) {
        if (isInvalidValue(value)) {
            throw new IllegalArgumentException();
        }

        return findRomanTranslation(value);
    }

    private String findRomanTranslation(int value) {
        String outputString = "";

        for (int i = keyNumbers.length - 1; i > -1; i--) {
            int timesDivisibleByKeyNumber = Math.floorDiv(value, keyNumbers[i]);

            outputString += keyNumberTranslations[i].repeat(timesDivisibleByKeyNumber);
            value -= timesDivisibleByKeyNumber * keyNumbers[i];

            if (value == 0) {
                break;
            }
        }

        return outputString;
    }

    private boolean isInvalidValue(int value) {
        return value < 1 || value > 3999;
    }
}