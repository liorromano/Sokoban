package controller.commands;

import java.util.List;

import model.Model;

public class MoveCommand extends Command {

	private Model model;
	
	public MoveCommand(Model model) {
		this.model = model;
	}
	
	@Override
	public void execute() {
		String direction = params.get(0);
		model.move(direction);
		
	}

@Override
public void setParams(List<String> params) {
	this.params=params;
	
}

}
