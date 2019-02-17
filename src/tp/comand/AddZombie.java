package tp.comand;
import tp.excepciones.ArrayOutException;
import tp.excepciones.CommandExecuteException;
import tp.excepciones.NoSuncoinsException;
import tp.excepciones.CommandParseException;
import tp.excepciones.SamePosicionException;
import tp.p2.*;
import tp.zombies.*;

public class AddZombie extends Command{
	private int x;
	private int y;
	private String planta;
		
	public AddZombie(){
		
	}
	
	public AddZombie(String planta, int x, int y){
		this.x = x;
		this.y = y;
		this.planta = planta;
	}
		
	@Override
	public Command parse(String argumentos) throws NumberFormatException, CommandParseException {	
		
	// cambio a por X texto
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("az") && !args[0].equalsIgnoreCase("addzombie"))
			return null;
		
		if(args.length != 4) 
			throw new CommandParseException("Incorrect number of arguments for addzombie command: [A]dd[Z]ombie <zombie><x><y>");
		
		try{
			return new AddZombie(args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
		} catch(NumberFormatException e){
			throw new NumberFormatException("Invalid argument for add command, number expected, introduced: "+args[2]+ " " +args[3]);
		}
	}

	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		
		FactoryZombie parseador = new FactoryZombie();
		Zombie z;
		try {
			z = parseador.parse(this.planta, x, y, g);
		} catch (CommandParseException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		try {
			g.update();
			return g.addZombie(z, x, y);
		} catch (ArrayOutException | NoSuncoinsException | SamePosicionException e) {
			throw new CommandExecuteException(e.getMessage());
		} catch (CommandParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
			
	}

	public String info(){
		return "[A]dd[Z]ombie <zombie><x><y>: Añade un Zombie en la posicion dada\n";
	}
}
