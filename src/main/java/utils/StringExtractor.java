package utils;

public class StringExtractor {
    public static int IntegerExtractor(String input) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                stringBuilder.append(currentChar);
            }
        }
        try {
            return Integer.parseInt(stringBuilder.toString());
        } catch (NumberFormatException e) {
            System.out.println("No integers found in the given string.");
            return 0;
        }
    }
}