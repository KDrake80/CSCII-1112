/*
 * Author Kevin Drake
 * Date 1/5/22
 * This Program displays a clock with random hour and minute hands, second hand is hidden.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RandomTime extends Application {
	@Override
	public void start(Stage primaryStage) {
		int h = (int)(Math.random() * 2);
		System.out.println(h);
		ClockPane clock = new ClockPane(0, 0, 0);
		clock.setHourHandVisible(true);
		clock.setMinuteHandVisible(true);
		clock.setSecondHandVisible(false);
		clock.setHour((int)(Math.random() * 12));
		if (h == 0) 
			clock.setMinute(0);
		
		else 
			clock.setMinute(30);
		
		Scene scene = new Scene(clock, 600, 600);
		primaryStage.setTitle("RandomTime");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}