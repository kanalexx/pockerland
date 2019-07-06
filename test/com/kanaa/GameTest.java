package com.kanaa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest extends MyTest {
  private Game game;

  @Before
  public void setUp() throws Exception {
    game = new Game();
  }

  @Test
  public void startAndStopTest() throws Exception {
    game.start();
    assertFalse(game.isStoped());
    //
    game.stop();
    assertTrue(game.isStoped());
  }
}
