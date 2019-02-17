package tp.p2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import tp.printer.*;
import tp.zombies.*;
import tp.excepciones.ArrayOutException;
import tp.excepciones.NoSuncoinsException;
import tp.excepciones.CommandParseException;
import tp.excepciones.FileContentsException;
import tp.excepciones.SamePosicionException;
import tp.pasives.GraveStone;
import tp.pasives.Sun;
import tp.pasives.Water;
import tp.plantas.*;

public class Game{
	private boolean exit = false;
	private int cicleCount;
	private Random generador;
	private Level nivel;
	private ListaGameObject listaObjetos;
	private ListaPasiveObject listaPasivos;
	private ZombieManager generadorZombie;
	private DebugPrinter pantallaD;
	private ReleasePrinter pantallaR;
	private SuncoinManager monedas;
	private int tamx, tamy;
	private PrintMode printMode;
	private boolean catchSun;
	private long seed;
	private int n_zombie = 6;
	
	//Constructora para cuando tenemos una semilla.
	public Game(Level nivel, Random r, long semilla) {
		this(nivel);
		this.setGenerador(r);
		this.seed = semilla;
	}
	
	//Constructora con semilla por defecto.
	public Game(Level nivel) {
		this.tamx = 8;
		this.tamy = 20;
		this.nivel = nivel;
		cicleCount = 0;
		setGenerador(new Random());
		listaObjetos = new ListaGameObject(5);
		listaPasivos = new ListaPasiveObject();
		generadorZombie = new ZombieManager(nivel);
		pantallaR = new ReleasePrinter(tamx, tamy, 50);
		pantallaD = new DebugPrinter(tamx, tamy, 50);
		monedas = new SuncoinManager(50);
		printMode = PrintMode.RELEASE;
		this.setCatchSun(false);
		this.addGraves();
		this.addWater();
	}
	
	private void addWater() {
		int t = this.tamx * this.tamy / 20;
		int x, y;
		for (int i = 0; i < t; ++i) {
			boolean done = false;
			while(!done) {
				y = this.generador.nextInt(tamy-2)+1;
				x = this.generador.nextInt(this.tamx);
				if (!this.listaPasivos.estaEnCasilla(x, y)) {
					PasiveGameObject pgc = new Water(x, y, this);
					this.listaPasivos.aniadirObjeto(pgc);
					done = true;
				}
			}
		}
		
	}

	private void addGraves() {
		int t = this.tamx * this.tamy / 20;
		int x, y;
		for (int i = 0; i < t; ++i) {
			boolean done = false;
			while(!done) {
				y = this.generador.nextInt(tamy - 2) + 1;
				x = this.generador.nextInt(this.tamx);
				if (!this.listaPasivos.estaEnCasilla(x, y)) {
					PasiveGameObject pgc = new GraveStone(x, y, this, this.generador.nextInt(3) + 3);
					this.listaPasivos.aniadirObjeto(pgc);
					done = true;
				}
			}
		}
		
	}

	public Game() {
		this.tamx = 4;
		this.tamy = 8;
		cicleCount = 0;
		setGenerador(new Random(this.seed));
		listaObjetos = new ListaGameObject(5);
		pantallaR = new ReleasePrinter(tamx, tamy, 50);
		pantallaD = new DebugPrinter(tamx, tamy, 50);
		this.setCatchSun(false);
	}
	
	public Game(Game g) {
		this.tamx = g.tamx;
		this.tamy = g.tamy;
		this.nivel = g.nivel;
		this.cicleCount = g.cicleCount;
		this.generador = g.generador;
		
		//Esto da problemas evidentemente
		this.listaObjetos = new ListaGameObject(g.listaObjetos, this);
		this.listaPasivos = new ListaPasiveObject(g.listaPasivos, this);
		
		this.generadorZombie = new ZombieManager(g.generadorZombie);
		this.pantallaR = new ReleasePrinter(tamx, tamy, 50);
		this.pantallaD = new DebugPrinter(tamx, tamy, 50);
		this.monedas = new SuncoinManager(g.monedas);
		this.printMode = g.printMode;
		this.setCatchSun(false);
	}

