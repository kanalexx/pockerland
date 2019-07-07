package com.kanaa;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
    for (int i = 0; i < collection.getCapacity(); i++) {
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
   * Тест конструктора с набором карт
   */
  @Test
  public void newCardCollection() throws Exception {
    CardCollection<SimpleCard> cards = new CardCollection<>(
        getMockCard(),
        getMockCard(),
        getMockCard()
    );
    assertEquals(3, cards.getCapacity());
    assertEquals(3, cards.getSize());
  }

  /**
   * Тест инициализации размера колоды
   */
  @Test
  public void getCapacity() throws Exception {
    assertEquals(COLLECTION_SIZE, collection.getCapacity());
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
  public void addCardAndGetSize() throws Exception {
    // было 0 карт
    assertEquals(0, collection.getSize());
    // добавить карту
    collection.addCard(Mockito.mock(SimpleCard.class));
    // в колекции осталась 1 карта
    assertEquals(1, collection.getSize());
  }

  @Test
  public void addCardList() throws Exception {
    // пустой список добавляется безопасно
    collection.addCardList();
    assertEquals(0, collection.getSize());
    collection.addCardList(
        getMockCard(),
        getMockCard(),
        getMockCard()
    );
    assertEquals(3, collection.getSize());
  }

  @Test
  public void getCount() throws Exception {
    SimpleCard card = getMockCard();
    collection.addCard(card);
    collection.addCard(card);
    assertEquals(2, collection.getCount(card));
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
    int count = collection.getSize();
    // возвращает карту именно с конца
    SimpleCard gottenCard = collection.getNext();
    assertSame(lastCard, gottenCard);
    // после получения карты, остаток карт в коллекции уменьшился на одну карту
    assertEquals(count - 1, collection.getSize());
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
    int count = collection.getSize();
    // добавленная карта удаляется успешно
    assertTrue(collection.pull(card));
    // количество карт уменьшилось
    assertEquals(count - 1, collection.getSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void pullError() throws Exception {
    SimpleCard card = getMockCard();
    collection.pull(card);
  }

  @Test
  public void pullCards() throws Exception {
    CardCollection<SimpleCard> cardList = new CardCollection<>(2);
    // добали две карт, затем удалили две карты
    SimpleCard card = getMockCard();
    cardList.addCard(card);
    collection.addCard(card);
    card = getMockCard();
    cardList.addCard(card);
    collection.addCard(card);
    int count = collection.getSize();
    // количество карт уменьшилось
    collection.pullCards(cardList);
    assertEquals(count - cardList.getSize(), collection.getSize());
  }

  @Test(expected = IllegalArgumentException.class)
  public void pullCardsError() throws Exception {
    CardCollection<SimpleCard> cardList = new CardCollection<>(2);
    cardList.addCard(getMockCard());
    cardList.addCard(getMockCard());
    collection.pullCards(cardList);
  }

}