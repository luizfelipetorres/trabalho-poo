package ui.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ui.components.JlabelRound;
import java.awt.Window.Type;
import java.awt.Toolkit;

public class MenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icons\\icon-defaultToolkit.png"));
		setResizable(false);
        
		ImageIcon imgNavBar = new ImageIcon("img\\icons\\icon-menuBar.png");
		ImageIcon imgPerfil = new ImageIcon("img\\icons\\icon-homem.png");
		ImageIcon imgBackgroundPhoto = new ImageIcon("img\\bg-main.jpg");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panelMain = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 588);
		panelMain.setBounds(contentPane.getBounds());
		contentPane.add(panelMain);
		
		JPanel minimizedMenu = new JPanel();
		minimizedMenu.setBackground(Color.DARK_GRAY);
		minimizedMenu.setBounds(0, 0, 54, 549);
		minimizedMenu.setLayout(null);
		minimizedMenu.setVisible(false);
		contentPane.add(minimizedMenu);
		
		JLabel navBarMinimized = new JLabel("New label");
		navBarMinimized.setHorizontalAlignment(SwingConstants.TRAILING);
		navBarMinimized.setBounds(0, 0, 54, 45);
		imgNavBar.setImage(imgNavBar.getImage().getScaledInstance(navBarMinimized.getWidth(), navBarMinimized.getHeight(), 100));
		navBarMinimized.setIcon(imgNavBar);
		minimizedMenu.add(navBarMinimized);
		
		JLabel createMatchMinimized = new JLabel("New label");
		createMatchMinimized.setHorizontalAlignment(SwingConstants.TRAILING);
		createMatchMinimized.setBounds(0, 61, 54, 45);
		minimizedMenu.add(createMatchMinimized);
		
		JLabel fetchMatchMinimized = new JLabel("New label");
		fetchMatchMinimized.setHorizontalAlignment(SwingConstants.TRAILING);
		fetchMatchMinimized.setBounds(0, 106, 54, 45);
		minimizedMenu.add(fetchMatchMinimized);
		
		JLabel rankingMinimized = new JLabel("New label");
		rankingMinimized.setHorizontalAlignment(SwingConstants.TRAILING);
		rankingMinimized.setBounds(0, 152, 54, 45);
		minimizedMenu.add(rankingMinimized);
		
		JLabel logoutMinimized = new JLabel("New label");
		logoutMinimized.setHorizontalAlignment(SwingConstants.TRAILING);
		logoutMinimized.setBounds(0, 492, 54, 45);
		minimizedMenu.add(logoutMinimized);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.DARK_GRAY);
		panelMenu.setBounds(0, 0, 191, 549);
		panelMenu.setLayout(null);
		contentPane.add(panelMenu);
		
		JLabel navBar = new JLabel("New label");
		navBar.setBounds(146, 11, 36, 36);
		imgNavBar.setImage(imgNavBar.getImage().getScaledInstance(navBar.getWidth(), navBar.getHeight(), 100));
		navBar.setIcon(imgNavBar);
		panelMenu.add(navBar);
		
		JlabelRound perfil = new JlabelRound();
		perfil.setHorizontalAlignment(SwingConstants.CENTER);
		perfil.setBounds(55, 54, 80, 75);
		imgPerfil.setImage(imgPerfil.getImage().getScaledInstance(perfil.getWidth(), perfil.getHeight(), 100));
		perfil.setIcon(imgPerfil);
		panelMenu.add(perfil);
		
		JLabel username = new JLabel("usuario");
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Arial Black", Font.PLAIN, 12));
		username.setForeground(new Color(255, 255, 255));
		username.setBounds(0, 140, 191, 18);
		panelMenu.add(username);
		
		JPanel createMatch = new JPanel();
		createMatch.setBackground(Color.DARK_GRAY);
		createMatch.setBounds(0, 210, 191, 46);
		panelMenu.add(createMatch);
		createMatch.setLayout(null);
		
		JLabel iconCreateMatch = new JLabel("New label");
		iconCreateMatch.setHorizontalAlignment(SwingConstants.CENTER);
		iconCreateMatch.setBounds(10, 0, 46, 45);
		createMatch.add(iconCreateMatch);
		
		JLabel textCreateMatch = new JLabel("Criar partida");
		textCreateMatch.setHorizontalAlignment(SwingConstants.CENTER);
		textCreateMatch.setFont(new Font("Arial Black", Font.PLAIN, 11));
		textCreateMatch.setForeground(Color.WHITE);
		textCreateMatch.setBounds(57, 13, 121, 20);
		createMatch.add(textCreateMatch);
		
		JPanel FetchMatch = new JPanel();
		FetchMatch.setLayout(null);
		FetchMatch.setBackground(Color.DARK_GRAY);
		FetchMatch.setBounds(0, 256, 191, 46);
		panelMenu.add(FetchMatch);
		
		JLabel iconFetchMatch = new JLabel("New label");
		iconFetchMatch.setHorizontalAlignment(SwingConstants.CENTER);
		iconFetchMatch.setBounds(10, 0, 46, 45);
		FetchMatch.add(iconFetchMatch);
		
		JLabel textFetchMatch = new JLabel("Buscar partida");
		textFetchMatch.setHorizontalAlignment(SwingConstants.CENTER);
		textFetchMatch.setForeground(Color.WHITE);
		textFetchMatch.setFont(new Font("Arial Black", Font.PLAIN, 11));
		textFetchMatch.setBounds(57, 13, 121, 20);
		FetchMatch.add(textFetchMatch);
		
		JPanel Ranking = new JPanel();
		Ranking.setLayout(null);
		Ranking.setBackground(Color.DARK_GRAY);
		Ranking.setBounds(0, 301, 191, 46);
		panelMenu.add(Ranking);
		
		JLabel iconRanking = new JLabel("New label");
		iconRanking.setHorizontalAlignment(SwingConstants.CENTER);
		iconRanking.setBounds(10, 0, 46, 45);
		Ranking.add(iconRanking);
		
		JLabel textRanking = new JLabel("Ranking");
		textRanking.setHorizontalAlignment(SwingConstants.CENTER);
		textRanking.setForeground(Color.WHITE);
		textRanking.setFont(new Font("Arial Black", Font.PLAIN, 11));
		textRanking.setBounds(57, 13, 121, 20);
		Ranking.add(textRanking);
		
		JPanel logout = new JPanel();
		logout.setLayout(null);
		logout.setBackground(Color.DARK_GRAY);
		logout.setBounds(0, 491, 191, 46);
		panelMenu.add(logout);
		
		JLabel iconLogout = new JLabel("New label");
		iconLogout.setHorizontalAlignment(SwingConstants.CENTER);
		iconLogout.setBounds(10, 0, 46, 45);
		logout.add(iconLogout);
		
		JLabel txtLogout = new JLabel("Logout");
		txtLogout.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogout.setForeground(Color.WHITE);
		txtLogout.setFont(new Font("Arial Black", Font.PLAIN, 11));
		txtLogout.setBounds(57, 13, 121, 20);
		logout.add(txtLogout);
		
		JPanel main = new JPanel();
		main.setBounds(192, 0, 681, 548);
		contentPane.add(main);
		
		JLabel backgroundPhoto = new JLabel("New label");
		backgroundPhoto.setIcon(imgBackgroundPhoto);
		main.add(backgroundPhoto);	
		
		
		navBarMinimized.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelMenu.setVisible(true);
				main.setBounds(190,0,683,548);
				minimizedMenu.setVisible(false);
			}
		});

		navBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelMenu.setVisible(false);
				main.setBounds(53,0,820,548);
				minimizedMenu.setVisible(true);
				
			}
		});
		
		
	}
}