	//Hace el update del juego, osea hace la ejecución de un turno.
	public void update() throws CommandParseException {
		
		//Hacemos el update de todos los objetos de la lista, llamando a un update que se 
		//Sobreescribe por el update de cada subclase
		for (int j = 0; j < listaObjetos.getElementos(); j++) {

			this.listaObjetos.obtenerPos(j).update();
		}
		//Generacion de zombies
		int zombie = this.getGenerador().nextInt(this.n_zombie);
		if(this.generadorZombie.numZombies() != 1) {
			addZombie(generarTipoZombie(zombie));
		} else {
			addZombie("C");
		}
		
		
		//Update de los suns
		this.monedas.update(this);
		
		//Aumentamos el contador de ciclo
		this.cicleCount++;
		this.setCatchSun(false);
	}
	
	public int turno() {
		return this.cicleCount;
	}
	
	public void turno(int t) {
		this.cicleCount = t;
	}
	
	public int eliminarObjeto(int x, int y) {
		return this.listaObjetos.eliminarObjeto(x, y);
	}
	
	//Metodo auxiliar para Controller que se utiliza para saber si gana alguien la partida y quien.
	public boolean winner() {
		if(!this.listaObjetos.quedanZombies() && (this.generadorZombie.numZombies() <= 0)) { // si gana el player.
			System.out.println("Game Over\nPlayer Wins");
			return true;
		}
		else if(zombieWinner()) { //Si ganan los zombies.
			System.out.println("Game Over\nZombies Wins");
			return true;
		}	
		else if (exit)
			return true;
		else
			return false;
	}	
	
	//Metodo auxiliar para el metodo winnner que comprueba si existe al menos un zombie en la columna 0.
	public boolean zombieWinner(){
		return (this.listaObjetos.zombiesGanan());
	}	
	
	//Metodo que se utiliza en update de Peashooter para ver si existe algun  zombie en su fila y golpearlo
	//Si la vida del zombie llega a 0 tambien lo mata.
	public void atacarZombies(int x, int y, int dmg, int dir, boolean toEnd) {
		GameObject gc;
		PasiveGameObject pgo;
		int i;
		
		if (dir > 0)
			i = y + 1;
		else
			i = y - 1;
		
		for (; i < this.tamy && i >= 0; i += dir) {
			if(this.listaPasivos.estaEnCasilla(x, i) || this.listaObjetos.estaEnCasilla(x, i)) {
				gc = this.listaObjetos.obtenerBoard(x, i);
				pgo = this.listaPasivos.getObjeto(x, i);
				if (pgo != null && pgo instanceof GraveStone)
					return;
				if(gc instanceof Zombie) {
					if (gc.muere(dmg)) {
						this.listaObjetos.eliminarObjeto(x, i);
					}
					if (!toEnd)	
						return;
				}
			}
		}
	}
	
	//Metodo auxiliar que enlaza mediante game al update de sunflower con SunconisManager.
	public void aniadirSunGame(int monedas) {
		this.monedas.aniadirSuncoin(monedas);
	}
	
	//Metodo auxiliar que resta los suncoins si se añade una planta.
	public boolean suficientesSuncoins(int coste) {
		return this.monedas.restarSuncoins(coste);
	}
	
	//Metodo auxiliar que dados una pos x e y comprueban si la casilla esta vacia dando igual si es zombie o planta.
	public boolean casillaVacia(int x, int y) {
		return !(listaObjetos.estaEnCasilla(x, y));
	}
	
	//Metodo que utilizan los zombies para atacar a las plantas.
	public int atacarPlanta(int x, int y, int fuerza) {
		GameObject gc;
		if(this.listaObjetos.estaEnCasilla(x, y)) {
			gc = this.listaObjetos.obtenerBoard(x, y);
			if(gc instanceof Planta) {
				if (gc.muere(fuerza)) {
					this.listaObjetos.eliminarObjeto(x, y);
				}
				if(gc instanceof SpikeWall) {
					return 1;
				}
				return 0;
			}
			return 0;
		}
		return 0;
	}
	
