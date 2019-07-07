package com.kanaa;

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

  private final int capacity;

  public CardCollection(int capacity) {
    this.capacity = capacity;
    cards = new ArrayList<>(capacity);
  }

  /**
   * Конструктор с предустановленным набором карт
   */
  @SafeVarargs
  public CardCollection(T... cardList) {
    this.capacity = cardList.length;
    cards = new ArrayList<>(capacity);
    addCardList(cardList);
  }

  /**
   * Размер. Максимальное количество карт. Вместимость.
   */
  public int getCapacity() {
    return capacity;
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
   * Добавить список карт в коллекцию
   */
  @SafeVarargs
  public final void addCardList(T... cardList) {
    for (T card : cardList) {
      addCard(card);
    }
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
  public T getNext() {
    if (!cards.isEmpty()) {
      return cards.remove(getSize() - 1);
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
  public int getSize() {
    return cards.size();
  }

  /**
   * Возвращает оставщееся количество указанных карт.
   * @param card карта
   */
  public int getCount(T card) {
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
  public void pullCards(CardCollection<T> cardList) {
    for (T card : cardList) {
      pull(card);
    }
  }
}
