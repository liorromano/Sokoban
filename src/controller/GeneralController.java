
package controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import controller.commands.Command;

public class GeneralController implements Controller{

	private BlockingQueue<Command> myQueue = new ArrayBlockingQueue<Command>(1024);
	boolean isRunning = true;

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(isRunning) {
					try {
						myQueue.take().execute();
					}
					catch (Exception e) {e.printStackTrace();}
				}
				System.out.println("controller thread is close");
			}
		}
	).start();

	}
	public void stop() { this.isRunning = false; }


/**
 * This function inserts a command to the commands queue
 * @param command- This is the command that need to be inserted.
 */
	public void insertCommand(Command command) {
			try
			{
				if (command != null)
					myQueue.put(command);
			}
			catch (InterruptedException e) { e.printStackTrace(); }
		}

}
