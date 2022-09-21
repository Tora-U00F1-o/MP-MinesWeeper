package uo.mp.minesweeper.ranking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.ranking.file.RankingLoader;
import uo.mp.minesweeper.ranking.file.RankingSaver;
import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.exceptions.ApplicationException;

public class GameRanking implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String fileName;
	List<Score> scores;
	
	public GameRanking(String fileName) {
		setFileName(fileName);
		setRanking(new ArrayList<Score>());
	}
	
	/**
	 * Devuelve el nombre del fichero donde 
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	private void setFileName(String fileName) {
		ArgumentCheck.checkString(fileName, "Nombre de fichero no valido");
		this.fileName = fileName;
	}

	/**
	 * Añade el objeto score al final de la lista de scores.
	 * @param score Registro de partida a añadir
	 */
	public void append(Score score) {
		ArgumentCheck.checkNotNull(score, "Score nulo");
		scores.add(score);
	}
	
	private void setRanking(List<Score> scores) {
		ArgumentCheck.checkNotNull(scores, "Lista no valida");
		this.scores = scores;
	}

	/**
	 * Devuelve una copia de la lista de scores.
	 * @return Una copia de la lista de scores.
	 */
	public List<Score> getGeneral() {
		List<Score> copy = new ArrayList<Score>(scores);
		CodeUtil.sort(copy, new ScoreComparator());
		return copy;
	}
	
	/**
	 * una lista que contiene solo aquellos scores cuyo usuario coincide con el
	 * userName recibido como parámetro.
	 * @param userName Nombre de usuario que quiere su marcador
	 * @return Lista que solo tiene sus scores 
	 */
	public List<Score> getScoresFor(String userName) {
		ArgumentCheck.checkString(userName, "Nombre usuario no valido");
		
		List<Score> copy = new ArrayList<Score>();
		for(Score score: scores)
			if(score.getUserName().equals(userName))
				copy.add(score);
		CodeUtil.sort(copy, new ScoreComparator());
		return copy;
	}

	/**
	 * Guarda el ranking en el fichero indicado -parametro-
	 * @param fileName
	 */
	public void saveRanking(String fileName) {
		ArgumentCheck.checkString(fileName, "Nombre de fichero no valido");
		new RankingSaver(fileName).save(getGeneral());
		
	}
	
	/**
	 * Importa el ranking del fichero indicado -parametro-
	 * @param fileName
	 */
	public void loadRanking(String fileName) throws ApplicationException {
		ArgumentCheck.checkString(fileName, "Nombre de fichero no valido");
		if(fileName.equals(getFileName()))
			throw new ApplicationException();
		List<Score> list = new RankingLoader(fileName).load();
		setRanking(list);
	}
	
	
	


	
}
