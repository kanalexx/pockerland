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

  /** ��������� ���� */
  private final ArrayList<T> cards;

  public SimpleDeck() {
    cards = new ArrayList<>(getSize());
    fillDeck();
  }

  /**
   * ������ ������
   */
  public abstract int getSize();

  /**
   * ��������� ������ �������
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
   * ���������� ��������� ����� �� ������, � ������� �� �� ������.
   */
  @Nullable
  public T getNext() {
    if (!cards.isEmpty()) {
      return cards.remove(0);
    }
    return null;
  }

  /**
   * ���� �� � ������ �����.
   */
  public boolean hasNext() {
    return !cards.isEmpty();
  }

  /**
   * ������������ ������.
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
   * ���������� ���������� ���������� ���� � ������.
   */
  public int getLeftCount() {
    return cards.size();
  }

  /**
   * ���������� ���������� ���������� ��������� ����.
   * @param card �����
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
   * ����� �� ������ ��������� �����.
   * @param card �����
   * @return ���� ����� � ������ ���, �� ���������� ����������.
   */
  public boolean pull(T card) {
    T found = find(card);
    if (found == null)
      throw new IllegalArgumentException(String.format("����� %s � ������ �� �������.", card));
    return cards.remove(found);
  }

  /**
   * ������� ����� ���� �� ������. ���� ���� ����� � ��������� ANY, �� ������� ����� ���, ��������������� ������� �����.
   * @param cardList ������ ���� � ������
   */
  public void pullCards(List<T> cardList) {
    if (!cards.removeAll(cardList))
      throw new IllegalArgumentException("���� ��� �������� �� ���� � ������ �� �������.");
  }
}