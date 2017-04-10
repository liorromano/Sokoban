package controller.commands;


import model.Model;
import view.View;

public class FinishCommand extends Command{
	private View view;
	private Model model;

	public FinishCommand (View view, Model model)
	{
		this.view=view;
		this.model=model;
	}

	@Override
	public void execute() {

		view.FinishLevel(model.getLevel().getName());
	}
}
