package ui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class Stopwatch{
	
	private static Stopwatch instance;
	private JLabel labelTime;
	private Timer timer;
	private int counter;
	private int timeInSeconds;
	private ActionListener taskPerformer = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			counter++;
			int seconds = counter % 60;
			int minutes = counter / 60;
			int hour = minutes / 60;
			minutes %= 60;
			setTimeInSeconds(counter);
			labelTime.setText(String.format("%02d:%02d:%02d", hour, minutes, seconds));
		}
	  };

	private Stopwatch() {
		this.timeInSeconds = 0;
		this.counter = 0;
		this.timer = new Timer(10, taskPerformer);
		this.labelTime = new JLabel("00:00:00");
	}
	
	public static Stopwatch getInstance() {
		if(instance == null) {
			instance = new Stopwatch();
		}
		return instance;
	}

	public Component initialize(JFrame frame) {
		
		JPanel panelStopWatch = new JPanel();
		panelStopWatch.setLayout(null);
		panelStopWatch.setBorder(new LineBorder(new Color(0, 0, 128)));
		panelStopWatch.setBounds(651, 11, 423, 69);
		frame.getContentPane().add(panelStopWatch);
		
		
		labelTime = new JLabel("00:00:00:00");
		labelTime.setFont(new Font("Tahoma", Font.PLAIN, 30));
		labelTime.setBounds(10, 11, 161, 48);
		
		JButton btnStart = new StrongButton("", "img\\icons\\icon-play.png", new Color(255, 255, 255), new Color(0, 0, 128), 181, 11, 70, 48);	
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().start();
			}
		});
		JButton btnPause = new StrongButton("", "img\\icons\\icon-pause.png", new Color(255, 255, 255), new Color(0, 0, 128), 260, 11, 70, 48);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().pause();
			}
		});
		StrongButton btnStop = new StrongButton("", "img\\icons\\icon-stop.png", Color.WHITE, new Color(0, 0, 128), 337, 11, 70, 48);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().stop();
			}
		});
		
		
		panelStopWatch.add(labelTime);
		panelStopWatch.add(btnStart);
		panelStopWatch.add(btnPause);
		panelStopWatch.add(btnStop);
		
		return panelStopWatch;
	
	}
	
	private void start() {
		timer.start();
	}
	
	private void pause() {
		timer.stop();
	}

	private void stop() {
		timer.stop();
	}

	public int getTimeInSeconds() {
		return timeInSeconds;
	}

	private void setTimeInSeconds(int timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
	
}
