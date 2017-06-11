package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Level;
import model.data.generalObjects.Point;

public class Displayer extends Canvas {

	private Level level;
	private StringProperty wallFileName;
	private StringProperty playerFileName;
	private StringProperty spaceFileName;
	private StringProperty boxFileName;
	private StringProperty targetFileName;
	private StringProperty playerOnTargetFileName;
	private StringProperty boxOnTargetFileName;
	private StringProperty levelCompleted;


	public void setCharacterPosition(Level level)
	{
		this.level=level;
		redraw();
	}

	public Displayer() {

		wallFileName=new SimpleStringProperty();
		playerFileName=new SimpleStringProperty();
		spaceFileName=new SimpleStringProperty();
		boxFileName=new SimpleStringProperty();
		targetFileName=new SimpleStringProperty();
		playerOnTargetFileName=new SimpleStringProperty();
		boxOnTargetFileName=new SimpleStringProperty();
		levelCompleted=new SimpleStringProperty();
	}

	public void setSokobanData (Level level)
	{
		
		this.level=level;
		redraw();
	}
	 public void redraw()
	 {
		 if (level!=null)
		 {
			 double W=getWidth();
			 double H=getHeight();

			double w=W/level.getMaxWidth();


			double h=H/(level.getHeight()+1);


			 GraphicsContext gc= getGraphicsContext2D();

			 Image wall=null;
			 Image player=null;
			Image space=null;
			Image box=null;
			Image target=null;
			Image boxOnTarget=null;
			Image playerOnTarget=null;

			 try {
				wall=new Image(new FileInputStream(wallFileName.get()));
				player=new Image(new FileInputStream(playerFileName.get()));
				space=new Image(new FileInputStream(spaceFileName.get()));
				box=new Image(new FileInputStream(boxFileName.get()));
				target=new Image(new FileInputStream(targetFileName.get()));
				boxOnTarget=new Image(new FileInputStream(boxOnTargetFileName.get()));
				playerOnTarget=new Image(new FileInputStream(playerOnTargetFileName.get()));

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			 Point p= new Point(0,0);
			 gc.clearRect(0, 0, W, H);
			 for (int i=0;i<level.getHeight();i++)
				 for(int j=0;j<level.getRowWidth().get(i);j++)
				 {

					 p.setX(j);
					 p.setY(i);
					 String s=level.getHashMap().get(p);

			    		if(s.equals("BOX"))
			    		{
			    			gc.drawImage(box,j*w, i*h, w, h);

			    		}
			    		else if(s.equals("WALL"))
			    		{
			    			gc.drawImage(wall,j*w, i*h, w, h);

			    		}
			    		else if(s.equals("SPACE"))
			    		{

			    			gc.drawImage(space,j*w, i*h, w, h);

			    		}
			    		else if(s.equals("TARGET"))
			    		{
			    			gc.drawImage(target,j*w, i*h, w, h);

			    		}
			    		else if(s.equals("PLAYER"))
			    		{
			    			gc.drawImage(player,j*w, i*h, w, h);

			    		}

		    			else if(s.equals("BOXONTARGET"))
			    		{

			    			gc.drawImage(boxOnTarget,j*w, i*h, w, h);

			    		}
		    			else if(s.equals("PLAYERONTARGET"))
			    		{

			    			gc.drawImage(playerOnTarget,j*w, i*h, w, h);

			    		}


				 }
				 }
		 }

public void finish()
{
	 GraphicsContext gc= getGraphicsContext2D();
	try {
		Image completed=new Image(new FileInputStream(levelCompleted.get()));
		gc.drawImage(completed, getWidth(), getHeight());
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}



}
	public String getWallFileName() {
		return wallFileName.get();
	}
	public void setWallFileName(String wallFileName) {
		this.wallFileName.set(wallFileName);
	}
public void setPlayerFileName(String playerFileName) {
	this.playerFileName.set(playerFileName);
}
public String getPlayerFileName() {
	return playerFileName.get();
}
public String getSpaceFileName() {
	return spaceFileName.get();
}
public void setSpaceFileName(String spaceFileName) {
	this.spaceFileName.set(spaceFileName);
}

public String getBoxFileName() {
	return boxFileName.get();
}
public void setBoxFileName(String boxFileName) {
	this.boxFileName.set(boxFileName);
}
public String getTargetFileName() {
	return targetFileName.get();
}
public void setTargetFileName(String targetFileName) {
	this.targetFileName.set(targetFileName);
}
public String getBoxOnTargetFileName() {
	return boxOnTargetFileName.get();
}
public String getPlayerOnTargetFileName() {
	return playerOnTargetFileName.get();
}

public void setBoxOnTargetFileName(String boxOnTargetFileName) {
	this.boxOnTargetFileName.set(boxOnTargetFileName);
}
public void setPlayerOnTargetFileName(String playerOnTargetFileName) {
	this.playerOnTargetFileName.set(playerOnTargetFileName);
}
public String getLevelCompleted() {
	return levelCompleted.get();
}
public void setLevelCompleted(String levelCompleted) {
	this.levelCompleted.set(levelCompleted);
}


}
