package com.kanaa;

import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * Коллекция карт
 *
 * Может использоваться для колоды, руки, стола и др.
 * Предоставляет интерфейс перебора коллекции и  методы добавления, удаления карт из коллекции.
 */
public class CardCollection <T extends SimpleCard> implements Iterable<T> {
  /** Коллекция карт */
  private final ArrayList<T> cards;

  private final int size;

  public CardCollection(int size) {
    this.size = size;
    cards = new ArrayList<>(size);
  }

  /**
   * Размер. Максимальное количество карт. Вместимость.
   */
  public int getSize() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return cards.iterator();
  }

  /**
   * Добавить указанную карту в коллекцию
   */
  public void addCard(T card) {
    cards.add(card);
  }

  /**
   * Добавить указанную карту в указанную позицию в коллекции
   */
  public T setCard(int idx, T card) {
    return cards.set(idx, card);
  }

  /**
   * Получить карту в позиции idx в коллекции
   */
  public T getCard(int idx) {
    return cards.get(idx);
  }

  /**
   * Возвращает следующую карту с конца из коллекции, и удаляет ее из коллекции
   */
  @Nullable
  public T getNext() {
    if (!cards.isEmpty()) {
      return cards.remove(getLeftCount() - 1);
    }
    return null;
  }

  /**
   * Есть ли в колоде карты
   */
  public boolean hasNext() {
    return !cards.isEmpty();
  }

  /**
   * Возвращает оставшееся количество карт в коллекции.
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
    for(Object entry : this) {
      if (entry.equals(card)) result++;
    }
    return result;
  }

  /**
   * Найти указанную карту в коллекции
   * @return найденная карта, null - если карта не нашлась
   */
  @Nullable
  public T find(T card) {
    int idx = cards.indexOf(card);
    return idx >= 0 ? cards.get(idx) : null;
  }

  /**
   * Удалить из коолекции указанную карту.
   * @param card карта
   * @return если карты в коллекции нет, то вызывается исключение.
   */
  public boolean pull(T card) {
    T found = find(card);
    if (found == null)
      throw new IllegalArgumentException(String.format("Карта %s не найдена.", card));
    return cards.remove(found);
  }

  /**
   * Изымает набор карт из коллекции.
   * @param cardList список карт к изятию
   */
  public void pullCards(List<T> cardList) {
    if (!cards.removeAll(cardList))
      throw new IllegalArgumentException("Одна или несолько карт не найдены.");
  }
}
