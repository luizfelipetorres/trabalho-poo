package ui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;

import dao.PlayerDAO;
import model.Player;
import ui.components.StrongButton;

import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame {

	private JFrame frame;
	private JTextField fieldRegisterUsername;
	private JPasswordField fieldRegisterPassword;
	private JTextField fieldLoginUsername;
	private JPasswordField fieldLoginPassword;
	private JTextField fieldRegisterEmail;
	private StrongButton btnLogin;
	private StrongButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(1000, 400, 850, 606);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(504, 22, 320, 545);
		tabbedPane.setOpaque(false);
		frame.getContentPane().add(tabbedPane);

		JPanel containerLogin = new JPanel();
		containerLogin.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Entrar", null, containerLogin, null);
		containerLogin.setLayout(null);

		JLabel lbLoginUsername = new JLabel("Nome do usuário:");
		lbLoginUsername.setBounds(20, 31, 270, 30);
		containerLogin.add(lbLoginUsername);
		lbLoginUsername.setForeground(new Color(69, 69, 69));
		lbLoginUsername.setFont(new Font("Tahoma", Font.BOLD, 15));

		fieldLoginUsername = new JTextField();
		fieldLoginUsername.setBounds(20, 72, 270, 40);
		containerLogin.add(fieldLoginUsername);
		fieldLoginUsername.setHorizontalAlignment(SwingConstants.CENTER);
		fieldLoginUsername.setForeground(new Color(0, 0, 0));
		fieldLoginUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldLoginUsername.setColumns(10);
		fieldLoginUsername.setBorder(null);
		fieldLoginUsername.setBackground(new Color(255, 255, 255));

		JSeparator separatorLoginUsername = new JSeparator();
		separatorLoginUsername.setBounds(21, 115, 270, 2);
		containerLogin.add(separatorLoginUsername);
		separatorLoginUsername.setForeground(new Color(0, 0, 128));
		separatorLoginUsername.setBackground(new Color(0, 0, 128));

		JLabel lbLoginPassword = new JLabel("Senha:");
		lbLoginPassword.setBounds(20, 140, 270, 30);
		containerLogin.add(lbLoginPassword);
		lbLoginPassword.setForeground(new Color(69, 69, 69));
		lbLoginPassword.setFont(new Font("Tahoma", Font.BOLD, 15));

		fieldLoginPassword = new JPasswordField();
		fieldLoginPassword.setBounds(21, 184, 270, 40);
		containerLogin.add(fieldLoginPassword);
		fieldLoginPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fieldLoginPassword.setForeground(new Color(0, 0, 0));
		fieldLoginPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldLoginPassword.setBorder(null);
		fieldLoginPassword.setBackground(new Color(255, 255, 255));

		JSeparator separatorLoginPassword = new JSeparator();
		separatorLoginPassword.setBounds(21, 226, 270, 2);
		containerLogin.add(separatorLoginPassword);
		separatorLoginPassword.setForeground(new Color(0, 0, 128));
		separatorLoginPassword.setBackground(new Color(0, 0, 128));

		btnLogin = new StrongButton("Entrar", new Color(255, 255, 255), new Color(0, 0, 128), 90, 279, 270, 50);
		btnLogin.setBounds(20, 279, 270, 50);
		containerLogin.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player player = new Player(fieldLoginUsername.getText(),
						Arrays.toString(fieldLoginPassword.getPassword()));

				PlayerDAO playerDAO = PlayerDAO.getInstance();
				Player playerSelected = playerDAO.authenticate(player);
				if (playerSelected != null) {

					KernelFrame.main(null, playerSelected);

				} else {
					JOptionPane.showMessageDialog(btnLogin,
							"Dados inválidos, por favor insira as credenciais novamente!");
				}

			}
		});
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(249, 13, 72));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 0, 128));
			}
		});
		
		JLabel lbRecover = new JLabel("Esqueceu sua senha?");
		lbRecover.setBounds(20, 418, 270, 23);
		containerLogin.add(lbRecover);
		lbRecover.setHorizontalAlignment(SwingConstants.CENTER);
		lbRecover.setForeground(new Color(249, 13, 72));
		lbRecover.setFont(new Font("Tahoma", Font.BOLD, 15));

		JPanel containerRegister = new JPanel();
		containerRegister.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Cadastrar-se", null, containerRegister, null);
		containerRegister.setLayout(null);

		JLabel lbRegisterUsername = new JLabel("Nome do usuário:");
		lbRegisterUsername.setBounds(21, 32, 270, 30);
		containerRegister.add(lbRegisterUsername);
		lbRegisterUsername.setForeground(new Color(69, 69, 69));
		lbRegisterUsername.setFont(new Font("Tahoma", Font.BOLD, 15));

		fieldRegisterUsername = new JTextField();
		fieldRegisterUsername.setBounds(21, 73, 270, 40);
		containerRegister.add(fieldRegisterUsername);
		fieldRegisterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		fieldRegisterUsername.setForeground(new Color(0, 0, 0));
		fieldRegisterUsername.setBackground(new Color(255, 255, 255));
		fieldRegisterUsername.setBorder(null);
		fieldRegisterUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldRegisterUsername.setColumns(10);

		JSeparator separatorRegisterUsername = new JSeparator();
		separatorRegisterUsername.setBounds(22, 116, 270, 2);
		containerRegister.add(separatorRegisterUsername);
		separatorRegisterUsername.setForeground(new Color(0, 0, 128));
		separatorRegisterUsername.setBackground(new Color(0, 0, 128));
		
		JLabel lbRegisterEmail = new JLabel("E-mail:");
		lbRegisterEmail.setBounds(20, 240, 270, 30);
		containerRegister.add(lbRegisterEmail);
		lbRegisterEmail.setForeground(new Color(69, 69, 69));
		lbRegisterEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		fieldRegisterEmail = new JTextField();
		fieldRegisterEmail.setBounds(20, 281, 270, 40);
		containerRegister.add(fieldRegisterEmail);
		fieldRegisterEmail.setHorizontalAlignment(SwingConstants.CENTER);
		fieldRegisterEmail.setForeground(Color.BLACK);
		fieldRegisterEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldRegisterEmail.setColumns(10);
		fieldRegisterEmail.setBorder(null);
		fieldRegisterEmail.setBackground(Color.WHITE);

		JSeparator separatorRegisterEmail = new JSeparator();
		separatorRegisterEmail.setBounds(21, 324, 270, 2);
		containerRegister.add(separatorRegisterEmail);
		separatorRegisterEmail.setForeground(new Color(0, 0, 128));
		separatorRegisterEmail.setBackground(new Color(0, 0, 128));

		JLabel lbRegisterPassword = new JLabel("Senha:");
		lbRegisterPassword.setBounds(21, 141, 270, 30);
		containerRegister.add(lbRegisterPassword);
		lbRegisterPassword.setForeground(new Color(69, 69, 69));
		lbRegisterPassword.setFont(new Font("Tahoma", Font.BOLD, 15));

		fieldRegisterPassword = new JPasswordField();
		fieldRegisterPassword.setBounds(22, 185, 270, 40);
		containerRegister.add(fieldRegisterPassword);
		fieldRegisterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		fieldRegisterPassword.setForeground(new Color(0, 0, 0));
		fieldRegisterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fieldRegisterPassword.setBackground(new Color(255, 255, 255));
		fieldRegisterPassword.setBorder(null);

		JSeparator separatorRegisterPassword = new JSeparator();
		separatorRegisterPassword.setBounds(22, 227, 270, 2);
		containerRegister.add(separatorRegisterPassword);
		separatorRegisterPassword.setForeground(new Color(0, 0, 128));
		separatorRegisterPassword.setBackground(new Color(0, 0, 128));

		btnRegister = new StrongButton("Cadastrar", new Color(255, 255, 255), new Color(0, 0, 128), 90, 373, 270, 50);
		btnRegister.setBounds(21, 374, 270, 50);
		containerRegister.add(btnRegister);	
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player player = new Player(fieldRegisterUsername.getText(), fieldRegisterEmail.getText(),
						Arrays.toString(fieldRegisterPassword.getPassword()));

				PlayerDAO playerDAO = PlayerDAO.getInstance();
				playerDAO.save(player);
			}
		});
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegister.setBackground(new Color(249, 13, 72));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegister.setBackground(new Color(0, 0, 128));
			}
		});
		
		JLabel lbBG = new JLabel("");
		lbBG.setIcon(new ImageIcon("img\\bg-login.jpg"));
		lbBG.setBounds(0, 0, 494, 567);
		frame.getContentPane().add(lbBG);

	}
}
