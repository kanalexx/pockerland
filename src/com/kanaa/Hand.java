package com.kanaa;

public class Hand {
  private Card[] cards = new Card[2];

  public Hand addCard(Card card) {
    if (cards[0] != null) {
      cards[0] = card;
    } else {
      cards[1] = card;
    }
    return this;
  }

  public Card[] getCards() {
    return cards;
  }

  public Card getCard(int index) {
    return cards[index];
  }
}
