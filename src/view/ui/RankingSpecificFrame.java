package view.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.DimensionUIResource;

import controller.PlayerMatchController;
import interfaces.HoverEffect;
import model.PlayerMatch;
import util.Format;

public class RankingSpecificFrame extends JPanel {

	private static final long serialVersionUID = -4017090534925567889L;
	private int limit = 10;
	private int width = 1000;
	private int height = 600;
	private Long idMatch;
	private List<PlayerMatch> playerMatch;
	private int x;
	private int y;
	private JPanel panel;

	public RankingSpecificFrame(List<PlayerMatch> playerMatch, int limit, int x, int y, int width, int height) {
		super();
		this.playerMatch = playerMatch;
		this.limit = limit;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		panel = new JPanel();
		initialize();
	}

	public void initialize() {
		Color backgroundColor = new Color(220, 220, 220);
		MouseListener hoverEffect = new HoverEffect(Color.WHITE, backgroundColor);
		
		this.setBackground(backgroundColor);
		this.setBounds(x, y, width, height);
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

		Arrays.asList(head, headClassification, headUsers, headPunctuation, headDuration).forEach(e -> e.setBackground(backgroundColor));

		panel.setLayout(null);
		panel.setBounds(head.getX(), headHeight + head.getY(), Width, (int) (bodyHeught * playerMatch.size()));
		panel.setPreferredSize(new DimensionUIResource(Width, (int) (bodyHeught * playerMatch.size())));
		panel.setBackground(backgroundColor);
		
		JScrollPane scrollBar = new JScrollPane(panel);
		scrollBar.setBounds(head.getX(), headHeight + head.getY(), Width + 20, (int) (height * 0.89));
		scrollBar.setViewportView(panel);
		add(scrollBar);

		int classification = 1;
		int vertexY = 0;

		for (int i = 0; i < playerMatch.size(); i++) {
			JPanel body = new JPanel();
			body.setBounds(0, vertexY, (int) (width * 0.91), bodyHeught);
			body.setLayout(null);
			body.setBackground(backgroundColor);
			panel.add(body);
			vertexY += bodyHeught;

			JLabel bodyClassification = new JLabel();
			bodyClassification.setHorizontalAlignment(SwingConstants.CENTER);
			bodyClassification.setBounds(0, 0, Width / 5, bodyHeught);
			Format.classification(bodyClassification, classification);
			body.add(bodyClassification);

			JLabel bodyUsers = new JLabel(playerMatch.get(i).getPlayer().getPlayerUsername());
			bodyUsers.setHorizontalAlignment(SwingConstants.CENTER);
			bodyUsers.setBounds(Width / 5, 0, Width / 5 * 2, bodyHeught);
			body.add(bodyUsers);

			JLabel bodyPunctuation = new JLabel(Format.punctuation(playerMatch.get(i).getPlayerPoints()));
			bodyPunctuation.setHorizontalAlignment(SwingConstants.CENTER);
			bodyPunctuation.setBounds(Width / 5 * 3, 0, Width / 5, bodyHeught);
			body.add(bodyPunctuation);

			JLabel bodyDuration = new JLabel(Format.hours(playerMatch.get(i).getMilliSecondsDuration()));
			bodyDuration.setHorizontalAlignment(SwingConstants.CENTER);
			bodyDuration.setBounds(Width / 5 * 4, 0, Width / 5, bodyHeught);
			body.add(bodyDuration);

			JSeparator separator = new JSeparator();
			separator.setBounds(headHeight * 50 / 455, 0, Width, 2);
			body.add(separator);
			body.addMouseListener(hoverEffect);

			classification++;
		} 
	}

	public List<PlayerMatch> getPlayerMatch() {
		return playerMatch;
	}

	public void setPlayerMatch(List<PlayerMatch> playerMatch) {
		this.playerMatch = playerMatch;
		panel.removeAll();
		removeAll();
		initialize();
	}

}
