package ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import controller.PlayerController;
import model.Player;

public class ConfigUser extends JPanel {

	private JlabelRound profilePicture; 
	private Player player;
	private File img;
	
	public ConfigUser(Player player) throws Exception {
		this.player = player;
		setLayout(null);
		setBounds(192, 0, 681, 548);		
		initiation();
	}

	public void initiation() throws Exception {		
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		profilePicture = new JlabelRound();
		profilePicture.setBounds(90, 106, 150, 150);
		configImg(new ImageIcon(ImageIO.read(player.getFileOrDefault())));
		add(profilePicture);
		
		JButton editPhoto = new JButton("Editar");
		editPhoto.setBackground(Color.LIGHT_GRAY);
		editPhoto.setFont(new Font("Arial Black", Font.BOLD, 15));
		editPhoto.setBounds(90, 281, 150, 29);
		add(editPhoto);
		
		JTextField textName = new JTextField();
		textName.setBackground(UIManager.getColor("CheckBox.background"));
		textName.setBounds(308, 126, 317, 46);
		textName.setText(player.getPlayerUsername());
		textName.setBorder(BorderFactory.createTitledBorder(lineBorder, "Nome"));
		add(textName);
		
		JTextField textEmail = new JTextField();
		textEmail.setBackground(SystemColor.menu);
		textEmail.setBounds(308, 210, 317, 46);
		textEmail.setBorder(BorderFactory.createTitledBorder(lineBorder, "Email"));
		textEmail.setText(player.getPlayerEmail());
		add(textEmail);
		
		JPasswordField currentPassword = new JPasswordField();
		currentPassword.setBackground(UIManager.getColor("Button.background"));
		currentPassword.setBounds(308, 318, 317, 46);
		currentPassword.setBorder(BorderFactory.createTitledBorder(lineBorder, "Digite sua senha atual"));
		currentPassword.setVisible(false);
		add(currentPassword);
		
		JPasswordField newPassword = new JPasswordField();
		newPassword.setBackground(UIManager.getColor("Button.background"));
		newPassword.setBounds(308, 381, 317, 46);
		newPassword.setBorder(BorderFactory.createTitledBorder(lineBorder, "Digite sua senha nova"));
		newPassword.setVisible(false);
		add(newPassword);
		
		JRadioButton changePassword = new JRadioButton("Alterar senha");
		changePassword.setBounds(520, 271, 109, 23);
		add(changePassword);

		JButton save = new JButton("Salvar");
		save.setBackground(Color.LIGHT_GRAY);
		save.setFont(new Font("Arial Black", Font.BOLD, 11));
		save.setBounds(329, 267, 109, 29);
		add(save);
		
		JButton saveWithPassword = new JButton("Salvar");
		saveWithPassword.setBackground(Color.LIGHT_GRAY);
		saveWithPassword.setFont(new Font("Arial Black", Font.BOLD, 11));
		saveWithPassword.setBounds(423, 453, 109, 29);
		saveWithPassword.setVisible(false);
		add(saveWithPassword);
		
		JLabel attention = new JLabel("");
		attention.setHorizontalAlignment(SwingConstants.CENTER);
		attention.setFont(new Font("Arial Black", Font.BOLD, 12));
		attention.setForeground(new Color(255, 0, 0));
		attention.setVisible(false);
		attention.setBounds(308, 49, 317, 29);
		add(attention);
		
		changePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(changePassword.isSelected()) {
					currentPassword.setVisible(true);
					newPassword.setVisible(true);
					saveWithPassword.setVisible(true);
					save.setVisible(false);
				}
				
				if(!changePassword.isSelected()) {
					currentPassword.setVisible(false);
					newPassword.setVisible(false);
					saveWithPassword.setVisible(false);
					save.setVisible(true);
				}
			}
		});
		
		editPhoto.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                btnImagemActionPerformed(evt);
	            }
	    });

		editPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				editPhoto.setBackground(Color.DARK_GRAY);
				editPhoto.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				editPhoto.setBackground(Color.LIGHT_GRAY);
				editPhoto.setForeground(Color.black);
			}
		});
		
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				save.setBackground(Color.DARK_GRAY);
				save.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				save.setBackground(Color.LIGHT_GRAY);
				save.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textName.getText().isBlank() || textEmail.getText().isBlank() ) {
					attention.setText("preencha todas as informações necessarias");
					attention.setVisible(true);
				}else {
					if(attention.isVisible()) attention.setVisible(false);
					player.setPlayerUsername(textName.getText());
					player.setPlayerEmail(textEmail.getText());
					
					if(img!= null) {		
						try {
							player.updateImage(img);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "não foi possivel salva suas alterações");
						} 	
					}
					
					try {
						PlayerController.getInstance().update(player);
						JOptionPane.showMessageDialog(null, "salvo com sucesso");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "não foi possivel salva suas alterações");
					}
				}
			}
		});
		
		saveWithPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				saveWithPassword.setBackground(Color.DARK_GRAY);
				saveWithPassword.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				saveWithPassword.setBackground(Color.LIGHT_GRAY);
				saveWithPassword.setForeground(Color.black);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(textName.getText().isBlank()|| textEmail.getText().isBlank() || 
						currentPassword.getPassword().length == 0||
						newPassword.getPassword().length == 0) {
					attention.setText("preencha todas as informações necessarias");
					attention.setVisible(true);
				}else if (!Arrays.toString(currentPassword.getPassword()).equals(player.getPlayerPassword())) {
					attention.setText("senhar atual inválida");
					attention.setVisible(true);
				}else {
					System.out.println("hello");
					if(attention.isVisible()) attention.setVisible(false);
					player.setPlayerUsername(textName.getText());
					player.setPlayerEmail(textEmail.getText());
					player.setPlayerPassword(Arrays.toString(newPassword.getPassword()));
					
					if(img!= null) {		
						try {
							player.updateImage(img);
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "não foi possivel salva suas alterações");
						} 	
					}
					
					try {
						PlayerController.getInstance().update(player);
						JOptionPane.showMessageDialog(null, "salvo com sucesso");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "não foi possivel salva suas alterações");
					}
					System.out.println("hello");
				}
			}
		});
	}
	
	private void btnImagemActionPerformed(ActionEvent evt) {
		
        JFileChooser fc = new JFileChooser();
        
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            img = fc.getSelectedFile();
            try {
            	BufferedImage imagem = ImageIO.read(img);
        		configImg(new ImageIcon(imagem));
            } catch (Exception ex) {
            	 JOptionPane.showMessageDialog(null, "selecionou um arquivo válido");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
        }
	}
	
	public void configImg(ImageIcon imgPerfil) {
		imgPerfil.setImage(imgPerfil.getImage().getScaledInstance(profilePicture.getWidth(), profilePicture.getHeight(), 100));
		profilePicture.setIcon(imgPerfil);
	}
}
