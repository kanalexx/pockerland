package com.kanaa;

public class Main {

  public static void main(String[] args) {
    Deck deck = new Deck();
    deck.shuffle();
    while (deck.hasNext()) {
      Card card = deck.getNext();
      System.out.println(card + " " + card.getColor());
    }
  }
}
