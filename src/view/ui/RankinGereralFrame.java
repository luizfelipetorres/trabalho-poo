package view.ui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.PlayerMatchController;
import model.PlayerMatch;
import util.Format;
import view.components.JPhotoRound;
import view.components.pagination.EventPagination;
import view.components.pagination.Pagination;

public class RankinGereralFrame extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private Pagination pagination;
	
	private JPanel[] body;
	private JLabel[] position;
	private JLabel[] photoPersonal;
	private JLabel[] name;
	private JLabel[] ldMatchPlayer;
	private JLabel[] punctuation;
	private JLabel[] duration;
	
	private final int limit = 5;
	
	public RankinGereralFrame () {
		
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
		
		body= new JPanel[limit];
		position= new JLabel[limit];
		photoPersonal= new JPhotoRound[limit];
		name= new JLabel[limit];
		ldMatchPlayer= new JLabel[limit];
		punctuation= new JLabel[limit];
		duration= new JLabel[limit];
		
		int vertexY = 106;

		for (int i = 0; i < limit; i++) {
			body[i] = new JPanel();
			body[i].setBackground(Color.WHITE);
			body[i].setBounds(10, vertexY, 899, 78);
			body[i].setLayout(null);
			body[i].setVisible(false);
			vertexY+=78;
			add(body[i]);
			
			position[i] = new JLabel();
			position[i].setFont(new Font("Tahoma", Font.BOLD, 25));
			position[i].setHorizontalAlignment(SwingConstants.CENTER);
			position[i].setBounds(23, 16, 40, 40);;
			body[i].add(position[i]);
			
			photoPersonal[i] = new JLabel();
			photoPersonal[i].setBounds(105, 7, 73, 62);
			body[i].add(photoPersonal[i]);
		
			name[i] = new JLabel();
			name[i].setBounds(195, 25, 347, 27);
			body[i].add(name[i]);
			
			ldMatchPlayer[i] = new JLabel();
			ldMatchPlayer[i].setHorizontalAlignment(SwingConstants.CENTER);
			ldMatchPlayer[i].setBounds(557, 25, 104, 27);
			body[i].add(ldMatchPlayer[i]);
			
			punctuation[i] = new JLabel();
			punctuation[i].setHorizontalAlignment(SwingConstants.CENTER);
			punctuation[i].setBounds(689, 25, 86, 27);
			body[i].add(punctuation[i]);
			
			duration[i] = new JLabel();
			duration[i].setHorizontalAlignment(SwingConstants.CENTER);
			duration[i].setBounds(795, 25, 104, 27);
			body[i].add(duration[i]);
			
			JSeparator separator= new JSeparator();
			separator.setBounds(0, 73, 899, 5);
			body[i].add(separator);
			
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
			position[0].setIcon(null);
			position[1].setIcon(null);
			position[2].setIcon(null);
		}else {
			position[0].setText(null);
			position[1].setText(null);
			position[2].setText(null);
		}
		
		for (int i = 0; i < limit; i++) {
			body[i].setVisible(false);
		}
		
		for (int i = 0; i < limit; i++) {
			PlayerMatch playerMatch = playerMatchs.get(i);
			
			body[i].setVisible(true);
			Format.image(playerMatch.getPlayer().getPlayerUrlImage(),photoPersonal[i]);
			name[i].setText(playerMatch.getPlayer().getPlayerUsername());
			ldMatchPlayer[i].setText(String.valueOf(playerMatch.getId()));
			
			Format.classification(position[i], classification);
			
			punctuation[i].setText(Format.punctuation(playerMatch.getPlayerPoints()));
			duration[i].setText(Format.hours(playerMatch.getMilliSecondsDuration()));
			
			classification++;
		}

	}

}
