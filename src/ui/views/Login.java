package ui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 460, 645);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 70));
		panel.setBounds(10, 143, 424, 385);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(0, 0, 0));
		textField.setBorder(null);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(79, 72, 270, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(240, 222, 48));
		separator.setBackground(new Color(240, 222, 48));
		separator.setBounds(80, 115, 270, 2);
		panel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(79, 31, 80, 30);
		panel.add(lblNewLabel);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSenha.setBounds(79, 140, 80, 30);
		panel.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBorder(null);
		passwordField.setBounds(80, 184, 270, 40);
		panel.add(passwordField);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(240, 222, 48));
		separator_1.setBackground(new Color(240, 222, 48));
		separator_1.setBounds(80, 226, 270, 2);
		panel.add(separator_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(240, 222, 48));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(79, 279, 270, 50);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_1.setForeground(new Color(240, 222, 48));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(79, 342, 270, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Slide Puzzle");
		lblNewLabel_2.setBounds(264, 56, 170, 40);
		frame.getContentPane().add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\luissant\\OneDrive - BRQ\\Documentos\\Project\\eclipse\\trabalho-poo\\img\\bg.png"));
		lblNewLabel_3.setBounds(0, 0, 445, 606);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		
		
	}
}
