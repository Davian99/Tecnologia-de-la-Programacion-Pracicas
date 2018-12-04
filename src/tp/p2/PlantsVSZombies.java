package tp.p2;


public class PlantsVSZombies {

	public static void main(String[] args) {
		System.out.println("Level: " + args[0].toUpperCase());
		new Controller(args);
	}
}

/*
Cambios
Eliminar el toStringD de todos los objetos y cambiar el boardToString de release para que ambos utilicen unicamente el simbolo del objeto
Hacer la funcion dañarObjeto
 */
