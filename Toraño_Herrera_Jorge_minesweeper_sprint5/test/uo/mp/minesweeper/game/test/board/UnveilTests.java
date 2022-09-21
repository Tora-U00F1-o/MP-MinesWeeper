package uo.mp.minesweeper.game.test.board;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UnveilTests {

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
	 * Given: un tablero con todas sus casillas cerradas
	 * When: llamamos al metodo unveil
	 * Then: Descubre todas las casillas
	 */
	@Test
	public void unveilTest1() {
		board.unveil();
		// Comprobamos que todo se ha abierto
		for(Square[] row: board.getSquaresForTest())
			for(Square s: row)
				assertTrue(s.isOpen());
	}
	
	/**
	 * Given: un tablero con algunas casillas marcadas con bandera
	 * When: llamamos al metodo unveil
	 * Then: Descubre todas las casillas marcadas
	 */
	@Test
	public void unveilTest2() {
		for(Square[] row: board.getSquaresForTest())
			for(Square s: row) 
				if(new Random().nextFloat() <= 0.4)  // algunas casillas se marcan
					s.flag(); 
		board.unveil();
		// Comprobamos que todo está abierto
		for(Square[] row: board.getSquaresForTest())
			for(Square s: row)
				assertTrue(s.isOpen());
	}
	
	/**
	 * Given: un tablero con algunas casillas descubiertas
	 * When: llamamos al metodo unveil
	 * Then: Descubre todas las casillas cerradas
	 */
	@Test
	public void unveilTest3() {
		for(Square[] row: board.getSquaresForTest())
			for(Square s: row) 
				if(new Random().nextFloat() <= 0.4)  // algunas casillas se marcan
					s.open();
		
		board.unveil();
		// Comprobamos que todo está abierto
		for(Square[] row: board.getSquaresForTest())
			for(Square s: row)
				assertTrue(s.isOpen());
	}
	
	
}
