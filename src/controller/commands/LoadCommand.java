package controller.commands;


import model.Model;


public class LoadCommand extends Command {

	
	private Model model;
	
	public LoadCommand (Model model){this.model = model;}
	
	@Override
	public void execute() {
		//System.out.println("did the excute");
		String path = params.get(0);
		model.load(path);
	}
	


}
