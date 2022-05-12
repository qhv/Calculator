import java.util.regex.Pattern;

class RomanNumber {
    static final char[] ROMANDIGITS = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    private int decimalValue;
    private String romanNumber;

    public RomanNumber(String romanNumberStr) throws Exception {
        if (!isCorrectRomanNumber(romanNumberStr)) {
            throw new Exception();
        }
        decimalValue = convertToInt(romanNumberStr);
        romanNumber = romanNumberStr;
    }

    static boolean isRomanDigit(char c) {
        for (char digit: ROMANDIGITS) {
            if (digit == c) {
                return true;
            }
        }
        return false;
    }

    static boolean isCorrectRomanNumber(String romanNumberStr) {
        String regex = "^M{0,3}(CM|CD|D?C{0,3})?(XC|XL|L?X{0,3})?(IX|IV|V?I{0,3})?$";

        return Pattern.matches(regex, romanNumberStr);
    }

    static RomanNumber intToRoman(int number) throws Exception {
        if  (number > 3999 || number < 1) {
            throw new Exception();
        }

        String result = "";

        for (int i = ROMANDIGITS.length - 1, rank = 1000; i >= 0; i -= 2, rank /= 10) {
            int digit = number / rank % 10;

            result += switch (digit) {
                case 0 -> "";
                case 1 -> ROMANDIGITS[i];
                case 2 -> "" + ROMANDIGITS[i] + ROMANDIGITS[i];
                case 3 -> "" + ROMANDIGITS[i] + ROMANDIGITS[i] + ROMANDIGITS[i];
                case 4 -> "" + ROMANDIGITS[i] + ROMANDIGITS[i + 1];
                case 5 -> ROMANDIGITS[i + 1];
                case 6 -> "" + ROMANDIGITS[i + 1] + ROMANDIGITS[i];
                case 7 -> "" + ROMANDIGITS[i + 1] + ROMANDIGITS[i] + ROMANDIGITS[i];
                case 8 -> "" + ROMANDIGITS[i + 1] + ROMANDIGITS[i] + ROMANDIGITS[i] + ROMANDIGITS[i];
                case 9 -> "" + ROMANDIGITS[i] + ROMANDIGITS[i + 2];
                default -> throw new Exception();
            };
        }

        return new RomanNumber(result);
    }

    private int convertToInt(String romanNumber) {
        int result = 0;
        char[] digits = romanNumber.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            boolean isNextDigit = i + 1 < digits.length;

            result += switch (digits[i]) {
                case 'I' -> (isNextDigit && (digits[i + 1] == 'V' || digits[i + 1] == 'X')) ? -1 : 1;
                case 'V' -> 5;
                case 'X' -> (isNextDigit && (digits[i + 1] == 'L' || digits[i + 1] == 'C')) ? -10 : 10;
                case 'L' -> 50;
                case 'C' -> (isNextDigit && (digits[i + 1] == 'D' || digits[i + 1] == 'M')) ? -100 : 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> 0;
            };
        }

        return result;
    }

    public String toString() {
        return romanNumber;
    }

    public RomanNumber add(RomanNumber anotherRomanNumber) throws Exception {
        return intToRoman(decimalValue + anotherRomanNumber.decimalValue);
    }

    public RomanNumber sub(RomanNumber anotherRomanNumber) throws Exception {
        return intToRoman(decimalValue - anotherRomanNumber.decimalValue);
    }

    public RomanNumber mul(RomanNumber anotherRomanNumber) throws Exception {
        return intToRoman(decimalValue * anotherRomanNumber.decimalValue);
    }

    public RomanNumber div(RomanNumber anotherRomanNumber) throws Exception {
        return intToRoman(decimalValue / anotherRomanNumber.decimalValue);
    }

    public boolean more(RomanNumber anotherRomanNumber) {
        return decimalValue > anotherRomanNumber.decimalValue;
    }
}
