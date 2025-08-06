import java.util.Scanner;

@SuppressWarnings("All")
public class CalDemo {


    // Generic Class to handle different data types
    public static class Result<T> {
        private T x;

        public Result(T value) {
            this.x = value;
        }

        public T getValue() {
            return x;
        }
    }

    // Basic operations of a Calculator
    public static class Calculator{

        public Result<Integer> add(int a, int b){
            return new Result<Integer>(a + b);
        }

        public Result<Integer> subtract(int a, int b) {
            return new Result<Integer>(a - b);
        }

        public Result<Integer> multiply(int a, int b) {
            return new Result<Integer>(a * b);
        }

        public Result<Double> divide(int a, int b) {
            try {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero not allowed.");
                }
                return new Result<>(((double) a) / b);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
                return new Result<>(0.0);
            }
        }
    }

    
    public static void main(String[] args) {
        System.out.println("Welcome to the Calculator App!");

        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        String option = "yes";
        while (option.equalsIgnoreCase("yes")) {
            System.out.println("Enter first number:");
            int firstNumber = scanner.nextInt();
            System.out.println("Enter second number:");
            int secondNumber = scanner.nextInt();

            System.out.println("Menu:");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.print("Select operation number: ");
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    Result<Integer> addResult = calculator.add(firstNumber, secondNumber);
                    System.out.println("Result: " + addResult.getValue());
                    break;
                case 2:
                    Result<Integer> subResult = calculator.subtract(firstNumber, secondNumber);
                    System.out.println("Result: " + subResult.getValue());
                    break;
                case 3:
                    Result<Integer> mulResult = calculator.multiply(firstNumber, secondNumber);
                    System.out.println("Result: " + mulResult.getValue());
                    break;
                case 4:
                    Result<Double> divResult = calculator.divide(firstNumber, secondNumber);
                    if(divResult.getValue() != 0.0){
                        System.out.println("Result: " + divResult.getValue());
                    } 
                    break;
                default:
                    System.out.println("Invalid operation!!!");
                    continue;
            }

            System.out.println("Do you want to continue?(yes/no)");
            option = scanner.next();
        }

        scanner.close();
    }
   

}
