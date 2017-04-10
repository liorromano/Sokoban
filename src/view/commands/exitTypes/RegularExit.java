package view.commands.exitTypes;


public class RegularExit extends GeneralExit {

	@Override
	public void exit() {
		System.out.println("bye bye...");
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

}
