package uo.mp.minesweeper.game.test.allTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.mp.minesweeper.game.test.actions.BlowUpActionTests;
import uo.mp.minesweeper.game.test.actions.ClearActionTests;

@RunWith(Suite.class)
@SuiteClasses({ BlowUpActionTests.class, ClearActionTests.class })
public class AllTestsActions {

}
