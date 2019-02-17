package tp.p2;

import java.io.BufferedWriter;
import java.io.IOException;

import tp.pasives.Sun;

public class SuncoinManager {

	private int suncoins;
	private Sun[] Suns;
	private int n_elementos;
	
	//Constructora de SuncoinManager.
	public SuncoinManager(int monedas) {
		suncoins = monedas;
		Suns = new Sun[32];
		n_elementos = 0;
	}
	
	public SuncoinManager(SuncoinManager m) {
		this.suncoins = m.suncoins;
		this.n_elementos = m.n_elementos;
		Suns = new Sun[32];
		for(int i = 0; i < m.n_elementos; ++i)
			this.Suns[i] = m.Suns[i];
	}

	public void setSuncoins(int m) {
		this.suncoins = m;
	}
	
	//Metodo que suma suncoins.
	public void aniadirSuncoin(int monedas){
		this.suncoins += monedas;
	}
	
	//Metodo que resta suncoins si hay suncoins suficientes.
	public boolean restarSuncoins(int coste) {
		if(coste <= this.suncoins){
			this.suncoins -= coste;
			return true;
		}
		return false;
	}
	
	//Metodo geter que da cuantas suncoins hay.
	public int getSunCoins() {
		return this.suncoins;
	}
	
	public boolean casillaVacia(int x, int y) {
		for (int i = 0; i < this.n_elementos; ++i) {
			if (x == this.Suns[i].getX() && y == this.Suns[i].getY())
				return false;
		}
		return true;
	}
	
	public void aniadirSun(Sun s) {
		this.Suns[n_elementos] = s;
		this.n_elementos++;
	}

	public Sun getSunBoard(int x, int y) {
		for (int i = 0; i < this.n_elementos; ++i) {
			if (x == this.Suns[i].getX() && y == this.Suns[i].getY()) {
				return this.Suns[i];
			}
		}
		return null;
	}
	
	public void update(Game g) {
		if (g.turno() % 5 == 0 && g.turno() != 0) {
			int x = g.getGenerador().nextInt(4);
			int y = g.getGenerador().nextInt(8);
			g.addSun(x, y);
		}
	}
	
	public boolean catchSun(int x, int y) {
		Sun s = this.getSunBoard(x, y);
		if(s != null){
			this.removeSun(x, y);
			this.aniadirSuncoin(10);
			return true;
		}
		
		return false;
	}

	private void removeSun(int x, int y) {
		int pos = 0;
		for (int i = 0; i < this.n_elementos; ++i) {
			if (x == this.Suns[i].getX() && y == this.Suns[i].getY()) {
				pos = i;
				break;
			}
		}
		
		for (int i = pos; i < this.n_elementos-1; ++i)
			this.Suns[i] = this.Suns[i+1];
		this.n_elementos--;
	}

	public void store(BufferedWriter bw) {
		try {
			bw.write("pasiveList: ");
			for (int i = 0; i < this.n_elementos; ++i) {
				this.Suns[i].store(bw);
				if (i + 1 != this.n_elementos)
					bw.write(", ");
			}
			bw.write("\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}














