package view.commands.display;

import java.util.HashMap;

import model.Level;

public class DisplayLevel {
	
private HashMap<String, Displayer> commands=new HashMap<String,Displayer>();
private Level level;
private String cmd;

	public DisplayLevel() {
		commands.put("char", new DisplayCharacters());
}

	public void start(){display();}
	
	private void display()
	{
		String last=getLast(cmd);
		try
		{
			if(last==null)
				commands.get("char").display(level);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private String getLast(String cmd)
	{
		String name=cmd;
		if (cmd.length()==7)
			return null;
		else
		{			
			String last=name.substring(8,cmd.length()); 
			return last;
		}
	}
public void setLevel(Level level) {
	this.level = level;
}
public void setCmd(String cmd) {
	this.cmd = cmd;
}
}
