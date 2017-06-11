package sokobanSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import strips.Action;
import strips.CheckPath;
import strips.Clause;
import strips.Plannable;
import strips.Predicate;
import strips.SokPredicate;

public class PredicateLevelBuilder {
	static Clause goal=new Clause(null);
	public static Clause getKB(ArrayList<char[]> level){
		Clause kb=new Clause(null);
		int boxCount=0;
		for(int i=0;i<level.size();i++){
			for(int j=0;j<level.get(i).length;j++){
				switch(level.get(i)[j]){
				case '#':kb.add(new SokPredicate("wallAt", "", i+","+j));break;
				case ' ':kb.add(new SokPredicate("clearAt", "", i+","+j));break;
				case 'A':kb.add(new SokPredicate("characterAt","", i+","+j));break;
				case '@':boxCount++;kb.add(new SokPredicate("boxAt", "b"+boxCount, i+","+j));break;
				case '$':boxCount++;kb.add(new SokPredicate("boxAt", "b"+boxCount, i+","+j));goal.add(new SokPredicate("boxAt", "?", i+","+j));break;
				case '%':kb.add(new SokPredicate("characterAt","", i+","+j));goal.add(new SokPredicate("boxAt", "?", i+","+j));break;
				case 'o':goal.add(new SokPredicate("boxAt", "?", i+","+j));
				kb.add(new SokPredicate("clearAt","", i+","+j));break;
				}
			}
		}
		return kb;
	}


	public static Plannable readFile(String fileName){
		try{

			ArrayList<char[]> level=new ArrayList<>();
			BufferedReader in=new BufferedReader(new FileReader(fileName));
			String line;
			while((line=in.readLine())!=null){
				level.add(line.toCharArray());
			}
			in.close();
			Clause kb=getKB(level);

			Plannable plannable=new Plannable() {

				@Override
				public Set<Action> getsatisfyingActions(Predicate top) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Action getsatisfyingAction(Predicate top) {

					String id=top.getId();
					String value=top.getValue();


					switch(top.getType()){

					case "characterAt":
						//getting character location
						Predicate p=getKnowledgebase().searchCharacter();
						return new Action("move",id,p.getValue()+" "+value);

					case "path":
						return new CheckPath("checkPath","", value,getKnowledgebase());

					case "clearAt":
						System.out.println("clearAt deadend"+"value "+value);
						return new Action("deadEnd", id, value);

					case "boxAt":
						Action chosen=null;
						String[] xy=value.split(",");
						int x=Integer.parseInt(xy[0]);
						int y=Integer.parseInt(xy[1]);
						String valueRight=x+","+(y-1);
						String valueLeft=x+","+(1+y);
						String valueUp=(x+1)+","+y;
						String valueDown=(x-1)+","+y;
						 List<Action> list=new ArrayList<>();
						Action right=new Action("pushRight", id, valueRight);
						if((getKnowledgebase().satisfies(new Predicate("clearAt", "", value))
								|| getKnowledgebase().satisfies(new Predicate("characterAt", "", x+","+(y-2))))
								&&((getKnowledgebase().satisfies(new Predicate("characterAt", "", value)) ||
										getKnowledgebase().satisfies(new Predicate("clearAt", "", x+","+(y-2))))))
							list.add(right);
						Action left=new Action("pushLeft", id, valueLeft);
						if((getKnowledgebase().satisfies(new Predicate("clearAt", "", value))
								|| getKnowledgebase().satisfies(new Predicate("characterAt", "", x+","+(y+2))))
								&&((getKnowledgebase().satisfies(new Predicate("characterAt", "", value)) ||
										getKnowledgebase().satisfies(new Predicate("clearAt", "", x+","+(y+2))))))
							list.add(left);
						Action up=new Action("pushUp", id, valueUp);
						if((getKnowledgebase().satisfies(new Predicate("clearAt", "", value))
								|| getKnowledgebase().satisfies(new Predicate("characterAt","", (x+2)+","+y)))
								&&((getKnowledgebase().satisfies(new Predicate("characterAt","", value)) ||
										getKnowledgebase().satisfies(new Predicate("clearAt","", (x+2)+","+y)))))
							list.add(up);
						Action down=new Action("pushDown", id, valueDown);
						if((getKnowledgebase().satisfies(new Predicate("clearAt","", value))
								|| getKnowledgebase().satisfies(new Predicate("characterAt","", (x-2)+","+y)))
								&&((getKnowledgebase().satisfies(new Predicate("characterAt","", value)) ||
										getKnowledgebase().satisfies(new Predicate("clearAt","", (x-2)+","+y)))))
							list.add(down);
						Collections.sort(list,new Comparator<Action>() {
							@Override
							public int compare(Action a2,Action a1){

								return a1.getPreconditions().numOfSatisfied(getKnowledgebase())-a2.getPreconditions().numOfSatisfied(getKnowledgebase());

							}
						});
						if(!list.isEmpty()){
						chosen=list.get(0);
						}

						return chosen;

					}


					return null;
				}

				@Override
				public Clause getKnowledgebase() {
					return kb;
				}

				@Override
				public Clause getGoal() {
					return goal;
				}
			};
			return plannable;
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

}