	public boolean atacarZombiePos(int x, int y, int dmg) {
		GameObject gc;
		if(this.listaObjetos.estaEnCasilla(x, y)) {
			gc = this.listaObjetos.obtenerBoard(x, y);
			if(gc instanceof Zombie) {
				if (gc.muere(dmg)) {
					this.listaObjetos.eliminarObjeto(x, y);
					return true;
				}
					
				return false;
			}
		}
		return false;
	}
	
	//Metodo auxiliar que hace que el controller printe el tablero.
	public void printTablero() {
		if (this.printMode == PrintMode.RELEASE) {
			this.pantallaR.encodeGame(this, this.cicleCount, monedas.getSunCoins(), generadorZombie.numZombies());
			System.out.println(this.pantallaR.boardToString());
		}
		else if (this.printMode == PrintMode.DEBUG) {
			this.pantallaD.encodeGame(this, this.cicleCount, monedas.getSunCoins(), generadorZombie.numZombies());
			System.out.println(this.pantallaD.boardToString());
		}
	}
	
	//Metodo utilizado que resetea el juego.
	public void reset(){
		
		System.out.println("Level: "+this.nivel);
		System.out.println("Semilla: "+this.seed);
		cicleCount = 0;
		this.setGenerador(new Random(this.seed));
		listaObjetos = new ListaGameObject(5);
		generadorZombie = new ZombieManager(nivel);
		//pantalla= new GameprinterObs(4, 8, 50); //Modificar al gusto
		monedas = new SuncoinManager(50);
		//this.setGenerador(new Random(this.seed));
	}

	public String printCasilla(int i, int j) {
		return this.listaObjetos.printObjeto(i, j);
	}
	
	public int objetosLista() {
		return this.listaObjetos.getElementos();
	}
	
	public GameObject getObject(int i) {
		return this.listaObjetos.obtenerPos(i);
	}
	
	public PrintMode getPrintMode() {
		return this.printMode;
	}
	
	public boolean addPlanta(Planta p, int x, int y) throws ArrayOutException, NoSuncoinsException, SamePosicionException {
		
		if (x >= this.tamx || x < 0 || y >= this.tamy-1 || y < 0) {
			throw new ArrayOutException("Failed to add " + p.toStringFull() + ": (" + x + ", " + y + ") is an invalid position.");
		}
		if (this.casillaVacia(x, y)) {
				if (this.suficientesSuncoins(p.getCoste())) {
					this.listaObjetos.aniadirPlanta(p);
					return true;
				}
				throw new NoSuncoinsException("Failed to add " + p.toStringFull() + ": not enough suncoins to buy it.");

		}
		throw new SamePosicionException("Failed to add " + p.toStringFull() + ":  position (" + x + ", " + y + ") is already occupied.");
	}
	
	public void changeGamePrinter(PrintMode change) {
		this.printMode = change;
	}

	public void addZombie(String zombie) throws CommandParseException{
		
		FactoryZombie parseador = new FactoryZombie();
		Zombie z;
		boolean salida = false;
		int cont = 0;
		
		if (generadorZombie.isZombieAdded(this.getGenerador())) {
			int pos = this.getGenerador().nextInt(this.tamx);
			while(!salida){
				if(casillaVacia(pos, this.tamy-1)) {
					z = parseador.parse(zombie, pos, this.tamy-1, this);
					this.listaObjetos.aniadirZombie(z);
					salida = true;
				}
				else pos = this.getGenerador().nextInt(this.tamx);
				cont++;
				if (cont == this.tamx-1) {
					salida = true;
				}
					
			}
		}
	}
	
	public boolean addZombie(Zombie z, int x, int y) throws ArrayOutException, NoSuncoinsException, SamePosicionException {
		
		if (x >= this.tamx || x < 0 || y >= this.tamy || y < 0) {
			throw new ArrayOutException("Failed to add " + z.toString() + ": (" + x + ", " + y + ") is an invalid position.");
		}
		if(this.casillaVacia(x, y)) {
			this.listaObjetos.aniadirZombie(z);
			return true;
		}
		throw new SamePosicionException("Failed to add " + z.toString() + ":  position (" + x + ", " + y + ") is already occupied.");
	}
	
	
	
