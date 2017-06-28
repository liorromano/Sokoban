package model.data.loadersAndSavers;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import model.Level;

public class MyObjectLevelLoader implements LevelLoader{

	/**
	 * This function loads level from obj file.
	 */
	public Level loadLevel (InputStream inputStream)
	{
		try{
			ObjectInputStream in = new ObjectInputStream(inputStream);
			Level level = (Level)in.readObject();
			in.close();
			return level;
		}
		catch (IOException i){
			i.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException c){
			System.out.println("Level class not found");
			c.printStackTrace();
			return null;
		}
	}

}
