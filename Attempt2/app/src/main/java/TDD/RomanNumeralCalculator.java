package TDD;

import java.util.HashMap;

public class RomanNumeralCalculator {
    HashMap<Integer, String> romanNumeralTranslator = new HashMap<Integer, String>();

    public RomanNumeralCalculator() {
        initTranslator();
    }

    private void initTranslator() {
        romanNumeralTranslator.put(1, "I");
        romanNumeralTranslator.put(5, "V");
        romanNumeralTranslator.put(10, "X");
        romanNumeralTranslator.put(50, "L");
        romanNumeralTranslator.put(100, "C");
        romanNumeralTranslator.put(500, "D");
        romanNumeralTranslator.put(1000, "M");
    }

    public String intToRoman(int value) {
        if (isInvalidValue(value)) {
            throw new IllegalArgumentException();
        }

        return findRomanTranslation(value);
    }

    private String findRomanTranslation(int value) {
        return romanNumeralTranslator.containsKey(value) ? romanNumeralTranslator.get(value) : null;
    }

    private boolean isInvalidValue(int value) {
        return value < 1 || value > 3999;
    }
}
