package ui.components;

import java.awt.Color;
import java.awt.Font;import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.dnd.DropTarget;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class CustomComboBox<T> extends JComboBox<T> {

	private static final long serialVersionUID = 1L;

	public CustomComboBox(T[] model, int x, int y, int width, int height) {

		setForeground(new Color(0, 0, 0));
		setBackground(new Color(255, 255, 255));
		setModel(new DefaultComboBoxModel<T>(model));
		setBounds(x, y, width, height);
		setFont(new Font("Tahoma", Font.BOLD, 15));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
}
