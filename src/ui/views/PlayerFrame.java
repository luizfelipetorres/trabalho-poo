package ui.views;

import java.awt.Color;

import javax.swing.JPanel;

import ui.components.CustomButton;
import ui.components.CustomField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Font;

public class PlayerFrame extends JPanel {


	private static final long serialVersionUID = 1L;

	public PlayerFrame() {
		this.setBounds(0, 0, 1175, 670);
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);
		
		CustomField fieldUsername = new CustomField("Nome do usuário:", 300, 500, 50, false);
		CustomField fieldEmail = new CustomField("E-mail:", 300, 500, 150, false);
		CustomField fieldPassword = new CustomField("Senha:", 300, 500, 250, true);
		CustomField fieldConfirmPassword = new CustomField("Confirmar senha:", 300, 500, 350, true);
		
		this.add(fieldUsername.initialize());
		this.add(fieldEmail.initialize());
		this.add(fieldPassword.initialize());
		this.add(fieldConfirmPassword.initialize());
		
		CustomButton btnRegister = new CustomButton("Atualizar", "img\\icons\\icon-update.png", new Color(255, 255, 255), new Color(0, 0, 128), 500, 600, 270, 50);
		btnRegister.setBounds(500, 590, 300, 50);
		this.add(btnRegister);	
		
		JPanel panelInformationPersona = new JPanel();
		panelInformationPersona.setBackground(new Color(205, 205, 205));
		panelInformationPersona.setBounds(10, 11, 457, 630);
		add(panelInformationPersona);
		panelInformationPersona.setLayout(null);
		
		JLabel photoPersona = new JLabel("");
		photoPersona.setIcon(new ImageIcon("img\\icons\\icon-persona.png"));
		photoPersona.setHorizontalAlignment(SwingConstants.CENTER);
		photoPersona.setBounds(10, 11, 437, 400);
		panelInformationPersona.add(photoPersona);
		
		JSeparator separatorPhotoPersona = new JSeparator();
		separatorPhotoPersona.setBounds(10, 424, 437, 7);
		separatorPhotoPersona.setForeground(new Color(249, 13, 72));
		separatorPhotoPersona.setBackground(new Color(249, 13, 72));
		panelInformationPersona.add(separatorPhotoPersona);
		
		JLabel lbTitleInformationPersona = new JLabel("Informações gerais");
		lbTitleInformationPersona.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTitleInformationPersona.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitleInformationPersona.setBounds(10, 442, 437, 20);
		panelInformationPersona.add(lbTitleInformationPersona);
	}
}

