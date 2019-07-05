package com.kanaa;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import com.sun.istack.internal.Nullable;

/**
 * @author Alexander Kanunnikov
 */

public abstract class SimpleDeck <T extends SimpleCard> implements Iterable<T> {

  /** Коллекция карт */
  private final ArrayList<T> cards;

  public SimpleDeck() {
    cards = new ArrayList<>(getSize());
    fillDeck();
  }

  /**
   * Размер колоды
   */
  public abstract int getSize();

  /**
   * Заполнить колоду картами
   */
  public abstract void fillDeck();

  @Override
  public Iterator<T> iterator() {
    return cards.iterator();
  }

  protected void addCard(T card) {
    cards.add(card);
  }

  /**
   * Возвращает следующую карту из колоды, и удаляет ее из колоды.
   */
  @Nullable
  public T getNext() {
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
      int newPos = generator.nextInt(getSize());
      T tmpCard = cards.set(newPos, cards.get(i));
      cards.set(i, tmpCard);
      if(i%(getSize() /4) == 0) {
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
  public int getLeftCount(T card) {
    int result = 0;
    for(T entry : cards) {
      if (entry.equals(card)) result++;
    }
    return result;
  }

  @Nullable
  public T find(T card) {
    int idx = cards.indexOf(card);
    return idx >= 0 ? cards.get(idx) : null;
  }

  /**
   * Взять из колоды указанную карту.
   * @param card карта
   * @return если карты в колоде нет, то вызывается исключение.
   */
  public boolean pull(T card) {
    T found = find(card);
    if (found == null)
      throw new IllegalArgumentException(String.format("Карта %s в колоде не найдена.", card));
    return cards.remove(found);
  }

  /**
   * Изымает набор карт из колоды. Если есть карты с атрибутом ANY, то удалены будут все, соответствующие условию карты.
   * @param cardList список карт к изятию
   */
  public void pullCards(List<T> cardList) {
    if (!cards.removeAll(cardList))
      throw new IllegalArgumentException("Одна или несолько из карт в колоде не найдены.");
  }
}