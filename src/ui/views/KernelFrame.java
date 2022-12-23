package ui.views;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.Player;
import ui.components.PuzzleBoard;
import ui.components.Stopwatch;

import java.awt.Font;

public class KernelFrame extends AbstractWindow{

	private JFrame frame;
	private Player player;
	
	public static void main(String[] args, Player player) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KernelFrame window = new KernelFrame(player);
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private KernelFrame(Player player) {
		this.player = player;
		setTheme();
		initialize();
	}

	@Override
	protected void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1100, 734);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuOptions = new JMenu("Opções");
		menuBar.add(menuOptions);
		
		JMenuItem menuItemMyProfile = new JMenuItem("Meu perfil");
		menuOptions.add(menuItemMyProfile);
		
		JMenuItem menuItemRankingVerify = new JMenuItem("Verificar ranking");
		menuOptions.add(menuItemRankingVerify);
		
		JMenuItem menuItemQuit = new JMenuItem("Sair");
		menuOptions.add(menuItemQuit);
		frame.getContentPane().setLayout(null);
		
		JLabel lbBgmain = new JLabel("");
		lbBgmain.setIcon(new ImageIcon("img\\bg-main.jpg"));
		lbBgmain.setBounds(651, 91, 433, 582);
		frame.getContentPane().add(lbBgmain);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBorder(new LineBorder(new Color(0, 0, 128)));
		panelInformation.setBounds(10, 11, 631, 69);
		frame.getContentPane().add(panelInformation);
		panelInformation.setLayout(null);
		
		JLabel lbUsername = new JLabel("Nome do usuário: ");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setBounds(10, 11, 300, 47);
		lbUsername.setText(lbUsername.getText() + player.getPlayerUsername());
		panelInformation.add(lbUsername);
		
		JLabel lblEmailDoUsurio = new JLabel("E-mail do usuário: ");
		lblEmailDoUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailDoUsurio.setBounds(321, 11, 300, 47);
		lblEmailDoUsurio.setText(lblEmailDoUsurio.getText() + player.getPlayerEmail());
		panelInformation.add(lblEmailDoUsurio);
		
		
		try {
			PuzzleBoard.getInstance().initialize(frame, 2);
			Stopwatch.getInstance().initialize(frame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
