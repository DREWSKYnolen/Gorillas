import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Randomly generates the buildings and places them in a list to be sent to GamePanel to be drawn
 * @author Drew Nolen
 * @version 4-25-2016
 *
 */
public class CityScape 
{
	final int WINDOW_WIDTH = 1000;
	final int WINDOW_HEIGHT = 750;
	static ArrayList<File> buildListFile = new ArrayList<File>();
	Random buildRand = new Random();
	int buildMax = 17;

	/**
	 * Generates a random number to place in a file name to input that building image
	 */
	public CityScape()
	{
		for (int i = 0; i < 8; i++)
		{
			String filename = "Buildings\\building" + (buildRand.nextInt(buildMax)+1) + ".png";
			File building = new File(filename);
			buildListFile.add(building);
		}
		
		
	}
}
