package uo.mp.minesweeper.game.test.allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsActions.class, AllTestsBoard.class, AllTestsRankingParser.class,
		AllTestsRankingSerializer.class, AllTestsScoreComparator.class, AllTestsSquare.class })
public class AllTests {

}
