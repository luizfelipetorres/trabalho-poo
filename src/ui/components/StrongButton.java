package ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;


public class StrongButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorForeground;
	private Color colorBackground;
	private int x;
	private int y;
	int width;
	int height;
	
	public StrongButton(String title, Color colorForeground, Color colorBackground, int x, int y, int width, int height) {
		super(title);
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
		this.setContentAreaFilled(false);
		this.setOpaque(true);
		this.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.setBounds(x, y, width, height);
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	}

}
