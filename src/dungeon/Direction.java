package dungeon;

/**
 * The direction enum that represents directions that cells are connected and player moving in the
 * dungeon.
 */
public enum Direction {
  NORTH("north"),
  SOUTH("south"),
  WEST("west"),
  EAST("east");

  private final String name;

  private Direction(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
