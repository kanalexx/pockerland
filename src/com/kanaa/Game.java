package com.kanaa;

import org.apache.log4j.Logger;

public class Game {
  private static  Logger log = Logger.getLogger(Game.class);
  private boolean isStoped = true;

  public void init() {
    isStoped = false;
    log.info("Игра запущена.");
  }

  public boolean isStoped() {
    return isStoped;
  }

  public void stop {
    isStoped = true;
    log.info("Игра остановлена.");
  }
}
