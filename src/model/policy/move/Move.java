package model.policy.move;

import model.Level;

public interface Move {
	public void move(String direction);
	public Level getLevel();
	public void setLevel(Level level);

}
