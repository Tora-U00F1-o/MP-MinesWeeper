package uo.mp.minesweeper.ranking.scoreComparator;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.ScoreComparator;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.CodeUtil;

public class CompareTest {
	
	private ScoreComparator comparator = new ScoreComparator();
	
	/**
	 * Given: dos scores el primero con mayor nivel que el segundo
	 * When: los comparamos por el ScoreComparator
	 * Then: devolverá un numero negativo
	 */
	@Test
	public void compareTest1() {
		Score s1 = new Score("Jorge", GameLevel.HARD, 10, true);
		Score s2 = new Score("Jorge", GameLevel.MEDIUM, 10, true);
		int diff = comparator.compare(s1, s2);
		assertTrue(diff < 0);
	}
	
	/**
	 * Given: dos scores con mismo nivel, y el primero con 
	 * 	un menor tiempo registrado 
	 * When: los comparamos por el ScoreComparator
	 * Then: devolverá un numero negativo
	 */
	@Test
	public void compareTest2() {
		Score s1 = new Score("Jorge", GameLevel.MEDIUM, 5, true);
		Score s2 = new Score("Jorge", GameLevel.MEDIUM, 10, true);
		int diff = comparator.compare(s1, s2);
		assertTrue(diff < 0);
	}
	
	/**
	 * Given: dos scores con mismo nivel y tiempo, pero siendo 
	 * el primero anterior al segundo en fecha
	 * When: los comparamos por el ScoreComparator
	 * Then: devolverá un numero negativo
	 */
	@Test
	public void compareTest3() {
		Score s1 = null;
		Score s2 = null;
		try {
			Date date1 = CodeUtil.parseDate("dd/MM/yyyy", "17/01/2020");
			s1 = new Score("Jorge", GameLevel.MEDIUM, 10, true, date1);
			Date date2 = CodeUtil.parseDate("dd/MM/yyyy", "18/01/2020");
			s2 = new Score("Jorge", GameLevel.MEDIUM, 10, true, date2);
		} catch(ParseException e) {
			fail("Excepcion al parsear");
		}
		int diff = comparator.compare(s1, s2);
		assertTrue(diff < 0);
	}
}
