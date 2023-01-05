package interfaces;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverEffect implements MouseListener{
	private Color backgroundEntered;
	private Color backgroundExited;
	
	
	

	public HoverEffect(Color backgroundEntered, Color backgroundExited) {
		super();
		this.backgroundEntered = backgroundEntered;
		this.backgroundExited = backgroundExited;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		e.getComponent().setBackground(backgroundEntered);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setBackground(backgroundExited);
	}

	public Color getBackgroundEntered() {
		return backgroundEntered;
	}

	public void setBackgroundEntered(Color backgroundEntered) {
		this.backgroundEntered = backgroundEntered;
	}

	public Color getBackgroundExited() {
		return backgroundExited;
	}

	public void setBackgroundExited(Color backgroundExited) {
		this.backgroundExited = backgroundExited;
	}

	
}
