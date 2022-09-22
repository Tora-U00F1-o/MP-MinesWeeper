package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class ConstructorTests {

	/**
	 * Given: Dimension y porcentaje de minas al constructor
	 * When: Creamos un tablero
	 * Then: Se creará con los datos introducidos
	 */
	@Test
	public void constructorTest() {
		int width = 2;
		int height = 8;
		int percentage = 24;
		
		Board board = new Board(width, height, percentage);
		int expectedNumMines = (int) (width*height*percentage/100+0.5);
		
		assertEquals(expectedNumMines, board.getNumberOfMines());
		assertEquals(board.rows(), height);
		assertEquals(board.cols(), width);
	}
	
}
