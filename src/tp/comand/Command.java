package tp.comand;

import java.io.IOException;

import tp.p2.*;

public abstract class Command {
	
	public abstract Command parse(String argumentos);
	
	public abstract boolean execute(Game g) throws IOException;

	public abstract String info();

}
