/*
 * Kevin Drake
 * 1/13/22
 * This Program Countdown from the Number entered. When it reaches Zero it begins to play a song from the internet.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountDownStopWatch extends Application {


	@Override
	public void start(Stage primaryStage) {
		TextField tf = new TextField();
		BorderPane paneForTextField = new BorderPane();
		paneForTextField.setPadding(new Insets(5, 5, 5, 5));
		paneForTextField.setStyle("-fx-border-color:green");
		paneForTextField.setCenter(tf);
		
		Media sound = new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3");
		MediaPlayer mpSound = new MediaPlayer(sound);
		Scene scene = new Scene(paneForTextField, 200, 200);
		primaryStage.setTitle("Assignment 16-21");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
			tf.setText(tf.getText());
			String s = tf.getText().toString();
			s.trim();
			Integer x = Integer.parseInt(s);
			x -= 1;
			s = x.toString();
			tf.setText("");
			tf.setText(s);
			
			if (x <= 0) {
				mpSound.play();
				if (x == -10) {
					System.exit(0);
				}
			}
		}));
		animation.setCycleCount(Timeline.INDEFINITE);
		tf.setOnAction(e -> {
			animation.play();
		});
	}
	public static void main(String[] args) {
		launch(args);
	}
}
