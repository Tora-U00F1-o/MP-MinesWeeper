package uo.mp.minesweeper.game.test.allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.mp.minesweeper.game.test.board.ConstructorTests;
import uo.mp.minesweeper.game.test.board.FlagTests;
import uo.mp.minesweeper.game.test.board.GetStatusTests;
import uo.mp.minesweeper.game.test.board.StepOnTests;
import uo.mp.minesweeper.game.test.board.UncoverWelcomeAreaTest;
import uo.mp.minesweeper.game.test.board.UnflagTests;
import uo.mp.minesweeper.game.test.board.UnveilTests;

@RunWith(Suite.class)
@SuiteClasses({ ConstructorTests.class, FlagTests.class, GetStatusTests.class, StepOnTests.class,
		UncoverWelcomeAreaTest.class, UnflagTests.class, UnveilTests.class })
public class AllTestsBoard {

}
