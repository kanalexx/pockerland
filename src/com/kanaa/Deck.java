package com.kanaa;

import java.util.List;

public class Deck extends SimpleDeck <Card>  {

  /** Размер колоды */
  private static final int deckSize = 52;

  public Deck() {
    super(deckSize);
  }

  @Override
  public void fillDeck() {
    for (Suit suit : Suit.getValues()) {
      for (Rating rating : Rating.getValues()) {
        addCard(Card.get(rating, suit));
      }
    }
  }

  /**
   * Возвращает оставщееся количество карт, указанного цвета.
   * @param color цвет
   */
  public int getLeftCount(Suit.Color color) {
    int result = 0;
    for(Card card : this) {
      if (color.isAny() || color.equals(card.getColor())) {
        result++;
      }
    }
    return result;
  }

  /**
   * @param card карта
   * @return Возвращает шанс вытащить из колоды указанную карту.
   */
  public double getChance(Card card) {
    return Utils.roundTo((double) getLeftCount(card) / getLeftCount() * 100);
  }

  /**
   * Вероятность вытянуть карту, из не полной калоды
   * @param cardList список карт к изятию
   * @param card карта, для которой находтся вероятность
   * @return Возвращает вероятность вытащить указанную карты из колоды, если в ней не будет указнного набора карт.
   */
  public double getChanceAfterPull(List<Card> cardList, Card card) {
    pullCards(cardList);
    return getChance(card);
  }

  /**
   * Вероятность вытянуть последовательно набор карт
   * @param cardList набор карт
   * @return возвращает вероятность вытянуть последовательно набор карт
   */
  public double getChanceCards(List<Card> cardList) {
    double chance = 0;
    for (int i = 0; i < cardList.size(); i++) {
      Card card = cardList.get(i);
      int leftCount = getLeftCount(card);
      for (int j = i; j < cardList.size(); j++) {
        if (!card.equals(cardList.get(j))) {
          leftCount += getLeftCount(cardList.get(j));
        }
      }
      if (i == 0)
        chance = (double) leftCount / getLeftCount();
      else
        chance *= (double) leftCount / getLeftCount();
      pull(card);
    }
    return Utils.roundTo(chance*100);
  }
}
