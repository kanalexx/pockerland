package com.kanaa;

public class Hand extends SimpleHand<Card> {

  /** В покере в руке игрока две карты */
  private static final int HAND_SIZE = 2;

  public Hand() {
    super(HAND_SIZE);
  }

}
