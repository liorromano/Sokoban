package controller.commands;


import view.View;
import controller.server.ClientHandler;
import model.Model;

public class DisplayCommand extends Command {

	private View view;
	private Model model;
	private ClientHandler clientHandler;

	public DisplayCommand(Model model, View view2,ClientHandler clientHandler)
	{
		this.clientHandler=clientHandler;
		this.model=model;
		this.view=view2;
	}


	@Override
	public void execute() {

		if (clientHandler!=null)
			clientHandler.sendLevel(model.getLevel());
		String path = "display";
		view.displayData(model.getLevel(), path);
	}
}

