package model;

import java.net.Socket;

public interface Model {

	public Level getLevel();

	public void setLevel(Level level);

	public void save(String last);

	public void move(String last);

	public void load(String last);

	public void solve(Socket socket);

}
