package uo.mp.minesweeper.game.test.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class ClearActionTests {
	
	private Board board;
		
	/**
	 * Given: Tablero con casillas vacias cerradas
	 * When: llamamos al metodo stepOn de una casilla cerrada y vacia
	 * Then: Al ser todas vacias las abrira todas, por el efecto cadena 
	 */
	@Test
	public void uncoverWelcomeAreaTest4() {
		setUpTest4();
		board.stepOn(0, 0);;
		//Comprobamos que se han abierto todas
		for(int i=0; i<board.rows(); i++)
			for(int j=0; j<board.cols(); j++)
				assertTrue(board.getSquaresForTest()[i][j].isOpen());
	}
	/**
	 * Creamos un tablero 3x3 todas sus casillas vacias
	 */
	private void setUpTest4() {
		Square[][] boardToTeste = new Square[3][3];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		board = new Board(0, boardToTeste);
	}
	
	/**
	 * Given: Tablero con alguna casilla abierta y otras cerradas vacias
	 * When: llamamos al metodo stepOn de una casilla cerrada y vacia
	 * Then: Al ser todas las del borde derecho y del inferior 
	 * 				vacias las abrira todas, por el efecto cadena 
	 */
	@Test
	public void uncoverWelcomeAreaTest5() {
		setUpTest5();
		board.stepOn(0, 2);;
		/*Comprobamos que se han abierto todas las
			inferiores y las del borde derecho */
		assertTrue(board.getSquaresForTest()[0][2].isOpen());
		assertTrue(board.getSquaresForTest()[1][2].isOpen());
			for(int j=0; j<board.cols(); j++)
				assertTrue(board.getSquaresForTest()[2][j].isOpen());
	}
	/**
	 * Creamos un tablero 3x3 
	 * Algunas casillas abiertas y otras cerradas, vacias y no vacias
	 */
	private void setUpTest5() {
		Square[][] boardToTeste = new Square[3][3];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[0][0].putMine();
		boardToTeste[1][0].stepOn();
		board = new Board(0, boardToTeste);
	}
}


