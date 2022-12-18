package ui.views;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class LoadingPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JProgressBar progressBar;
	JLabel porcentagem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingPage frame = new LoadingPage();
					frame.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoadingPage() throws IOException {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("windowText"));
		panel.setBounds(0, 0, 667, 413);
		contentPane.add(panel);
		panel.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(0, 0, 0));
		progressBar.setForeground(new Color(255, 140, 0));
		progressBar.setBounds(10, 251, 647, 14);
		panel.add(progressBar);
		
		JLabel img = new JLabel("New label");
		img.setBounds(250, 35, 159, 188);
		ImageIcon icon = new ImageIcon("img\\logo.gif");
		img.setIcon(icon);
		panel.add(img);
		
		progressBar.setBounds(10, 251, 647, 14);
		panel.add(progressBar);
		
		porcentagem = new JLabel("New label");
		porcentagem.setForeground(new Color(245, 255, 250));
		porcentagem.setBounds(298, 276, 46, 14);
		panel.add(porcentagem);
	}

}