	public String generarTipoZombie(int zombie){
		
		String tipoZombie = "";
		
		switch(zombie){
		
		case 0:
			tipoZombie = "z";
			break;
		case 1:
			tipoZombie = "b";
			break;
		case 2:
			tipoZombie = "r";
			break;
		case 3:
			tipoZombie = "a";
			break;
		case 4:
			tipoZombie = "hp";
			break;
		case 5:
			tipoZombie = "m";
			break;
		}
		
		return tipoZombie;
	}
	
	public void addSun(int x, int y) {
		if(this.monedas.casillaVacia(x, y)) {
			Sun s = new Sun(x, y, this);
			this.monedas.aniadirSun(s);
		}
		else
			return;
	}
	
	public GameObject getter(int x, int y) {
		return this.listaObjetos.obtenerBoard(x, y);
	}
	
	public boolean sunEnCasilla(int x, int y) {
		if (this.monedas.casillaVacia(x, y))
			return false;
		return true;
	}

	public Sun getSun(int x, int y) {
		return this.monedas.getSunBoard(x, y);
	}

	public Random getGenerador() {
		return generador;
	}

	public void setGenerador(Random generador) {
		this.generador = generador;
	}

	public boolean catchSunEstate() {
		return catchSun;
	}

	public void setCatchSun(boolean catchSun) {
		this.catchSun = catchSun;
	}
	
	public void cogerSun(int x, int y) {
		if (this.monedas.catchSun(x, y))
			this.setCatchSun(true);
		else
			System.out.println("No hay ningún Sol en esa casilla\n");
	}

	public String listInfoPlanta() {
		FactoryPlanta parseador = new FactoryPlanta();
		StringBuilder str = new StringBuilder();
		str.append(parseador.listAll());
		return str.toString();
	}
	
	public String listInfoZombie() {
		FactoryZombie parseador = new FactoryZombie();
		StringBuilder str = new StringBuilder();
		str.append(parseador.listAll());
		return str.toString();
	}
	
	public void resetWithGame(Game g) {
		this.cicleCount = g.cicleCount;
		this.generador = g.generador;
		this.nivel = g.nivel;
		this.listaObjetos = g.listaObjetos;
		this.generadorZombie = g.generadorZombie;
		this.pantallaD = g.pantallaD;
		this.pantallaR = g.pantallaR;
		this.monedas = g.monedas;
		this.tamx = g.tamx;
		this.tamy = g.tamy;
		this.printMode = g.printMode;
		this.catchSun = g.catchSun;
	}
	

