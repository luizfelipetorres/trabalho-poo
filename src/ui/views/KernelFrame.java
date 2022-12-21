package ui.views;

import static javax.swing.JOptionPane.YES_OPTION;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import interfaces.ClickListener;
import model.Player;
import ui.components.CustomButton;
import ui.components.Stopwatch;

public class KernelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnPlay;
	private JButton btnConfig;
	private JButton btnLogout;
	private JDesktopPane desktopPane;
	private File image;
	private JPanel panelPersona;
	private JPanel panelLeftMenu;

	public static void main(String[] args, Player player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KernelFrame window = new KernelFrame(player);
					window.setVisible(true);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KernelFrame(Player player) {
		initialize(player);
	}

	private void initialize(Player player) {
		this.getContentPane().setBackground(new Color(255, 255, 255));
		this.setBounds(100, 100, 1300, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setBackground(new Color(249, 13, 72));
				panelLeftMenu.setBounds(0, 0, 200, 711);
				panelPersona.setVisible(true);
				btnPlay.setLocation(0, 300);
				btnConfig.setLocation(0, 360);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setBackground(new Color(0, 0, 128));
				panelLeftMenu.setBounds(0, 0, 70, 711);
				panelPersona.setVisible(false);
				btnPlay.setLocation(0, 63);
				btnConfig.setLocation(0, 122);
			}
		};

		panelPersona = new JPanel();
		panelPersona.setBackground(new Color(45, 45, 45));
		panelPersona.setBounds(10, 63, 180, 217);
		panelPersona.setLayout(null);
		panelPersona.setVisible(false);

		JLabel lbPhotoPersona = new JLabel("");
		lbPhotoPersona.setHorizontalAlignment(SwingConstants.CENTER);
		lbPhotoPersona.setIcon(new ImageIcon("img\\icons\\icon-persona.png"));
		lbPhotoPersona.setBounds(10, 11, 160, 110);
		panelPersona.add(lbPhotoPersona);

		JLabel lbUsername = new JLabel(player.getPlayerUsername());
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbUsername.setForeground(new Color(255, 255, 255));
		lbUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lbUsername.setBounds(10, 132, 160, 30);
		panelPersona.add(lbUsername);

		JLabel lbEmail = new JLabel(player.getPlayerEmail());
		lbEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbEmail.setForeground(new Color(255, 255, 255));
		lbEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lbEmail.setBounds(10, 173, 160, 30);
		panelPersona.add(lbEmail);

		btnPlay = new CustomButton("", "img\\icons\\icon-control.png", new Color(255, 255, 255), new Color(0, 0, 128),
				0, 63, 200, 48);
		btnConfig = new CustomButton("", "img\\icons\\icon-config.png", new Color(255, 255, 255), new Color(0, 0, 128),
				0, 122, 200, 48);
		btnLogout = new CustomButton("", "img\\icons\\icon-logout.png", new Color(255, 255, 255), new Color(0, 0, 128),
				0, 200, 200, 48);

		panelLeftMenu = new JPanel();
		panelLeftMenu.setBounds(0, 0, 70, 711);
		panelLeftMenu.setBackground(new Color(60, 60, 60));
		panelLeftMenu.setLayout(null);
		this.getContentPane().add(panelLeftMenu);
		panelLeftMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelLeftMenu.setBounds(0, 0, 200, 711);
				panelPersona.setVisible(true);
				btnPlay.setLocation(0, 300);
				btnConfig.setLocation(0, 360);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelLeftMenu.setBounds(0, 0, 70, 711);
				panelPersona.setVisible(false);
				btnPlay.setLocation(0, 63);
				btnConfig.setLocation(0, 122);
			}
		});

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBounds(200, 60, 1084, 651);
		this.getContentPane().add(desktopPane);

		btnPlay.setIcon(new ImageIcon("img\\icons\\icon-control.png"));
		btnPlay.setText("JOGAR ");

		btnPlay.addMouseListener(mouseAdapter);

		ClickListener listener = (image, size) -> {
			showFrame(new PuzzleFrame(player, image, size));
		};
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Stopwatch watch = Stopwatch.getInstance();
				if (watch.getDuration() > 0) {
					watch.pause();
					String message = "Quer desistir da partida atual e iniciar uma nova?";
					int newGame = JOptionPane.showConfirmDialog(btnPlay, message);
					if (newGame != YES_OPTION)
						return;
				}
				showFrame(new PreGameFrame(player, listener));
			}
		});

		btnConfig.setIcon(new ImageIcon("img\\icons\\icon-config.png"));
		btnConfig.setText("AJUSTE");
		btnConfig.addMouseListener(mouseAdapter);
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFrame(new PlayerFrame());
			}
		});

		btnLogout.setLocation(0, 650);
		btnLogout.setIcon(new ImageIcon("img\\icons\\icon-logout.png"));
		btnLogout.setText("SAIR    ");
		btnLogout.addMouseListener(mouseAdapter);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* implements method in next issue */
			}
		});

		JLabel lbIconMenu = new JLabel("");
		lbIconMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconMenu.setIcon(new ImageIcon("img\\icons\\icon-menu.png"));
		lbIconMenu.setBounds(10, 11, 50, 40);

		panelLeftMenu.add(lbIconMenu);
		panelLeftMenu.add(panelPersona);
		panelLeftMenu.add(btnPlay);
		panelLeftMenu.add(btnConfig);
		panelLeftMenu.add(btnLogout);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(69, 0, 1215, 55);
		panelHeader.setBackground(new Color(45, 45, 45));
		panelHeader.setLayout(null);

		JLabel lbTitle = new JLabel("█▓▒­░⡷⠂ S̳L̳I̳D̳E̳R̳ ̳P̳U̳Z̳Z̳L̳E̳ ⠐⢾░▒▓█");
		lbTitle.setForeground(new Color(240, 240, 240));
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbTitle.setBackground(new Color(240, 240, 240));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 11, 1195, 33);
		panelHeader.add(lbTitle);

		showFrame(new PreGameFrame(player, listener));
		this.getContentPane().add(panelHeader);
	}

	private void showFrame(JPanel frame) {
		desktopPane.removeAll();
		desktopPane.add(frame);
		frame.setVisible(true);
	}
}
