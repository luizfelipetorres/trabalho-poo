package view.ui;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;

import controller.PlayerMatchController;
import model.PlayerMatch;
import util.Format;

public class RankingSpecificFrame extends JPanel {

	private static final long serialVersionUID = -4017090534925567889L;
	private int limit = 10;
	private int width = 1000;
	private int height = 600;
	private Long idMatch;
	

	public RankingSpecificFrame(int limit, int width, int height, Long idMatch) {
		super();
		this.limit = limit;
		this.width = width;
		this.height = height;
		this.idMatch = idMatch;
		initialize();
	}


	public void  initialize() {
		this.setBackground(UIManager.getColor("Button.highlight"));
		this.setBounds(0, 0, width, height);
		this.setLayout(null);

		int Width = (int) (width * 0.91);
		int headHeight = (int) (height * 0.07);
		int bodyHeught = (int) (height * 0.12);

		JPanel head = new JPanel();
		head.setBounds(10, 11, Width, headHeight);
		head.setLayout(null);
		add(head);

		JLabel headClassification = new JLabel("Classificação");
		headClassification.setFont(new Font("Tahoma", Font.BOLD, 11));
		headClassification.setHorizontalAlignment(SwingConstants.CENTER);
		headClassification.setBounds(0, 0, Width / 5, headHeight);
		head.add(headClassification);

		JLabel headUsers = new JLabel("Usuário");
		headUsers.setFont(new Font("Tahoma", Font.BOLD, 11));
		headUsers.setHorizontalAlignment(SwingConstants.CENTER);
		headUsers.setBounds(Width / 5, 0, Width / 5 * 2, headHeight);
		head.add(headUsers);

		JLabel headPunctuation = new JLabel("Pontuação");
		headPunctuation.setFont(new Font("Tahoma", Font.BOLD, 11));
		headPunctuation.setHorizontalAlignment(SwingConstants.CENTER);
		headPunctuation.setBounds(Width / 5 * 3, 0, Width / 5, headHeight);
		head.add(headPunctuation);

		JLabel headDuration = new JLabel("Duração");
		headDuration.setFont(new Font("Tahoma", Font.BOLD, 11));
		headDuration.setHorizontalAlignment(SwingConstants.CENTER);
		headDuration.setBounds(Width / 5 * 4, 0, Width / 5, headHeight);
		head.add(headDuration);

		List<PlayerMatch> playerMatchs = PlayerMatchController.getInstance().findByMatchID(idMatch, limit, true);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(head.getX(), headHeight + head.getY(), Width, (int) (bodyHeught * playerMatchs.size()));
		panel.setPreferredSize(new DimensionUIResource(Width, (int) (bodyHeught * playerMatchs.size())));

		JScrollPane scrollBar = new JScrollPane(panel);
		scrollBar.setBounds(head.getX(), headHeight + head.getY(), Width + 20, (int) (height * 0.89));
		scrollBar.setViewportView(panel);
		add(scrollBar);

		int classification = 1;
		int vertexY = 0;

		for (int i = 0; i < playerMatchs.size(); i++) {
			JPanel body = new JPanel();
			body.setBounds(0, vertexY, (int) (width * 0.91), bodyHeught);
			body.setLayout(null);
			panel.add(body);
			vertexY += bodyHeught;

			JLabel bodyClassification = new JLabel();
			bodyClassification.setHorizontalAlignment(SwingConstants.CENTER);
			bodyClassification.setBounds(0, 0, Width / 5, bodyHeught);
			Format.classification(bodyClassification, classification);
			body.add(bodyClassification);

			JLabel bodyUsers = new JLabel(playerMatchs.get(i).getPlayer().getPlayerUsername());
			bodyUsers.setHorizontalAlignment(SwingConstants.CENTER);
			bodyUsers.setBounds(Width / 5, 0, Width / 5 * 2, bodyHeught);
			body.add(bodyUsers);

			JLabel bodyPunctuation = new JLabel(Format.punctuation(playerMatchs.get(i).getPlayerPoints()));
			bodyPunctuation.setHorizontalAlignment(SwingConstants.CENTER);
			bodyPunctuation.setBounds(Width / 5 * 3, 0, Width / 5, bodyHeught);
			body.add(bodyPunctuation);

			JLabel bodyDuration = new JLabel(Format.hours(playerMatchs.get(i).getMilliSecondsDuration()));
			bodyDuration.setHorizontalAlignment(SwingConstants.CENTER);
			bodyDuration.setBounds(Width / 5 * 4, 0, Width / 5, bodyHeught);
			body.add(bodyDuration);

			JSeparator separator = new JSeparator();
			separator.setBounds(headHeight * 50 / 455, 0, Width, 2);
			body.add(separator);
			
			classification++;
		}
	}
}
