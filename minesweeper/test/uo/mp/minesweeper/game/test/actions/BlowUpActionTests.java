package uo.mp.minesweeper.game.test.actions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class BlowUpActionTests {
	
	private Board board;
	
	@Before
	public void setUp() {
	// Creamos el tablero de pruebas
		Square[][] boardToTeste = new Square[3][3];
		for (int i = 0; i < boardToTeste.length; i++)
			for (int j = 0; j < boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[0][0].putMine();    //Casilla (0, 0) mina cerrada

		board = new Board(2, boardToTeste);
	}
	
	/**
	 * Given: un tablero con una mina
	 * When: stepOn en la casilla con mina
	 * Then: Explotará el tablero y abrirá la casilla
	 */
	@Test
	public void blowUpTest() {
		board.stepOn(0, 0);
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
		assertTrue(board.isExploded());
	}

}
