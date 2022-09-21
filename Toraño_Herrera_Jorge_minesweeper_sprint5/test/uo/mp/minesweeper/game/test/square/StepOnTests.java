package uo.mp.minesweeper.game.test.square;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class StepOnTests {

	Square square;
	
	@Before
	public void setUp() {
		square = new Square();
	}
	
	/**
	 * Given: una casilla descubierta
	 * When: llamamos al metodo stepOn
	 * Then: la casilla seguira abierta, no cambiará
	 */
	@Test
	public void stepOnTest1() {
		square.open();	// Descubrimos la casilla
		
		square.stepOn();
		assertTrue(square.isOpen());
	}
	
	/**
	 * Given: una casilla cerrada
	 * When: llamamos al metodo stepOn
	 * Then: la casilla se abrirá
	 */
	@Test
	public void stepOnTest2() {
		square.stepOn();
		assertTrue(square.isOpen());
	}
	
	/**
	 * Given: una casilla marcada
	 * When: llamamos al metodo stepOn
	 * Then: la casilla seguirá marcada, no cambiará
	 */
	@Test
	public void stepOnTest3() {
		square.flag();	// Marcamos la casilla
		
		square.stepOn();
		assertTrue(square.hasFlag());
	}

}
