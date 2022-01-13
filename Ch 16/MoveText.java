/*
 * Author Kevin Drake
 * Date 1/12/22
 * This Program displays a text object, allows you to move back and forth, and change its color.
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MoveText extends Application {
	@Override
	public void start(Stage primaryStage) {
		Text text = new Text(50, 35, "Programming is Fun!");
		text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));
		text.setStroke(Color.BLACK);
		text.setFill(Color.BLACK);		
		Pane pane = new Pane(text);
		
		HBox forRadioButtons = new HBox(20);
		RadioButton rbRed = new RadioButton("Red");
		RadioButton rbYellow = new RadioButton("Yellow");
		RadioButton rbBlack = new RadioButton("Black");
		RadioButton rbOrange = new RadioButton("Orange");
		RadioButton rbGreen = new RadioButton("Green");
		forRadioButtons.getChildren().addAll(rbRed, rbYellow, rbBlack, rbOrange, rbGreen);
		forRadioButtons.setAlignment(Pos.CENTER);
		
		HBox forMoveButtons = new HBox(20);
		Button btLeft = new Button("<=");
		Button btRight = new Button("=>");
		
		forMoveButtons.getChildren().addAll(btLeft, btRight);
		forMoveButtons.setAlignment(Pos.CENTER);
		BorderPane bp = new BorderPane();
		bp.setTop(forRadioButtons);
		bp.setCenter(pane);
		bp.setBottom(forMoveButtons);
		Scene scene = new Scene(bp, 400, 350);
		primaryStage.setTitle("Assignment 16-01");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btLeft.setOnAction(e -> {
			if (text.getX() <= 0) {
				text.setX(bp.getWidth() / 4);
				text.setY(bp.getHeight() / 4);
			}
			else {
				text.setX(text.getX() - 5);
			}
		});
		
		btRight.setOnAction(e -> {
			if (text.getX() > bp.getWidth() - 200) {
				text.setX(bp.getWidth() / 4);
				text.setY(bp.getHeight() / 4);
			}
			else {
				text.setX(text.getX() + 5);
			}
		});
		rbRed.setOnAction(e -> {
			if (rbRed.isSelected()) {
				text.setStroke(Color.RED);
			}
		});
		rbYellow.setOnAction(e -> {
			if (rbYellow.isSelected()) {
				text.setStroke(Color.YELLOW);
			}
		});
		rbBlack.setOnAction(e -> {
			if (rbBlack.isSelected()) {
				text.setStroke(Color.BLACK);
			}
		});
		rbOrange.setOnAction(e -> {
			if (rbOrange.isSelected()) {
				text.setStroke(Color.ORANGE);
			}
		});
		rbGreen.setOnAction(e -> {
			if (rbGreen.isSelected()) {
				text.setStroke(Color.GREEN);
			}
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}
