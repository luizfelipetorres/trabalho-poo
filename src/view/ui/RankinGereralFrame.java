package view.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.PlayerMatchController;
import model.PlayerMatch;
import util.Format;
import view.components.DefaultRankinGeneralItem;
import view.components.pagination.EventPagination;
import view.components.pagination.Pagination;

public class RankinGereralFrame extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Pagination pagination;
	private List<DefaultRankinGeneralItem> defaultRankinGenerals;
	private final int limit = 5;
	
	public RankinGereralFrame () {
		this.defaultRankinGenerals = new ArrayList<>();
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(0, 0, 1175, 670);
		
		JPanel head = new JPanel();
		head.setBounds(10, 76, 899, 31);
		head.setLayout(null);
		add(head);	
		
		JLabel heardClassification = new JLabel("Classificação");
		heardClassification.setFont(new Font("Tahoma", Font.BOLD, 11));
		heardClassification.setBounds(0, 0, 86, 31);
		heardClassification.setHorizontalAlignment(SwingConstants.CENTER);
		head.add(heardClassification);
		
		JLabel heardUsers = new JLabel("Usuário");
		heardUsers.setFont(new Font("Tahoma", Font.BOLD, 11));
		heardUsers.setBounds(87, 0, 460, 31);
		heardUsers.setHorizontalAlignment(SwingConstants.CENTER);
		head.add(heardUsers);
		
		JLabel heardPonctuation = new JLabel("Pontuação");
		heardPonctuation.setFont(new Font("Tahoma", Font.BOLD, 11));
		heardPonctuation.setBounds(689, 0, 86, 31);
		heardPonctuation.setHorizontalAlignment(SwingConstants.CENTER);
		head.add(heardPonctuation);
		
		JLabel heardDuration= new JLabel("Duração");
		heardDuration.setHorizontalAlignment(SwingConstants.CENTER);
		heardDuration.setBounds(795, 0, 104, 31);
		heardDuration.setFont(new Font("Tahoma", Font.BOLD, 11));
		head.add(heardDuration);
		
		JLabel heardIDPlayerMatch= new JLabel("ID Sala Partida");
		heardIDPlayerMatch.setFont(new Font("Tahoma", Font.BOLD, 11));
		heardIDPlayerMatch.setHorizontalAlignment(SwingConstants.CENTER);
		heardIDPlayerMatch.setBounds(557, 0, 104, 31);
		head.add(heardIDPlayerMatch);
		
		JPanel panelGerenalPagination = new JPanel();
		panelGerenalPagination.setBackground(Color.DARK_GRAY);
		panelGerenalPagination.setBounds(10, 604, 899, 66);	
		add(panelGerenalPagination);
		
		pagination = new Pagination();
		pagination.setPagegination(1, 10);
		pagination.addEventPagination(new EventPagination() {
			@Override
			public void pageChanged(int page) {
				try {
					loadData(page);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		panelGerenalPagination.add(pagination);
		
		startup();
	}
	
	private void  startup(){

		int vertexY = 106;

		for (int i = 0; i < limit; i++) {
			defaultRankinGenerals.add(new DefaultRankinGeneralItem(10, vertexY,899, 78, this));
			vertexY+=78;
		}
		
		try {
			loadData(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void loadData(int page) throws IOException {
		
		int totalPage = PlayerMatchController.getInstance().totalPages(limit, true);
		pagination.setPagegination(page, totalPage);
		List<PlayerMatch> playerMatchs = PlayerMatchController.getInstance().findAll(page, limit, true);
		
		int classification = ((page-1) * limit ) +1;
		
		if(page >= 2) {
			defaultRankinGenerals.get(0).getPosition().setIcon(null);
			defaultRankinGenerals.get(1).getPosition().setIcon(null);
			defaultRankinGenerals.get(2).getPosition().setIcon(null);
		}else {
			defaultRankinGenerals.get(0).getPosition().setText(null);
			defaultRankinGenerals.get(1).getPosition().setText(null);
			defaultRankinGenerals.get(2).getPosition().setText(null);
		}
		
		for (int i = 0; i < limit; i++) {
			defaultRankinGenerals.get(i).setVisible(false);
		}
		
		for (int i = 0; i < playerMatchs.size(); i++) {
			PlayerMatch playerMatch = playerMatchs.get(i);
			
			defaultRankinGenerals.get(i).setVisible(true);
			Format.image(playerMatch.getPlayer().getPlayerUrlImage(),defaultRankinGenerals.get(i).getPhotoPersonal());
			defaultRankinGenerals.get(i).setName(playerMatch.getPlayer().getPlayerUsername());
			defaultRankinGenerals.get(i).setIdMatchPlayer(String.valueOf(playerMatch.getId()));
			
			Format.classification(defaultRankinGenerals.get(i).getPosition(), classification);
			
			defaultRankinGenerals.get(i).setPunctuation(Format.punctuation(playerMatch.getPlayerPoints()));
			defaultRankinGenerals.get(i).setDuration(Format.hours(playerMatch.getMilliSecondsDuration()));
			
			classification++;
		}

	}

}