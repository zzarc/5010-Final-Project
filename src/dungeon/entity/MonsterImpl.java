package dungeon.entity;

import dungeon.Direction;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Monster concrete class. Monster can be spawned in caves and hurt player if player enters its
 * location. Monster can be killed by player if player hit it twice with arrows. Monster hit once
 * will become injured and player has 50% chance to escape if in the same location. Monster will
 * emit odor to nearby locations and will stop after being killed.
 */
public class MonsterImpl extends AbstractDungeonEntity implements Monster {

  private int health;
  private Location current;
  private String name;

  /**
   * Constructor for monster.
   *
   * @param health  the health of monster.
   * @param current current location of monster.
   */
  public MonsterImpl(String name, int health, Location current) {
    if (current == null || name == null) {
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.health = health;
    this.current = current;
    onSpawn();
  }

  /**
   * Get the health of monster.
   *
   * @return monster's health
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Damage the monster.
   */
  public void damage() {
    if (this.health > 0) {
      this.health--;
      if (this.health == 0) {
        onDeath();
      }
    }
  }

  /**
   * Get the name of the monster.
   *
   * @return the name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Helper class for BFS.
   */
  private class Pair {

    Location location;
    int distance;

    Pair(Location l, int distance) {
      this.location = l;
      this.distance = distance;
    }
  }

  /**
   * Generate smell to nearby locations upon spawning.
   */
  private void onSpawn() {
    // add smell with BFS to nearby locations
    List<Location> visited = new ArrayList<Location>();
    Queue<Pair> bfsQueue = new LinkedList<>();
    bfsQueue.add(new Pair(this.current, 0));
    while (!bfsQueue.isEmpty()) {
      Pair current = bfsQueue.poll();
      if (visited.contains(current.location) || current.distance > 2) {
        continue;
      }
      current.location.addSmell(3 - current.distance);
      visited.add(current.location);
      List<Direction> availableDirections = current.location.getConnectedDirections();
      for (Direction d : availableDirections) {
        bfsQueue.add(new Pair(current.location.getLocationFrom(d), current.distance + 1));
      }
    }
  }

  /**
   * remove smells from nearby locations upon being killed.
   */
  private void onDeath() {
    // remove smell with BFS to nearby locations
    List<Location> visited = new ArrayList<Location>();
    Queue<Pair> bfsQueue = new LinkedList<>();
    bfsQueue.add(new Pair(this.current, 0));
    while (!bfsQueue.isEmpty()) {
      Pair current = bfsQueue.poll();
      if (visited.contains(current.location) || current.distance > 2) {
        continue;
      }
      current.location.removeSmell(3 - current.distance);
      visited.add(current.location);
      List<Direction> availableDirections = current.location.getConnectedDirections();
      for (Direction d : availableDirections) {
        bfsQueue.add(new Pair(current.location.getLocationFrom(d), current.distance + 1));
      }
    }
  }
}
