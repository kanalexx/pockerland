package com.kanaa;

public class Card {
  private Rating rating;
  private Suit suit;

  private Card(Rating rating, Suit suit) {
    this.rating = rating;
    this.suit = suit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Card)) return false;
    Card card = (Card) o;
    if (!getSuit().isEquals(card.getSuit())) return false;
    return getRating().isEquals(card.getRating());
  }

  @Override
  public int hashCode() {
    int result = getSuit() != null ? getSuit().hashCode() : 0;
    result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
    return result;
  }

  public static Card get(Rating rating, Suit suit) {
    return new Card(rating, suit);
  }

  @Override
  public String toString() {
    return "[" + rating.toString() + "_" + suit.toString() + "]";
  }

  public Suit getSuit() {
    return suit;
  }

  public Rating getRating() {
    return rating;
  }

  public Suit.Color getColor() {
    return suit.getColor();
  }
}
