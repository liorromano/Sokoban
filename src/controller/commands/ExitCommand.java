package controller.commands;

import controller.MyController;
import controller.server.MyServer;

import view.View;

public class ExitCommand extends Command {
	private View view;
	private MyController controller;
	private MyServer theServer;

	/**
	 *This is the c'tor of the ExitCommand.
	 * @param view- this is the view of the game.
	 * @param controller-this is the controller that managed the view and the model.
	 * @param theServer-this is a server that work with the clients.
	 */
	public ExitCommand (View view, MyController controller,MyServer theServer)
	{
		this.view=view;
		this.controller=controller;
		this.theServer=theServer;
	}

/**
 * This function stops the General controller thread that is in the controller.
 * Also, it stops the server and calls the exit function in view.
 */
	@Override
	public void execute() {
		controller.getController().stop();
		if (theServer!=null)
			theServer.stopServer();
		view.exit();
	}



}
