package calculator.logic;

public class Operations {

    public double a;
    public double b;
    public double memory;
    public String operation;
    public boolean undefined;

    public void reset() {
        this.a = 0;
        this.b = 0;
        this.operation = null;
        this.undefined = false;
    }

    public double executeOperation() throws NumberFormatException {
        double result = 0;
        switch (this.operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = divide();
                break;
            case "^":
                result = power();
                break;
            case "^2":
                result = Math.pow(a, 2);
                break;
            case "sqrtRoot":
                result = Math.sqrt(a);
                break;
            case "n!":
                result = factorial();
                break;
            case "nCr":
                result = nCr();
                break;
            case "nPr":
                result = nPr();
                break;
            case "mod":
                result = a % b;
                break;
        }
        return result;
    }

    public double divide() {
        if (this.b == 0) {
            this.undefined = true;
            return 0;
        }
        return this.a / this.b;
    }

    public double factorial() {
        if (this.a < 0) {
            this.undefined = true;
            return 0;
        }
        double result = 1;
        for (int i = 2; i <= this.a; i++) {
            result *= i;
        }
        return result;
    }

    private double factorial(double x) {
        double result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    public double nCr() {
        if (this.b > this.a || this.a < 0 || this.b < 0) {
            this.undefined = true;
            return 0;
        }
        return factorial(this.a) / (factorial(this.b) * factorial(this.a - this.b));
    }

    public double nPr() {
        if (this.b > this.a || this.a < 0 || this.b < 0) {
            this.undefined = true;
            return 0;
        }
        return factorial(this.a) / factorial(this.a - this.b);
    }

    public boolean biggerThanInteger(double x) {
        if (x == 1) {
            return this.a < Integer.MIN_VALUE || this.a > Integer.MAX_VALUE;
        } else if (x == 2) {
            return this.b < Integer.MIN_VALUE || this.b > Integer.MAX_VALUE;
        } else {
            return false; // won't be used
        }
    }

    public void setMemory(double x) {
        this.memory = x;
    }

    public double getMemory() {
        return memory;
    }

    public double power() {
        return Math.pow(this.a, this.b);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isUndefined() { return undefined; }
}
