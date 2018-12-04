package tp.comand;
import tp.p2.*;

public class Help extends Command{
		
	public Help(){
		
	}
		
		
	@Override
	public Command parse(String argumentos) {	
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 1)
			return null;
		if (!args[0].equalsIgnoreCase("h") && !args[0].equalsIgnoreCase("help"))
			return null;
		
		return new Help();
	}

	@Override
	public void execute(Controller control, Game game) {
		
		control.printInfo();
		control.changeIfPrint(false);
	}
	
	public String info(){
		return "[H]elp: ¿Tu que crees que hace don obvio?\n";
	}
}