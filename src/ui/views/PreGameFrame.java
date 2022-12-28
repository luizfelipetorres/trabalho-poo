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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfaces.ClickListener;
import model.Player;
import ui.components.CustomButton;
import ui.components.CustomComboBox;
import ui.components.CustomField;
import ui.components.CustomLabel;
import util.TypeShuffle;

public class PreGameFrame extends JPanel {

	private static final long serialVersionUID = 1L;

	private CustomButton buttonInit;
	private JTable table;
	private File image;
	private JLabel labelRanking;
	private JLabel labelImge;
	private JLabel labelImageName;
	private CustomButton buttonChooseImg;
	private ClickListener clickListener;
	private static final int widthColumnLeft = 253;

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

		Map<String, Integer> optionsSize = new HashMap<>() {
			{
				put("3x3", 3);
				put("4x4", 4);
				put("5x5", 5);
			}
		};

		Map<String, TypeShuffle> optionsShuffle = new HashMap<>() {
			{
				put("Par", TypeShuffle.pairs);
				put("Ímpar", TypeShuffle.odd);
			}
		};

		CustomLabel labelSize = new CustomLabel("Selecione o tamanho...", 38, 20, widthColumnLeft, 22);
		add(labelSize);
		CustomComboBox<Object> cbSize = new CustomComboBox<>(optionsSize.keySet().stream().sorted().toArray(), 38, 50,
				widthColumnLeft, 22);
		add(cbSize);

		CustomLabel labelShuffle = new CustomLabel("Selecione o embaralhamento...", 38, 80, widthColumnLeft, 22);
		add(labelShuffle);
		CustomComboBox<Object> cbShuffle = new CustomComboBox<>(optionsShuffle.keySet().stream().sorted().toArray(), 38,
				110, widthColumnLeft, 22);
		add(cbShuffle);

		labelRanking = new JLabel("Ranking");
		labelRanking.setVerticalAlignment(SwingConstants.TOP);
		labelRanking.setHorizontalAlignment(SwingConstants.CENTER);
		labelRanking.setBounds(301, 39, 474, 458);
		add(labelRanking);
		labelRanking.setBorder(new LineBorder(new Color(0, 0, 128)));

		buttonChooseImg = new CustomButton("Escolha uma imagem", null, 38, 160, widthColumnLeft, 23);
		buttonChooseImg.addMouseListener(hoverEffect);
		buttonChooseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				image = chooseImage();
				showChoosedImage();
			}
		});
		add(buttonChooseImg);

		labelImge = new JLabel("");
		labelImge.setFont(new Font("Tahoma", Font.ITALIC, 11));
		labelImge.setBounds(38, 260, widthColumnLeft, 14);
		labelImge.setHorizontalTextPosition(SwingConstants.RIGHT);
		labelImge.setIconTextGap(50);
		labelImge.setVerticalTextPosition(SwingConstants.TOP);
		add(labelImge);

		table = new JTable();
		table.setBounds(86, 323, 82, -65);
		add(table);

		labelImageName = new JLabel("Imagem escolhida: ");
		labelImageName.setFont(new Font("Tahoma", Font.ITALIC, 11));
		labelImageName.setBounds(38, 190, widthColumnLeft, 14);
		add(labelImageName);

		buttonInit = new CustomButton("Jogar", "img\\icons\\icon-control.png", 20, 500, 270, 50);
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
		ImageIcon icon = new ImageIcon(image.getAbsolutePath());
		labelImageName.setText(concatImageName());
		try {
			BufferedImage resized = ImageIO.read(image);
			Image newImage = resized.getScaledInstance(widthColumnLeft, -1, 0);
			labelImge.setIcon(new ImageIcon(newImage));
			labelImge.setBounds(38, 200, widthColumnLeft, newImage.getHeight(labelImge) + 50);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public File getImage() {
		return image;
	}
}
