package com.kanaa;

public enum Suit {
  SPADES(Color.BLACK),
  DIAMONDS(Color.RED),
  CLUBS(Color.BLACK),
  HEARTS(Color.RED),
  ANY(Color.ANY);

  public enum Color {RED, BLACK, ANY;

    public boolean isAny() {
      return this.equals(Color.ANY);
    }

    public static Color[] getValues() {
      return new Color[]{RED, BLACK};
    }

  }

  private Color color;

  Suit(Color color) {
    this.color = color;
  }

  public boolean isEquals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Suit)) return false;
    return ((Suit) o).isAny() || isAny();
  }

  public Color getColor() {
    return color;
  }

  public static Suit[] getValues() {
    return new Suit[]{SPADES, DIAMONDS, CLUBS, HEARTS};
  }

  public boolean isAny() {
    return this.equals(Suit.ANY);
  }

}
