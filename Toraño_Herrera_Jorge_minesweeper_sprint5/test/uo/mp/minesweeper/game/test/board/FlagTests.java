package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class FlagTests {

	private static Board board;

	@BeforeClass
	public static void setUp() {
	// Creamos el tablero de pruebas
		Square[][] boardToTeste = new Square[2][2];
		for (int i = 0; i < boardToTeste.length; i++)
			for (int j = 0; j < boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[0][0].putMine(); //Ponemos mina en la casilla (0,0)
		boardToTeste[0][1].flag();    //Marcamos la casilla (1, 0) = (x, y) = (j, i)

		board = new Board(4, boardToTeste);
		/* En la anterior orden, pongo que 'hay 4 minas', es falso, pero
		 * asi me dejará poner banderas en los test sin problemas 
		 */

	}
	
	/**
	 * Given: un tablero
	 * When: Bandera sobre casilla ya marcada.
	 * Then: No hará nada
	 */
	@Test
	public void flagTest1() {
		board.flag(1, 0);
		assertTrue(board.getSquaresForTest()[0][1].hasFlag()); //tiene bandera
	}
	
	/**
	 * Given: un tablero
	 * When: Bandera sobre casilla desmarcada de mina.
	 * Then: Marcará la casilla
	 */
	@Test
	public void flagTest2() {
		board.flag(0, 0);
		assertTrue(board.getSquaresForTest()[0][0].hasFlag()); //tiene bandera
	}
	
	/**
	 * Given: un tablero
	 * When: Bandera sobre casilla desmarcada sin mina.
	 * Then: Marcará la casilla
	 */
	@Test
	public void flagTest3() {
		board.flag(1, 1);
		assertTrue(board.getSquaresForTest()[1][1].hasFlag()); //tiene bandera
	}
	
	/**
	 * Given: un tablero
	 * When: Bandera sobre una casilla dos veces consecutivas.
	 * Then: La marcará en la primera llamada y la segunda no hará nada
	 */
	@Test
	public void flagTest4() {
		board.flag(0, 1); // Esta casilla tiene una pista
		board.flag(0, 1);
		// está marcada
		assertTrue(board.getSquaresForTest()[1][0].hasFlag());
	}

}
