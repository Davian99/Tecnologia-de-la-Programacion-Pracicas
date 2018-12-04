package tp.comand;
import tp.p2.*;

public class ZombieList extends Command{

	public ZombieList(){
		
	}
	
	
	public Command parse(String argumentos) {	
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 1)
			return null;
		
		if (!args[0].equalsIgnoreCase("zl") && !args[0].equalsIgnoreCase("zombielist"))
			return null;
		
		return new ZombieList();
	}
	
	public void execute(Controller control, Game game) {
		control.printListZombies();
		control.changeIfPrint(false);
	}
	
	public String info(){
		return "[Z]ombie[L]ist: Muestra el nombre de los zombies, su velocidad, daño y vida\n";
	}
}
