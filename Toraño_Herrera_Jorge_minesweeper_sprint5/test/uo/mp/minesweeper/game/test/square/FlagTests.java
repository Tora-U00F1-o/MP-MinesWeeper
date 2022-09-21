package uo.mp.minesweeper.game.test.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class FlagTests {

	Square square;

	@Before
	public void setUp() {
		square = new Square();
	}

	/**
	 * Given: una casilla descubierta 
	 * When: llamamos al metodo flag 
	 * Then: la casilla seguira abierta, no cambiará
	 */
	@Test
	public void flagTest1() {
		square.open(); // Descubrimos la casilla

		square.flag();
		assertTrue(square.isOpen());
	}

	/**
	 * Given: una casilla cerrada
	 * When: llamamos al metodo flag  
	 * Then: la casilla se marcará
	 */
	@Test
	public void flagTest2() {
		square.flag();
		assertTrue(square.hasFlag());
	}

	/**
	 * Given: una casilla marcada 
	 * When: llamamos al metodo flag  
	 * Then: la casilla seguirá marcada
	 */
	@Test
	public void flagTest3() {
		square.flag(); // Marcamos la casilla

		square.flag();
		assertTrue(square.hasFlag());
	}

}
