package view.components.pagination;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class DefaultPaginationItemRender  {
 
    static public JButton createPaginationItem(Object value,boolean isSelected,boolean enable) {
        JButton cmd = new JButton(value.toString());
        cmd.setBorderPainted(false);
        cmd.setOpaque(true);
        cmd.setFont(new Font("Tahoma", Font.BOLD, 15));
        cmd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        if(isSelected) {
        	cmd.setSelected(isSelected);
        	cmd.setBackground(new Color(249, 13, 72));
        	cmd.setForeground(Color.BLACK);
        }else {
	    	cmd.setBackground(Color.WHITE);
	    	cmd.setForeground(Color.BLACK);
	    	
	    	cmd.addMouseListener(new MouseAdapter() {
	    		@Override
	    		public void mouseEntered(MouseEvent e) {
	    			cmd.setBackground(new Color(0, 0, 128));
	    			cmd.setForeground(Color.WHITE);
	    		}
	    		
	    		@Override
	    		public void mouseExited(MouseEvent e) {
	    			cmd.setBackground(Color.WHITE);
	            	cmd.setForeground(Color.BLACK);
	    		}
	    	});
        }
        
        if (!enable) {
            cmd.setFocusable(false);
        }
     
        return cmd;
    }

}