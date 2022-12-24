package ui.components;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.PlayerMatchController;
import model.PlayerMatch;
import ui.components.pagination.EventPagination;
import ui.components.pagination.Pagination;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Ranking extends JPanel {
	
	private Pagination pagination;
	private JPanel[] user;
	private JLabel[] position;
	private JlabelRound[] photograph;
	private JLabel[] name;
	private JLabel[] duration;
	private JLabel[] punctuation;
	private final int limit=6;
	/**
	 * Create the panel.
	 */
	public Ranking() {
		setLayout(null);
		setBounds(192, 0, 682, 548);	
		

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 509, 681, 39);
		add(panel);
		
		pagination = new Pagination();
		pagination.addEventPagination(new EventPagination() {
			@Override
			public void pageChanged(int page) {
				try {
					loadData(page);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(pagination);
		startup();

	}
	
	private void  startup() {
		int userY = 85;
		user= new JPanel[limit];
		position = new JLabel[limit];
		photograph = new JlabelRound[limit];
		name = new JLabel[limit];
		duration = new JLabel[limit];
		punctuation = new JLabel[limit];
		
		for (int i = 0; i < limit; i++) {
			user[i] = new JPanel();
			user[i].setBounds(64, userY, 577, 60);
			user[i].setLayout(null);
			user[i].setVisible(false);
			userY = user[i].getY() + 60;
			add(user[i]);
			
			position[i] = new JLabel();
			position[i].setBounds(10, 11, 46, 38);
			user[i].add(position[i]);
			
			photograph[i] = new JlabelRound();
			photograph[i].setBounds(94, 0, 70, 60);
			user[i].add(photograph[i]);
			
			name[i] = new JLabel();
			name[i].setBounds(182, 23, 227, 14);
			user[i].add(name[i]);
			
			duration[i] = new JLabel();
			duration[i].setBounds(432, 11, 135, 14);
			user[i].add(duration[i]);
			
			punctuation[i] = new JLabel();
			punctuation[i].setBounds(432, 35, 135, 14);
			user[i].add(punctuation[i]);
		}
		
		try {
			loadData(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadData(int page) throws IOException {
		System.out.println(page);
		int totalPage = PlayerMatchController.getInstance().totalPages(limit, true);
		pagination.setPagegination(page, totalPage);
		List<PlayerMatch> playerMatchs = PlayerMatchController.getInstance().findAll(page, limit, true);
		
		int classification = ((page-1) * limit ) +1;
		for (int i = 0; i < playerMatchs.size(); i++) {
			PlayerMatch playerMatch = playerMatchs.get(i);
			
			user[i].setVisible(true);
			position[i].setText(String.valueOf(classification++));
			
			BufferedImage bufferedImage = ImageIO.read(playerMatch.getPlayer().getFileOrDefault());
			ImageIcon imageIcon =  new ImageIcon(bufferedImage);
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(photograph[i].getWidth(), photograph[i].getHeight(), 100));
			photograph[i].setIcon(imageIcon);
			
			name[i].setText(playerMatch.getPlayer().getPlayerUsername());
			
			duration[i].setText(String.valueOf(playerMatch.getDuration()));
			
			punctuation[i].setText(String.valueOf(playerMatch.getPlayerPoints()));
			
		}

	}
}
