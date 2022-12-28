package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CustomField{

	private JTextField fieldCommon;
	private JPasswordField fieldPassword;
	private String title;
	private int width;
	private int x;
	private int y;
	private boolean isFieldPassword;
	
	public CustomField(String title, int width, int x, int y, boolean isFieldPassword) {
		this.title = title;
		this.width = width;
		this.x = x;
		this.y = y;
		this.isFieldPassword = isFieldPassword;
	}
	
	public Component initialize() {
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBorder(null);
		container.setBounds(x, y, width, 75);
		
		JLabel label = new JLabel(title);
		label.setBounds(0, 0, width, 30);
		label.setForeground(new Color(69, 69, 69));
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		if(!isFieldPassword) {
			fieldCommon = new JTextField();
			fieldCommon.setBounds(0, 30, width, 40);
			fieldCommon.setHorizontalAlignment(SwingConstants.CENTER);
			fieldCommon.setForeground(new Color(0, 0, 0));
			fieldCommon.setFont(new Font("Tahoma", Font.PLAIN, 15));
			fieldCommon.setColumns(10);
			fieldCommon.setBorder(null);
			fieldCommon.setBackground(new Color(255, 255, 255));
			container.add(fieldCommon);
		}else{
			fieldPassword = new JPasswordField();
			fieldPassword.setBounds(0, 30, width, 40);
			fieldPassword.setHorizontalAlignment(SwingConstants.CENTER);
			fieldPassword.setForeground(new Color(0, 0, 0));
			fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			fieldPassword.setBorder(null);
			fieldPassword.setBackground(new Color(255, 255, 255));
			container.add(fieldPassword);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, width, 2);
		separator.setForeground(new Color(0, 0, 128));
		separator.setBackground(new Color(0, 0, 128));
		
		container.add(label);
		container.add(separator);
		
		return container;
	}
	
	public String getText() {
		return fieldCommon.getText();
	}
	
	public void setText(String text) {
		fieldCommon.setText(text);
	}
	
	public char[] getPassword() {
		return fieldPassword.getPassword();
	}

}