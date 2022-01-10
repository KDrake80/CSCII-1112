/*
 * Author Kevin Drake
 * Date 1/6/22
 * This Class defines a Ball pane to keep the ball in
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BallPane extends Pane {
	private final double radius = 20;
	private double x = radius, y = radius;
	private double bx = 1, by = 1; 
	private Circle circle = new Circle(x, y, radius);	
	private Timeline a;

	public BallPane() {
		circle.setFill(Color.GREEN);	
		getChildren().add(circle);
		circle.setLayoutX(100);
		circle.setLayoutY(100);
	}
	public void moveLeft() {
		if (circle.getLayoutX() < 0) {
			circle.setLayoutX(100);
		}
		circle.setLayoutX(circle.getLayoutX() - 5);
	}
	public void moveRight() {
		if (circle.getLayoutX() > 175) {
			circle.setLayoutX(100);
		}
		circle.setLayoutX(circle.getLayoutX() + 5);
	}
	public void moveUp() {
		if (circle.getLayoutY() < 0) {
			circle.setLayoutY(100);
		}
		circle.setLayoutY(circle.getLayoutY() - 5);
	}
	public void moveDown() {
		if (circle.getLayoutY() > 175) {
			circle.setLayoutY(100);
		}
		circle.setLayoutY(circle.getLayoutY() + 5);
	}
}
	
