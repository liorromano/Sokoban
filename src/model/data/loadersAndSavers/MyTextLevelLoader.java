package model.data.loadersAndSavers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.Level;

public class MyTextLevelLoader implements LevelLoader
{
	public Level loadLevel (InputStream inputStream)
	{
		try(Scanner scanner=new Scanner(new BufferedReader(new InputStreamReader(inputStream))))
				{
					String inputStreamString= scanner.useDelimiter("\\A").next();
					Level level=new Level(inputStreamString);
					level.setLevelString(inputStreamString);
					return level;
				}
	}
}