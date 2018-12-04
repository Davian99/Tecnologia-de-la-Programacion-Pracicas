package tp.comand;

import java.io.IOException;

import tp.excepciones.ArrayOutException;
import tp.excepciones.FileContentsException;
import tp.excepciones.IllegalObjectPosition;
import tp.excepciones.NotAGameObjectException;
import tp.excepciones.ParseException;
import tp.excepciones.SamePosicionException;
import tp.p2.*;

public abstract class Command {
	
	public abstract Command parse(String argumentos);
	
	public abstract boolean execute(Game g) throws IOException, FileContentsException, ArrayOutException, SamePosicionException, NotAGameObjectException, IllegalObjectPosition, ParseException;

	public abstract String info();

}
