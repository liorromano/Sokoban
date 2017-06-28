package controller.commands;

import java.util.List;

public abstract class Command {
	protected List<String> params;

	/**
	 * This function sets the params of the game.
	 * @param params- the parameters of the game.
	 */
	public void setParams(List<String> params) {
		this.params = params;
	}

	public abstract void execute();
}