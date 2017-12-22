import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

/**
 * Class that controls the poop
 * @author Drew Nolen
 * @version 4-25-2016
 *
 */
public class Poop implements ActionListener {

	private double x;
	private double y;

	private double xVelocity;
	private double yVelocity;
	double time = 0.25;
	double gravity = 9.8;
	boolean isFlying = true;

	/**
	 * Constructor, creates poop object
	 * @param _x x position of poop object
	 * @param _y y position of poop object
	 * @param _xVelocity velocity in x direction 
	 * @param _yVelocity velocity in y direction
	 */
	public Poop(int _x, int _y, double _xVelocity, double _yVelocity) {
		Timer timer = new Timer(1000 / 15, this);
		timer.start();
		x = _x;
		y = _y;
		xVelocity = _xVelocity;
		yVelocity = _yVelocity;
	}

	/**
	 * checks for collisions of poop object with buildings
	 */
	public void update() {
		x = x + (xVelocity * time);
		y = y + ((-1 * (yVelocity * time)) + ((0.5 * gravity) * Math.pow(time, 2)));
		try {
			if ((x >= 0 && x <= 159) || (x+45>=45 && x+45 <= 204)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(0))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
					
				}
			} else if ((x >= 160&& x < 319)||(x+45>=205 && x+45 <=364)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(1))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 320 && x <= 479)&&(x+45>=365 && x+45 <=524)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(2))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 480 && x <= 639)&&(x+45>=525 && x+45<=684)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(3))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 640 && x <= 799)&&(x+45>=685 && x+45<=844)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(4))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 800 && x <= 959)&&(x+45>=845 && x+45<=1004)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(5))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 960 && x <= 1119)&&(x+45>=1045 && x+45<=1164)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(6))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			} else if ((x >= 1120 && x <= 1279)&&(x+45>=1165 && x+45 <=1324)) {
				if (y >= GamePanel.WINDOW_HEIGHT - (ImageIO.read(GamePanel.cslist.get(7))).getHeight() - 118) {
					yVelocity = 0;
					xVelocity = 0;
					x = -75;
					y = -75;
					splatSound();
				}
			}
			

		} catch (Exception e) {

		}

		if (y <= 27)
			yVelocity = -yVelocity;

		if (x <= 15 || x >= 1250) {
			xVelocity = -xVelocity;
		}

	}

	/**
	 * Returns x value
	 * @return x value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the X value
	 * @param x x value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the Y value
	 * @return y Y value
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the Y value
	 * @param y Y Vaule
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns the x velocity
	 * @return the x velocity
	 */
	public double getxVelocity() {
		return xVelocity;
	}

	/**
	 * Sets the x velocity
	 * @param xVelocity the new x velocity
	 */
	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * Returns the Y velocity
	 * @return the y velocity
	 */
	public double getyVelocity() {
		return yVelocity;
	}

	/**
	 * Sets the Y velocity
	 * @param yVelocity the new y velocity
	 */
	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	/**
	 * Returns boolean if the poop is flying
	 * @return if the poop is flying
	 */
	public boolean getIsFlying() {
		return isFlying;
	}

	/**
	 * Sets if the poop is flying
	 * @param set if the poop s flying
	 */
	public void setIsFlying(boolean set) {
		isFlying = set;
	}

	/**
	 * Makes calls to the update method
	 */
	public void actionPerformed(ActionEvent e) {
		update();
	}
	
	/**
	 * The method that has the splat sound
	 */
	private void splatSound()
	{
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Pizza_Hit.WAV").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
}
