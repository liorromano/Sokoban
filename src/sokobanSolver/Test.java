package sokobanSolver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import sokobanSolver.PredicateLevelBuilder;
import strips.Plannable;
import strips.Strips;

public class Test {

	public static void main(String[] args) {
		Plannable plannable=PredicateLevelBuilder.readFile("resources\\levels\\"+args[0]);

		Strips plan=new Strips();
		plan.plan(plannable);
		List<String> solution = plan.getActions();

		try
		{
		FileWriter writer = new FileWriter(args[1]);
		 for (String s : solution)
		 {
			 String save=s.replace("\n", "\r\n");
			 writer.write(save+"\r\n");
			 System.out.println(save);

		 }
		writer.close();
		}
		catch (IOException e) {
		  System.out.println("Caught IOException: " );
		}

}
}