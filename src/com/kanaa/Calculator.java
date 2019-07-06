package com.kanaa;

import java.util.List;

/**
 * Калькулятор вероятностей (шансов) возможного схода учитывая состояние колоды и карт на столе
 *
 * Калькулятор учитывает состояние колоды как игрок, т.е. он знает сколько карт в колоде нет,
 *   но каких карт нет в колоде - он знает не о всех, только о тех, которые лежат на столе и в его руке.
 */
public class Calculator {
  //private Deck deck;

  //public Calculator(Deck deck) {
//    this.deck = deck;
//  }

  public int getChance(List<Rating> ratings) {
    Deck deck = new Deck();
    int chance = 0;
    for (Rating rating: ratings) {

    }
    return 0;
  }

}
