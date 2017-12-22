import javax.swing.JFrame;

/**
 * Creates the frame for the game
 * @author Drew Nolen
 * @version 4-25-2016
 */
public class GameFrame extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	static final int WINDOW_WIDTH = 1280;
	static final int WINDOW_HEIGHT = 720;
	
	/**
	 * Creates the window for the Menu
	 */
	public GameFrame()
	{
	setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
	setResizable(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(new Menu(this));
	setVisible(true);
	}
	
	/**
	 * Creates the window for the game
	 * @param p1 Player 1
	 * @param p2 Player 2
	 */
	public GameFrame(Player1 p1, Player2 p2)
	{
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new GamePanel(p1,p2));
		setVisible(true);
	}
	
}
	
