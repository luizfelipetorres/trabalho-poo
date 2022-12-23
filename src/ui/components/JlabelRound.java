package ui.components;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class JlabelRound extends JLabel{
	
	private final BorderRound borderRound = new BorderRound();
	private String name = "";
	private ImageIcon icon = null;
	
	public JlabelRound() {
		initComponent();
	}
	
	public JlabelRound(String name) {
		this.name = name;
		initComponent();
	}
	
	public JlabelRound(ImageIcon icon) {
		this.icon = icon;
		initComponent();
	}
	
	
	private void initComponent() {
		setText(name);
		setIcon(icon);
		setBorder(borderRound);
		setOpaque(true);
	}
	
}
