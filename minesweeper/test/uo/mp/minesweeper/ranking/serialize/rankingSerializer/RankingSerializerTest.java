package uo.mp.minesweeper.ranking.serialize.rankingSerializer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.parse.RankingParser;
import uo.mp.minesweeper.ranking.serialize.RankingSerializer;

public class RankingSerializerTest {

	private final static String[] TO_CHECK = 
		{"Jorge;18/01/2020;17:39:18;EASY;lost;401",
		"Torano;22/05/2019;14:39:18;MEDIUM;won;20",
		"Filemon;11/07/2020;10:39:18;HARD;lost;4"};
	
	private List<String> serialized;
	private List<Score> toParse = new ArrayList<Score>();
	private RankingSerializer serializer = new RankingSerializer();
	
	
	@Before
	public void setUp() {
		serializer = new RankingSerializer();
	}
	
	/**
	 * Given: una lista de score vacia
	 * When: la serializamos
	 * Then: devolverá una lista de Strings vacia
	 */
	@Test
	public void serializeTest1() {
		serialized = serializer.serialize(toParse);
		
		assertEquals(0, serialized.size());
	}
	
	/**
	 * Given: una lista de score todos validos
	 * When: la serializamos
	 * Then: devolverá una lista de los scores serializados
	 */
	@Test
	public void serializeTest2() {
		toParse = new RankingParser().parse(getStringList());
		serialized = serializer.serialize(toParse);
		
		assertEquals(3, serialized.size());
		checkSerialized(serialized);
	}
	
	/**
	 * Comprueba la lista introducida con el array TO_CHECK 
	 */
	private boolean checkSerialized(List<String> list) {
		for(int i=0; i<list.size(); i++)
			if(!list.get(i).equals(TO_CHECK[i]))
				return false;
		return true;
	}
	
	/**
	 * Vuelca el contenido del array TO_CHECK a una arrayList y la devuelve 
	 */
	private List<String> getStringList() {
		List<String> list = new ArrayList<String>();
		for(String s: TO_CHECK)
			list.add(s);
		return list;
	}

}
