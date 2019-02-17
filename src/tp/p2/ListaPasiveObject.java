package tp.p2;

import tp.pasives.GraveStone;
import tp.pasives.Water;

public class ListaPasiveObject {
	private PasiveGameObject ListaPasivos[];
	private int n_elementos;
	
	public ListaPasiveObject() {
		this.ListaPasivos = new PasiveGameObject[100];
		this.n_elementos = 0;
	}
	
	public ListaPasiveObject(ListaPasiveObject l, Game game) {
		this.ListaPasivos = new PasiveGameObject[100];
		PasiveGameObject pgo;
		for(int i = 0; i < l.n_elementos; ++i) {
			if (l.ListaPasivos[i] instanceof GraveStone) {
				pgo = new GraveStone(l.ListaPasivos[i].getX(), l.ListaPasivos[i].getY(), game, ((GraveStone) l.ListaPasivos[i]).getHP());
				this.aniadirObjeto(pgo);
			} else if (l.ListaPasivos[i] instanceof Water) {
				pgo = new Water(l.ListaPasivos[i].getX(), l.ListaPasivos[i].getY(), game);
				this.aniadirObjeto(pgo);
			}
		}
		this.n_elementos = l.n_elementos;
	}

	public void aniadirObjeto(PasiveGameObject pgc) {
		this.ListaPasivos[this.n_elementos] = pgc;
		this.n_elementos++;
	}
	
	public void eliminarObjeto(int k) {
		for (int i = k; i < this.n_elementos-1; ++i)
			this.ListaPasivos[i] = this.ListaPasivos[i+1];
		this.n_elementos--;
	}
	
	public Boolean estaEnCasilla(int x, int y) {
		for (int i = 0; i < this.n_elementos; ++i) {
			if (this.ListaPasivos[i].getX() == x && this.ListaPasivos[i].getY() == y)
				return true;
		}
		return false;
	}
	
	public PasiveGameObject getObjeto(int x, int y) {
		if (this.n_elementos == 0)
			return null;
		
		for (int i = 0; i < this.n_elementos; ++i) {
			if (this.ListaPasivos[i].getX() == x && this.ListaPasivos[i].getY() == y)
				return this.ListaPasivos[i];
		}
		return null;
	}
	
}
