package tp.comand;
import tp.p2.*;

public class Exit extends Command{

	public Exit(){
		
	}
		
	@Override
	public Command parse(String argumentos) {	
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 1) 
			return null;
		
		if (!args[0].equalsIgnoreCase("e") && !args[0].equalsIgnoreCase("exit"))
			return null;
		
		return new Exit();
	}

	@Override
	public void execute(Controller control, Game g) {
		control.terminar();
	}
	
	public String info(){
		return "[E]xit: Terminas la partida.\n";
	}
}