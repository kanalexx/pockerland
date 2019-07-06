package com.kanaa;

import java.util.Date;
import java.util.Random;

/**
 * Базовый класс для колоды карт
 */

public abstract class SimpleDeck<T extends SimpleCard> extends CardCollection<T> {

  public SimpleDeck(int size) {
    super(size);
    fillDeck();
  }

  /**
   * Заполнить колоду картами
   */
  public abstract void fillDeck();

  /**
   * Перемешивает колоду.
   */
  public void shuffle() {
    Random generator = new Random(new Date().getTime());
    for(int i = 0; i < getLeftCount(); i++) {
      int newPos = generator.nextInt(getLeftCount());
      T tmpCard = setCard(newPos, getCard(i));
      setCard(i, tmpCard);
      if(i%(getLeftCount() /4) == 0) {
        int pause = generator.nextInt(20);
        try {
          Thread.sleep(pause);
        } catch (InterruptedException e) {
          generator.setSeed(new Date().getTime());
        }
      }
    }
  }

}