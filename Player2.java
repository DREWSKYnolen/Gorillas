/**
 * Player 2 object class
 * @author Drew Nolen
 * @version 4-25-2016
 *
 */
public class Player2
{
	String name;
	int angle;
	int velocity;
	boolean isTurn;
	boolean isHit;
	double xVelocity;
	double yVelocity;
	
	/**
	 * Constructor, sets name and creates object
	 * @param _name name of player
	 */
	public Player2(String _name)
	{
		name = _name;
		isHit = false;
		isTurn = true;
	}
	
	/**
	 * Returns name
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Sets angle that player typed in
	 * @param _angle angle that player typed in
	 */
	public void setAngle(int _angle)
	{
		angle = _angle;
	}

	
	/**
	 * Returns the angle the player typed in
	 * @return the angle the player typed in
	 */
	public int getAngle()
	{
		return angle;
	}
	
	/**
	 * Sets the velocity the player typed in
	 * @param _velocity velocity the player typed in
	 */
	public void setVelocity(int _velocity)
	{
		velocity = _velocity;
		velocityConvert();
	}
	
	/**
	 * Returns the velocity the player typed in
	 * @return the velocity the player typed in
	 */
	public int getVelocity()
	{
		return velocity;
	}
	
	/**
	 * Converts the velocity the player typed in to X and Y velocities
	 */
	private void velocityConvert()
	{
		xVelocity = -velocity * ((double)Math.cos(angle*(Math.PI/180)));
		yVelocity = velocity * ((double)Math.sin(angle*(Math.PI/180)));
	}
	
	/**
	 * Sets if it is this player's turn
	 * @param set if it is this player's turn
	 */
	public void setTurn(boolean set)
	{
		isTurn = set;
	}
	
	/**
	 * Returns if it is this player's turn
	 * @return if it is this player's turn
	 */
	public boolean getTurn()
	{
		return isTurn;
	}
	
	/**
	 * Returns if this player has been hit
	 * @return if this player has been hit
	 */
	public boolean isHit() {
		return isHit;
	}

	/**
	 * Sets if this player has been hit
	 * @param isHit if this player has been hit
	 */
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}

	/**
	 * Returns the x velocity
	 * @return the x velocity
	 */
	public double getXVelocity() {
		return xVelocity;
	}

	/**
	 * Returns the y velocity
	 * @return the y velocity
	 */
	public double getYVelocity() {
		return yVelocity;
	}
}