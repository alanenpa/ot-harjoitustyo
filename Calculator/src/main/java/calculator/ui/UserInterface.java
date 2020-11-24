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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {
    Operations logic = new Operations();
    Text text;
    TextField field;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(15);
        text = new Text();
        field = new TextField();
        field.setAlignment(Pos.BASELINE_RIGHT);

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
            if (logic.getOperation() == null) return;
            if (!logic.isUndefined()) logic.setB(Double.parseDouble(field.getText()));
            double result = logic.executeOperation();
            if (logic.isUndefined()) {
                field.setText("Undefined");
                return;
            }
            if (result % 1 == 0 && (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE)) {
                field.setText(String.valueOf((int)result));
                return;
            }
            field.setText(String.valueOf(result));
        });
        keypad.add(equals, 5, 4);

        Button clear = new Button("C");
        clear.setOnAction(actionEvent -> {
            logic.reset();
            field.clear();
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

        keypad.add(add, 5, 0);
        keypad.add(subtract, 5, 1);
        keypad.add(multiply, 5, 2);
        keypad.add(divide, 5, 3);
        keypad.add(power, 1, 0);
        keypad.add(square, 2, 0);
        keypad.add(sqrtRoot, 3, 0);
        keypad.add(fact, 0, 1);
        keypad.add(nCr, 0, 2);
        keypad.add(nPr, 0, 3);

        vBox.getChildren().add(text);
        vBox.getChildren().add(field);
        vBox.getChildren().add(keypad);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setScene(scene);
        stage.setTitle("JavaFX Calculator");
        stage.setWidth(350);
        stage.setHeight(460);
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
            if (text.equals("0")) {
                field.setText(Integer.toString(this.number));
            } else {
                field.setText(text + this.number);
            }
        }
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
            if (!logic.isUndefined()) logic.setA(Double.parseDouble(field.getText()));
            logic.setOperation(operation);
            double result = logic.executeOperation();
            if (logic.isUndefined()) {
                field.setText("Undefined");
                return;
            }
            if (result % 1 == 0 && (result < Integer.MAX_VALUE && result > Integer.MIN_VALUE)) {
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
            if (field.getText().equals("") || field.getText().equals("Type a number!")) {
                field.setText("Type a number!");
                return;
            }
            double a = Double.parseDouble(field.getText());
            logic.setA(a);
            logic.setOperation(operation);
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
