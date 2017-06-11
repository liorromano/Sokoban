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

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.generalObjects.Box;
import model.data.generalObjects.Player;
import model.data.generalObjects.PlayerOnTarget;
import model.data.generalObjects.Point;
import model.data.generalObjects.Target;
import model.policy.GeneralSokobanPolicy;


public class Level implements Serializable{

	private static final long serialVersionUID = 1L;
    private int height=0;
    private int countBoxOnTarget=0;
    private int numOfTargets=0;
    private HashMap<Point, String> hashMap = new HashMap<Point, String>();
    private ArrayList<Player> players=new ArrayList<Player>();
    private ArrayList<Target> targetsArray = new ArrayList<Target> ();
    private ArrayList<Box> boxesArray = new ArrayList<Box>();
    private ArrayList<PlayerOnTarget> playersOnTarget=new ArrayList<PlayerOnTarget>();
    private String levelString = "";
    private GeneralSokobanPolicy policy=null;
    private ArrayList<Integer> rowWidth=new ArrayList<Integer>();
    private int maxWidth=0;
    private int steps=0;
    private String name;

public Level ()
    {}

   public Level(String inputStreamString) {
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
    			boxesArray.add(new Box(x,y));
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
    			targetsArray.add(new Target(x,y));
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
    			boxesArray.add(new Box(x,y));
    			targetsArray.add(new Target(x,y));
    			hashMap.put(new Point(x,y),"BOXONTARGET");
    			countBoxOnTarget++;
    			numOfTargets++;
    			x++;
    			break;
    		}
    		case '%':
    		{
    			targetsArray.add(new Target(x,y));
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
    	height=y+1;

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
public int getMaxWidth() {
	return maxWidth;
}
public void setMaxWidth(int maxWidth) {
	this.maxWidth = maxWidth;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}

public int getSteps() {
	return steps;
}
public void setSteps(int steps) {
	this.steps = steps;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ArrayList<Box> getBoxesArray() {
	return boxesArray;
}
public ArrayList<Target> getTargetsArray() {
	return targetsArray;
}
public void setBoxesArray(ArrayList<Box> boxesArray) {
	this.boxesArray = boxesArray;
}
public void setTargetsArray(ArrayList<Target> targetsArray) {
	this.targetsArray = targetsArray;
}

public ArrayList<String> getLevelByArrayListOfStrings() // returns an ArrayList<String> object of the level data by the convention (box = @, wall = #, character = A, target = o)
{
	ArrayList<String> levelDataTXT = new ArrayList<String>();
	StringBuilder sb = new StringBuilder("");

	int rows=this.height;

	for (int i=0;i<rows;i++)//row
	{
		int column=this.rowWidth.get(i);
		for (int j=0;j<column;j++)//column
		{
			if (!(i==rows && j==column))
			{

				Point p=new Point(j,i);
				String s=this.getHashMap().get(p).toString();
				if(s.equals("BOX"))
				{
					sb.append("@");
				}
				else if(s.equals("WALL"))
				{
					sb.append("#");
				}
				else if(s.equals("SPACE"))
				{
					sb.append(" ");
				}
				else if(s.equals("TARGET"))
				{
					sb.append("o");
				}
				else if(s.equals("PLAYER"))
				{
					sb.append("A");
				}
				else if(s.equals("BOXONTARGET"))
				{
					sb.append("$");
				}
				else if(s.equals("PLAYERONTARGET"))
				{
					sb.append("%");
				}
				else
				{
					sb.append("\r"+"\n");
				}

			}
		}
		levelDataTXT.add(new String(sb.toString()));
		sb.setLength(0);
	}
	return levelDataTXT;
}

public char[][] getLevelByChar2DArray()
 	{
 		char[][] data = new char[this.height][];
 		for(int i = 0; i < this.height; i++)
			data[i] = new char[this.rowWidth.get(i)];


		ArrayList<String> leveldata = getLevelByArrayListOfStrings();

 		for(int i = 0; i < this.height; i++)
 			for(int j = 0; j < this.rowWidth.get(i); j++)
 				data[i][j] = leveldata.get(i).charAt(j);

 		return data;
 	}

}




