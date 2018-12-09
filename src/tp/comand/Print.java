package tp.comand;
import tp.excepciones.CommandParseException;
import tp.p2.*;
import tp.printer.*;

public class Print extends Command{
	private PrintMode printMode;
	
	public Print() {
		
	}
	
	public Print(String pmode) {
		this.printMode = PrintMode.valueOf(pmode.toUpperCase());
	}

	@Override
	public Command parse(String argumentos) throws CommandParseException {
		
		String[] args = argumentos.split(" ");
		
		if (!args[0].equalsIgnoreCase("p") && !args[0].equalsIgnoreCase("printmode"))
			return null;
		
		if(args.length != 2) 
			throw new CommandParseException("Incorrect number of arguments for Print command: [P]rintmode <mode>");
		
		if (args[1].equalsIgnoreCase("r") || args[1].equalsIgnoreCase("release"))
			return new Print("release");
		
		else if (args[1].equalsIgnoreCase("d") || args[1].equalsIgnoreCase("debug"))
			return new Print("debug");
		
		else
			throw new CommandParseException("Unknown print mode: " + args[1]);
	}

	@Override
	public boolean execute(Game g) {
		g.changeGamePrinter(this.printMode);
		return true;
	}
	
	public String info(){
		return "[P]rintmode <mode>: Cambia la impresion del tablero entre release y debug\n";
	}

}
