package uo.mp.minesweeper.ranking;

import java.util.Comparator;

import uo.mp.minesweeper.util.checks.ArgumentCheck;

public class ScoreComparator implements Comparator<Score>{
	
	/**
	 * Compara dos score por el criterio: va primero :
	 * [1_Mayor Nivel  2_Menor Tiempo  3_Mas Antiguas]
	 */
	public int compare(Score s1, Score s2) {
		ArgumentCheck.checkNotNull(s1, "Score 1 a comparar no valido");
		ArgumentCheck.checkNotNull(s2, "Score 2 a comparar no valido");
		
		long diff = s1.getLevel().compareTo(s2.getLevel());
		diff = diff==0 ? s1.getTime() - s2.getTime() : diff;
		diff = diff==0 ? s1.getDate().compareTo(s2.getDate()) : diff;
		return (diff==0) ? 0 : (diff<0) ? -1 : 1;
	}

}
