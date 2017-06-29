package controller.commands;

import java.net.Socket;

import model.Model;
import view.View;


public class SolveCommand extends Command {
	private Model model;
	private String name;
	/**
	   * C'tor to the SaveCommand.
	* @param model-this is the model that doing all the functions of the game.
	 */
	public SolveCommand (Model model,String name){
		this .name=name;
		this.model=model;
		}

	/**
	 * This function calls the save function in the model.
	 */
	@Override
	public void execute() {
		model.solve(model.getLevel(),name);
	}


}