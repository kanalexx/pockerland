package com.kanaa;

public enum Rating {
  ACE,
  KING,
  QUEEN,
  JACK,
  TEN,
  NINE,
  EIGHT,
  SEVEN,
  SIX,
  FIVE,
  FOUR,
  THREE,
  TWO,
  ANY;

  public boolean isAny() {
    return this.equals(Rating.ANY);
  }

  public boolean isEquals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Rating)) return false;
    return ((Rating) o).isAny() || isAny();

  }

  public static Rating[] getValues() {
    return new Rating[]{ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO};
  }
}
