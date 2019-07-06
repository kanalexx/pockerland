package com.kanaa;

import org.apache.log4j.PropertyConfigurator;

public class Main {

  public static void main(String[] args) {
    String nameFile = "log4j.properties";
    PropertyConfigurator.configure(nameFile);

    SimpleGame game = new Game();
    game.start();
    game.stop();

    Deck deck = new Deck();
    deck.shuffle();
    while (deck.hasNext()) {
      Card card = deck.getNext();
      System.out.println(card + " " + card.getColor());
    }
  }
}
