package ui.components;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stopwatch extends Component{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelTime;
	private Timer timer;
	private int counter;
	private int timeInSeconds;

	private Stopwatch(JPanel panel) {
		this.timeInSeconds = 0;
		this.counter = 0;
		this.timer = new Timer();
		this.labelTime = new JLabel("00:00:00");
		this.initialize(panel);

	}
	
	public static Stopwatch getInstance(JPanel panel) {
		return new Stopwatch(panel);
	}

	private void initialize(JPanel panel) {
		this.labelTime.setFont(new Font(labelTime.getName(), Font.PLAIN, 40));
		panel.add(labelTime, BorderLayout.CENTER);
		start();
	}

	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				counter++;
				int seconds = counter % 60;
				int minutes = counter / 60;
				int hour = minutes / 60;
				minutes %= 60;
				setTimeInSeconds(counter);
				labelTime.setText(String.format("%02d:%02d:%02d", hour, minutes, seconds));
			}

		}, 1000, 1000);

	}

	public void stop() {
		timer.cancel();
	}

	public int getTimeInSeconds() {
		return timeInSeconds;
	}

	private void setTimeInSeconds(int timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
	
}
