package com.kanaa;

import org.apache.log4j.Logger;

public abstract class SimpleGame {

  private static Logger log = Logger.getLogger(SimpleGame.class);
  private boolean isStoped = true;

  public void start() {
    isStoped = false;
    log.info("Игра запущена.");
  }

  public boolean isStoped() {
    return isStoped;
  }

  public void stop() {
    isStoped = true;
    log.info("Игра остановлена.");
  }

}
