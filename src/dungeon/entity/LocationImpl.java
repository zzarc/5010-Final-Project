package dungeon.entity;

import dungeon.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The location implementation. Location is the basic unit in the dungeon that the map is made of.
 * Treasures, monsters and arrows will be spawned in locations.
 */
public class LocationImpl implements Location {

  private List<CollectableEntity> items;
  private Map<Direction, Location> connected;
  private Monster monster;
  private int smell;
  private int x;
  private int y;
  private boolean visited;

  private List<CollectableEntity> backupItems;
  private Monster backupMonster;
  private boolean backupVisited;

  /**
   * Constructor for location.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public LocationImpl(int x, int y) {
    this.smell = 0;
    this.x = x;
    this.y = y;
    this.items = new ArrayList<>();
    this.connected = new HashMap<>();
    this.monster = null;
    this.visited = false;

    this.backupItems = new ArrayList<>();
    this.backupMonster = null;
    this.backupVisited = false;
  }

  /**
   * Get the location x coordinate.
   *
   * @return the x coordinate
   */
  @Override
  public int getX() {
    return this.x;
  }

  /**
   * Get the location y coordinate.
   *
   * @return the y coordinate
   */
  @Override
  public int getY() {
    return this.y;
  }

  /**
   * Set the location x coordinate.
   *
   * @param x the x coordinate
   */
  @Override
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Set the location y coordinate.
   *
   * @param y the y coordinate
   */
  @Override
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Get the connected direction of the location.
   *
   * @return all connected directions.
   */
  @Override
  public List<Direction> getConnectedDirections() {
    List<Direction> directions = this.connected.keySet().stream().collect(Collectors.toList());
    directions.sort((a, b) -> a.toString().compareTo(b.toString()));
    return directions;
  }

  /**
   * Get the location from the connected direction.
   *
   * @param d the connected direction.
   * @return the location from that connected direction.
   */
  @Override
  public Location getLocationFrom(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException();
    }
    return this.connected.get(d);
  }

  /**
   * Add the location to the connected direction.
   *
   * @param d the connected direction.
   * @param l the location to be connected.
   */
  @Override
  public void addLocationFrom(Direction d, Location l) {
    if (d == null || l == null) {
      throw new IllegalArgumentException();
    }
    this.connected.put(d, l);
  }

  /**
   * Get the monster from location.
   *
   * @return the monster.
   */
  @Override
  public Monster getMonster() {
    return this.monster;
  }

  /**
   * Add a monster to location.
   *
   * @param m the monster to be added.
   */
  @Override
  public void addMonster(Monster m) {
    if (m == null) {
      throw new IllegalArgumentException();
    }
    this.monster = m;
  }

  /**
   * Get the location's type, tunnel or cave.
   *
   * @return location's type.
   */
  @Override
  public LocationType getType() {
    return this.connected.keySet().size() == 2 ? LocationType.TUNNEL : LocationType.CAVE;
  }

  /**
   * Add an item to the location.
   *
   * @param item the item to be added.
   */
  @Override
  public void addItem(CollectableEntity item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    this.items.add(item);
  }

  /**
   * Get all items from the location.
   *
   * @return All items in the location.
   */
  @Override
  public List<CollectableEntity> getItems() {
    return this.items;
  }

  /**
   * Clear all items in the location.
   */
  @Override
  public void clearItem() {
    this.items.clear();
  }

  /**
   * Get the smell level at the location.
   *
   * @return the smell level.
   */
  @Override
  public SmellLevel getSmellLevel() {
    if (this.smell == 1) {
      return SmellLevel.WEAK;
    } else if (this.smell >= 2) {
      return SmellLevel.STRONG;
    }
    return SmellLevel.NOTHING;
  }

  /**
   * Clear the monster in the location.
   */
  @Override
  public void clearMonster() {
    this.monster = null;
  }

  /**
   * Add smell to the location.
   *
   * @param level how many levels to add.
   */
  @Override
  public void addSmell(int level) {
    this.smell += level;
  }

  /**
   * Remove smell from the location.
   *
   * @param level how many levels to remove.
   */
  @Override
  public void removeSmell(int level) {
    this.smell -= level;
  }

  @Override
  public void clearSmell() {
    this.smell = 0;
  }

  @Override
  public void visit() {
    this.visited = true;
  }

  @Override
  public boolean getVisited() {
    return this.visited;
  }

  @Override
  public String toString() {
    String connected = "";
    for (Direction d : getConnectedDirections()) {
      connected += String.format("%s ", d.toString());
    }

    String items = "";
    if (this.items.size() == 0) {
      items = "nothing";
    } else {
      for (CollectableEntity item : this.items) {
        items += String.format("%s ", item.toString());
      }
    }

    String s = ""
        + String.format("It's a %s\n", this.getType().toString())
        + String.format("It smells %s\n", this.getSmellLevel().toString())
        + String.format("It has %s here\n", items)
        + String.format("It leads to %s\n", connected)
        + String.format("What would you do? (move, pick, shoot)\n");
    return s;
  }

  @Override
  public void restore() {
    for (CollectableEntity item : backupItems) {
      this.items.add(item);
    }
    if (backupMonster != null) {
      this.monster = new MonsterImpl(backupMonster.getName(), 2, this);
    }
    this.visited = backupVisited;
  }

  @Override
  public void backup() {
    for (CollectableEntity item : items) {
      this.backupItems.add(item);
    }
    this.backupMonster = monster;
    this.backupVisited = visited;
  }
}
