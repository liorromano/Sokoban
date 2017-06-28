package model.data.loadersAndSavers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.Level;



public class MyObjectLevelSaver implements LevelSaver {
	/**
	 * This function saves level to obj file.
	 */
	public void saveLevel(Level level,String name)
	{
		try{
			FileOutputStream file = new FileOutputStream(name);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(level);
			oos.close();
		}
		catch (IOException i){
			i.printStackTrace();
		}

	}

}
