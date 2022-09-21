package uo.mp.minesweeper.ranking.parse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.CodeUtil;
import uo.mp.minesweeper.util.checks.ArgumentCheck;
import uo.mp.minesweeper.util.exceptions.LineFormatException;
import uo.mp.minesweeper.util.log.FileLogger;

public class RankingParser {
	
	private final static String LOG_FILE = "minesweeper.log";
	
	public final static String DATE_FORMAT = "dd/MM/yy HH:mm:ss";
	public final static String PARTS_SEPARATOR = ";";
	public final static int NUMBER_OF_PARTS = 6;
	public final static int USER_NAME = 0;
	public final static int DATE = 1;
	public final static int DATE_TIME = 2;
	public final static int LEVEL = 3;
	public final static int RESULT = 4;
	public final static int TIME = 5;
	
	public final static String WON = "won";
	public final static String LOST = "lost";


	public List<Score> parse(List<String> lines){
		List<Score> scores = new ArrayList<Score>();
		int cont = 0;
		for(String s: lines) {
			try {
				scores.add(parseLine(s, ++cont));
				
			} catch(LineFormatException e) {
				new FileLogger(LOG_FILE).log(e);
			}
		}
		
		return scores;
	}
	
	private Score parseLine(String line, int lineNumber) throws LineFormatException {
		String[] parts = line.split(PARTS_SEPARATOR);
		
		try {
			ArgumentCheck.checkNumDataParsed(parts.length, NUMBER_OF_PARTS); 
			
			String user = parts[USER_NAME];
			GameLevel level = GameLevel.valueOf(parts[LEVEL]);
			long time = Long.parseLong(parts[TIME]);
			boolean hasWon = CodeUtil.parseBoolean(parts[RESULT], WON, LOST);
			String dateString = parts[DATE]+" "+parts[DATE_TIME];
			Date date = CodeUtil.parseDate(DATE_FORMAT, dateString);
			
			return new Score(user, level, time, hasWon, date);
			
		} catch (IllegalArgumentException | ParseException e) {
			throw new LineFormatException(lineNumber, "Algún dato no valido");
		} 
		
	}
	
}
