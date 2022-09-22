package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UncoverWelcomeAreaTest {

	private Board board;
	
	@Before
	public void setUp() {
		Square[][] boardToTeste = new Square[2][2];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		board = new Board(0, boardToTeste);
	}
	
	/**
	 * Given: un tablero 2x2
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: no abrira ninguna pues todas las casillas son esquinas
	 */
	@Test
	public void uncoverWelcomeAreaTest1() {
		setUpTest1();
		board.uncoverWelcomeArea();
		//Comprobamos que no ha abierto ninguna
		for(int i=0; i<board.rows(); i++)
			for(int j=0; j<board.cols(); j++)
				assertEquals(Square.State.CLOSED, 
						board.getSquaresForTest()[i][j].getState());
	}
	/**
	 * Creamos un tablero 2x2 vacio
	 */
	private void setUpTest1() {
		Square[][] boardToTeste = new Square[2][2];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		board = new Board(0, boardToTeste);
	}
	
	/**
	 * Given: Tablero sin casillas vacias cerradas
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: no abrira ninguna pues no hay casillas vacias
	 */
	@Test
	public void uncoverWelcomeAreaTest2() {
		setUpTest2();
		board.uncoverWelcomeArea();
		//Comprobamos que no ha abierto ninguna
		for(int i=0; i<board.rows(); i++)
			for(int j=0; j<board.cols(); j++)
				assertEquals(Square.State.CLOSED, 
						board.getSquaresForTest()[i][j].getState());
	}
	/**
	 * Creamos un tablero 3x3 con una mina en el centro
	 */
	private void setUpTest2() {
		Square[][] boardToTeste = new Square[3][3];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		boardToTeste[1][1].putMine();
		board = new Board(1, boardToTeste);
	}
	
	 /**
	 * Given: Tablero con alguna casilla abierta y otras cerradas vacias
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: no abrira ninguna pues no hay casillas vacias
	 */
	@Test
	public void uncoverWelcomeAreaTest3() {
		setUpTest2();
		board.stepOn(0, 0); //Abrimos una casilla
		board.uncoverWelcomeArea();
		//Comprobamos que no ha abierto ninguna
		for(int i=0; i<board.rows(); i++)
			for(int j=0; j<board.cols(); j++) {
				if(i==0 && j==0) //Saltamos la casilla porque es la abierta
					continue;
				assertEquals(Square.State.CLOSED, 
						board.getSquaresForTest()[i][j].getState());
			}
	}
	
	/**
	 * Given: Tablero con casillas vacias cerradas
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: abrira una al azar (Al ser todas vacias las abrira
	 * 		 						todas, por el efecto cadena )
	 */
	@Test
	public void uncoverWelcomeAreaTest4() {
		setUpTest4();
		board.uncoverWelcomeArea();
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
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: abrira una al azar (Al ser todas las del borde derecho y del inferior 
	 * 		vacias las abrira todas, por el efecto cadena )
	 */
	@Test
	public void uncoverWelcomeAreaTest5() {
		setUpTest5();
		board.uncoverWelcomeArea();
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
	
	/**
	 * Given: Tablero con todas las casillas vacias y descubiertas
	 * When: llamamos al metodo uncoverWelcomeArea
	 * Then: no habrirá ninguna
	 */
	@Test
	public void uncoverWelcomeAreaTest6() {
		setUpTest6();
		board.uncoverWelcomeArea();
		/*Comprobamos que no ha hecho nada */
		assertTrue(board.getSquaresForTest()[0][2].isOpen());
		assertTrue(board.getSquaresForTest()[1][2].isOpen());
		for(int j=0; j<board.cols(); j++)
			assertTrue(board.getSquaresForTest()[2][j].isOpen());
	}
	/**
	 * Creamos un tablero 3x3 
	 * todas las casillas vacias y descubiertas
	 */
	private void setUpTest6() {
		Square[][] boardToTeste = new Square[3][3];
		for(int i=0; i<boardToTeste.length; i++)
			for(int j=0; j<boardToTeste[0].length; j++)
				boardToTeste[i][j] = new Square();
		board = new Board(0, boardToTeste);
		board.stepOn(0, 0); // lo destapamos todo
	}
}
