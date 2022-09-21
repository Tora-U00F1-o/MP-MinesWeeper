package uo.mp.minesweeper.console;


import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.Console;

public class ConsoleGameInteractor implements GameInteractor {

	/**
	 * Informa al usuario de que el juego está listo imprimiendo la cadena "Ready to
	 * start"
	 */
	public void showReadyToStart() {
		Console.println("Ready to start");
	}

	public GameMove askMove(int heigth, int width) {
		char op = Console.readCharacter("Movements (s | f | u ):");// Pregunta operacion 
		
		int x = Console.readInteger("Coordenada x");// Pregunta coord X
		int y = Console.readInteger("Coordenada y");// Pregunta coord Y
		
		return new GameMove(op, y, x);
	}

	/**
	 * Muestra el estado del juego al usuario
	 * 
	 * @param elapsedTime Tiempo transcurrido desde el comienzo del juego
	 * @param board       Referencia al tablero de juego
	 */
	public void showGame(long elapsedTime, Board board) {
		Console.println("Time: " + elapsedTime);
		Console.println("Flags left: " + board.getFlagsLeft());
		Console.println(getBoardStatus(board));

	}

	/**
	 * Dvuelve una cadena representando el tablero en el que se juega
	 * 
	 * @param board
	 * @return
	 */
	private String getBoardStatus(Board board) {
		char[][] state = board.getStatus();
		StringBuffer bf = new StringBuffer();
		// Leyenda de numeros cabecera
		String lineaNum = "     "; 
		int p = 0;
		for (int j = 0; j < state[0].length; j++) {
			lineaNum += CodeUtil.fillStringWithWhites(""+(p++), 4);
		}
		
		// Linea separacion de filas
		String lineaSeparacion = getSeparationLine(state[0].length);

		/* formacion del tablero */
		bf.append(lineaNum + "\n");
		bf.append(lineaSeparacion + "\n");
		
		int rowNumber = 0;
		for (int i = 0; i < state.length; i++) {
			String rowNumFormated = (rowNumber/10==0)? " " +rowNumber : ""+rowNumber;
			String linVar = rowNumFormated + " | ";
			for (char c: state[rowNumber])
				linVar += c + " | ";

			bf.append(linVar + "\n");
			bf.append(lineaSeparacion + "\n");
			rowNumber++;
		}
		return bf.toString();
	}
	
	/**
	 * Devuelve una linea de caracteres del formato: 
	 * + - + - + - + ... hasta x longitud (x == numero de '-')
	 * @return
	 */
	private String getSeparationLine(int lon) {
		return "   + " +CodeUtil.stringMultiplicator("- + ", lon);
	}

	/**
	 * Informa al usuario de que el juego ha terminado.
	 */
	public void showGameFinished() {
		Console.println("Game Over");
	}

	/**
	 * Informa al usuario de que ha pisado una casilla de mina
	 */
	public void showBooommm() {
		Console.println("Boooomba");
	}

	/**
	 * Informa al usuario de que ha ganado el juego y muestra la duracion total de
	 * la partida
	 * 
	 * @param elapsedTime Tiempo de la partida
	 */
	public void showCongratulations(long elapsedTime) {
		Console.println("You Win!");
		Console.println("Time: " + elapsedTime);
	}
}
