package view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class CustomComboBox<T>{

	private JComboBox<T> comboBox;
	private String title;
	private T[] model;
	private int x;
	private int y;
	private int width;

	public CustomComboBox(String title, T[] model, int x, int y, int width) {
		this.title = title;
		this.model = model;
		this.x = x;
		this.y = y;
		this.width = width;
		initialize();
	}
	
	public Component initialize() {
		
		MouseAdapter hoverEffect = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setForeground(new Color(249, 13, 72));
				e.getComponent().setBackground(new Color(249, 13, 72));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setForeground(new Color(0, 0, 128));
				e.getComponent().setBackground(new Color(0, 0, 128));
			}
		};
		
		JPanel container = new JPanel();
		container.setLayout(null);
		container.setBorder(null);
		container.setBounds(x, y, width, 75);
		
		JLabel label = new CustomLabel(title, 0, 0, width, 30);
		
		comboBox = new JComboBox<T>();
		comboBox.setModel(new DefaultComboBoxModel<T>(model));
		comboBox.setBounds(0, 30, width, 40);
		comboBox.setUI(new BasicComboBoxUI() {
             @Override
             protected void installDefaults() {
         		 comboBox.setBackground(new Color(255, 255, 255));
                 comboBox.setForeground(new Color(0, 0, 0));
                 comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
            	 comboBox.setFocusable(false);
             }

             @Override
             protected JButton createArrowButton() {
                     final JButton button = new JButton(new ImageIcon("img\\icons\\icon-arrowdown.png"));
                     button.setFocusPainted(false);
                     button.setFocusable(false);
                     button.setRequestFocusEnabled(false);
                     button.setContentAreaFilled(false);
                     return button;
             }

         });
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, width, 7);
		separator.setForeground(new Color(0, 0, 128));
		separator.setBackground(new Color(0, 0, 128));
		separator.addMouseListener(hoverEffect);
		
		container.add(label);
		container.add(comboBox);
		container.add(separator);

		return container;
		
	}
	
	public Object getSelectedItem(){
		return comboBox.getSelectedItem();
	}
	
}
