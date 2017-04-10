package view.commands.display;

import model.Level;

public class DisplayCharacters implements Displayer {

	
	@Override
	public void display(Level level) {
		for(int i=0;i<level.getLevelString().length();i++)
		{
			System.out.print(level.getLevelString().charAt(i));
		}
		System.out.print("\n");
		
	}

}
