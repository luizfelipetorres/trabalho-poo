package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import ui.views.PuzzleFrame;

public class Stopwatch {

	private static Stopwatch instance;
	private JLabel labelTime;
	private JButton btnStart;
	private JButton btnPause;
	private JButton btnStop;
	private Timer timer;
	private boolean isRunning;
	private boolean isVisible;
	private long initialTime;
	private long duration;
	private ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			long currentTime = new Date().getTime();
			duration = currentTime - initialTime;
			labelTime.setText(String.format("%02d:%02d:%02d:%02d", getHours(), getMinutes(), getSeconds(),
					getHundredthSeconds()));
		}

	};

	private ActionListener onPause = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			labelTime.setVisible(isVisible = !isVisible);
		}
	};

	private Stopwatch() {
		this.isRunning = false;
		this.isVisible = true;
		this.duration = 0;
		this.timer = new Timer(10, null);
		this.labelTime = new JLabel("00:00:00");
	}

	public static Stopwatch getInstance() {
		if (instance == null) {
			instance = new Stopwatch();
		}
		return instance;
	}

	private long getHundredthSeconds() {
		return duration / 10 % 100;
	}

	private long getSeconds() {
		return duration / 1000 % 60;
	}

	private long getMinutes() {
		return duration / 1000 / 60 % 60;
	}

	private long getHours() {
		return duration / 1000 / 60 / 60 % 24;
	}

	public long getTimeInSeconds() {
		return duration / 1000;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public Component initialize(PuzzleFrame puzzleInternalFrame) {

		JPanel panelStopWatch = new JPanel();
		panelStopWatch.setLayout(null);
		panelStopWatch.setBorder(new LineBorder(new Color(0, 0, 128)));
		panelStopWatch.setBounds(10, 0, 630, 70);
		puzzleInternalFrame.add(panelStopWatch);

		labelTime = new JLabel("00:00:00:00");
		labelTime.setFont(new Font("Tahoma", Font.PLAIN, 30));
		labelTime.setBounds(10, 10, 200, 50);

		btnStart = new CustomButton("", "img\\icons\\icon-play.png", new Color(255, 255, 255), new Color(0, 0, 128),
				390, 10, 70, 50);
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
		btnPause = new CustomButton("", "img\\icons\\icon-pause.png", new Color(255, 255, 255), new Color(0, 0, 128),
				470, 10, 70, 50);
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
		btnStop = new CustomButton("", "img\\icons\\icon-stop.png", new Color(255, 255, 255), new Color(0, 0, 128), 550,
				10, 70, 50);
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

		Arrays.asList(labelTime, btnStart, btnPause, btnStop).forEach(panelStopWatch::add);
		return panelStopWatch;
	}

	private void start() {
		switchTimer();
		long currentTime = new Date().getTime();
		initialTime = currentTime - duration;
		if (!timer.isRunning())
			timer.start();

		btnStart.setEnabled(false);
		btnPause.setEnabled(true);
		btnStop.setEnabled(true);
	}

	private void pause() {
		switchTimer();
		btnPause.setEnabled(false);
		btnStart.setEnabled(true);
		btnStop.setEnabled(true);
	}

	public void stop() {
		if (timer.isRunning()) {
			timer.stop();
			Arrays.asList(onPause, taskPerformer).forEach(timer::removeActionListener);
			this.duration = 0;
			isRunning = false;
			btnStop.setEnabled(false);
			btnStart.setEnabled(true);
			btnPause.setEnabled(false);
			labelTime.setText("00:00:00:00");
			labelTime.setVisible(true);
		}
	}

	public void switchTimer() {
		isRunning = !isRunning;
		timer.stop();
		if (isRunning) {
			timer.addActionListener(taskPerformer);
			timer.removeActionListener(onPause);
			timer.setDelay(10);
			labelTime.setVisible(true);
		} else {
			timer.removeActionListener(taskPerformer);
			timer.addActionListener(onPause);
			timer.setDelay(500);
		}
		timer.start();
	}
}
