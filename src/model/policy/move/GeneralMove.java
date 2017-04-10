package model.policy.move;

import model.Level;

public abstract class GeneralMove implements Move{

	@Override
	public abstract void move(String direction);

	@Override
	public abstract Level getLevel();

}
