package view.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.PlayerController;
import controller.PlayerMatchController;
import model.Player;
import util.RecordPlayerMatch;
import view.components.CustomButton;
import view.components.CustomField;
import view.components.JPhotoRound;

public class PlayerFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	private Player player;
	private JPhotoRound photoPersona;
	private RecordPlayerMatch recordPlayerMatch;
	private File image;

	public PlayerFrame(Player player) {
		super();
		this.player = player;
		this.recordPlayerMatch = PlayerMatchController.getInstance().recordPlayerMatchByPlayer(player.getPlayerId());
		this.image = null;
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
		this.add(panelInformationPersona);
		panelInformationPersona.setLayout(null);

		photoPersona = new JPhotoRound(player.getPlayerUrlImage(), 350);
		photoPersona.setCursor(new Cursor(Cursor.HAND_CURSOR));
		photoPersona.setBounds(51, 21, 350, 350);
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
		titleTotalMatch.setBounds(10, 431, 136, 25);
		panelInformationPersona.add(titleTotalMatch);

		JLabel titleMatchComplete = new JLabel("Partidas Completas: ");
		titleMatchComplete.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMatchComplete.setBounds(10, 456, 136, 25);
		panelInformationPersona.add(titleMatchComplete);

		JLabel titleMatchNotComplete = new JLabel("Partidas nao completadas:");
		titleMatchNotComplete.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMatchNotComplete.setBounds(213, 456, 165, 25);
		panelInformationPersona.add(titleMatchNotComplete);

		JLabel titleTotalPoints = new JLabel("Total de pontos: ");
		titleTotalPoints.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTotalPoints.setBounds(10, 570, 113, 25);
		panelInformationPersona.add(titleTotalPoints);

		JLabel titleMaxPoints = new JLabel("Maior pontuação:  ");
		titleMaxPoints.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMaxPoints.setBounds(223, 594, 116, 25);
		panelInformationPersona.add(titleMaxPoints);

		JLabel titleMenorPontis = new JLabel("Menor pontuação: ");
		titleMenorPontis.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMenorPontis.setBounds(10, 594, 124, 25);
		panelInformationPersona.add(titleMenorPontis);

		JLabel titleTotalDuration = new JLabel("Total de horas jogadas: ");
		titleTotalDuration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleTotalDuration.setBounds(10, 517, 156, 25);
		panelInformationPersona.add(titleTotalDuration);

		JLabel titleMaxduration = new JLabel("Maior duração : ");
		titleMaxduration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMaxduration.setBounds(10, 545, 113, 25);
		panelInformationPersona.add(titleMaxduration);

		JLabel titleMinDuration = new JLabel("Menor duração :  ");
		titleMinDuration.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleMinDuration.setBounds(223, 545, 113, 25);
		panelInformationPersona.add(titleMinDuration);

		JLabel titleAvgPontuação = new JLabel("Média de pontos: ");
		titleAvgPontuação.setFont(new Font("Tahoma", Font.BOLD, 12));
		titleAvgPontuação.setBounds(223, 570, 124, 25);
		panelInformationPersona.add(titleAvgPontuação);

		JLabel textTotalMatch = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatch()));
		textTotalMatch.setHorizontalAlignment(SwingConstants.RIGHT);
		textTotalMatch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalMatch.setBounds(147, 431, 50, 25);
		panelInformationPersona.add(textTotalMatch);

		JLabel textMatchComplete = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatchCompleta()));
		textMatchComplete.setHorizontalAlignment(SwingConstants.RIGHT);
		textMatchComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMatchComplete.setBounds(147, 456, 50, 25);
		panelInformationPersona.add(textMatchComplete);

		JLabel textMatchNotComplete = new JLabel(String.valueOf(recordPlayerMatch.getTotalMatchNotCompleta()));
		textMatchNotComplete.setHorizontalAlignment(SwingConstants.RIGHT);
		textMatchNotComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMatchNotComplete.setBounds(383, 456, 50, 25);
		panelInformationPersona.add(textMatchNotComplete);

		JLabel textTotalDuration = new JLabel(formatHours(recordPlayerMatch.getTotalDuration()));
		textTotalDuration.setHorizontalAlignment(SwingConstants.CENTER);
		textTotalDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalDuration.setBounds(167, 517, 280, 25);
		panelInformationPersona.add(textTotalDuration);

		JLabel textMaxduration = new JLabel(formatHours(recordPlayerMatch.getMaxDuration()));
		textMaxduration.setHorizontalAlignment(SwingConstants.RIGHT);
		textMaxduration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMaxduration.setBounds(121, 545, 93, 25);
		panelInformationPersona.add(textMaxduration);

		JLabel textMinDuration = new JLabel(formatHours(recordPlayerMatch.getMinDuration()));
		textMinDuration.setHorizontalAlignment(SwingConstants.RIGHT);
		textMinDuration.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMinDuration.setBounds(335, 546, 112, 25);
		panelInformationPersona.add(textMinDuration);

		JLabel textTotalPoints = new JLabel(formatNumber(recordPlayerMatch.getTotalPoints()));
		textTotalPoints.setHorizontalAlignment(SwingConstants.RIGHT);
		textTotalPoints.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textTotalPoints.setBounds(131, 570, 82, 25);
		panelInformationPersona.add(textTotalPoints);

		JLabel textMaxPoints = new JLabel(formatNumber(recordPlayerMatch.getMaxPoints()));
		textMaxPoints.setHorizontalAlignment(SwingConstants.RIGHT);
		textMaxPoints.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMaxPoints.setBounds(344, 594, 103, 25);
		panelInformationPersona.add(textMaxPoints);

		JLabel textMenorPontis = new JLabel(formatNumber(recordPlayerMatch.getMinPoints()));
		textMenorPontis.setHorizontalAlignment(SwingConstants.RIGHT);
		textMenorPontis.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textMenorPontis.setBounds(141, 594, 72, 25);
		panelInformationPersona.add(textMenorPontis);

		JLabel textAvgPontuação = new JLabel(formatNumber(recordPlayerMatch.getAvgPoints()));
		textAvgPontuação.setHorizontalAlignment(SwingConstants.RIGHT);
		textAvgPontuação.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textAvgPontuação.setBounds(346, 570, 99, 25);
		panelInformationPersona.add(textAvgPontuação);

		JSeparator separatorPhotoPersona_1 = new JSeparator();
		separatorPhotoPersona_1.setForeground(new Color(74, 74, 74));
		separatorPhotoPersona_1.setBackground(new Color(74, 74, 74));
		separatorPhotoPersona_1.setBounds(10, 499, 437, 7);
		panelInformationPersona.add(separatorPhotoPersona_1);

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
				String username = fieldUsername.getText();
				String email = fieldEmail.getText();
				String image = photoPersona.getPath();
				boolean isValid = false;

				if (changePassword.isSelected()) {

					if (currentPassword.getPassword().length == 0 || newPassword.getPassword().length == 0
							|| newPasswordInt.isBlank() || currentPasswordInt.isBlank() || username.isBlank()
							|| email.isBlank()) {
						JOptionPane.showMessageDialog(null, "Preencha todas as informações necessaria.");
					} else if (!currentPasswordInt.equals(newPasswordInt)) {
						JOptionPane.showMessageDialog(null, "Senha inválida.");
					} else {
						player.setPlayerUrlImage(image);
						player.setPlayerUsername(username);
						player.setPlayerEmail(email);
						player.setPlayerPassword(newPasswordInt);
						isValid = true;
					}
				} else {

					if (username.isBlank() || email.isBlank()) {
						JOptionPane.showMessageDialog(null, "Preencha todas as informações necessaria.");
					} else {
						player.setPlayerUrlImage(image);
						player.setPlayerUsername(username);
						player.setPlayerEmail(email);
						isValid = true;
					}
				}

				if (isValid) {
					if (PlayerController.getInstance().update(player)) {
						JOptionPane.showMessageDialog(null, "Informações foram atualizadas.");
						setVisible(false);
						// kernelFrame.updateUserInformation(player);
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

				Object message = "O que deseja fazer com a foto do seu perfil ?";
				Object[] options = { "Editar", "Remover" };
				int response = JOptionPane.showOptionDialog(null, message, "Pergunta", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

				if (response == 0) {
					updatePhoto();
				} else {
					removePhoto();
				}
			}
		});
	}

	private void updatePhoto() {
		String destinationPath = "img\\users\\" + player.getPlayerUsername() + ".png";
		image = this.chooseImage();
		if (image != null) {
			try {
				this.copyFile(new File(image.getAbsolutePath()), new File(destinationPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			photoPersona.setPath(destinationPath);
		}
	}

	private void removePhoto() {
		String sourcePath = "img\\icons\\icon-persona.png";
		String destinationPath = "img\\users\\" + player.getPlayerUsername() + ".png";
		try {
			this.copyFile(new File(sourcePath), new File(destinationPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		photoPersona.setPath(destinationPath);
	}

	private File chooseImage() {
		JFileChooser fileChooser = new JFileChooser("img\\users");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		boolean choosed = fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION;
		return choosed ? fileChooser.getSelectedFile() : image;
	}

	@SuppressWarnings("resource")
	public void copyFile(File source, File destination) throws IOException {
		if (destination.exists())
			destination.delete();

		FileChannel sourceChannel = null;
		FileChannel destinationChannel = null;

		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destinationChannel = new FileOutputStream(destination).getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
		} finally {
			if (sourceChannel != null && sourceChannel.isOpen())
				sourceChannel.close();
			if (destinationChannel != null && destinationChannel.isOpen())
				destinationChannel.close();
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
