package model.data.loadersAndSavers;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import model.Level;
import model.policy.GeneralSokobanPolicy;

public class LoadLevel {

	private Level level=null;
	private HashMap<String, LevelLoader> commands=new HashMap<String,LevelLoader>();

	public LoadLevel(GeneralSokobanPolicy policy,String path)
	{
		commands.put("txt", new MyTextLevelLoader());
		commands.put("XML", new MyXMLLevelLoader());
		commands.put("obj", new MyObjectLevelLoader());
		createLevel(policy,path);
	}

	private void createLevel(GeneralSokobanPolicy policy,String path)
	{

			String last=getLast(path);
			if (last==null)
			{
				System.out.println("please write load+load'filname.type' of the file to load ");
				return;
			}
			else if (last.equals("txt")||last.equals("XML")||last.equals("obj"))
			{
				try{
					InputStream file= new FileInputStream(path);
					this.level=commands.get(last).loadLevel(file);
					this.level.setName(getLevelName(path));
					this.level.setPolicy(policy);
				}
				catch (Exception e)
				{
					System.out.println("can't find the file");
					return;
				}
			}
			else
				System.out.println("inccorect file type, please insert type txt/XML/obj");

		}



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
	private String getLevelName(String path)
	{
		int i=path.lastIndexOf("\\");
		String word = path.substring(i+1, path.length());
		return word;
	}

	public Level getLevel(){return level;}


}