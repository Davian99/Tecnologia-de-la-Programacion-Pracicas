package tp.p2;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.plantas.Planta;
import tp.zombies.Zombie;

public class ListaGameObject {
	
	private ActiveGameObject listaobjetos[];
	private int n_elementos;
	private int max_elementos;
	
	public ListaGameObject(int max) {
		this.listaobjetos = new ActiveGameObject[max];
		this.max_elementos = max;
		this.n_elementos = 0;
	}
	
	
	public int getElementos() {
		return n_elementos;
	}
	
	
	public boolean maxAlcanzado() {
		return n_elementos == max_elementos;
	}
	
	//Si se ha llegado al maximo numero de elementos en el array, se crea uno nuevo
	//con el doble de elementos, se copian todos del array anterior, y se sustituye
	//Java se encarga de eliminar el array anterior
	private void redimensionar() {
		ActiveGameObject[] nuevo = new ActiveGameObject[max_elementos * 2];
		
		for (int i = 0; i < n_elementos; ++i)
			nuevo[i] = listaobjetos[i];
		
		this.listaobjetos = nuevo;
		this.max_elementos *= 2;
	}
	
	public boolean estaEnCasilla(int x, int y) {
		if (n_elementos == 0)
			return false;
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i].getX() == x && listaobjetos[i].getY() == y)
				return true;
		}
		return false;
	}

	public boolean quedanZombies() {
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i] instanceof Zombie)
				return true;
		}
		return false;
	}


	public boolean zombiesGanan() {
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i] instanceof Zombie) {
				if(listaobjetos[i].getY() == 0)
					return true;
			}
		}
		return false;
	}
	
	public GameObject obtenerPos(int i) {
		return listaobjetos[i];
	}
	
	public GameObject obtenerBoard(int x, int y) {
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i].getX() == x && listaobjetos[i].getY() == y)
				return this.listaobjetos[i];
		}
		return null;
	}
	
	public String printObjeto(int x, int y) {
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i].getX() == x && listaobjetos[i].getY() == y)
				return this.listaobjetos[i].toString();
		}
		return "       ";
	}
	
	public void aniadirPlanta(Planta p) {
		this.listaobjetos[n_elementos] = p;
		this.n_elementos++;
		if (this.n_elementos == this.max_elementos) {
			redimensionar();
		}
	}

	public void aniadirZombie(Zombie z) {
		this.listaobjetos[n_elementos] = z;
		this.n_elementos++;
		if (this.n_elementos == this.max_elementos) {
			redimensionar();
		}
	}

	public void eliminarObjeto(int x, int y) {
		int pos = 0;
		for (int i = 0; i < n_elementos; ++i) {
			if (listaobjetos[i].getX() == x && listaobjetos[i].getY() == y)
				pos = i;
		}
		
		this.n_elementos--;
		for (int i = pos; i < this.n_elementos; ++i)
			this.listaobjetos[i] = this.listaobjetos[i+1];
		
	}


	public void store(BufferedWriter bw) {
		try {
			bw.write("activelist: ");
			for (int i = 0; i < this.n_elementos; ++i) {
				this.listaobjetos[i].store(bw);
				if (i + 1 != this.n_elementos)
					bw.write(", ");
			}
			bw.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}









































