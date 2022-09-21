package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class GetStatusTests {

	/*
 Tablero en situación de juego intermedia:alguna casilla descubierta con pista
numérica
*/

	private static Board board;

	@BeforeClass
	public static void setUp() {
		// Creamos el tablero de pruebas
			Square[][] boardToTeste = new Square[2][2];
			for (int i = 0; i < boardToTeste.length; i++)
				for (int j = 0; j < boardToTeste[0].length; j++)
					boardToTeste[i][j] = new Square();
			board = new Board(2, boardToTeste);
		}
	
	/**
	 * Given: Tablero completamente cubierto
	 * When: llamamos al getStatus
	 * Then: devolvera una matriz de caracteres '#'
	 */
	@Test
	public void getStatusTest1() {
		char[][] expected = {{'#', '#'},{'#', '#'}};
		assertArrayEquals(expected, board.getStatus());
	}
	
	/**
	 * Given: Tablero completamente descubierto.
	 * When: llamamos al getStatus
	 * Then: devolvera una matriz de caracteres ' '
	 */
	@Test
	public void getStatusTest2() {
		char[][] expected = {{' ', ' '},{' ', ' '}};
		board.unveil();
		assertArrayEquals(expected, board.getStatus());
	}
	
	/**
	 * Given: Tablero en situación de juego intermedia
	 * When: llamamos al getStatus
	 * Then: devolvera una matriz de caracteres con
	 * 			 los iconos respectivos a cada casilla
	 */
	@Test
	public void getStatusTest3() {
		setUpForTest3();
		char[][] expected = {{'1', (char) 182, '#'},{'#', '#', '#'},{'#', '#', ' '}};
		assertArrayEquals(expected, board.getStatus());
	}
	
	private void setUpForTest3() {
	// Creamos el tablero de pruebas
		Square[][] boardToTeste = new Square[3][3];
		for (int i = 0; i < boardToTeste.length; i++)
			for (int j = 0; j < boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[2][2].open();    //casilla descubierta vacía
		boardToTeste[0][1].putMine();
		boardToTeste[0][1].flag();    //casilla con bandera
		boardToTeste[0][0].open();    //casilla descubierta con pista

		board = new Board(2, boardToTeste);
	}
	
	
	
	
	
}
