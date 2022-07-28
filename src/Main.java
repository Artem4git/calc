import java.util.Scanner;

class Main {

    private static int numOne;
    private static int numTwo;
    private static int answer;

    private static String operator;

    private static String[] strings;

    public static void main(String[] args) throws Exception {

/**
 * test
 */

//        String[] operators = {" + ", " - ", " * ", " / "};
//        int randomOne;
//        int randomTwo;
//
//        while (true) {
//            randomOne = 1 + (int) (Math.random() * 11);
//            randomTwo = 1 + (int) (Math.random() * 11);
//
//            for (String s : operators) {
//                Thread.sleep(200);
//                System.out.println(randomOne + s + randomTwo + " = " + calc(randomOne + s + randomTwo));
//            }
//
//            for (String s : operators) {
//                Thread.sleep(200);
//                System.out.println(Convert.toRoman(randomOne) + s + Convert.toRoman(randomTwo)
//                        + " = " + calc(Convert.toRoman(randomOne) + s + Convert.toRoman(randomTwo)));
//
//            }
//
//        }

        Scanner scanner = new Scanner(System.in);
        String name;
        while (true) {
            name = scanner.nextLine();
            System.out.println(calc(name));
        }

    }

    public static String calc(String input) throws Exception {
        answer = 0;

        if (input.isEmpty()) {
            throw new Exception("пустая строка");
        }

        strings = input.split(" ");
        operator = strings[1];

        if (strings.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (strings.length < 3) {
            throw new Exception("строка не является математической операцией");
        }

        if (isArab()) {
//            if (hasNull()) {
//                return "Ноль недопустимое значение-";
//            }
            if (moreTen()) {
                return "Используется число больше 10";
            }
            try {
                calculate();
            }catch (ArithmeticException e) {
                throw new ArithmeticException("деление на ноль");
            }

            return String.valueOf(answer);
        }

        if (isRom()) {
            if (moreTen()) {
                return "Используется число больше X";
            }

            calculate();

            if (answer < 1) {
                throw new NumberFormatException("в римской системе нет чисел меньше I");
            }

            return Convert.toRoman(answer);
        }

        return "используются одновременно разные системы счисления";
    }

    private static boolean moreTen() {
        if (numOne > 10) {
            return true;
        }
        return numTwo > 10;
    }

    private static boolean hasNull() {
        if (numOne == 0) {
            return true;
        }
        return numTwo == 0;
    }

    private static boolean isArab() {
        char one = strings[0].charAt(0);
        char two = strings[2].charAt(0);
        boolean boolOne = Character.isDigit(one);

        if (boolOne) {
            numOne = Integer.parseInt(strings[0]);
            boolean boolTwo = Character.isDigit(two);
            if (boolTwo) {
                numTwo = Integer.parseInt(strings[2]);
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    private static boolean isRom() throws Exception {
        String one = strings[0];
        String two = strings[2];
        numOne = Convert.toArabic(one);
        numTwo = Convert.toArabic(two);
        calculate();
        return true;
    }


    private static void calculate() {

        switch (operator) {
            case "*" -> answer = multiply(numOne, numTwo);
            case "/" -> answer = divide(numOne, numTwo);
            case "+" -> answer = sum(numOne, numTwo);
            case "-" -> answer = subtract(numOne, numTwo);
            default -> {
                System.out.println("'" + operator + "'" + " не является математическим оператором");
                System.exit(0);
            }
        }
    }

    private static int divide(int one, int two) {

        return one / two;
    }

    private static int multiply(int one, int two) {
        return one * two;
    }

    private static int subtract(int one, int two) {
        return one - two;
    }

    private static int sum(int one, int two) {
        return one + two;
    }
}
