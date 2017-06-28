package model.data.loadersAndSavers;


import java.util.HashMap;

import model.Level;

public class SaveLevel {

	private Level level;
	private String filename;
	private HashMap<String, LevelSaver> commands=new HashMap<String,LevelSaver>();
	/**
	 * This is the c'tor of SaveLevel.
	 * It initialize the commands Hash map.
	 */
	public SaveLevel()
	{
		commands.put("txt", new MyTextLevelSaver());
		commands.put("XML", new MyXMLLevelSaver());
		commands.put("obj", new MyObjectLevelSaver());
	}

	public void start(){saveLevel();}

	/**
	 * This function saves level to file.
	 */
	private void saveLevel()
	{
		String last=getLast(filename);
		if (last==null)
		{
			System.out.println("please write save+'filname.type' of the file to save ");
			return;
		}
		else if (last.equals("txt")||last.equals("XML")||last.equals("obj"))
		{
			try
			{
				commands.get(last).saveLevel(level, filename);
			}
			catch (Exception e)
			{
				System.out.println("inccorect file type, please write type txt/XML/obj");
				return;
			}
		}
		else
			System.out.println("inccorect file type, please insert type txt/XML/obj");
	}

	/**
	 * This function find which type of file we need to save.
	 * @param cmd-the string of the path.
	 * @return- return a string with the file type.
	 */
	private String getLast(String cmd)
	{
		int i = cmd.indexOf(".");
		if (i==(-1))
		{
			return null;
		}
		else
		{
			String word = cmd.substring(i+1, cmd.length());
			return word;
		}
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public void setLevel(Level level) {
		this.level = level;
	}

}

