package controller.commands;


import model.Model;
import view.View;

public class FinishCommand extends Command{
	private View view;
	private Model model;

	/**
	 * C'tor of FinishCommand.
	 * @param view- this is the view of the game.
	 * @param model-this is the model that doing all the functions of the game.
	 */
	public FinishCommand (View view, Model model)
	{
		this.view=view;
		this.model=model;
	}

	/**
	 * This function calls the function FinishLevel in the view.
	 * It transfer the name of the level that the user finished.
	 */
	@Override
	public void execute() {

		view.FinishLevel(model.getLevel().getName());
	}
}
