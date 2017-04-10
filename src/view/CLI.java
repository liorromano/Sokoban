package view;


import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class CLI extends Observable{
	boolean isRunning = true;

	public CLI(){}

	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				Scanner scanner = new Scanner(System.in);
				System.out.println("Commands: (need to write full command)\n1.Load filename.{txt ,XML, obj}\n2.Display\n3.Move {up, down, left, right}\n4.Save filename.{txt, XML, obj}\n5.Exit");
				while (isRunning) {
					//System.out.print("Enter command: \n");
					String commandLine = scanner.nextLine();

					String[] arr = commandLine.split(" ");
					List<String> params = new LinkedList<String>();

					for (String s: arr) {
						params.add(s);
					}

					setChanged();
					notifyObservers(params);

					if (commandLine.equals("exit"))
						break;
				}
				scanner.close();
			}
		});
		thread.start();

	}
	public void stop() { this.isRunning = false; }

}










