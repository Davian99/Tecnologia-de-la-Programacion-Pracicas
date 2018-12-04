package tp.comand;

import tp.p2.*;

public abstract class Command {
	
	public abstract Command parse(String argumentos);
	
	public abstract void execute(Controller control, Game g);

	public abstract String info();

}
