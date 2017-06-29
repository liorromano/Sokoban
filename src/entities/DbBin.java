/*package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.generalObjects.Player;
import model.data.generalObjects.PlayerOnTarget;
import model.data.generalObjects.Point;
import model.policy.GeneralSokobanPolicy;


public class Level implements Serializable{

	private static final long serialVersionUID = 1L;
    private int height=0;
    private int countBoxOnTarget=0;
    private int numOfTargets=0;
    private HashMap<Point, String> hashMap = new HashMap<Point, String>();
    private ArrayList<Player> players=new ArrayList<Player>();
    private ArrayList<PlayerOnTarget> playersOnTarget=new ArrayList<PlayerOnTarget>();
    private String levelString = "";
    private GeneralSokobanPolicy policy=null;
    private ArrayList<Integer> rowWidth=new ArrayList<Integer>();
    private int maxWidth;

public Level ()
    {}

   public Level(String inputStreamString) {
	   this.maxWidth=0;
	   InitalizeLevel(inputStreamString);
}

    private void InitalizeLevel(String inputStreamString) {
    	int x=0;//column
		int y=0;//rows
    	for (int i=0;i<inputStreamString.length();i++)
    	{
    		switch (inputStreamString.charAt(i))
    		{
    		case '@':
    		{
    			hashMap.put(new Point(x,y),"BOX");
    			x++;
    			break;
    		}
    		case '#':
    		{
    			hashMap.put(new Point(x,y),"WALL");
    			x++;
    			break;
    		}
    		case ' ':
    		{
    			hashMap.put(new Point(x,y),"SPACE");
    			x++;
    			break;
    		}
    		case 'o':
    		{
    			hashMap.put(new Point(x,y),"TARGET");
    			numOfTargets++;
    			x++;
    			break;
    		}
    		case 'A':
    		{
    			players.add(new Player(x,y));
    			hashMap.put(new Point(x,y),"PLAYER");
    			x++;
    			break;
    		}
    		case '$':
    		{
    			hashMap.put(new Point(x,y),"BOXONTARGET");
    			countBoxOnTarget++;
    			numOfTargets++;
    			x++;
    			break;
    		}
    		case '%':
    		{
    			playersOnTarget.add(new PlayerOnTarget(x,y));
    			hashMap.put(new Point(x,y),"PLAYERONTARGET");
    			numOfTargets++;
    			x++;
    			break;
    		}

    		case '\n':
    		{
    			hashMap.put(new Point(x,y),"\r\n");
    			x++;
    			if(x>=maxWidth)
    				maxWidth=x;
    			rowWidth.add(x);
    			x=0;
    			y++;
    			break;
    		}

    		}
    	}
    	if(x>=maxWidth)
    		maxWidth=x;
    	rowWidth.add(x);
    	height=y;

}

public HashMap<Point, String> getHashMap() {
	return hashMap;
}

public void setHashMap(HashMap<Point, String> hashMap) {
	this.hashMap = hashMap;
}

    public int getHeight() { return height; }
    public int getCountBoxOnTarget(){return countBoxOnTarget;}
    public void setCountBoxOnTarget(int count){this.countBoxOnTarget=count;}
    public int numOfTargets(){return numOfTargets;}
    public ArrayList<PlayerOnTarget> getPlayersOnTarget(){return playersOnTarget;}
    public ArrayList<Player> getPlayers(){return players;}
    public String getLevelString() {

  		return levelString;
  	}

  	public void setLevelString(String levelString) {
  			this.levelString=levelString;
  	}

	public GeneralSokobanPolicy getPolicy() {
		return policy;
	}
public void setPolicy(GeneralSokobanPolicy policy) {
		this.policy = policy;
	}
	public ArrayList<Integer> getRowWidth() {
		return rowWidth;
	}
	public int getNumOfTargets() {
		return numOfTargets;
	}
public void setHeight(int height) {
	this.height = height;
}
public void setNumOfTargets(int numOfTargets) {
	this.numOfTargets = numOfTargets;
}
public void setPlayers(ArrayList<Player> players) {
	this.players = players;
}
public void setPlayersOnTarget(ArrayList<PlayerOnTarget> playersOnTarget) {
	this.playersOnTarget = playersOnTarget;
}
public void setRowWidth(ArrayList<Integer> rowWidth) {
	this.rowWidth = rowWidth;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public int getMaxWidth() {
	return maxWidth;
}
}*/

package entities;

import java.io.Serializable;

public class DbBin implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String levelName;
	private int step;
	private int time;

public DbBin ()
{
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLevelName() {
	return levelName;
}

public void setLevelName(String levelName) {
	this.levelName = levelName;
}

public int getStep() {
	return step;
}

public void setStep(int step) {
	this.step = step;
}

public int getTime() {
	return time;
}

public void setTime(int time) {
	this.time = time;
}



}




