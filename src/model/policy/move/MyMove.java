package model.policy.move;

import java.io.Serializable;

import model.Level;
import model.data.generalObjects.Player;
import model.data.generalObjects.PlayerOnTarget;
import model.data.generalObjects.Point;

public class MyMove extends GeneralMove implements Serializable{

	private static final long serialVersionUID = 1L;
	private Level level=null;
	private int countGoals=0;
	private int countSteps;
	
	public MyMove(Level level) {
		
		this.level=level;
		this.countSteps=level.getSteps();
		this.countGoals=level.getCountBoxOnTarget();
	}
	
	@Override
	public void move(String direction) {
		
		int x;
		int y;
		
		
		if(!level.getPlayers().isEmpty())
		{
			Player c=level.getPlayers().get(0);
			x=c.getPoint().getX();
			y=c.getPoint().getY();
		}
		else
		{
			PlayerOnTarget c=level.getPlayersOnTarget().get(0);
			x=c.getPoint().getX();
			y=c.getPoint().getY();
		}
		
		Point player=new Point(x,y);
		switch(direction)
		{
	    case "left":
	        movePlayer(player,new Point(x-1,y),new Point(x-2,y), 'l');
	        break;
		case "up":
		    movePlayer(player,new Point(x,y-1),new Point(x,y-2), 'u');
		    break;
		case "right":
		    movePlayer(player,new Point(x+1,y),new Point(x+2,y), 'r');
		    break;
		case "down":
		    movePlayer(player,new Point(x,y+1),new Point(x,y+2), 'd');
	        break;           
	    default:
	    	System.out.println("please insert direction:left/right/up/down");
	        break;
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void movePlayer(Point player,Point one,Point two, char c)
    {
		
		String s=level.getHashMap().get(one);
        switch(s)
        {
            case "WALL":
                //hit a wall
            	if(level.getPolicy().WalkThroughWall()==false)
            		break;
            	
            	
            case "SPACE": //empty space, move player here
            	if (level.getHashMap().get(player).equals("PLAYERONTARGET"))
            	{
            		level.getHashMap().put(player ,"TARGET");
            		level.getHashMap().put(one,"PLAYER");
                	level.getPlayersOnTarget().remove(0);
                	level.getPlayers().add(0,new Player(one.getX(),one.getY()));
                	countSteps++;
            	}
            	else
            	{
            		level.getHashMap().put(player ,"SPACE");
            		level.getHashMap().put(one,"PLAYER");
            		level.getPlayers().get(0).setPoint(one);
            		countSteps++;
            	}
            	
          
                break;
            case "TARGET": //player on goal
            	
            	if (level.getHashMap().get(player).equals("PLAYERONTARGET"))
            	{
            		level.getHashMap().put(player ,"TARGET");
            		level.getHashMap().put(one,"PLAYERONTARGET");
                	level.getPlayersOnTarget().get(0).setPoint(one);
                	countSteps++;
            	}
            	else
            	{
            		level.getHashMap().put(player ,"SPACE");
            		level.getHashMap().put(one,"PLAYERONTARGET");
            		level.getPlayers().remove(0);
                	level.getPlayersOnTarget().add(0,new PlayerOnTarget(one.getX(),one.getY()));
                	countSteps++;
            	}
          
                break;
           case "BOX": //player moves box
        	   if(moveBoxPush(one,two)||movepullBox(one, two))
               {
               	if (level.getHashMap().get(player).equals("PLAYERONTARGET"))
               	{
               		level.getHashMap().put(player ,"TARGET");
               		level.getHashMap().put(one,"PLAYER");
                   	level.getPlayersOnTarget().remove(0);
                   	level.getPlayers().add(0,new Player(one.getX(),one.getY()));
               	}
               	else
               	{
               		level.getHashMap().put(player ,"SPACE");
               		level.getHashMap().put(one,"PLAYER");
               		level.getPlayers().get(0).setPoint(one);
               	}
               	countSteps++;
               }
                break;
           case "BOXONTARGET": //box is on goal, so player moves onto goal and box is moved
        	   if(moveBoxPush(one,two)||movepullBox(one, two))
               {
               	if (level.getHashMap().get(player).equals("PLAYERONTARGET"))
               	{
               		level.getHashMap().put(player ,"TARGET");
               		level.getHashMap().put(one,"PLAYERONTARGET");
                   	level.getPlayersOnTarget().get(0).setPoint(one);
               	}
               	else
               	{
               		level.getHashMap().put(player ,"SPACE");
               		level.getHashMap().put(one,"PLAYERONTARGET");
               		level.getPlayers().remove(0);
                	level.getPlayersOnTarget().add(0,new PlayerOnTarget(one.getX(),one.getY()));
               	}
               	countSteps++;
               }
                break;
        }                 
        level.setSteps(countSteps);     
        changeLevelStr();  
        level.setCountBoxOnTarget(countGoals);
      
    }
    

	private void changeLevelStr()

	{
		String str ="";
		int rows=level.getHeight();
		
		for (int i=0;i<=rows;i++)//row
		{
			int column=level.getRowWidth().get(i);
			for (int j=0;j<column;j++)//column
			{
				if (!(i==rows && j==column))
				{
					
					Point p=new Point(j,i);
					String s=level.getHashMap().get(p).toString();
					if(s.equals("BOX"))
					{
						str=str+"@";
					}
					else if(s.equals("WALL"))
					{
						str=str+"#";
					}
					else if(s.equals("SPACE"))
					{
						str=str+" ";	
					}
					else if(s.equals("TARGET"))
					{
						str=str+"o";	
					}
					else if(s.equals("PLAYER"))
					{
						str=str+"A";
					}
					else if(s.equals("BOXONTARGET"))
					{
						str=str+"$";
					}
					else if(s.equals("PLAYERONTARGET"))
					{
						str=str+"%";
					}
					else 
					{
						str=str+"\r"+"\n";
					}	
					
				}
		}
				
		}
				
			
		level.setLevelString(str);
	}

    public boolean moveBoxPush(Point one,Point two)
    {
        boolean result = false;
        //can't move the box if it's blocked by a wall, boxongoal, or box
        if(level.getPolicy().pushBlockedBox()==false)
        {
        	if(!level.getHashMap().get(two).equals("BOX") && !level.getHashMap().get(two).equals("BOXONTARGET") && !level.getHashMap().get(two).equals("WALL"))
        	{
        		result = true;
        		//if goal, the box is on goal, else empty space, move box here
        		String s=level.getHashMap().get(two);
        		if(s.equals("TARGET"))
        		{
        			level.getHashMap().put(two,"BOXONTARGET");
        			if (level.getHashMap().get(one).equals("BOX"))
        				countGoals++;
        		}
        		
        		else
        			level.getHashMap().put(two,"BOX");
        		if (level.getHashMap().get(one).equals("BOXONTARGET"))
        			countGoals--;
        	}
        }
		return result;

    }
    public boolean movepullBox (Point one,Point two)
    {
    	if(level.getPolicy().pullBox()==false)
    		return false;
    	else
    		return false;	
}


}

	



