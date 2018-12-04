package tp.printer;

import tp.p2.Game;

public interface GamePrinter {
	abstract public void encodeGame(Game game, int cicle, int soles, int zombiesLeft);
}
