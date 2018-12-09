package tp.excepciones;

@SuppressWarnings("serial")
public class CommandExecuteException extends Exception {
	public CommandExecuteException(String causa){
		super(causa);
	}
}
