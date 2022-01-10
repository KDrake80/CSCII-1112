/*
 * Author Kevin Drake
 * Date 1/10/22
 * This Displays a rectangle that fades out as well as it following the path of a polygon
 */
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RectangleOnPentagon extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		// Create a polygon and place a polygon to pane
		Polygon polygon = new Polygon();
		polygon.setFill(Color.WHITE);
		polygon.setStroke(Color.BLACK);
		ObservableList<Double> list = polygon.getPoints();
		
		double centerX = 100, centerY = 100;
		double radius = 80;
		//Add points to polygon list
		for (int i = 0; i < 6; i++) {
		list.add(centerX + radius * Math.cos(2 * i * Math.PI / 5));
		list.add(centerY - radius * Math.sin(2 * i * Math.PI / 5));
		}
		pane.getChildren().clear();
		pane.getChildren().add(polygon);
		polygon.setRotate(-19);
		
		Rectangle rectangle = new Rectangle(0, 0, 25, 50);
		rectangle.setFill(Color.RED);
		pane.getChildren().add(rectangle);
		
		
		
		PathTransition pt = new PathTransition(Duration.millis(5000), polygon, rectangle);
		pt.setOrientation(
				PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setCycleCount(Timeline.INDEFINITE);
		pt.setAutoReverse(true);
		pt.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(5000), rectangle);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(Timeline.INDEFINITE);
		ft.setAutoReverse(true);
		ft.play();
		
		pane.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				pt.play();
				ft.play();
			}
			else if (e.getButton() == MouseButton.SECONDARY) {
				pt.pause();
				ft.pause();
			}
		});
		Scene scene = new Scene(pane, 200, 200);
		primaryStage.setTitle("Path Transition");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);

	}

}
