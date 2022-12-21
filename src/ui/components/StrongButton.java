package ui.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class StrongButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iconPath;
	private Color colorForeground;
	private Color colorBackground;
	private int x;
	private int y;
	int width;
	int height;
	
	public StrongButton(String title, String iconPath, Color colorForeground, Color colorBackground, int x, int y, int width, int height) {
		super(title);
		this.iconPath = iconPath;
		this.colorForeground = colorForeground;
		this.colorBackground = colorBackground;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		initialize();
	}
	
	private void initialize() {
		this.setForeground(colorForeground);
		this.setBackground(colorBackground);
		this.setIcon(new ImageIcon(iconPath));
		this.setContentAreaFilled(false);
		this.setOpaque(true);
		this.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.setBounds(x, y, width, height);
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

}
