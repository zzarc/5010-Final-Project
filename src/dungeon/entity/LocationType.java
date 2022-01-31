package dungeon.entity;

/**
 * Location's type, it's a tunnel if there's 2 connections, else it's a cave.
 */
public enum LocationType {
  TUNNEL("Tunnel"),
  CAVE("Cave");

  private String name;

  private LocationType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
