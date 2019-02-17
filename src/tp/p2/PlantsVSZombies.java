package tp.p2;


public class PlantsVSZombies {

	public static void main(String[] args) {
		
		try {
			new Controller(args);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}

/*
Cambios
Hacer el array de games dinamico

 */
