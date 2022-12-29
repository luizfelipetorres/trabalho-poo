package ui.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfaces.ClickListener;
import model.Player;
import ui.components.CustomButton;
import ui.components.CustomComboBox;
import ui.components.CustomLabel;
import util.TypeShuffle;

public class PreGameFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	private CustomButton buttonInit;
	private File image;
	private JLabel labelImge;
	private JLabel labelImageName;
	private CustomButton buttonChooseImg;
	private ClickListener clickListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreGameFrame window = new PreGameFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PreGameFrame(Player player, ClickListener listener) {
		super();
		this.clickListener = listener;
		image = new File("img//naruto.jpg");
		initialize();
	}

	public PreGameFrame() {
		super();
		image = new File("img//naruto.jpg");
		initialize();
	}

	private void initialize() {
		this.setBounds(0, 0, 1175, 670);
		this.setLayout(null);

		MouseAdapter hoverEffect = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				e.getComponent().setBackground(new Color(249, 13, 72));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				e.getComponent().setBackground(new Color(0, 0, 128));
			}
		};

		Map<String, Integer> optionsSize = new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;
			{
				put("3x3", 3);
				put("4x4", 4);
				put("5x5", 5);
			}
		};

		Map<String, TypeShuffle> optionsShuffle = new HashMap<String, TypeShuffle>() {
			private static final long serialVersionUID = 1L;
			{
				put("Par", TypeShuffle.pairs);
				put("Ímpar", TypeShuffle.odd);
			}
		};
		
		JLabel labelTitle = new CustomLabel("PRÉ-CONFIGURAÇÃO", 10, 0, 270, 30);
		this.add(labelTitle);
		
		CustomComboBox<Object> cbSize = new CustomComboBox<>(
				"Selecione o tamanho do tabuleiro:", 
				optionsSize.keySet().stream().sorted().toArray(), 
				10, 50,
				270);
		

		CustomComboBox<Object> cbShuffle = new CustomComboBox<>(
				"Selecione o tipo de permutação:", 
				optionsShuffle.keySet().stream().sorted().toArray(), 
				10, 135, 
				270);
		
		this.add(cbSize.initialize());
		this.add(cbShuffle.initialize());

		buttonChooseImg = new CustomButton("Escolha uma imagem", null, 10, 225, 270, 40);
		buttonChooseImg.addMouseListener(hoverEffect);
		buttonChooseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = chooseImage();
				showChoosedImage();
			}
		});
		add(buttonChooseImg);
		
		JPanel containerImage = new JPanel();
		containerImage.setLayout(null);
		containerImage.setBorder(null);
		containerImage.setBounds(290, 0, 500, 638);
		containerImage.setBackground(new Color(220, 220, 220));
		
		labelImageName = new JLabel("Imagem escolhida: ");
		labelImageName.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelImageName.setBounds(10, 0, 270, 30);
		
		labelImge = new JLabel("");

		containerImage.add(labelImageName);
		containerImage.add(labelImge);

		this.add(containerImage);


		buttonInit = new CustomButton("Jogar", "img\\icons\\icon-control.png", 10, 589, 270, 50);
		buttonInit.addMouseListener(hoverEffect);
		buttonInit.addActionListener((e) -> {
			int selectedSize = optionsSize.get(cbSize.getSelectedItem());
			TypeShuffle selectedShuffle = optionsShuffle.get(cbShuffle.getSelectedItem());
			clickListener.onClick(image, selectedSize, selectedShuffle);
			this.removeAll();
		});
		add(buttonInit);

		showChoosedImage();

	}

	private String concatImageName() {
		return String.format("Imagem escolhida: %s", this.image.getName().toString());
	}

	private File chooseImage() {
		JFileChooser fileChooser = new JFileChooser(image);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		boolean choosed = fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION;
		return choosed ? fileChooser.getSelectedFile() : image;
	}

	private void showChoosedImage() {
		labelImageName.setText(concatImageName());
		try {
			BufferedImage resized = ImageIO.read(image);
			Image newImage = resized.getScaledInstance(480, 400, 1);
			labelImge.setIcon(new ImageIcon(newImage));
			labelImge.setBounds(10, 50, 480, newImage.getHeight(labelImge));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public File getImage() {
		return image;
	}
}
