import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
public class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;
	private boolean hourHandVisible;
	private boolean minuteHandVisible;
	private boolean secondHandVisible;
	
	/** Construct a default clock with the Current time */
	public ClockPane() {
		setCurrentTime();
	}
	/** Constructor with specified Hour, Minute, Second */
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}	
	/** Return Hour */
	public int getHour() {
		return hour;
	}
	/** Set Hour */
	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}
	/** Return Minute */
	public int getMinute() {
		return minute;
	}
	/** Set Minute */
	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}
	/** Return Second */
	public int getSecond() {
		return second;
	}
	/** Set Second */
	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}
	public boolean isHourHandVisible() {
		return hourHandVisible;
	}
	public void setHourHandVisible(boolean visible) {
		this.hourHandVisible = visible;
		paintClock();
	}
	public boolean isMinuteHandVisible() {
		return minuteHandVisible;
	}
	public void setMinuteHandVisible(boolean visible) {
		this.minuteHandVisible = visible;
		paintClock();
	}
	public boolean isSecondHandVisible() {
		return secondHandVisible;
	}
	public void setSecondHandVisible(boolean visible) {
		this.secondHandVisible = visible;
		paintClock();
	}

	/** Set the current time for the clock */
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();
		
		// Set the current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		paintClock(); // Repaint the clock
	}
	
		/** Paint the Clock */
	public void paintClock() {
		// Initialize clock parameters
		double clockRadius = 
				Math.min(getWidth() / 2, getHeight()) * 0.8 * 0.5;
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		
		// Draw a Circle
		Circle circle = new Circle (centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
		Text t4 = new Text(centerX - 3, centerY + clockRadius - 3, "6");
		
		// Draw the Second Hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength *
				Math.sin(second * (2 * Math.PI / 60));
		double secondY = centerY - sLength * 
				Math.cos(second * (2 * Math.PI / 60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		if (secondHandVisible) {
		sLine.setStroke(Color.RED);
		}
		else {
			sLine.setStroke(Color.WHITE);
		}
		
		// Draw the Minute Hand
		double mLength = clockRadius * 0.65;
		double minuteX = centerX + mLength *
				Math.sin(minute * (2 * Math.PI / 60));
		double minuteY = centerY - mLength * 
				Math.cos(minute * (2 * Math.PI / 60));
		Line mLine = new Line(centerX, centerY, minuteX, minuteY);
		if (minuteHandVisible) {
		mLine.setStroke(Color.BLUE);
		}
		else {
			mLine.setStroke(Color.WHITE);
		}
		// Draw the Hour Hand
		
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength * 
				Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		double hourY = centerY - hLength * 
				Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		if (hourHandVisible) {
		hLine.setStroke(Color.GREEN);
		}
		else {
			hLine.setStroke(Color.WHITE);
		}
		getChildren().clear();
		getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
		
	}
	
	@Override 
	public void setWidth(double width) {
		super.setWidth(width);
		paintClock();
	}
	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paintClock();
	}
	public static void main(String[] args) {
	}
}