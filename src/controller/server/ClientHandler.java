package controller.server;

import java.io.InputStream;
import java.io.OutputStream;

import model.Level;

public interface ClientHandler {
	public void handleClient(InputStream inFromClient, OutputStream outToClient);
	public void sendLevel(Level level);
}
