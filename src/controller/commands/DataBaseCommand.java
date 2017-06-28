package controller.commands;

import entities.DBmanager;
import model.Model;
import view.View;

public class DataBaseCommand extends Command{

	private View view;
	private Model model;
	private DBmanager manager;

	/**
	 * This is the DataBaseCommand c'tor.
	 * @param view- this is the view of the game.
	 * @param model-this is the model that doing all the functions of the game.
	 * @param manager-this is the db manager.
	 */
	public DataBaseCommand(View view, Model model, DBmanager manager) {
		this.view=view;
		this.model=model;
		this.manager=manager;
	}

	/**
	 * This function is doing the work of the DataBaseCommand. It's check things in the db.
	 */
	@Override
	public void execute() {
		manager.checkIfsave(view.getPlayerName(),model.getLevel().getName(),view.getStep(),view.getTime());

	}

}
