package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageManager {

	private File image;
	private String absolutePath;

	public ImageManager() {
	}

	public ImageManager(String directorySource) {
		this.image = this.chooseImage();
		if (this.image != null) {
			this.absolutePath = directorySource + image.getName();
			this.replace(absolutePath);
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
		System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
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
