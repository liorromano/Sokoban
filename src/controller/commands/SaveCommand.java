package controller.commands;



import model.Model;


public class SaveCommand extends Command {
	private Model model;
	
	public SaveCommand (Model model){	this.model = model;}
	
	@Override
	public void execute() {
		String name = params.get(0);
		model.save(name);
	}

	
}