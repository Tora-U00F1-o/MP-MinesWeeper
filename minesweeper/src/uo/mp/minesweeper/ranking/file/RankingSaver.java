package uo.mp.minesweeper.ranking.file;

import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.serialize.RankingSerializer;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.files.FileUtil;

public class RankingSaver {

	private String fileName;

	public RankingSaver(String fileName) {
		setFileName(fileName);
	}

	/**
	 * Devuelve el nombre del fichero indicado para guardar el ranking
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Establece el nombre del fichero en el que se guardará el ranking
	 * @param fileName
	 */
	private void setFileName(String fileName) {
		ArgumentCheck.checkString(fileName, "Nombre de fichero no valido");
		this.fileName = fileName;
	}
	
	/**
	 * Guarda la lista introducida en el fichero indeicado -atributo-
	 * @param rank
	 */
	public void save(List<Score> rank) {
		List<String> lines = new RankingSerializer().serialize( rank );
		new FileUtil().writeLines(lines, fileName);
	}
	
	

}
