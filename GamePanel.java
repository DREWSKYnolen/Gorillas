import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * The game
 * @author Drew Nolen
 * @version 4-25-2016
 *
 */
public class GamePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JFrame frame;
	static final int WINDOW_WIDTH = 1262;
	static final int WINDOW_HEIGHT = 750;
	JPanel panel = new JPanel();
	Random colorRand = new Random();
	Random heightRand = new Random();
	CityScape build = new CityScape();
	int max = 3;
	Random buildRand = new Random();
	int buildNum = buildRand.nextInt(max);
	static ArrayList<File> cslist;

	Player1 p1;
	Player2 p2;

	BufferedImage sunState;

	/**
	 * Constructor, uses the p1 and p2 players
	 * @param _p1 player 1
	 * @param _p2 player 2
	 */
	public GamePanel(Player1 _p1, Player2 _p2) {
		p1 = _p1;
		p2 = _p2;

		setVisible(true);
		Timer time = new Timer(1000 / 30, this);
		time.start();
		setLayout(null);

		this.setFocusable(true);
		panel.setBackground(Color.BLUE);

	}

	JLabel p1AngleLabel = new JLabel("Angle:");
	JLabel p1VelocityLabel = new JLabel("Velocity:");
	JLabel p2AngleLabel = new JLabel("Angle:");
	JLabel p2VelocityLabel = new JLabel("Velocity:");
	JTextArea p1Angle = new JTextArea();
	JTextArea p2Angle = new JTextArea();
	JTextArea p1Velocity = new JTextArea();
	JTextArea p2Velocity = new JTextArea();
	JButton p1Throw = new JButton("Throw!");
	JButton p2Throw = new JButton("Throw!");

	/**
	 * Shows velocity and angle labels, text areas, and throw button during player 1's turn
	 */
	private void p1Turn() {
		p1Angle.setSize(60, 20);
		p1Velocity.setSize(60, 20);
		p1Angle.setLocation(80, 35);
		p1Velocity.setLocation(80, 60);
		p1Throw.setSize(140, 20);
		p1Throw.setLocation(1, 90);
		p1Angle.setVisible(true);
		p1Velocity.setVisible(true);
		p1Throw.setVisible(true);
		add(p1AngleLabel);
		add(p1Angle);
		add(p1Velocity);
		add(p1Throw);
		p1Throw.addActionListener(this);
		p1.setTurn(false);
		p2.setTurn(true);

	}

	/**
	 * Shows velocity and angle labels, text areas, and throw button during player 2's turn
	 */
	private void p2Turn() {
		p2Angle.setSize(60, 20);
		p2Velocity.setSize(60, 20);
		p2Angle.setLocation(WINDOW_WIDTH - 50, 36);
		p2Velocity.setLocation(WINDOW_WIDTH - 50, 60);
		p2Throw.setSize(140, 20);
		p2Throw.setLocation(WINDOW_WIDTH - 125, 90);
		p2Angle.setVisible(true);
		p2Velocity.setVisible(true);
		p2Throw.setVisible(true);
		add(p2Angle);
		add(p2Velocity);
		add(p2Throw);
		p2Throw.addActionListener(this);
		p2.setTurn(false);
		p1.setTurn(true);

	}

	/**
	 * Repaints
	 */
	private void update() {
		while (p1.getTurn()) {
			p1Turn();
			repaint();
		}
	}

	boolean flag = false;

	int xPos = 0;
	int xLGorPos;
	int yLGorPos;
	int xRGorPos;
	int yRGorPos;
	boolean p1Flag = true;
	boolean p2Flag = false;
	Poop poopObject;
	BufferedImage leftGorilla = null;
	BufferedImage rightGorilla = null;
	Image poopImg = null;
	BufferedImage gorillaHit = null;
	BufferedImage suprisedSun = null;
	BufferedImage happySun = null;

	/**
	 * Draws all components of game
	 * @param g the default graphics object
	 */
	public void paintComponent(Graphics g) {

		setLayout(null);
		Color SKY_COLOR = new Color(0, 0, 173);
		g.setColor(SKY_COLOR);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		cslist = CityScape.buildListFile;

		try {
			leftGorilla = ImageIO.read(new File("Sprites\\standing.png"));
			rightGorilla = ImageIO.read(new File("Sprites\\standing.png"));
			poopImg = ImageIO.read(new File("Sprites\\poop.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			happySun = ImageIO.read(new File("Sprites\\smile_sun.png"));
			suprisedSun = ImageIO.read(new File("Sprites\\shocked_sun.png"));
			sunState = happySun;
			g.drawImage(sunState, WINDOW_WIDTH / 2 - 45, 0, this);

			BufferedImage buildingPic;
			int xPos = 0;
			xLGorPos = WINDOW_WIDTH / 4 - 135;
			yLGorPos = WINDOW_HEIGHT - (ImageIO.read(cslist.get(1))).getHeight() - leftGorilla.getHeight() - 75;
			xRGorPos = (WINDOW_WIDTH / 2) + 330;
			yRGorPos = WINDOW_HEIGHT - (ImageIO.read(cslist.get(6))).getHeight() - rightGorilla.getHeight() - 75;
			// build0H = cslist.get(0).getHeight();

			for (int i = 0; i < 8; i++) {
				buildingPic = ImageIO.read(cslist.get(i));
				g.drawImage(buildingPic, xPos, WINDOW_HEIGHT - buildingPic.getHeight() - 75, this);
				xPos += 160;
			} // for

			g.drawImage(leftGorilla, xLGorPos, yLGorPos, this);
			g.drawImage(rightGorilla, xRGorPos, yRGorPos, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ariel", Font.PLAIN, 20));
			g.drawString(p1.getName(), 1, 20);
			FontMetrics fm = g.getFontMetrics();
			g.drawString(p2.getName(), WINDOW_WIDTH - fm.stringWidth(p2.getName()) - 10, 20);
			if (p1Flag) {
				g.drawString("Angle:", 1, 50);
				g.drawString("Velocity:", 1, 75);
				g.drawImage(poopImg, (int) poopObject.getX(), (int) poopObject.getY(), this);

			}
			if (p2Flag) {
				g.drawString("Angle:", WINDOW_WIDTH - 130, 50);
				g.drawString("Velocity:", WINDOW_WIDTH - 130, 75);
				g.drawImage(poopImg, (int) poopObject.getX(), (int) poopObject.getY(), this);

			}
			if ((poopObject.getX() >= WINDOW_WIDTH / 4 - 135 && poopObject.getX() <= GamePanel.WINDOW_WIDTH / 4)
					&& (poopObject.getY() >= WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(1))).getHeight()
							- 250)) {

				poopObject.setxVelocity(0);
				poopObject.setyVelocity(0);
				poopObject.setX(-75);
				poopObject.setY(-75);
				rightGorilla = ImageIO.read(new File("Sprites\\standing_hit.png"));
				try {
			        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Pizza_Hit.WAV").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			        ex.printStackTrace();
			    }
				repaint();
				win();

			}
			if ((poopObject.getX() >= 966 && poopObject.getX() <= 1088)
					&& (poopObject.getY() >= WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(6))).getHeight()
							- 250)) {

				poopObject.setxVelocity(0);
				poopObject.setyVelocity(0);
				poopObject.setX(-75);
				poopObject.setY(-75);
				leftGorilla = ImageIO.read(new File("Sprites\\standing_hit.png"));
				try {
			        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Pizza_Hit.WAV").getAbsoluteFile());
			        Clip clip = AudioSystem.getClip();
			        clip.open(audioInputStream);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			        ex.printStackTrace();
			    }
				repaint();
				win();

			}
			poopObject.update();

		} // try
		catch (Exception e) {
			// System.out.println(e.getMessage());
		}

	}
	
	private JFrame outcome = new JFrame("Results");
	private JFrame again = new JFrame("Play Again?");
	private JButton newGame = new JButton("Play Again");
	private JButton noMore = new JButton("Quit");

	/**
	 * Displays a play again or quit button when someone wins
	 */
	public void win() {
		again.setLayout(null);
		again.setVisible(true);
		again.setSize(400,233);
		again.setLocationRelativeTo(outcome);
		newGame.addActionListener(this);
		noMore.addActionListener(this);
		newGame.setSize(200,200);
		newGame.setLocation(0,0);
		noMore.setSize(200,200);
		noMore.setLocation(200,0);
		again.add(newGame);
		again.add(noMore);
		frame.dispose();
		
	}

	/**
	 * Takes button presses and performs actions based on the button pressed
	 */
	public void actionPerformed(ActionEvent e) {
		frame=new JFrame();
		update();
		repaint();
		if (e.getSource() == p1Throw && !(p1Angle.getText().equals("")) && !(p1Velocity.equals(""))) {

			poopObject = null;
			p1.setAngle(Integer.parseInt(p1Angle.getText()));
			p1.setVelocity(Integer.parseInt(p1Velocity.getText()));
			poopObject = new Poop(xLGorPos + 5, yLGorPos - 14 - 47, p1.getXVelocity(), p1.getYVelocity());

			p1Throw.setVisible(false);
			p1Angle.setVisible(false);
			p1Velocity.setVisible(false);
			p2Turn();
			p1Angle.setText("");
			p1Velocity.setText("");
			p1.setTurn(false);
			p2.setTurn(true);

			p1Flag = false;
			p2Flag = true;
			update();
			repaint();

		}

		if (e.getSource() == p2Throw && !(p2Angle.getText().equals("")) && !(p2Velocity.equals(""))) {
			poopObject = null;
			p2.setAngle(Integer.parseInt(p2Angle.getText()));
			p2.setVelocity(Integer.parseInt(p2Velocity.getText()));
			poopObject = new Poop(xRGorPos + 5, yRGorPos - 14 - 47, p2.getXVelocity(), p2.getYVelocity());
			p2Throw.setVisible(false);
			p2Angle.setVisible(false);
			p2Velocity.setVisible(false);
			p1Turn();
			p2Angle.setText("");
			p2Velocity.setText("");
			p1.setTurn(true);
			p2.setTurn(false);
			p2Flag = false;
			p1Flag = true;
			update();
			repaint();

		}
		if (e.getSource() ==newGame)
		{
			setVisible(false);
			frame.dispose();
			new GameFrame();
			again.dispose();
			
			
		}
		if (e.getSource() == noMore)
		{
			System.exit(0);
		}
	}

}
