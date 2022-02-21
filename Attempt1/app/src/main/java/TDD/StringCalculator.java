package TDD;

public class StringCalculator {
    public int calculate(String input) {
        if (input.equals("")) {
            return 0;
        }

        if (isCustomDelimiterFormat(input)) {
            return customDelimiterStringTotal(input.substring(2), input.charAt(0));
        }
        
        return defaultDelimiterStringTotal(input);
    }

    private int calculateStringTotal(String[] splitString) {
        int total = 0;
        for (String stringPart: splitString) {
            total += convertPartToInt(stringPart);
        }

        return total;
    }

    private int convertPartToInt(String stringPart) {
        try {
            return Integer.parseInt(stringPart);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private int customDelimiterStringTotal(String input, char delimiter) {
        return calculateStringTotal(input.split("[" + delimiter + "]"));
    }

    private int defaultDelimiterStringTotal(String input) {
        return calculateStringTotal(input.split("[:,]"));
    }

    private boolean isCustomDelimiterFormat(String input) {
        return input.length() > 1 && input.charAt(1) == '\n';
    }  
}
