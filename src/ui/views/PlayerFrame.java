package ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.PlayerController;
import controller.PlayerMatchController;
import model.Player;
import ui.components.CustomButton;
import ui.components.CustomField;
import ui.components.JLabelRound;
import util.RecordPlayerMatch;

public class PlayerFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabelRound photoPersona;
	private Player player;
	private RecordPlayerMatch recordPlayerMatch;
	private KernelFrame kernelFrame;

	public PlayerFrame(Player player, KernelFrame kernelFrame) {
		this.player = player;
		this.kernelFrame = kernelFrame;
		this.recordPlayerMatch = PlayerMatchController.getInstance().recordPlayerMatchByPlayer(player.getPlayerId());
		initialize();
	}

	public void initialize() {
		this.setBounds(0, 0, 1175, 670);
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);

		CustomField fieldUsername = new CustomField("Nome do usuário:", 300, 500, 50, false);
		CustomField fieldEmail = new CustomField("E-mail:", 300, 500, 150, false);
		CustomField currentPassword = new CustomField("Senha atual:", 300, 500, 300, true);
		CustomField newPassword = new CustomField("Nova senha:", 300, 500, 400, true);

		Component componentCurrentPassword = currentPassword.initialize();
		componentCurrentPassword.setVisible(false);
		Component componentNewPassword = newPassword.initialize();
		componentNewPassword.setVisible(false);

		this.add(fieldUsername.initialize());
		this.add(fieldEmail.initialize());
		this.add(componentCurrentPassword);
		this.add(componentNewPassword);

		fieldUsername.setText(player.getPlayerUsername());
		fieldEmail.setText(player.getPlayerEmail());

		CustomButton btnRegister = new CustomButton("Atualizar", "img\\icons\\icon-update.png",
				new Color(255, 255, 255), new Color(0, 0, 128), 500, 600, 270, 50);
		btnRegister.setBounds(500, 590, 300, 50);
		this.add(btnRegister);

		JPanel panelInformationPersona = new JPanel();
		panelInformationPersona.setBackground(new Color(205, 205, 205));
		panelInformationPersona.setBounds(10, 11, 457, 630);
		add(panelInformationPersona);
		panelInformationPersona.setLayout(null);

		photoPersona = new JLabelRound();
		photoPersona.setIcon(new ImageIcon("img\\icons\\icon-persona.png"));
		photoPersona.setHorizontalAlignment(SwingConstants.CENTER);
		photoPersona.setBounds(47, 11, 364, 336);
		configImg(player.getFile());
		panelInformationPersona.add(photoPersona);

		JSeparator separatorPhotoPersona = new JSeparator();
		separatorPhotoPersona.setBounds(10, 382, 437, 7);
		separatorPhotoPersona.setForeground(new Color(249, 13, 72));
		separatorPhotoPersona.setBackground(new Color(249, 13, 72));
		panelInformationPersona.add(separatorPhotoPersona);

		JLabel lbTitleInformationPersona = new JLabel("Informações gerais");
		lbTitleInformationPersona.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbTitleInformationPersona.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitleInformationPersona.setBounds(10, 400, 437, 20);
		panelInformationPersona.add(lbTitleInformationPersona);

		JLabel titleTotalMatch = new JLabel("Total de partidas: ");
		titleTotalMatch.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTotalMatch.setBounds(10, 431, 113, 26);
		panelInformationPersona.add(titleTotalMatch);

		JLabel titleMatchComplete = new JLabel("Partidas Completas: ");
		titleMatchComplete.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMatchComplete.setBounds(10, 454, 136, 26);
		panelInformationPersona.add(titleMatchComplete);

		JLabel titleMatchNotComplete = new JLabel("Partidas nao completadas:");
		titleMatchNotComplete.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMatchNotComplete.setBounds(213, 454, 165, 26);
		panelInformationPersona.add(titleMatchNotComplete);

		JLabel titleTotalPoints = new JLabel("Total de pontos: ");
		titleTotalPoints.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTotalPoints.setBounds(10, 549, 113, 26);
		panelInformationPersona.add(titleTotalPoints);

		JLabel titleMaxPoints = new JLabel("Maior pontuação:  ");
		titleMaxPoints.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMaxPoints.setBounds(10, 572, 116, 26);
		panelInformationPersona.add(titleMaxPoints);

		JLabel titleMenorPontis = new JLabel("Menor pontuação: ");
		titleMenorPontis.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMenorPontis.setBounds(213, 572, 124, 26);
		panelInformationPersona.add(titleMenorPontis);

		JLabel titleTotalDuration = new JLabel("Total de horas jogadas: ");
		titleTotalDuration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTotalDuration.setBounds(10, 491, 156, 26);
		panelInformationPersona.add(titleTotalDuration);

		JLabel titleMaxduration = new JLabel("Maior duração : ");
		titleMaxduration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMaxduration.setBounds(10, 512, 99, 26);
		panelInformationPersona.add(titleMaxduration);

		JLabel titleMinDuration = new JLabel("Menor duração :  ");
		titleMinDuration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMinDuration.setBounds(213, 512, 113, 26);
		panelInformationPersona.add(titleMinDuration);

		JLabel titleAvgPontuação = new JLabel("Média de pontos: ");
		titleAvgPontuação.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleAvgPontuação.setBounds(213, 549, 124, 26);
		panelInformationPersona.add(titleAvgPontuação);

		JLabel textTotalMatch = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatch()));
		textTotalMatch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalMatch.setBounds(133, 431, 56, 26);
		panelInformationPersona.add(textTotalMatch);

		JLabel textMatchComplete = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatchCompleta()));
		textMatchComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMatchComplete.setBounds(147, 454, 56, 26);
		panelInformationPersona.add(textMatchComplete);

		JLabel textMatchNotComplete = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatchNotCompleta()));
		textMatchNotComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMatchNotComplete.setBounds(383, 454, 64, 26);
		panelInformationPersona.add(textMatchNotComplete);

		JLabel textTotalDuration = new JLabel(formatHours(recordPlayerMatch.getTotalDuration()));
		textTotalDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalDuration.setBounds(167, 491, 202, 26);
		panelInformationPersona.add(textTotalDuration);

		JLabel textMaxduration = new JLabel(formatHours(recordPlayerMatch.getMaxDuration()));
		textMaxduration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMaxduration.setBounds(121, 512, 56, 26);
		panelInformationPersona.add(textMaxduration);

		JLabel textMinDuration = new JLabel(formatHours(recordPlayerMatch.getMinDuration()));
		textMinDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMinDuration.setBounds(322, 512, 56, 26);
		panelInformationPersona.add(textMinDuration);

		JLabel textTotalPoints = new JLabel(formatNumber(recordPlayerMatch.getTotalPoints()));
		textTotalPoints.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalPoints.setBounds(121, 549, 82, 26);
		panelInformationPersona.add(textTotalPoints);

		JLabel textMaxPoints = new JLabel(formatNumber(recordPlayerMatch.getMaxPoints()));
		textMaxPoints.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMaxPoints.setBounds(121, 572, 72, 26);
		panelInformationPersona.add(textMaxPoints);

		JLabel textMenorPontis = new JLabel(formatNumber(recordPlayerMatch.getMinPoints()));
		textMenorPontis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMenorPontis.setBounds(336, 572, 56, 26);
		panelInformationPersona.add(textMenorPontis);

		JLabel textAvgPontuação = new JLabel(formatNumber(recordPlayerMatch.getAvgPoints()));
		textAvgPontuação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textAvgPontuação.setBounds(332, 549, 115, 26);
		panelInformationPersona.add(textAvgPontuação);

		JRadioButton changePassword = new JRadioButton("Alterar senha");
		changePassword.setBackground(Color.WHITE);
		changePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changePassword.setBounds(680, 251, 120, 23);
		add(changePassword);

		changePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (changePassword.isSelected()) {
					componentCurrentPassword.setVisible(true);
					componentNewPassword.setVisible(true);

				}

				if (!changePassword.isSelected()) {
					componentCurrentPassword.setVisible(false);
					componentNewPassword.setVisible(false);
				}
			}
		});

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentPasswordInt = Arrays.toString(currentPassword.getPassword());
				String newPasswordInt = Arrays.toString(newPassword.getPassword());
				String Username = fieldUsername.getText();
				String email = fieldEmail.getText();
				boolean isValid = false;

				if (changePassword.isSelected()) {

					if (currentPassword.getPassword().length == 0 || newPassword.getPassword().length == 0
							|| newPasswordInt.isBlank() || currentPasswordInt.isBlank() || Username.isBlank()
							|| email.isBlank()) {
						JOptionPane.showMessageDialog(null, "Preencha todas as informações necessaria");
					} else if (!currentPasswordInt.equals(player.getPlayerPassword())) {
						JOptionPane.showMessageDialog(null, "Senha inválida");
					} else {
						player.setPlayerUsername(Username);
						player.setPlayerEmail(email);
						player.setPlayerPassword(newPasswordInt);
						isValid = true;
					}
				} else {

					if (Username.isBlank() || email.isBlank()) {
						JOptionPane.showMessageDialog(null, "Preencha todas as informações necessaria");
					} else {
						player.setPlayerUsername(Username);
						player.setPlayerEmail(email);
						isValid = true;
					}
				}

				if (isValid) {
					try {
						PlayerController.getInstance().update(player);
						JOptionPane.showMessageDialog(null, "salvo com sucesso");
						kernelFrame.updateUserInformation(PlayerController.getInstance().findById(player.getPlayerId()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "não foi possivel salva suas alterações");
					}
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegister.setBackground(new Color(249, 13, 72));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegister.setBackground(new Color(0, 0, 128));
			}
		});

		photoPersona.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JPanel panel = new JPanel();

				JLabel title = new JLabel("O que deseja fazer com a foto do seu perfil ? ");
				title.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panel.add(title);

				CustomButton editPhoto = new CustomButton("Editar", "img\\icons\\icon-editPhoto.png",
						new Color(255, 255, 255), new Color(0, 0, 128), 50, 50, 100, 50);
				CustomButton removePhoto = new CustomButton("Remover", "img\\icons\\icon-remove.png", Color.WHITE,
						new Color(0, 0, 128), 50, 50, 100, 50);
				Object[] options1 = { editPhoto, removePhoto };

				editPhoto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnUpdatePhotoActionPerformed(evt);

					}

				});

				removePhoto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnRemovePhotoActionPerformed(evt);
					}

				});

				editPhoto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						editPhoto.setBackground(new Color(249, 13, 72));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						editPhoto.setBackground(new Color(0, 0, 128));
					}
				});

				removePhoto.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						removePhoto.setBackground(new Color(249, 13, 72));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						removePhoto.setBackground(new Color(0, 0, 128));
					}
				});

				JOptionPane.showOptionDialog(null, panel, "Perfil", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options1, null);

			}
		});
	}

	private void configImg(File file) {

		if (file == null) {
			file = new File("img//users//defaultUsers.png");
		}

		try {
			ImageIcon imgPersona = new ImageIcon(ImageIO.read(file));
			imgPersona.setImage(
					imgPersona.getImage().getScaledInstance(photoPersona.getWidth(), photoPersona.getHeight(), 100));
			photoPersona.setIcon(imgPersona);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void btnUpdatePhotoActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser();

		int res = fc.showOpenDialog(null);

		if (res == JFileChooser.APPROVE_OPTION) {
			try {
				configImg(fc.getSelectedFile());
				PlayerController.getInstance().updatePhoto(fc.getSelectedFile(), player);
				kernelFrame.updateUserInformation(PlayerController.getInstance().findById(player.getPlayerId()));
				JOptionPane.showMessageDialog(null, "Foto do perfil atualizada com sucesso");
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Selecionou um arquivo válido");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Não foi possivel atualizar sua foto do perfil");
			}

		} else {
			JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum arquivo.");
		}
	}

	private void btnRemovePhotoActionPerformed(ActionEvent evt)  {
		try {
			if (player.getFile() == null) {
				JOptionPane.showMessageDialog(null, "Você não possui foto de perfil");
				kernelFrame.updateUserInformation(player);
			} else {
				PlayerController.getInstance().updatePhoto(null, player);
				kernelFrame.updateUserInformation(PlayerController.getInstance().findById(player.getPlayerId()));
				JOptionPane.showMessageDialog(null, "Foto do perfil foi removida com sucesso");
				configImg(null);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel remover sua foto do perfil");
		}
	}

	private String formatHours(Long duration) {
		Long seconds = duration;
		Long hours = seconds / 3600;
		seconds %= 3600;
		Long minutes = seconds / 60;
		seconds %= 60;
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	private String formatNumber(double number) {
		DecimalFormat df = new DecimalFormat(",##0.00");
		return df.format(number);
	}
}
