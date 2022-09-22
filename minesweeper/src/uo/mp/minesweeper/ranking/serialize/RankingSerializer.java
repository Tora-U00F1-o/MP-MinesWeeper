package uo.mp.minesweeper.ranking.serialize;

import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.CodeUtil;

public class RankingSerializer {

	/**
	 * 
	 * @param ranking
	 * @return
	 */
	public List<String> serialize(List<Score> ranking) {
		List<String> lines = new ArrayList<String>();
		
		for(Score score: ranking) {
			lines.add(serializeScore(score));
		}
		return lines;
	}
	
	/**
	 * Serializa el Score en el formato:
	 * userName;date;dateTime;level;result;time
	 * @param s score a serializar
	 * @return
	 */
	public String serializeScore(Score s) {
		String patron = "%s;%s;%s;%s;%s;%s";
		String simpleDate = CodeUtil.getProcessedDate(s.getDate());
		String simpleTime = CodeUtil.getProcessedTime(s.getDate());
		String level = s.getLevel().toString();
		String result = s.hasWon() ? "won" : "lost";
		String time = Long.toString(s.getTime());
		
		return String.format(patron, s.getUserName(), simpleDate, simpleTime,
				level, result, time);
	}
}
