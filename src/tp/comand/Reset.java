package tp.comand;
import tp.p2.*;

public class Reset extends Command{

		public Reset(){
			
		}
		
		
	@Override
	public Command parse(String argumentos) {	
		
	
		String[] args = argumentos.split(" ");
		
		if(args.length != 1) 
			return null;
		if (!args[0].equalsIgnoreCase("r") && !args[0].equalsIgnoreCase("reset"))
			return null;
		
		return new Reset();
	}

	@Override
	public void execute(Controller control, Game game) {
		game.reset(control.getSemilla());
	}
	
	public String info(){
		return "[R]eset: Reinicia la partida\n";
	}
}