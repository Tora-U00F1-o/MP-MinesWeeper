package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class StepOnTests {

	private static Board board;

	@BeforeClass
	public static void setUp() {
	// Creamos el tablero de pruebas
		Square[][] boardToTeste = new Square[3][3];
		for (int i = 0; i < boardToTeste.length; i++)
			for (int j = 0; j < boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[0][0].open();    //Casilla (0, 0) abierta
		boardToTeste[0][1].putMine();
		boardToTeste[0][1].flag();    //Casilla (1, 0) marcada con mina
		boardToTeste[0][2].flag();    //Casilla (2, 0) marcada sin mina
		boardToTeste[1][0].putMine(); //Casilla (0, 1) cerrada con mina

		board = new Board(2, boardToTeste);
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla ya descubierta
	 * Then: no hará nada
	 */
	@Test
	public void stepOnTest1() {
		board.stepOn(0,0);
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla con bandera con mina
	 * Then: no hará nada
	 */
	@Test
	public void stepOnTest2() {
		board.stepOn(1,0);
		assertTrue(board.getSquaresForTest()[0][1].hasFlag());
		assertTrue(board.getSquaresForTest()[0][1].hasMine());
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla con bandera sin mina
	 * Then: no hará nada
	 */
	@Test
	public void stepOnTest3() {
		board.stepOn(2,0);
		assertTrue(board.getSquaresForTest()[0][2].hasFlag());
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla con mina
	 * Then: Explotará el tablero y abrirá la casilla
	 */
	@Test
	public void stepOnTest4() {
		board.stepOn(0, 1);
		assertTrue(board.getSquaresForTest()[1][0].isOpen());
		assertTrue(board.isExploded());
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla con pista numérica
	 * Then: abrirá la casilla
	 */
	@Test
	public void stepOnTest5() {
		board.stepOn(1, 1);
		assertTrue(board.getSquaresForTest()[1][1].isOpen());
	}
	
	/**
	 * Given: un tablero
	 * When: stepOn en casilla vacía
	 * Then: abrirá la casilla
	 */
	@Test
	public void stepOnTest6() {
		board.stepOn(2, 2);  // la casilla (2, 2) esta vacia y cerrada
		assertTrue(board.getSquaresForTest()[2][2].isOpen());
	}
	
}
