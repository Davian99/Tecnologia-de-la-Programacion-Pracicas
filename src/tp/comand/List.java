package tp.comand;
import tp.p2.*;

public class List extends Command{

	public List(){
		
	}
	
	
	public Command parse(String argumentos) {	
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 1)
			return null;
		
		if (!args[0].equalsIgnoreCase("l") && !args[0].equalsIgnoreCase("list"))
			return null;
		
		return new List();
	}
	
	public void execute(Controller control, Game game) {
		
		control.printListPlantas();
		control.changeIfPrint(false);
	}
	
	public String info(){
		return "[L]ist: Muestra el nombre de las plantas disponibles y su coste\n";
	}
}
