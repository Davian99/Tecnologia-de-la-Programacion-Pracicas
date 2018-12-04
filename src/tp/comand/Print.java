package tp.comand;
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
	public Command parse(String argumentos) {
		
		String[] args = argumentos.split(" ");
		
		if(args.length != 2) 
			return null;
		if (!args[0].equalsIgnoreCase("p") && !args[0].equalsIgnoreCase("printmode"))
			return null;
		
		if (!args[1].equalsIgnoreCase("r") && !args[1].equalsIgnoreCase("release") &&
			!args[1].equalsIgnoreCase("d") && !args[1].equalsIgnoreCase("debug"))
			return null;
		
		if (args[1].equalsIgnoreCase("r"))
			return new Print("release");
		else if (args[1].equalsIgnoreCase("d"))
			return new Print("debug");
		
		return new Print(args[1]);
	}

	@Override
	public void execute(Controller control, Game g) {
		g.changeGamePrinter(this.printMode);
		control.changeIfPrint(true);
	}
	
	public String info(){
		return "[P]rintmode: Cambia la impresion del tablero entre release y debug\n";
	}

}
