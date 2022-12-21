package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class Stopwatch{
	
	private static Stopwatch instance;
	private JLabel labelTime;
	private JButton btnStart;
	private JButton btnPause;
	private JButton btnStop;
	private Timer timer;
	private int hundredthSeconds;
	private int seconds;
	private int minutes;
	private int hours;
	private int timeInSeconds;
	private ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			hundredthSeconds ++;
			if(hundredthSeconds == 100) {
				seconds ++;
				timeInSeconds ++;
				hundredthSeconds = 0;
			}else if(seconds == 60) {
				minutes ++;
				seconds = 0;
			}else if(minutes == 60) {
				hours ++;
				minutes = 0;
			}else if(hours == 24) {
				hours = 0;
			}
			labelTime.setText(String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, hundredthSeconds));
		}
	  };

	private Stopwatch() {
		this.hundredthSeconds = 0;
		this.seconds = 0;
		this.minutes = 0;
		this.hours = 0;
		this.timeInSeconds = 0;
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
		
		btnStart = new StrongButton(
				"", 
				"img\\icons\\icon-play.png", 
				new Color(255, 255, 255), 
				new Color(0, 0, 128), 
				183, 11, 70, 48
				);	
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().start();
			}
		});
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStart.setBackground(new Color(249, 13, 72));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStart.setBackground(new Color(0, 0, 128));
			}
		});
		btnPause = new StrongButton(
				"", 
				"img\\icons\\icon-pause.png", 
				new Color(255, 255, 255), 
				new Color(0, 0, 128), 
				260, 11, 70, 48
				);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().pause();
			}
		});
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPause.setBackground(new Color(249, 13, 72));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPause.setBackground(new Color(0, 0, 128));
			}
		});
		btnStop = new StrongButton(
				"", 
				"img\\icons\\icon-stop.png", 
				new Color(255, 255, 255),
				new Color(0, 0, 128), 
				337, 11, 70, 48);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch.getInstance().stop();
			}
		});
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStop.setBackground(new Color(249, 13, 72));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStop.setBackground(new Color(0, 0, 128));
			}
		});
		
		btnStop.setEnabled(false);
		btnStart.setEnabled(true);
		btnPause.setEnabled(false);
		
		panelStopWatch.add(labelTime);
		panelStopWatch.add(btnStart);
		panelStopWatch.add(btnPause);
		panelStopWatch.add(btnStop);
		
		return panelStopWatch;
	
	}
	
	private void start() {
		timer.start();
		btnStart.setEnabled(false);
		btnPause.setEnabled(true);
		btnStop.setEnabled(true);
	}
	
	private void pause() {
		timer.stop();
		btnPause.setEnabled(false);
		btnStart.setEnabled(true);
		btnStop.setEnabled(true);
	}

	public void stop() {
		if(timer.isRunning()) {
			timer.stop();
			btnStop.setEnabled(false);
			btnStart.setEnabled(true);
			btnPause.setEnabled(false);
			this.hundredthSeconds = 0;
			this.seconds = 0;
			this.minutes = 0;
			this.hours = 0;
			labelTime.setText("00:00:00:00");
		}
	}

	public int getTimeInSeconds() {
		return timeInSeconds;
	}

}
