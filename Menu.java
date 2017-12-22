import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * THe menu panel
 * @author Drew Nolen
 * @version 4-25-2016
 *
 */
public class Menu extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	static final int WINDOW_WIDTH = 1280;
	static final int WINDOW_HEIGHT = 720;
	private JTextField p1NameArea = new JTextField("");
	private JTextField p2NameArea = new JTextField("");
	private JButton submit1 = new JButton("PLAY!");
	private JLabel p1NameQ = new JLabel("Player 1 name: (Default to \"Player 1\")");
	private JLabel p2NameQ = new JLabel("Player 2 name: (Default to \"Player 2\")");
	
	private int x = 1;
	private int y = 25;
	private int x2 = WINDOW_WIDTH-1;
	private int y2 = WINDOW_HEIGHT-100;
	
	private int xVelocity = 25;
	private int yVelocity = 0;
	private int x2Velocity = -25;
	private int y2Velocity = 0;
	private int size = 25;
	JFrame frame;
	
	/**
	 * Gives user options to add a custom name to the players
	 * @param _frame for use of disposing when done
	 */
	public Menu(JFrame _frame)
	{
		frame = _frame;
		Timer time = new Timer(1000/90,this);
		time.start();
		
		setLayout(null);
		setSize(1262,750);
		setVisible(true);
		p1NameQ.setFont(new Font("Ariel", Font.BOLD, 25));
		p1NameQ.setForeground(Color.WHITE);
		p1NameQ.setSize(500,50);
		p1NameQ.setLocation(425,100);
		p1NameArea.setFont(new Font("Ariel", Font.PLAIN, 20));
		p1NameArea.setSize(300,50);
		p1NameArea.setLocation(500,200);		
		add(p1NameQ);
		add(p1NameArea);
		p2NameQ.setFont(new Font("Ariel", Font.BOLD, 25));
		p2NameQ.setForeground(Color.WHITE);
		p2NameQ.setSize(500,50);
		p2NameQ.setLocation(425,300);
		p2NameArea.setFont(new Font("Ariel", Font.PLAIN, 20));
		p2NameArea.setSize(300,50);
		p2NameArea.setLocation(500,400);
		add(p2NameQ);
		add(p2NameArea);
		submit1.setSize(100,50);
		submit1.setLocation(600,500);
		submit1.addActionListener(this);
		add(submit1);
	}
	
	/**
	 * updates the movement of the balls on the menu
	 */
	public void update()
	{
		x = x + xVelocity;
		y = y + yVelocity;
		x2 = x2 + x2Velocity;
		y2 = y2 + y2Velocity;
		
		if (x<0)
		{
			xVelocity=25;
		}
		else if (x + size >WINDOW_WIDTH)
		{
			xVelocity = -25;
		}
		if (x2<0)
		{
			x2Velocity=25;
		}
		else if (x2 + size >WINDOW_WIDTH)
		{
			x2Velocity = -25;
		}
		
	}
	
	/**
	 * paints the balls on the screen
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		g.setColor(Color.BLUE);
		g.fillOval(x,y,size,size);
		g.setColor(Color.BLUE);
		g.fillOval(x2,y2,size,size);
	}
	
	
	/**
	 * Sets names to each player when the submit button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		if (e.getSource() == submit1)	
		{
			Player1 p1;
			Player2 p2;
			if (p1NameArea.getText().equals("")){
				p1 = new Player1("Player 1");
			}
			else
				p1 = new Player1(p1NameArea.getText());
			
			if (p2NameArea.getText().equals("")){
				p2 = new Player2("Player 2");
			}
			else
				p2 = new Player2(p2NameArea.getText());
			setVisible(false);
			frame.dispose();
			new GameFrame(p1, p2);
		}
		
	}
	
}
