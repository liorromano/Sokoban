package controller.commands;



import java.net.Socket;

import model.Model;


public class SolveCommand extends Command {
	private Model model;
	private Socket socket;
	/**
	   * C'tor to the SaveCommand.
	* @param model-this is the model that doing all the functions of the game.
	 */
	public SolveCommand (Model model,Socket socket){
		this.model = model;
		this.socket=socket;
		}

	/**
	 * This function calls the save function in the model.
	 */
	@Override
	public void execute() {
		model.solve(socket);
	}


}