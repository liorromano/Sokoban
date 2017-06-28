package controller.commands;



import model.Model;


public class SaveCommand extends Command {
	private Model model;
	/**
	   * C'tor to the SaveCommand.
	* @param model-this is the model that doing all the functions of the game.
	 */
	public SaveCommand (Model model){	this.model = model;}

	/**
	 * This function calls the save function in the model.
	 */
	@Override
	public void execute() {
		String name = params.get(0);
		model.save(name);
	}


}