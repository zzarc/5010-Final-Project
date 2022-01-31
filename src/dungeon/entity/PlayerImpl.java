package dungeon.entity;

import dungeon.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * The implementation of Player interface. Player represents the object that take action with
 * commands. Player can move up, down, left, right, and pick up treasures. Player can also shoot
 * arrow to kill monsters in the dungeon, if moving to the end or eaten by monster, the game will
 * end. Player can use arrow to shoot and kill monsters, but will consume the arrow. After using up
 * all arrows, player need to find more arrows in the dungeon.
 */
public class PlayerImpl extends AbstractDungeonEntity implements Player {

  private Map<Class<? extends CollectableEntity>, Integer> items;

  /**
   * Constructor for PlayerImpl class.
   */
  public PlayerImpl(Location location) {
    if (location == null) {
      throw new IllegalArgumentException();
    }
    this.currentLocation = location;
    this.items = new HashMap<>();
    addItem(new Arrow());
    addItem(new Arrow());
    addItem(new Arrow());
  }

  /**
   * Get the list of items that player has collected.
   *
   * @return Player's collected items list
   */
  @Override
  public Map<Class<? extends CollectableEntity>, Integer> getAllItems() {
    return this.items;
  }

  /**
   * Add an item to the player's collected item list.
   *
   * @param item the item to be added
   */
  @Override
  public void addItem(CollectableEntity item) {
    if (item == null) {
      throw new IllegalStateException();
    }
    if (!(item instanceof CollectableEntity)) {
      throw new IllegalArgumentException();
    }
    if (this.items.containsKey(item.getClass())) {
      int num = this.items.get(item.getClass());
      this.items.put(item.getClass(), num + 1);
    } else {
      this.items.put(item.getClass(), 1);
    }
  }

  /**
   * Get the number of arrows player current has.
   *
   * @return the number of arrows.
   */
  public int getArrows() {
    if (items.get(Arrow.class) == null) {
      return 0;
    } else {
      return items.get(Arrow.class);
    }
  }

  /**
   * Move the player towards the given direction.
   *
   * @param d the direction.
   */
  public void move(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException();
    }
    Location c = currentLocation;
    Location newLocation = c.getLocationFrom(d);
    if (newLocation != null) {
      this.currentLocation = newLocation;
    }
  }

  private class Pair {

    Location location;
    Direction direction;
    int distance;

    Pair(Location l, Direction direction, int distance) {
      this.location = l;
      this.direction = direction;
      this.distance = distance;
    }
  }

  /**
   * Shoot the arrow towards a direction with distance (how many caves passed, include the last
   * one).
   *
   * @param d        the direction to shoot arrow
   * @param distance the distance to shoot arrow(how many caves passed, including last one)
   * @return the destination location that the arrow ends up with
   */
  @Override
  public Location shoot(Direction d, int distance) {
    if (d == null || distance < 0) {
      throw new IllegalArgumentException();
    }
    if (getArrows() == 0) {
      throw new IllegalStateException("Out of arrow");
    }

    this.items.put(Arrow.class, getArrows() - 1);

    List<Location> visited = new ArrayList<Location>();
    Queue<Pair> bfsQueue = new LinkedList<>();
    // if current location has a monster, let player hit the monster
    if (currentLocation.getMonster() != null) {
      return currentLocation;
    }

    // if current location is a tunnel, treat it as a cave.
    if (currentLocation.getType() == LocationType.TUNNEL) {
      if (currentLocation.getLocationFrom(d) != null) {
        visited.add(currentLocation);
        distance -= 1;
        bfsQueue.add(
            new Pair(currentLocation.getLocationFrom(d), d, distance));
      } else {
        return currentLocation;
      }
    } else {
      bfsQueue.add(new Pair(this.currentLocation, d, distance));
    }

    while (!bfsQueue.isEmpty()) {
      Pair current = bfsQueue.poll();
      if (visited.contains(current.location)) {
        continue;
      }
      visited.add(current.location);
      List<Direction> availableDirections = current.location.getConnectedDirections();
      if (current.location.getType() == LocationType.TUNNEL) {
        for (Direction direction : availableDirections) {
          bfsQueue.add(
              new Pair(current.location.getLocationFrom(direction), direction, current.distance));
        }
      } else {
        if (current.distance == 0 && currentLocation.getType() == LocationType.CAVE) {
          return current.location;
        }
        // only decrease distance when passing a cave (including last cave)
        if (current.location.getLocationFrom(current.direction) != null) {
          bfsQueue.add(
              new Pair(current.location.getLocationFrom(current.direction), current.direction,
                  current.distance - 1));
        } else {
          return current.location;
        }
      }
    }

    return this.currentLocation;
  }

  /**
   * Alternative shoot method without distance restriction.
   *
   * @param d the direction to shoot arrow.
   * @return the location arrow ends.
   */
  public Location shoot(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException();
    }
    if (getArrows() == 0) {
      throw new IllegalStateException("Out of arrow");
    }

    this.items.put(Arrow.class, getArrows() - 1);

    List<Location> visited = new ArrayList<Location>();
    Queue<Pair> bfsQueue = new LinkedList<>();

    // if current location has a monster, let player hit the monster
    if (currentLocation.getMonster() != null) {
      return currentLocation;
    }

    // if current location is a tunnel, treat it as a cave.
    if (currentLocation.getType() == LocationType.TUNNEL) {
      if (currentLocation.getLocationFrom(d) != null) {
        visited.add(currentLocation);
        bfsQueue.add(
            new Pair(currentLocation.getLocationFrom(d), d, 0));
      } else {
        return currentLocation;
      }
    } else {
      bfsQueue.add(new Pair(this.currentLocation, d, 0));
    }

    while (!bfsQueue.isEmpty()) {
      Pair current = bfsQueue.poll();
      if (visited.contains(current.location)) {
        continue;
      }
      if (current.location.getMonster() != null) {
        return current.location;
      }
      visited.add(current.location);
      if (current.location.getType() == LocationType.TUNNEL) {
        List<Direction> availableDirections = current.location.getConnectedDirections();
        for (Direction direction : availableDirections) {
          bfsQueue.add(
              new Pair(current.location.getLocationFrom(direction), direction, current.distance));
        }
      } else {
        if (current.location.getLocationFrom(current.direction) != null) {
          bfsQueue.add(
              new Pair(current.location.getLocationFrom(current.direction), current.direction,
                  current.distance));
        } else {
          return current.location;
        }
      }
    }

    return this.currentLocation;
  }

  @Override
  public String toString() {
    String result = String.format("location: (%d, %d)\n", currentLocation.getX(),
        currentLocation.getY());
    for (Class key : items.keySet()) {
      result += String.format("%s: %d\n", key.getSimpleName(), items.get(key));
    }

    return result;
  }
}