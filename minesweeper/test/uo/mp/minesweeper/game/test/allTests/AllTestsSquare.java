package uo.mp.minesweeper.game.test.allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.mp.minesweeper.game.test.square.FlagTests;
import uo.mp.minesweeper.game.test.square.StepOnTests;
import uo.mp.minesweeper.game.test.square.UnflagTests;

@RunWith(Suite.class)
@SuiteClasses({ FlagTests.class, StepOnTests.class, UnflagTests.class })
public class AllTestsSquare {

}
