package tp.excepciones;

@SuppressWarnings("serial")
public class ArrayOutException extends Exception{

	public ArrayOutException(String causa){
		super(causa);
	}
}
