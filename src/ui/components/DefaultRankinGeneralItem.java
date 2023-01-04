package ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class DefaultRankinGeneralItem {
	
	private JPanel body;
	private JLabel position;
	private JLabel photoPersonal;
	private JLabel name;
	private JLabel ldMatchPlayer;
	private JLabel punctuation;
	private JLabel duration;
	
	public DefaultRankinGeneralItem(int x, int y, int width, int height, JPanel JPanel) {
		body = new JPanel();
		body.setBackground(Color.WHITE);
		body.setBounds(x, y, width,height);
		body.setBorder(null);
		body.setLayout(null);
		body.setVisible(false);
		JPanel.add(body);
		
		position = new JLabel();
		position.setFont(new Font("Tahoma", Font.BOLD, 25));
		position.setHorizontalAlignment(SwingConstants.CENTER);
		position.setBounds(23, 16, 40, 40);;
		body.add(position);
		
		photoPersonal = new JLabelRound();
		photoPersonal.setBounds(105, 7, 73, 62);
		body.add(photoPersonal);
	
		name = new JLabel();
		name.setBounds(195, 25, 347, 27);
		body.add(name);
		
		ldMatchPlayer = new JLabel();
		ldMatchPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		ldMatchPlayer.setBounds(557, 25, 104, 27);
		body.add(ldMatchPlayer);
		
		punctuation= new JLabel();
		punctuation.setHorizontalAlignment(SwingConstants.CENTER);
		punctuation.setBounds(689, 25, 86, 27);
		body.add(punctuation);
		
		duration = new JLabel();
		duration.setHorizontalAlignment(SwingConstants.CENTER);
		duration.setBounds(795, 25, 104, 27);
		body.add(duration);
		
		JSeparator separator= new JSeparator();
		separator.setBounds(0, 73, 899, 5);
		body.add(separator);	
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setVisible(boolean isVisible) {
		body.setVisible(isVisible);
	}
	public void setIdMatchPlayer(String ldMatchPlayer) {
		this.ldMatchPlayer.setText(ldMatchPlayer);
	}
	
	public void setPunctuation(String punctuation) {
		this.punctuation.setText(punctuation);
	}

	public void setDuration(String duration) {
		this.duration.setText(duration);
	}
	
	public JLabel getPhotoPersonal() {
		return photoPersonal;
	}
	public JLabel getPosition() {
		return position;
	}
	
	
}
