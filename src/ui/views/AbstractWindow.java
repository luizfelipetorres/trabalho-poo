package ui.views;

import javax.swing.JPanel;

public abstract class AbstractWindow extends JPanel{

	private static final long serialVersionUID = 1L;

	public AbstractWindow() {
		setTheme();
	}
	
	protected void setTheme() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
}
