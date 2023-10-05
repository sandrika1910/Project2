package data;

public class HelperClassForSteps {
    public static int getNumbersFromString(String input) {
        StringBuilder numbersOnly = new StringBuilder();

        for (char character : input.toCharArray()) {
            if (Character.isDigit(character)) {
                numbersOnly.append(character);
            }
        }

        return Integer.parseInt(numbersOnly.toString());
    }
}
