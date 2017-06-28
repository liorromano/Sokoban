package controller.commands;


import view.View;
import model.Model;

public class DisplayCommand extends Command {

	private View view;
	private Model model;
	//private ClientHandler clientHandler;

	/**
	 *This is the c'tor of DisplayCommand.
	 * @param view2- this is the view of the game.
	 * @param model-this is the model that doing all the functions of the game.
	 * @param clientHandler-this handle the client requests from the server.
	 */
	public DisplayCommand(Model model, View view2)
	{
		//this.clientHandler=clientHandler;
		this.model=model;
		this.view=view2;
	}

/**
 * This is the execute function of the Display command.
 * It send the request to the view, to display the game.
 */
	@Override
	public void execute() {

		/*if (clientHandler!=null)
			clientHandler.sendLevel(model.getLevel());*/
		String path = "display";
		view.displayData(model.getLevel(), path);
	}
}

