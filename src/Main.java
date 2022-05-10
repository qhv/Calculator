import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение вида: число [операция] число.");
        System.out.println("Допустимы римские и арабские числа от 1 до 10.");
        System.out.println("Допустимые операции: сложение (+), вычитание (-), умножение (*), деление (/).");
        String line = scanner.nextLine();

        String result = calc(line);

     // System.out.print("Результат равен ");
        System.out.println(result);
    }

    static String operators = "-+*/";
    static char[] romanDigits = { 'I', 'V', 'X' };

    public static String calc(String input) {
        String[] operands = input.split("[" + operators + "]");

        if (operands.length != 2) {
            ;
        }

        char operator = input.charAt(operands[0].length());

        for (int i = 0; i < operands.length; i++) {
            operands[i] = operands[i].trim();
        }

        char firstSymbol = operands[0].charAt(0);

        if (Character.isDigit(firstSymbol)) {
            int firstOperand = Integer.parseInt(operands[0]);
            int lastOperand = Integer.parseInt(operands[1]);

            return Integer.toString(arabicCalc(firstOperand, lastOperand, operator));
        }

        for (char romanDigit: romanDigits) {
            if (romanDigit == firstSymbol) {
                return romanCalc(operands[0], operands[1], operator);
            }
        }

        return "";
    }

    static int arabicCalc(int firstOperand,int lastOperand, char operator) {

        return switch (operator) {
            case '+' -> firstOperand + lastOperand;
            case '-' -> firstOperand - lastOperand;
            case '*' -> firstOperand * lastOperand;
            case '/' -> firstOperand / lastOperand;
            default -> -1;
        };
    }

    static String romanCalc(String firstOperand, String lastOperand, char operator) {
        int result = arabicCalc(romanToArabic(firstOperand), romanToArabic(lastOperand), operator);

        return arabicToRoman(result);
    }

    static String[] romans = { "", "I", "II", "III", "IV", "V",
            "VI", "VII", "VIII", "IX", "X"};

    static int romanToArabic(String romansNumber) {
        for (int i = 1; i < romans.length; i++) {
            if (romans[i].equals(romansNumber)) {
                return i;
            }
        }

        return -1;
    }

    static String arabicToRoman(int arabicNumber) {
        return romans[arabicNumber];
    }
}