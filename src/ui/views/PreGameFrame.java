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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import interfaces.ClickListener;
import model.Player;
import ui.components.CustomButton;
import ui.components.CustomComboBox;

public class PreGameFrame extends AbstractWindow {

	private static final long serialVersionUID = 1L;

	private CustomButton buttonInit;
	private JTable table;
	private File image;
	private JLabel labelRanking;
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
		image = new File("img//naruto.jpg");
		this.clickListener = listener;
		initialize();
	}

	public PreGameFrame() {
		image = new File("img//naruto.jpg");
		initialize();
	}

	private void initialize() {
		// frame = new JFrame();
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

		String[] options = new String[] { "Seleciona o tamanho...", "3x3", "4x4", "5x5" };
		CustomComboBox<String> cbSize = new CustomComboBox<>(options, 38, 51, 253, 22);
		add(cbSize);

		labelRanking = new JLabel("Ranking");
		labelRanking.setVerticalAlignment(SwingConstants.TOP);
		labelRanking.setHorizontalAlignment(SwingConstants.CENTER);
		labelRanking.setBounds(301, 39, 474, 458);
		add(labelRanking);
		labelRanking.setBorder(new LineBorder(new Color(0, 0, 128)));

		buttonChooseImg = new CustomButton("Escolha uma imagem", null, 38, 107, 253, 23);
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
		labelImge.setBounds(38, 152, 253, 14);
		labelImge.setHorizontalTextPosition(SwingConstants.RIGHT);
		labelImge.setIconTextGap(50);
		labelImge.setVerticalTextPosition(SwingConstants.TOP);
		add(labelImge);

		table = new JTable();
		table.setBounds(86, 323, 82, -65);
		add(table);

		labelImageName = new JLabel("Imagem escolhida: ");
		labelImageName.setFont(new Font("Tahoma", Font.ITALIC, 11));
		labelImageName.setBounds(38, 138, 253, 14);
		add(labelImageName);

		buttonInit = new CustomButton("Jogar", "img\\icons\\icon-control.png", 20, 500, 270, 50);
		buttonInit.addMouseListener(hoverEffect);
		buttonInit.addActionListener((e) -> {
			int selected = cbSize.getSelectedIndex();

			// TODO: Tentar usar um Enum no combobox
			if (selected == 0) {
				String message = "Selecione o tamanho do tabuleiro";
				JOptionPane.showInternalMessageDialog(cbSize, message, "Atenção", JOptionPane.ERROR_MESSAGE);
			} else {
				clickListener.onClick(image, selected + 2);
			}
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
			Image newImage = resized.getScaledInstance(253, -1, 0);
			labelImge.setIcon(new ImageIcon(newImage));
			labelImge.setBounds(38, 130, 253, newImage.getHeight(labelImge) + 50);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public File getImage() {
		return image;
	}
}
