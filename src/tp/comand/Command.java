package tp.comand;

import tp.excepciones.CommandExecuteException;
import tp.excepciones.CommandParseException;
import tp.p2.*;

public abstract class Command {
	
	public abstract Command parse(String argumentos) throws NumberFormatException, CommandParseException;
	
	public abstract boolean execute(Game g) throws CommandExecuteException;

	public abstract String info();

}
