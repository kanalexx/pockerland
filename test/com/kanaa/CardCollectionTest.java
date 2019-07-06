package com.kanaa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

public class CardCollectionTest extends MyTest {

  private static final int COLLECTION_SIZE = 33;

  private CardCollection<SimpleCard> collection;

  /**
   * Заполнить коллекцию
   */
  private void fill(CardCollection collection) {
    for (int i = 0; i < collection.getSize(); i++) {
      collection.addCard(getMockCard());
    }
  }

  private SimpleCard getMockCard() {
    return Mockito.mock(SimpleCard.class);
  }

  @Before
  public void setUp() throws Exception {
    collection = new CardCollection<>(COLLECTION_SIZE);
  }

  /**
   * Тест инициализации размера колоды
   */
  @Test
  public void getSize() throws Exception {
    assertEquals(COLLECTION_SIZE, collection.getSize());
  }

  /**
   * Итератор непустой
   */
  @Test
  public void iterator() throws Exception {
    assertNotNull(collection.iterator());
  }

  /**
   * Добавив карту в пустую коллекцию, остаток в коллекции станет 1 карта
   */
  @Test
  public void addCardAndGetLeftCount() throws Exception {
    // было 0 карт
    assertEquals(0, collection.getLeftCount());
    // добавить карту
    collection.addCard(Mockito.mock(SimpleCard.class));
    // в колекции осталась 1 карта
    assertEquals(1, collection.getLeftCount());
  }

  @Test
  public void getLeftCount() throws Exception {
    SimpleCard card = getMockCard();
    collection.addCard(card);
    collection.addCard(card);
    assertEquals(2, collection.getLeftCount(card));
  }

  @Test
  public void setCardAndGetCard() throws Exception {
    fill(collection);
    SimpleCard card = getMockCard();
    Random generator = new Random(new Date().getTime());
    int idx = generator.nextInt(COLLECTION_SIZE);
    // карта, помещенная в позицию idx, заменила другую карту
    SimpleCard otherCard = collection.setCard(idx, card);
    assertNotSame(card, otherCard);
    // на позиции idx стоит именно так карта, которую установили
    SimpleCard sameCard = collection.getCard(idx);
    assertSame(card, sameCard);
  }

  @Test
  public void getNext() throws Exception {
    SimpleCard firstCard = getMockCard();
    SimpleCard lastCard = getMockCard();
    collection.addCard(firstCard);
    collection.addCard(lastCard);
    int count = collection.getLeftCount();
    // возвращает карту именно с конца
    SimpleCard gottenCard = collection.getNext();
    assertSame(lastCard, gottenCard);
    // после получения карты, остаток карт в коллекции уменьшился на одну карту
    assertEquals(count - 1, collection.getLeftCount());
  }

  @Test
  public void hasNext() throws Exception {
    // в пустой коллекции нет следующей карты
    assertFalse(collection.hasNext());
    // после добавления карты
    collection.addCard(getMockCard());
    assertTrue(collection.hasNext());
  }

  @Test
  public void find() throws Exception {
    SimpleCard card = getMockCard();
    collection.addCard(card);
    // добавленная карта находится
    assertSame(card, collection.find(card));
    // другая карта не находится
    assertNull(collection.find(getMockCard()));
  }

  @Test
  public void pull() throws Exception {
    SimpleCard card = getMockCard();
    collection.addCard(card);
    int count = collection.getLeftCount();
    // добавленная карта удаляется успешно
    assertTrue(collection.pull(card));
    // количество карт уменьшилось
    assertEquals(count - 1, collection.getLeftCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void pullError() throws Exception {
    SimpleCard card = getMockCard();
    collection.pull(card);
  }

  @Test
  public void pullCards() throws Exception {
    ArrayList<SimpleCard> cardList = new ArrayList<>();
    // добали две карт, затем удалили две карты
    SimpleCard card = getMockCard();
    cardList.add(card);
    collection.addCard(card);
    card = getMockCard();
    cardList.add(card);
    collection.addCard(card);
    int count = collection.getLeftCount();
    // количество карт уменьшилось
    collection.pullCards(cardList);
    assertEquals(count - cardList.size(), collection.getLeftCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void pullCardsError() throws Exception {
    ArrayList<SimpleCard> cardList = new ArrayList<>();
    cardList.add(getMockCard());
    cardList.add(getMockCard());
    collection.pullCards(cardList);
  }

}