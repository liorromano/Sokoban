package model.data.loadersAndSavers;
import java.io.FileWriter;
import java.io.IOException;

import model.Level;


public class MyTextLevelSaver implements LevelSaver{
	public void saveLevel(Level level,String name)
	{
	try
	{
	FileWriter writer = new FileWriter(name);
	for(int i=0;i<level.getLevelString().length();i++)
	{
		writer.write(level.getLevelString().charAt(i));
	}
	writer.close();
	}
	catch (IOException e) {
	  System.out.println("Caught IOException: " );
	}
}
}

