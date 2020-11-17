package application.logic;

public class Operations {

    public int a;
    public int b;
    public String operation;

//    public double add(double a, double b) {
//        return a + b;
//    }
//    public double subtract(double a, double b) {
//        return a - b;
//    }
//    public double multiply(double a, double b) {
//        return a * b;
//    }

    public double divide() {
//        ei vielä toiminassa
//        if (this.b == 0) {
//            throw new ArithmeticException("Undefined");
//        }
//        return this.a / this.b;
        return 0;
    }

    public int executeOperation() throws NumberFormatException {
        if (this.operation == "+") {
            return a + b;
        } else if (this.operation == "-") {
            return a - b;
        } else if (this.operation == "*") {
            return a * b;
        } else {
            return (int) divide();
        }
    }

    public void reset() {
        this.a = 0;
        this.b = 0;
        this.operation = null;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
