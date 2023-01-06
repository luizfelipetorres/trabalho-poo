package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageManager {

	private File image;
	private String absolutePath;
	private String directorySource;

	public ImageManager() {
	}

	public ImageManager(String directorySource, boolean isFromBd) {
		this.directorySource = directorySource;

		try {
			this.image = new File(directorySource);
			if (image.isDirectory())
				throw new Exception("Foi passado um diretório. É necessário escolher uma imagem");

		} catch (Exception e) {
			this.image = this.chooseImage();

		} finally {
			this.absolutePath = image.getAbsolutePath();
			this.replace(absolutePath);

		}

	}

	private void replace(String path) {

		try {
			if (!image.getAbsolutePath().contains(directorySource))
				this.copyFile(image, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private File chooseImage() {
		JFileChooser fileChooser = new JFileChooser("img//puzzle");
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
