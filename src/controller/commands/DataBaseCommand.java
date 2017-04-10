package controller.commands;

import entities.DBmanager;
import model.Model;
import view.View;

public class DataBaseCommand extends Command{

	private View view;
	private Model model;
	private DBmanager manager;

	public DataBaseCommand(View view, Model model, DBmanager manager) {
		this.view=view;
		this.model=model;
		this.manager=manager;
	}

	@Override
	public void execute() {
		manager.checkIfsave(view.getPlayerName(),model.getLevel().getName(),view.getStep(),view.getTime());

	}

}
