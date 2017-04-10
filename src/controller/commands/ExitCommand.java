package controller.commands;

import controller.MyController;
import controller.server.MyServer;

import view.View;

public class ExitCommand extends Command {
	private View view;
	private MyController controller;
	private MyServer theServer;

	public ExitCommand (View view, MyController controller,MyServer theServer)
	{
		this.view=view;
		this.controller=controller;
		this.theServer=theServer;
	}


	@Override
	public void execute() {
		controller.getController().stop();
		if (theServer!=null)
			theServer.stopServer();
		view.exit();
	}



}
