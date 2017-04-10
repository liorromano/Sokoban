package model.data.loadersAndSavers;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import model.Level;

public class MyXMLLevelSaver implements LevelSaver{
	public void saveLevel(Level level,String name)
	{
		
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(new FileOutputStream(name)));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("error xml");
		}
		encoder.writeObject(level);
		encoder.close();
		


} 
}
	

