package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UnflagTests {


	private static Board board;

	@BeforeClass
	public static void setUp() {
	// Creamos el tablero de pruebas
		Square[][] boardToTeste = new Square[2][2];
		for (int i = 0; i < boardToTeste.length; i++)
			for (int j = 0; j < boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
	// Ponemos una mina en una casilla y la marcamos util para el test 1 y 2
		boardToTeste[0][0].putMine(); 
		boardToTeste[0][0].flag();
		board = new Board(0, boardToTeste);
	}
	
	/**
	 * Given: un tablero
	 * When: desmarcamos una casilla de mina con bandera
	 * Then: Desmarcará la casilla y la dejara cerrada
	 */
	@Test
	public void unflagTest1() {
		board.unflag(0, 0);
		assertTrue(!board.getSquaresForTest()[0][0].hasFlag()); //no tiene bandera
		assertTrue(board.getSquaresForTest()[0][0].getState() == Square.State.CLOSED); // está cerrada
	}
	
	/**
	 * Given: un tablero
	 * When: desmarcamos una casilla de mina sin bandera
	 * Then: no hará nada
	 */
	@Test
	public void unflagTest2() {
		board.unflag(0, 0);
		// está cerrada
		assertTrue(board.getSquaresForTest()[0][0].getState() == Square.State.CLOSED); 
	}
	
	/**
	 * Given: un tablero
	 * When: desmarcamos una casilla sin bandera
	 * Then: no hará nada
	 */
	@Test
	public void unflagTest3() {
		board.unflag(1, 0); // Esta casilla tiene una pista
		// está cerrada
		assertTrue(board.getSquaresForTest()[1][0].getState() == Square.State.CLOSED); 
	}
	
	/**
	 * Given: un tablero
	 * When: desmarcamos una casilla dos veces
	 * Then: no hará nada
	 */
	@Test
	public void unflagTest4() {
		board.unflag(1, 0); // Esta casilla tiene una pista
		board.unflag(1, 0);
		// está cerrada
		assertTrue(board.getSquaresForTest()[1][0].getState() == Square.State.CLOSED); 
	}

}
