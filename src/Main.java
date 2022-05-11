import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
    //  System.out.println("Введите выражение вида: число [операция] число.");
    //  System.out.println("Допустимы римские и арабские числа от 1 до 10.");
    //  System.out.println("Допустимые операции: сложение (+), вычитание (-), умножение (*), деление (/).");
        String line = scanner.nextLine();

        String result = calc(line);

    //  System.out.print("Результат равен ");
        System.out.println(result);
    }

    static String operators = "-+*/";

    public static String calc(String input) throws Exception {
        String[] operands = input.split("[" + operators + "]");

        if (operands.length != 2) {
            throw new Exception();
        }

        char operator = input.charAt(operands[0].length());

        for (int i = 0; i < operands.length; i++) {
            operands[i] = operands[i].trim();
        }

        char firstSymbol = operands[0].charAt(0);

        if (Character.isDigit(firstSymbol)) {
            int firstOperand = Integer.parseInt(operands[0]);
            int lastOperand = Integer.parseInt(operands[1]);

            return Integer.toString(simpleCalc(firstOperand, lastOperand, operator));
        }

        return "";
    }

    static int simpleCalc(int firstOperand,int lastOperand, char operator) throws Exception {

        return switch (operator) {
            case '+' -> firstOperand + lastOperand;
            case '-' -> firstOperand - lastOperand;
            case '*' -> firstOperand * lastOperand;
            case '/' -> firstOperand / lastOperand;
            default -> throw new Exception();
        };
    }
}