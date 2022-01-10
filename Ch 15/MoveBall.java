/*
 * Author Kevin Drake
 * Date 1/10/22 
 * This class sets up the controls to move the ball
 */
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MoveBall extends Application {
	@Override
	public void start(Stage primaryStage) {
		BallPane ball = new BallPane();
		HBox hBox = new HBox(11);
		BorderPane bp = new BorderPane();
		Button btLeft = new Button("Left"), btRight = new Button("Right"),
		btUp = new Button("Up"), btDown = new Button("Down");
		hBox.getChildren().addAll(btLeft, btRight, btUp, btDown);
		bp.setCenter(ball);
		bp.setBottom(hBox);
		btLeft.setOnMousePressed(e -> ball.moveLeft());
		btRight.setOnMousePressed(e -> ball.moveRight());
		btUp.setOnMousePressed(e -> ball.moveUp());
		btDown.setOnMousePressed(e -> ball.moveDown());
		Scene scene = new Scene(bp, 200, 200);
		primaryStage.setTitle("Move Ball");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}