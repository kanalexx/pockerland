package com.kanaa;

import static com.kanaa.Rating.*;
import static com.kanaa.Suit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DeckTest {

  private Deck deck;

  @Before
  public void setUp() throws Exception {
    deck = new Deck();
  }

  /** В колоде покера 52 карты */
  @Test
  public void deckSizeTest() throws Exception {
    assertEquals(52, deck.getSize());
  }

  @Test
  public void shuffleTest() throws Exception {
    deck.shuffle();
    int clubs = 0;
    int spades = 0;
    int hearts = 0;
    int diamonds = 0;

    while (deck.hasNext()) {
      Card card = deck.getNext();
      switch (card.getSuit()) {
        case CLUBS: clubs++;
            break;
        case HEARTS: hearts++;
            break;
        case SPADES: spades++;
            break;
        case DIAMONDS: diamonds++;
            break;
      }
    }

    assertEquals(13, clubs);
    assertEquals(13, hearts);
    assertEquals(13, spades);
    assertEquals(13, diamonds);
  }

  @Test
  public void getLeftCountSuitTest() throws Exception {
    for(Suit suit: Suit.getValues()) {
      assertEquals(13, deck.getLeftCount(Card.get(Rating.ANY, suit)));
    }
  }

  @Test
  public void getLeftCountRatingTest() throws Exception {
    for(Rating rating: Rating.getValues()) {
      assertEquals(4, deck.getLeftCount(Card.get(rating, Suit.ANY)));
    }
  }

  @Test
  public void getLeftCountColorTest() throws Exception {
    for(Suit.Color color: Suit.Color.getValues()) {
      assertEquals(26, deck.getLeftCount(color));
    }
  }

  @Test
  public void getChanceSuitTest() throws Exception {
    assertEquals(1.92, deck.getChance(Card.get(ACE, Suit.SPADES)), Utils.EXP);
    assertEquals(7.69, deck.getChance(Card.get(ACE, Suit.ANY)), Utils.EXP);
    assertEquals(25, deck.getChance(Card.get(Rating.ANY, Suit.SPADES)), Utils.EXP);
    assertEquals(100, deck.getChance(Card.get(Rating.ANY, Suit.ANY)), Utils.EXP);
  }

  @Test
  public void findTest() throws Exception {
    Card card = Card.get(ACE, Suit.SPADES);
    assertNotNull(deck.find(card));

    card = Card.get(ACE, Suit.ANY);
    card = deck.find(card);
    assertNotNull(card);
    assertTrue(ACE.isEquals(card.getRating()));
    assertNotEquals(Rating.ANY, card.getSuit());
  }

  @Test
  public void pullTest() throws Exception {
    Card card = Card.get(ACE, Suit.SPADES);
    assertTrue(deck.pull(card));
    assertEquals(0, deck.getLeftCount(card));
  }

  @Test
  public void pullCardsTest() throws Exception {
    List<Card> cardList = Arrays.asList(
        Card.get(ACE, CLUBS),
        Card.get(KING, CLUBS),
        Card.get(QUEEN, CLUBS),
        Card.get(SEVEN, CLUBS),
        Card.get(TWO, SPADES),
        Card.get(FIVE, DIAMONDS)
    );
    deck.pullCards(cardList);
    assertEquals(46,deck.getLeftCount());
    assertNull(deck.find(Card.get(ACE, Suit.CLUBS)));
  }

  @Test
  public void getChanceAfterPullTest() throws Exception {
    List<Card> cardList = Arrays.asList(
        Card.get(ACE, CLUBS),
        Card.get(KING, CLUBS),
        Card.get(QUEEN, CLUBS),
        Card.get(SEVEN, CLUBS),
        Card.get(TWO, SPADES),
        Card.get(FIVE, DIAMONDS)
    );
    assertEquals(19.57, deck.getChanceAfterPull(cardList, Card.get(Rating.ANY, CLUBS)), Utils.EXP);
  }

  @Test
  public void getChanceCardsTest() throws Exception {
    List<Card> cardList = Arrays.asList(
        Card.get(ACE, Suit.ANY),
        Card.get(ACE, Suit.ANY)
    );
    assertEquals(0.45, deck.getChanceCards(cardList), Utils.EXP);
  }

  @Test
  public void getChanceCards2Test() throws Exception {
    List<Card> cardList = Arrays.asList(
        Card.get(ACE, Suit.ANY),
        Card.get(KING, Suit.ANY)
    );
    assertEquals(1.21, deck.getChanceCards(cardList), Utils.EXP);
  }

  @Test
  public void getChanceCards3Test() throws Exception {
    List<Card> cardList = Arrays.asList(
        Card.get(ACE, Suit.ANY),
        Card.get(KING, Suit.ANY),
        Card.get(ACE, Suit.ANY)
    );
    assertEquals(0.13, deck.getChanceCards(cardList), Utils.EXP);
  }

  @Test
  public void getChanceCardsAfterPullTest() throws Exception {
    List<Card> pullCardList = Arrays.asList(
        Card.get(ACE, CLUBS),
        Card.get(KING, CLUBS),
        Card.get(QUEEN, CLUBS),
        Card.get(EIGHT, SPADES),
        Card.get(TWO, HEARTS)
    );
    deck.pullCards(pullCardList);
    List<Card> cardList = Arrays.asList(
        Card.get(Rating.ANY, CLUBS),
        Card.get(Rating.ANY, CLUBS)
    );
    assertEquals(4.16, deck.getChanceCards(cardList), Utils.EXP);
  }
}
