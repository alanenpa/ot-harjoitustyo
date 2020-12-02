package calculator.ui;

import calculator.logic.Operations;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {
    Operations logic = new Operations();
    Text header;
    TextField field;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(15);
        header = new Text();
        header.setId("header");
        BorderPane bp = new BorderPane();
        bp.setRight(header);
        field = new TextField();
        field.setAlignment(Pos.BASELINE_RIGHT);
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") && !newValue.equals("Infinity")) {
                field.setText(newValue.replaceAll("[^\\d^.^E^-]", ""));
            }
        });

        GridPane keypad = createNumPad();

        Button comma = new Button(",");
        comma.setOnAction(actionEvent -> {
            String text = field.getText();
            if (!text.contains(".")) field.setText(field.getText() + ".");
        });
        keypad.add(comma,3, 4);

        Button neg = new Button("+/-");
        neg.setOnAction(actionEvent -> {
            String text = field.getText();
            if (!text.contains("-")) {
                field.setText("-" + field.getText());
            } else {
                field.setText(text.substring(1));
            }
        });
        keypad.add(neg,1, 4);

        Button equals = new Button("=");
        equals.setOnAction(actionEvent -> {
            if (field.getText().equals("")) {
                emptyStringError();
                return;
            }
            if (logic.getOperation() == null) return;
            double b;
            if (!logic.isUndefined()) {
                if (header.getText().contains("=")) {
                    b = Double.parseDouble(field.getText());
                    logic.setA(b);
                } else {
                    b = Double.parseDouble(field.getText());
                    logic.setB(b);
                }
            }

            double result = logic.executeOperation();
            if (logic.isUndefined()) {
                header.setText("Undefined");
                field.clear();
                return;
            }
            if (result % 1 == 0 && (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE)) {
                printCalculation2();
                field.setText(String.valueOf((int)result));
                return;
            }
            printCalculation2();
            field.setText(String.valueOf(result));
        });
        keypad.add(equals, 5, 4);

        Button clear = new Button("C");
        clear.setOnAction(actionEvent -> {
            logic.reset();
            field.clear();
            header.setText("");
        });
        keypad.add(clear, 0, 0);

        Button add = createOperationButton2("+");
        Button subtract = createOperationButton2("-");
        Button multiply = createOperationButton2("*");
        Button divide = createOperationButton2("/");

        Button power = createOperationButton2("^");
        power.setText("x" + '\u207F');

        Button square = createOperationButton1("^2");
        square.setText("x" + '\u00B2');

        Button sqrtRoot = createOperationButton1("sqrtRoot");
        sqrtRoot.setText(String.valueOf('\u221A'));

        Button fact = createOperationButton1("n!");
        Button nCr = createOperationButton2("nCr");
        Button nPr = createOperationButton2("nPr");
        Button mod = createOperationButton2("mod");

        Button mAdd = mAddButton();
        Button mGet = mGetButton();
        Button mC = mClearButton();

        keypad.add(add, 5, 0);
        keypad.add(subtract, 5, 1);
        keypad.add(multiply, 5, 2);
        keypad.add(divide, 5, 3);
        keypad.add(mAdd, 6, 0);
        keypad.add(mGet, 6, 1);
        keypad.add(mC, 6, 2);
        keypad.add(power, 1, 0);
        keypad.add(square, 2, 0);
        keypad.add(sqrtRoot, 3, 0);
        keypad.add(fact, 0, 1);
        keypad.add(nCr, 0, 2);
        keypad.add(nPr, 0, 3);
        keypad.add(mod, 0, 4);

        vBox.getChildren().add(bp);
        vBox.getChildren().add(field);
        vBox.getChildren().add(keypad);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setScene(scene);
        stage.setTitle("JavaFX Calculator");
        stage.setWidth(500);
        stage.setHeight(550);
        stage.show();
    }

    class NumberButtonHandler implements EventHandler<ActionEvent> {
        private final int number;
        NumberButtonHandler(int number) {
            this.number = number;
        }
        @Override
        public void handle(ActionEvent event) {
            if (logic.undefined) {
                logic.reset();
                field.clear();
            }
            String text = field.getText();
            if (header.getText().contains("=")) {
                header.setText("");
                text = "";
            }
            if (text.equals("0")) {
                field.setText(Integer.toString(this.number));
            } else {
                field.setText(text + this.number);
            }
        }
    }

    public void addToMemory() {
        if (logic.undefined) {
            return;
        }
        if (field.getText().equals("")) {
            emptyStringError();
            return;
        }
        double x = Double.parseDouble(field.getText());
        logic.setMemory(x);
        field.clear();
        header.setText("added to memory!");
    }

    public void pasteFromMemory() {
        if (logic.undefined) {
            logic.reset();
        }
        double x = logic.getMemory();
        if (x % 1 == 0 && !logic.biggerThanInteger(1)) {
            field.setText(String.valueOf((int) x));
        } else {
            field.setText(String.valueOf(x));
        }
    }

    public void clearMemory() {
        logic.setMemory(0);
        header.setText("memory reset (default value 0)");
    }

    public Button mAddButton() {
        Button button = new Button("ADDM");
        button.setOnAction(actionEvent -> addToMemory());
        return button;
    }

    public Button mGetButton() {
        Button button = new Button("MGET");
        button.setOnAction(actionEvent -> pasteFromMemory());
        return button;
    }

    public Button mClearButton() {
        Button button = new Button("MC");
        button.setOnAction(actionEvent -> clearMemory());
        return button;
    }

    private void printCalculation1(int x) {
        if (x == 1) {
            switch (logic.getOperation()) {
                case "^2":
                    header.setText("sqr(" + (int)logic.getA() + ")");
                    break;
                case "sqrtRoot":
                    header.setText("sqrt(" + (int)logic.getA() + ")");
                    break;
                case "n!":
                    header.setText("fact(" + (int)logic.getA() + ")");
                    break;
            }
        } else if (x == 2) {
            switch (logic.getOperation()) {
                case "^2":
                    header.setText("sqr(" + logic.getA() + ")");
                    break;
                case "sqrtRoot":
                    header.setText("sqrt(" + logic.getA() + ")");
                    break;
                case "n!":
                    header.setText("fact(" + logic.getA() + ")");
                    break;
            }
        }
    }

    private void printCalculation2() {
//        when equals is pressed
        String operation = logic.getOperation();
        String text = header.getText();
        double a = logic.getA();
        double b = logic.getB();
        if(operation.equals("nCr") || operation.equals("nPr")) {
            String substring = text.substring(0, text.length() - 3);
            if (b % 1 == 0 && !logic.biggerThanInteger(1)) {
                header.setText(substring + (int)logic.getB() + ") =");
            } else {
                header.setText(substring + logic.getB() + ") =");
            }
            return;
        }
        if (b % 1 == 0 && !logic.biggerThanInteger(1)) {
            if (text.contains("=")) {
                if (a % 1 == 0 && !logic.biggerThanInteger(1)) {
                    header.setText((int)a + " " + operation + " " + (int)b + " =");
                } else {
                    header.setText(a + " " + operation + " " + (int)b + " =");
                }
            } else {
                header.setText(text + " " + (int)b + " =");
            }
        } else {
            if (text.contains("=")) {
                if (a % 1 == 0 && !logic.biggerThanInteger(1)) {
                    header.setText((int)a + " " + operation + " " + b + " =");
                } else {
                    header.setText(a + " " + operation + " " + b + " =");
                }
                header.setText(a + " " + operation + " " + b + " =");
            } else {
                header.setText(text + " " + b + " =");
            }
        }
    }

    private void print(double x, String op) {
        if(op.equals("nCr") || op.equals("nPr")) {
            if (x % 1 == 0 && !logic.biggerThanInteger(1)) {
                header.setText(op + "(" + (int) x + ", ?)");
            } else {
                header.setText(op + "(" + x + ", ?)");
            }
            return;
        }
        if (x % 1 == 0 && !logic.biggerThanInteger(1)) {
            header.setText((int) x + " " + op);
        } else {
            header.setText(x + " " + op);
        }
    }

    private void emptyStringError() {
        header.setText("Type a number!");
    }

    private Button createNumberButton(int number) {
        Button button = new Button(Integer.toString(number));
        button.setOnAction(new NumberButtonHandler(number));
        return button;
    }

    private Button createOperationButton1(String operation) {
//        for one operand
        Button button = new Button(operation);
        button.setOnAction(actionEvent -> {
            if (field.getText().equals("")) {
                emptyStringError();
                return;
            }
            if (!logic.isUndefined()) logic.setA(Double.parseDouble(field.getText()));
            logic.setOperation(operation);
            double result = logic.executeOperation();
            if (logic.isUndefined()) {
                header.setText("Undefined");
                field.clear();
                return;
            }
            if (logic.getA() % 1 == 0 && (logic.getA() > Integer.MIN_VALUE && logic.getA() < Integer.MAX_VALUE)) {
                printCalculation1(1);
            } else {
                printCalculation1(2);
            }
            if (result % 1 == 0 && (result > Integer.MIN_VALUE && result < Integer.MAX_VALUE)) {
                field.setText(String.valueOf((int)result));
                return;
            }
            field.setText(String.valueOf(result));
        });
        return button;
    }

    private Button createOperationButton2(String operation) {
//        for two operands
        Button button = new Button(operation);
        button.setOnAction(actionEvent -> {
            if (field.getText().equals("")) {
                emptyStringError();
                return;
            }
            double a = Double.parseDouble(field.getText());
            logic.setA(a);
            logic.setOperation(operation);
            print(a, operation);
            field.clear();
        });
        return button;
    }

    private GridPane createNumPad() {
        GridPane numPad = new GridPane();
        numPad.setAlignment(Pos.CENTER);
        numPad.setPadding(new Insets(10));
        numPad.setVgap(5);
        numPad.setHgap(10);
        numPad.add(createNumberButton(0), 2, 4);
        int row = 3;
        int column;
        for (int i = 1; i < 10; i++) {
            if (i > 3) row = 2;
            if (i > 6) row = 1;
            column = (i-1) % 3;
            numPad.add(createNumberButton(i), column+1, row);
        }
        return numPad;
    }
}