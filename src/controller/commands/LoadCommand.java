package controller.commands;


import model.Model;


public class LoadCommand extends Command {


	private Model model;
	/**
	 * C'tor to the LoadCommand.
	* @param model-this is the model that doing all the functions of the game.
	 */
	public LoadCommand (Model model){this.model = model;}

	/**
	 * This function calls the load function in the model.
	 */
	@Override
	public void execute() {
		//System.out.println("did the excute");
		String path = params.get(0);
		model.load(path);
	}



}
