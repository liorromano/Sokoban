package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import model.data.loadersAndSavers.LoadLevel;
import model.data.loadersAndSavers.SaveLevel;
import model.policy.GeneralSokobanPolicy;
import model.policy.MySokobanPolicy;
import model.policy.move.GeneralMove;
import model.policy.move.MyMove;

public class MyModel extends Observable implements Model{

	private Level level;

	public MyModel() {

	}
	@Override
	public void load(String last)
	{
		GeneralSokobanPolicy policy=new MySokobanPolicy();
		LoadLevel create= new LoadLevel(policy,last);
		this.setLevel(create.getLevel());
		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);
	}
	@Override
	public void save(String last)
	{
		SaveLevel save= new SaveLevel();
		save.setFilename(last);
		save.setLevel(level);
		save.start();
		setChanged();
		notifyObservers();
}
	@Override
	public void move(String last)
	{

		GeneralMove move=new MyMove(level);
		move.move(last);
		this.level=move.getLevel();
		this.setChanged();
		List<String> params = new LinkedList<String>();
		params.add("display");
		this.notifyObservers(params);

	}
	@Override
	public Level getLevel() {
		return level;
	}
	@Override
	public void setLevel(Level level) {
		if (level!=null)
			this.level = level;
	}




}