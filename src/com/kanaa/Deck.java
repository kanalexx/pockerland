package com.kanaa;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Deck {
  private static int deckSize = 52;
  private ArrayList<Card> cards = new ArrayList<>(deckSize);

  public Deck() {
    for (Suit suit : Suit.getValues()) {
      for (Rating rating : Rating.getValues()) {
        cards.add(Card.get(rating, suit));
      }
    }
  }

  /**
   * Возвращает следующую карту из колоды, и удаляет ее из колоды.
   */
  public Card getNext() {
    if (!cards.isEmpty()) {
      return cards.remove(0);
    }
    return null;
  }

  /**
   * Есть ли в колоде карты.
   */
  public boolean hasNext() {
    return !cards.isEmpty();
  }

  /**
   * Перемешивает колоду.
   */
  public void shuffle() {
    Random generator = new Random(new Date().getTime());
    for(int i = 0; i < cards.size(); i++) {
      int newPos = generator.nextInt(deckSize);
      Card tmpCard = cards.set(newPos, cards.get(i));
      cards.set(i, tmpCard);
      if(i%(deckSize /4) == 0) {
        int pause = generator.nextInt(20);
        try {
          Thread.sleep(pause);
        } catch (InterruptedException e) {
          generator.setSeed(new Date().getTime());
        }
      }
    }
  }

  /**
   * Возвращает оставшееся количество карт в колоде.
   */
  public int getLeftCount() {
    return cards.size();
  }

  /**
   * Возвращает оставщееся количество указанных карт.
   * @param card карта
   */
  public int getLeftCount(Card card) {
    int result = 0;
    for(Card entry : cards) {
      if (entry.equals(card)) result++;
    }
    return result;
  }

  /**
   * Возвращает оставщееся количество карт, указанного цвета.
   * @param color цвет
   */
  public int getLeftCount(Suit.Color color) {
    int result = 0;
    for(Card card : cards) {
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

  public Card find(Card card) {
      int idx = cards.indexOf(card);
      return idx >= 0 ? cards.get(idx) : null;
  }

  /**
   * Взять из колоды указанную карту.
   * @param card карта
   * @return если карты в колоде нет, то вызывается исключение.
   */
  public boolean pull(Card card) {
    Card found = find(card);
    if (found == null)
      throw new IllegalArgumentException(String.format("Карта %s в колоде не найдена.", card));
    return cards.remove(found);
  }

  /**
   * Изымает набор карт из колоды. Если есть карты с атрибутом ANY, то удалены будут все, соответствующие условию карты.
   * @param cardList список карт к изятию
   */
  public void pullCards(List<Card> cardList) {
    if (!cards.removeAll(cardList))
      throw new IllegalArgumentException("Одна из карт в колоде не найдена.");;
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
