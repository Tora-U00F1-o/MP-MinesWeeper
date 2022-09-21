package uo.mp.minesweeper.game.test.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class UnflagTests {

	Square square;

	@Before
	public void setUp() {
		square = new Square();
	}

	/**
	 * Given: una casilla descubierta 
	 * When: llamamos al metodo unflag 
	 * Then: la casilla seguira abierta, no cambiará
	 */
	@Test
	public void unflagTest1() {
		square.open(); // Descubrimos la casilla

		square.unflag();
		assertTrue(square.isOpen());
	}

	/**
	 * Given: una casilla cerrada
	 * When: llamamos al metodo unflag  
	 * Then: la casilla seguira cerrada, no cambia su estado
	 */
	@Test
	public void unflagTest2() {
		square.unflag();
		assertEquals(Square.State.CLOSED, square.getState());
	}

	/**
	 * Given: una casilla marcada 
	 * When: llamamos al metodo unflag  
	 * Then: la casilla se desmarcará y estará cerrada
	 */
	@Test
	public void unflagTest3() {
		square.flag(); // Marcamos la casilla

		square.unflag();
		assertEquals(Square.State.CLOSED, square.getState());
	}

}
