package ui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import dao.PlayerDAO;
import model.Player;
import ui.components.CustomButton;
import ui.components.CustomField;

import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends AbstractWindow {

	private JFrame frame;
	private CustomButton btnLogin;
	private CustomButton btnRegister;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		super();
		initialize();
	}

	protected void initialize() {
		MouseAdapter hoverEffect = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setBackground(new Color(249, 13, 72));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setBackground(new Color(0, 0, 128));
			}
		};

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

		CustomField fieldLoginUsername = new CustomField("Nome do usuário:", 270, 30, 50, false);
		CustomField fieldLoginPassword = new CustomField("Senha:", 270, 30, 150, true);

		btnLogin = new CustomButton("Entrar", "img\\icons\\icon-login.png", new Color(255, 255, 255), new Color(0, 0, 128), 20, 279, 270, 50);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player player = new Player(fieldLoginUsername.getText(),
						Arrays.toString(fieldLoginPassword.getPassword()));

				Player playerSelected = PlayerDAO.getInstance().authenticate(player);

				if (playerSelected != null) {
					KernelFrame.main(null, playerSelected);
				} else {
					JOptionPane.showMessageDialog(btnLogin,
							"Dados inválidos, por favor insira as credenciais novamente!");
				}
			}
		});
		btnLogin.addMouseListener(hoverEffect);

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

		CustomField fieldRegisterUsername = new CustomField("Nome do usuário:", 270, 30, 50, false);
		CustomField fieldRegisterEmail = new CustomField("E-mail:", 270, 30, 150, false);
		CustomField fieldRegisterPassword = new CustomField("Senha:", 270, 30, 250, true);

		btnRegister = new CustomButton("Cadastrar", "img\\icons\\icon-adduser.png", new Color(255, 255, 255), new Color(0, 0, 128), 21, 374, 270, 50);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Player player = new Player(fieldRegisterUsername.getText(), fieldRegisterEmail.getText(),
						Arrays.toString(fieldRegisterPassword.getPassword()));

				if (PlayerDAO.getInstance().save(player)) {
					JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", "Feito", JOptionPane.INFORMATION_MESSAGE);
					tabbedPane.setSelectedIndex(0);
				}
				
			}
		});
		btnRegister.addMouseListener(hoverEffect);
		
		containerLogin.add(fieldLoginUsername.initialize());
		containerLogin.add(fieldLoginPassword.initialize());
		containerLogin.add(btnLogin);
		
		containerRegister.add(fieldRegisterUsername.initialize());
		containerRegister.add(fieldRegisterEmail.initialize());
		containerRegister.add(fieldRegisterPassword.initialize());
		containerRegister.add(btnRegister);

		JLabel lbBG = new JLabel("");
		lbBG.setIcon(new ImageIcon("img\\bg-login.jpg"));
		lbBG.setBounds(0, 0, 494, 567);
		frame.getContentPane().add(lbBG);
	}
}