	public void store(BufferedWriter bw) {
		try {
			bw.write("cycle: " + this.cicleCount + "\n");
			bw.write("sunCoins: " + this.monedas.getSunCoins() + "\n");
			bw.write("level: " + this.nivel.toString() + "\n");
			bw.write("remZombies: " + this.generadorZombie.numZombies() + "\n");
			this.listaObjetos.store(bw);
			this.monedas.store(bw);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}

	public void load(BufferedReader br) throws FileContentsException {
		Game g = new Game();
		g.printMode = PrintMode.RELEASE;
		try {
			String aux, args[], objeto[], coma;
			
			//Ciclo
			aux = br.readLine();
			args = aux.split(" ");
			if (!args[0].equals("cycle:"))
				throw new FileContentsException("Load failed: invalid file contents");
			g.cicleCount = Integer.parseInt(args[1]);
			
			//Monedas
			aux = br.readLine();
			args = aux.split(" ");
			if (!args[0].equals("sunCoins:"))
				throw new FileContentsException("Load failed: invalid file contents");
			g.monedas = new SuncoinManager(Integer.parseInt(args[1]));
			
			//Level
			aux = br.readLine();
			args = aux.split(" ");
			g.nivel = Level.valueOf(args[1].toUpperCase());
			if (!args[0].equals("level:"))
				throw new FileContentsException("Load failed: invalid file contents");
			g.generadorZombie = new ZombieManager(g.nivel);
		
			//Zombies restantes
			aux = br.readLine();
			args = aux.split(" ");
			if (!args[0].equals("remZombies:"))
				throw new FileContentsException("Load failed: invalid file contents");
			g.generadorZombie.setZombies(Integer.parseInt(args[1]));
			
			//Lista de objetos activos
			ListaGameObject lgo = new ListaGameObject(5);
			aux = br.readLine();
			args = aux.split(" ");
			if (!args[0].equals("activelist:"))
				throw new FileContentsException("Load failed: invalid file contents");
			if (args.length > 1) {
				int i = 1;
				Planta p;
				Zombie z;
				while(i < args.length) {
					objeto = args[i].split(":");
					if (!lgo.estaEnCasilla(Integer.parseInt(objeto[2]), Integer.parseInt(objeto[3]))
							&& Integer.parseInt(objeto[2]) < this.tamx && Integer.parseInt(objeto[2]) >= 0 
							&& Integer.parseInt(objeto[3]) < this.tamy && Integer.parseInt(objeto[3]) >= 0) {
						coma = objeto[4].substring(objeto[4].length()-1);
						if (",".equals(coma))
							objeto[4] = objeto[4].substring(0, objeto[4].length()-1);
						FactoryPlanta plantaParser = new FactoryPlanta();
						FactoryZombie zombieParser = new FactoryZombie();
						try {
							p = plantaParser.parse(objeto[0], Integer.parseInt(objeto[2]), Integer.parseInt(objeto[3]), this);
							if (Integer.parseInt(objeto[3]) == this.tamy) {
								throw new FileContentsException("Load failed: invalid file contents");
							} else {
								p.turno = Integer.parseInt(objeto[4]);
								p.vida = Integer.parseInt(objeto[1]);
								lgo.aniadirPlanta(p);
							}
						} catch (CommandParseException e) {
							try {
									z = zombieParser.parse(objeto[0], Integer.parseInt(objeto[2]), Integer.parseInt(objeto[3]), this);
									z.turno = z.getAvanzar() - Integer.parseInt(objeto[4]);
									z.vida = Integer.parseInt(objeto[1]);
									lgo.aniadirZombie(z);
								} catch (CommandParseException e1){
									throw new FileContentsException("Load failed: invalid file contents");
								}
							}
						}
					else {
						//Excepcion
						throw new FileContentsException("Load failed: invalid file contents");
					}
					++i;
				}
				g.listaObjetos = lgo;
				
			}
			
			//Soles en tablero
			aux = br.readLine();
			args = aux.split(" ");
			if (!args[0].equals("pasiveList:"))
				throw new FileContentsException("Load failed: invalid file contents");
			if (args.length > 1) {
				int i1 = 1;
				while (i1 < args.length) {
					objeto = args[i1].split(":");
					coma = objeto[2].substring(objeto[2].length()-1);
					if (",".equals(coma))
						objeto[2] = objeto[2].substring(0, objeto[2].length()-1);
					if(g.monedas.casillaVacia(Integer.parseInt(objeto[1]), Integer.parseInt(objeto[2]))) {
						g.monedas.aniadirSun(new Sun(Integer.parseInt(objeto[1]), Integer.parseInt(objeto[2]), g));
					} else
						throw new FileContentsException("Load failed: invalid file contents");
					++i1;
				}
			}
			
			resetWithGame(g);
			
		} catch (IOException e) {
			throw new FileContentsException("Load failed: invalid file contents");
		
		} catch (NumberFormatException nfe) {
			throw new FileContentsException("Load failed: invalid file contents");
		
		} catch (IllegalArgumentException iae) {
			throw new FileContentsException("Load failed: invalid file contents");
		
		} catch(ArrayIndexOutOfBoundsException aioobe) {
			throw new FileContentsException("Load failed: invalid file contents");
		
		} catch (NullPointerException e) {
			throw new FileContentsException("Load failed: invalid file contents");
		}
	}
	
	public void terminar() {
		this.exit = true;
	}
	
	public int getTamY() {
		return this.tamy;
	}

	public boolean hayPlantas() {
		return this.listaObjetos.hayPlantas();
	}

	public PasiveGameObject getPGO(int i, int j) {
		return this.listaPasivos.getObjeto(i, j);
	}
}
