package tp.comand;
import tp.excepciones.ArrayOutException;
import tp.excepciones.CommandExecuteException;
import tp.excepciones.NoSuncoinsException;
import tp.excepciones.CommandParseException;
import tp.excepciones.SamePosicionException;
import tp.p2.*;
import tp.plantas.Planta;

public class Add extends Command{
	private int x;
	private int y;
	private String planta;
		
	public Add(){
		
	}
	
	public Add(String planta, int x, int y){
		this.x = x;
		this.y = y;
		this.planta = planta;
	}
		
	@Override
	public Command parse(String argumentos) throws NumberFormatException, CommandParseException {	
		
	// cambio a por X texto
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("a") && !args[0].equalsIgnoreCase("add"))
			return null;
		
		if(args.length != 4) 
			throw new CommandParseException("Incorrect number of arguments for add command: [A]dd <plant><x><y>");
		
		try{
			return new Add(args[1], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
		}catch(NumberFormatException e){
			throw new NumberFormatException("Invalid argument for add command, number expected, introduced: "+args[2]+ " " +args[3]);
		}
	}

	@Override
	public boolean execute(Game g) throws CommandExecuteException {
		
		FactoryPlanta parseador = new FactoryPlanta();
		Planta p;
		
		try {
			p = parseador.parse(this.planta, x, y, g);
		} catch (CommandParseException e) {
			throw new CommandExecuteException(e.getMessage());
		}
		try {
			return g.addPlanta(p, x, y);
		} catch (ArrayOutException | NoSuncoinsException | SamePosicionException e) {
			throw new CommandExecuteException(e.getMessage());
		}
			
	}

	public String info(){
		return "[A]dd <plant><x><y>: Añade una planta en la posicion dada\n";
	}
}
