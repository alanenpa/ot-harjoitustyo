package application.ui;

import application.logic.Operations;
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
        comma.setPrefSize(50,65);
        comma.setOnAction(actionEvent -> {
            String text = field.getText();
            if (!text.contains(",")) field.setText(field.getText() + ",");
        });
        keypad.add(comma,2, 4);

        Button neg = new Button("+/-");
        neg.setPrefSize(50,65);
        neg.setOnAction(actionEvent -> {
            String text = field.getText();
            if (!text.contains("-")) {
                field.setText("-" + field.getText());
            } else {
                field.setText(text.substring(1));
            }
        });
        keypad.add(neg,0, 4);

        Button equals = new Button("=");
        equals.setPrefSize(50,65);
        equals.setOnAction(actionEvent -> {
            logic.setB(Integer.parseInt(field.getText()));
            int result = logic.executeOperation();
            field.setText(String.valueOf(result));
        });
        keypad.add(equals, 4, 4);

        Button clear = new Button("C");
        clear.setPrefSize(50,65);
        clear.setOnAction(actionEvent -> {
            logic.reset();
            field.clear();
        });
        keypad.add(clear, 0, 0);

        Button add = createBasicArithmeticOperationButton("+");
        Button subtract = createBasicArithmeticOperationButton("-");
        Button multiply = createBasicArithmeticOperationButton("*");
        Button divide = createBasicArithmeticOperationButton("/");
        keypad.add(add, 4, 0);
        keypad.add(subtract, 4, 1);
        keypad.add(multiply, 4, 2);
        keypad.add(divide, 4, 3);

        vBox.getChildren().add(text);
        vBox.getChildren().add(field);
        vBox.getChildren().add(keypad);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("Stylesheet.css");
        stage.setScene(scene);
        stage.setTitle("JavaFX Calculator");
        stage.setWidth(300);
        stage.setHeight(450);
        stage.show();
    }

    class NumberButtonHandler implements EventHandler<ActionEvent> {
        private final int number;
        NumberButtonHandler(int number) {
            this.number = number;
        }
        @Override
        public void handle(ActionEvent event) {
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
//        button.setPadding(new Insets(20));
        button.setPrefSize(50,65);
        button.setOnAction(new NumberButtonHandler(number));
        return button;
    }

    private Button createBasicArithmeticOperationButton(String operation) {
        Button button = new Button(operation);
        button.setPrefSize(50,65);
        button.setOnAction((actionEvent -> {
            if (field.getText().equals("") || field.getText().equals("Anna jokin luku!")) {
                field.setText("Anna jokin luku!");
                return;
            }
            int a = Integer.parseInt(field.getText());
            logic.setA(a);
            logic.setOperation(operation);
            field.clear();
        }));
        return button;
    }

    private void placeOperationButtons(GridPane pane) {
        Button plus = createBasicArithmeticOperationButton("+");
        Button button2 = createBasicArithmeticOperationButton("-");
        Button button3 = createBasicArithmeticOperationButton("*");
        Button button4 = createBasicArithmeticOperationButton("/");
        pane.add(plus,4,0);
    }

    private GridPane createNumPad() {
        GridPane numPad = new GridPane();
        numPad.setAlignment(Pos.CENTER);
        numPad.setPadding(new Insets(10));
        numPad.setVgap(5);
        numPad.setHgap(10);
        numPad.add(createNumberButton(0), 1, 4);
        int row = 3;
        int column;
        for (int i = 1; i < 10; i++) {
            if (i > 3) row = 2;
            if (i > 6) row = 1;
            column = (i-1) % 3;
            numPad.add(createNumberButton(i), column, row);
        }
        return numPad;
    }
}
