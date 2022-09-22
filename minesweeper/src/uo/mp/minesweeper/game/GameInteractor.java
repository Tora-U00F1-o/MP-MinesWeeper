package uo.mp.minesweeper.game;

public interface GameInteractor {

	/**
	 * Informa al usuario de que el juego est� listo para comenzar.
	 */
	public void showReadyToStart();
	
	/**
	 * Solicita al usuario un comando (s, f, u) y unas coordenadas 
	 * de fila y columna. Devuelve un objeto de tipo GameMove que 
	 * contiene la informaci�n facilitada por el usuario
	 * @param heigth  tama�o m�ximo de fila
	 * @param width  tama�o m�ximo de columna
	 * @return la informaci�n facilitada por el usuario
	 */
	public GameMove askMove(int heigth, int width);

	/**
	 * Muestra el estado del juego al usuario
	 * @param elapsedTime Tiempo transcurrido desde el comienzo del juego
	 * @param board Referencia al tablero de juego
	 */
	public void showGame(long elapsedTime, Board board);

	/**
	 * Informa al usuario de que el juego ha terminado.
	 */
	public void showGameFinished();

	/**
	 * Informa al usuario de que ha ganado el juego y muestra
	 * la duracion total de la partida
	 * @param elapsedTime Tiempo de la partida 
	 */
	public void showCongratulations(long elapsedTime);

	/**
	 * Informa al usuario de que ha pisado una casilla de mina
	 */
	public void showBooommm();

}
