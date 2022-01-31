package dungeon.entity;

/**
 * The level of smells in the locations.
 */
public enum SmellLevel {
  STRONG("very pungent"),
  WEAK("a little pungent"),
  NOTHING("nothing");

  private String name;

  private SmellLevel(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
