package uo.mp.minesweeper.ranking.file;

import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.ranking.parse.RankingParser;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.exceptions.ApplicationException;
import uo.mp.minesweeper.util.files.FileUtil;

public class RankingLoader {

	private String fileName;

	public RankingLoader(String fileName) {
		setFileName(fileName);
	}

	/**
	 * Devuelve el nombre del fichero
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Establece el nombre del fichero a leer
	 * @param fileName
	 */
	private void setFileName(String fileName) {
		ArgumentCheck.checkString(fileName, "Nombre de fichero no valido");
		this.fileName = fileName;
	}

	/**
	 * Carga y procesa la informacion del fichero
	 * @return una lista de scores con la informacion del fichero
	 * @throws ApplicationException 
	 */
	public List<Score> load() throws ApplicationException  {
		List<String> lines = new FileUtil().loadLines( getFileName() );
		return new RankingParser().parse( lines );
	}

}
