package com.kanaa;

/**
 * Рука в карточной игре
 *
 * В руке игрока находится определенное количество карт.
 * Игрок может:
 * - видеть свои карты: знать какие они и исколько их
 * - добавлять и удалять карты
 *
 * Таким образом, это такая же коллекция карт как и колода, только проще и меньше
 */
public class SimpleHand<T extends SimpleCard> extends CardCollection<T> {

  public SimpleHand(int size) {
    super(size);
  }

}
