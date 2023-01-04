package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageManager {

	private File image;
	private String absolutePath;

	public ImageManager() {
	}

	public ImageManager(String directorySource) {
		this.image = this.chooseImage();
		if (this.image != null && !image.getAbsolutePath().contains(directorySource)) {
			this.absolutePath = directorySource + image.getName();
			this.replace(absolutePath);
		}else{
			String message = "A imagem não foi atualizada. Porque o diretório da imagem selecionada coincide com o diretório destino!";
			JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void replace(String path) {

		try {
			this.copyFile(image, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private File chooseImage() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png");
		fileChooser.setFileFilter(filter);
		boolean choosed = fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION;
		return choosed ? fileChooser.getSelectedFile() : null;
	}

	public void copyFile(File source, File destination) throws IOException {

		FileInputStream inStream = new FileInputStream(source);
		FileOutputStream outStream = new FileOutputStream(destination);
		FileChannel inChannel = inStream.getChannel();
		FileChannel outChannel = outStream.getChannel();
		inChannel.transferTo(0, inChannel.size(), outChannel);
		inStream.close();
		outStream.close();

	}

	public String getAbsolutePath() {
		return absolutePath;
	}

}
