package TDD;

public class RomanNumerals {
    String romanNumeralStrings[] = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    int romanNumeralValues[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public String convertToRoman(int n) {
        int upperBound = romanNumeralValues.length - 1;
        String romanNumeral = "";

        while (n > 0) {
            for (int i = upperBound; i > - 1; i--) {
                if (inBiggestPossibleBaseNumber(n, i)) {
                    romanNumeral += romanNumeralStrings[i];
                    upperBound = i;
                    n -= romanNumeralValues[i];
                    break;
                }
            }
        }

        return romanNumeral;
    }

    private boolean inBiggestPossibleBaseNumber(int n, int i) {
        return n >= romanNumeralValues[i];
    }
}