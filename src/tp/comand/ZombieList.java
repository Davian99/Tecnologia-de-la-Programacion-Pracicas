package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public class ZombieList extends Command{

	public ZombieList(){
		
	}
	
	
	public Command parse(String argumentos) throws CommandParseException {	
		
		String[] args = argumentos.split(" ");
		
		
		
		if (!args[0].equalsIgnoreCase("zl") && !args[0].equalsIgnoreCase("zombielist"))
			return null;
		
		if(args.length != 1)
			throw new CommandParseException("Incorrect number of arguments for Save command: [Z]ombie[L]ist");
		
		return new ZombieList();
	}
	
	public boolean execute(Game game) {
		System.out.println(game.listInfoZombie());
		return false;
	}
	
	public String info(){
		return "[Z]ombie[L]ist: Muestra el nombre de los zombies, su velocidad, daño y vida\n";
	}
}
