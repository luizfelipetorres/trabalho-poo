package ui;

public class Principal {
	public static void main(String[] args) {
		try {
			LoadingPage frame = new LoadingPage();
			frame.setVisible(true);
			for(int i = 0 ; i <= 100;  i+=3) {
				try {
					 Thread.sleep(300);
					 frame.progressBar.setValue(i);
					 frame.porcentagem.setText(i + "%");
				} catch (InterruptedException e) {
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
