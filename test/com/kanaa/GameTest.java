package com.kanaa;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class GameTest {
  private Game game;

  @Before
  public void setUp() throws Exception {
    game = new Game();
  }

  @Test
  public void initTest() throws Exception {
    game.init();
    assertFalse(game.isStoped());
  }
}
