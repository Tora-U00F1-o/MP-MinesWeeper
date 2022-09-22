package uo.mp.minesweeper.ranking.parse.rankingParser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.parse.RankingParser;

public class ParseTest {

	private final static String[] TODO_BIEN = 
		{"Jorge;18/01/2020;17:39:18;EASY;lost;401",
		"Torano;22/05/2019;14:39:18;MEDIUM;won;20",
		"Filemon;11/07/2020;10:39:18;HARD;lost;4",
		"Ofelia;03/05/2020;19:39:18;EASY;won;40"};
	

	private List<String> list = new ArrayList<String>();;
	private List<Score> parsed;
	private RankingParser parser;
	
	@Before
	public void setUp() {
		parser = new RankingParser();
		
	}
	

	/**
	 * Given: una lista de strings vacia
	 * When: las parseamos
	 * Then: devolver· una lista de scores vacia
	 */
	@Test
	public void parseTest1() {
		parsed = parser.parse(list);
		
		assertEquals(0, parsed.size());
	}
	
	/**
	 * Given: una lista de strings con 4 cadenas sin errores
	 * When: las parseamos
	 * Then: crear· 4 scores y los devolver· en una lista a parte
	 */
	@Test
	public void parseTest2() {
		setList();
		parsed = parser.parse(list);
		
		assertEquals(4, parsed.size());
	}
	
	/**
	 * Given: una lista de strings con alguna linea en blanco
	 * When: las parseamos
	 * Then: omitir· las lineas malas, y parsear· las demas lineas
	 */
	@Test
	public void parseTest3() {
		setList();
		list.add("");
		list.add("                ");
		list.add("\n\t  \r");
		
		parsed = parser.parse(list);
		assertEquals(4, parsed.size());
	}
	
	/**
	 * Given: una lista de strings con alguna linea econ errores
	 * When: las parseamos
	 * Then: omitir· las lineas malas, y parsear· las demas lineas
	 */
	@Test
	public void parseTest4() {
		setList();
		list.add(";18/01/2020;17:39:18;EASY;lost;401");
		list.add("   ");
		list.add("Tora√±o;;14:39:18;MEDIUM;won;20");
		list.add("Filemon;11/07/2020;10:39:18;HARD;lost;-6");
		list.add("PEPE;11/07/2020;10:39:18;HARD;true;4");
		list.add("Popeye;11/07/2020;10:39:18;Facil;lost;4");
		list.add("Mariano;11/07/2020;muy rapido;HARD;lost;4");
		
		parsed = parser.parse(list);
		
		assertEquals(4, parsed.size());
	}
	
	/**
	 * Given: una lista de strings con todas lineas econ errores
	 * When: las parseamos
	 * Then: omitir· todas las lineas
	 */
	@Test
	public void parseTest5() {
		list.add(";18/01/2020;17:39:18;EASY;lost;401");
		list.add("   ");
		list.add("Tora√±o;;14:39:18;MEDIUM;won;20");
		list.add("Filemon;11/07/2020;10:39:18;HARD;lost;-6");
		list.add("PEPE;11/07/2020;10:39:18;HARD;true;4");
		list.add("Popeye;11/07/2020;10:39:18;Facil;lost;4");
		list.add("Mariano;11/07/2020;muy rapido;HARD;lost;4");
		
		parsed = parser.parse(list);
		
		assertEquals(0, parsed.size());
	}
	
	private void setList() {
		for(String s: TODO_BIEN)
			list.add(s);
	}
	
}
