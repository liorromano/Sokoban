package controller.commands;

import java.util.List;

import model.Model;

public class MoveCommand extends Command {

	private Model model;

	/**
	  * C'tor to the MoveCommand.
	* @param model-this is the model that doing all the functions of the game.
	 */
	public MoveCommand(Model model) {
		this.model = model;
	}

	/**
	 * This function get the direction from params and calls the move function in the model.
	 */
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
