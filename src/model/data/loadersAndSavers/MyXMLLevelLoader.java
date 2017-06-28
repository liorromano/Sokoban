package model.data.loadersAndSavers;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.InputStream;

import model.Level;

public class MyXMLLevelLoader implements LevelLoader {
	/**
	 * This function loads level from XML file.
	 */
	public Level loadLevel(InputStream in)
	{
		Level level=null;
		XMLDecoder decoder=null;
		try{
			decoder=new XMLDecoder(new BufferedInputStream(in));
			level=(Level)decoder.readObject();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		decoder.close();
		return level;

    }

}


