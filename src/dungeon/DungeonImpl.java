package dungeon;

import dungeon.entity.Arrow;
import dungeon.entity.CollectableEntity;
import dungeon.entity.Diamond;
import dungeon.entity.Location;
import dungeon.entity.LocationType;
import dungeon.entity.Monster;
import dungeon.entity.MonsterImpl;
import dungeon.entity.PlayerImpl;
import dungeon.entity.Ruby;
import dungeon.entity.Sapphire;
import dungeon.map.DungeonMap;
import dungeon.map.DungeonMapImpl;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import dungeon.entity.Player;

/**
 * The Dungeon concrete class. Dungeon supports randomly adding treasures to cave, getting player's-
 * description, getting the location's description that player current at, letting player pick up-
 * treasure, and move player with given direction.
 */
public class DungeonImpl implements Dungeon {

  private DungeonMap dungeonMap;
  private Player player;
  private boolean gameEnded;

  /**
   * Default constructor for dungeon, generate the map randomly.
   */
  public DungeonImpl() {
    this.dungeonMap = null;
    this.player = null;
    this.gameEnded = false;
  }

  /**
   * alternative constructor for dungeon.
   *
   * @param n                 height.
   * @param m                 width
   * @param interconnectivity interconnectivity
   * @param wrapping          wrapping
   * @param treasureRatio     treasure ratio
   * @param monsterNumber     monster number
   */
  public DungeonImpl(int n, int m, int interconnectivity, boolean wrapping, int treasureRatio,
      int monsterNumber) {
    this.dungeonMap = null;
    this.player = null;
    this.gameEnded = false;
    generateMap(n, m, interconnectivity, wrapping, treasureRatio, monsterNumber, false);
  }

  @Override
  public void generateMap(int n, int m, int interconnectivity, boolean wrapping, int treasureRatio,
      int monsterNumber, boolean cheat) {
    dungeonMap = new DungeonMapImpl(n, m, interconnectivity, wrapping, cheat);
    this.player = new PlayerImpl(dungeonMap.getStart());
    this.gameEnded = false;
    addTreasures(treasureRatio);
    addArrows(treasureRatio);
    addMonster(monsterNumber);
    // set start location visibility to true
    dungeonMap.getStart().visit();

    // back up locations
    Location[][] l = this.dungeonMap.getLocations();
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        l[i][j].backup();
      }
    }
  }

  /**
   * Get the location's information including coordinates and available treasures.
   *
   * @return location's description
   */
  @Override
  public String getPlayerLocationDescription() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    Location c = player.getCurrentLocation();
    return c.toString();
  }

  /**
   * Get the player's description including location and collected treasures.
   *
   * @return player's description
   */
  @Override
  public String getPlayerDescription() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    return player.toString();
  }

  /**
   * Add treasures randomly to caves according to the given percentage.
   *
   * @param percentage the percentage of caves to add treasures to
   */
  private void addTreasures(int percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("percentage must be between 0 and 100");
    }
    if (percentage < 20) {
      percentage = 20;
    }

    List<Location> caveList = new ArrayList<>();
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        Location c = dungeonMap.getLocationAt(i, j);
        if (c.getType() == LocationType.CAVE) {
          caveList.add(c);
        }
      }
    }

    List<Class<? extends CollectableEntity>> treasureTypes = List.of(
        Ruby.class,
        Sapphire.class,
        Diamond.class
    );

    int treasureNumber = Math.max(1, caveList.size() * percentage / 100);
    for (int i = 0; i < treasureNumber; i++) {
      int j = Rnd.rndInt(caveList.size());
      Location c = caveList.get(j);
      caveList.remove(j);
      Class treasureType = treasureTypes.get(Rnd.rndInt(treasureTypes.size()));
      try {
        c.addItem((CollectableEntity) treasureType.getConstructor().newInstance());
      } catch (NoSuchMethodException e) {
        throw new IllegalStateException();
      } catch (IllegalAccessException e) {
        throw new IllegalStateException();
      } catch (InstantiationException e) {
        throw new IllegalStateException();
      } catch (InvocationTargetException e) {
        throw new IllegalStateException();
      }
    }
  }


  /**
   * Add arrows randomly to locations according to the given percentage.
   *
   * @param percentage the percentage of locations to add arrows to
   */
  private void addArrows(int percentage) throws IllegalArgumentException {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("percentage must be between 0 and 100");
    }

    List<Location> locationList = new ArrayList<>();
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        Location c = dungeonMap.getLocationAt(i, j);
        locationList.add(c);
      }
    }

    int arrowNumber = Math.max(1, locationList.size() * percentage / 100);
    for (int i = 0; i < arrowNumber; i++) {
      int j = Rnd.rndInt(locationList.size());
      Location c = locationList.get(j);
      locationList.remove(j);
      c.addItem(new Arrow());
    }
  }

  /**
   * Helper class to add monsters to the dungeon.
   *
   * @param monsterNumber number of monsters
   * @throws IllegalArgumentException if monster number is negative
   */
  private void addMonster(int monsterNumber) throws IllegalArgumentException {
    if (monsterNumber < 0) {
      throw new IllegalArgumentException("monster number must be positive");
    }

    List<Location> caveList = new ArrayList<>();
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        Location c = dungeonMap.getLocationAt(i, j);
        if (c.getType() == LocationType.CAVE && c != dungeonMap.getStart()
            && c != dungeonMap.getEnd()) {
          caveList.add(c);
        }
      }
    }

    for (int i = 0; i < monsterNumber - 1 && caveList.size() > 0; i++) {
      int j = Rnd.rndInt(caveList.size());
      Location c = caveList.get(j);
      c.addMonster(new MonsterImpl("Otyughs", 2, c));
      caveList.remove(j);
    }
    if (dungeonMap.getEnd().getMonster() == null) {
      dungeonMap.getEnd().addMonster(new MonsterImpl("Otyughs", 2, dungeonMap.getEnd()));
    }
  }

  /**
   * Make player pick up items at current location.
   */
  @Override
  public List<CollectableEntity> pick() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    Location c = player.getCurrentLocation();
    List<CollectableEntity> items = c.getItems();
    for (CollectableEntity t : items) {
      player.addItem(t);
    }
    List<CollectableEntity> returnList = new ArrayList<>(items);
    c.clearItem();
    return returnList;
  }

  /**
   * Move player towards the given direction.
   *
   * @param d the given direction to walk to.
   */
  @Override
  public String move(Direction d) {
    if (d == null) {
      throw new IllegalArgumentException("direction is null");
    }
    player.move(d);
    player.getCurrentLocation().visit();
    if (player.getCurrentLocation().getMonster() != null) {
      Monster m = player.getCurrentLocation().getMonster();
      if (m.getHealth() <= 1) {
        int result = Rnd.rndInt(100);
        if (result > 50) {
          return "You encountered an injured monster, but dodged its attack";
        } else {
          gameEnded = true;
          return "You encountered an injured monster, got devoured\nGame Over";
        }
      } else {
        gameEnded = true;
        return "You encountered a monster, got devoured\nGame Over";
      }
    }
    if (player.getCurrentLocation().equals(dungeonMap.getEnd())) {
      gameEnded = true;
      return "End location reached, you beat the game\nCongratulations!";
    }
    return player.getCurrentLocation().toString();
  }

  /**
   * Let player shoot arrow with given direction and distance.
   *
   * @param d        the direction to shoot the arrow
   * @param distance the number of caves the arrow will travel through(including last cave)
   * @return the result
   */
  @Override
  public String shoot(Direction d, int distance) {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    if (d == null) {
      throw new IllegalStateException();
    }
    if (distance < 0) {
      throw new IllegalArgumentException();
    }
    if (player.getArrows() <= 0) {
      return "Out of arrows";
    }
    Location destination = player.shoot(d, distance);
    if (destination.getMonster() != null) {
      Monster m = destination.getMonster();
      m.damage();
      if (m.getHealth() == 0) {
        destination.clearMonster();
      }
      return "You hear a great howl in the distance";
    }
    return "You shoot an arrow into the darkness";
  }

  @Override
  public String shoot(Direction d) {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    if (d == null) {
      throw new IllegalStateException();
    }
    if (player.getArrows() <= 0) {
      return "Out of arrows";
    }
    Location destination = player.shoot(d);
    if (destination.getMonster() != null) {
      Monster m = destination.getMonster();
      m.damage();
      if (m.getHealth() == 0) {
        destination.clearMonster();
      }
      return "You hear a great howl in the distance";
    }
    return "You shoot an arrow into the darkness";
  }

  /**
   * Check if game is ended or not (if player moves to the end cell).
   */
  @Override
  public boolean isGameEnd() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    return gameEnded;
  }


  /**
   * Force the game to end.
   */
  @Override
  public void quitGame() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    this.gameEnded = true;
  }


  /**
   * For Debug only.
   *
   * @return dungeon visualization
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        Location c = dungeonMap.getLocationAt(i, j);
        List<Direction> directions = c.getConnectedDirections();
        result += directions.contains(Direction.NORTH) ? " | " : "   ";
      }
      result += "\n";
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        Location c = dungeonMap.getLocationAt(i, j);
        List<Direction> directions = c.getConnectedDirections();
        result += directions.contains(Direction.WEST) ? "-" : " ";
        if (player != null && player.getCurrentLocation().getX() == i
            && player.getCurrentLocation().getY() == j) {
          result += "P";
        } else {
          result += c.getMonster() != null ? "M" : "O";
        }
        result += directions.contains(Direction.EAST) ? "-" : " ";
      }
      if (i == dungeonMap.getHeight() - 1) {
        result += "\n";
        for (int j = 0; j < dungeonMap.getWidth(); j++) {
          Location c = dungeonMap.getLocationAt(i, j);
          List<Direction> directions = c.getConnectedDirections();
          result += directions.contains(Direction.SOUTH) ? " | " : "   ";
        }
      }
      result += "\n";
    }

    return result;
  }

  /**
   * Return the dungeon map.
   *
   * @return the dungeonMap in the map
   */
  @Override
  public DungeonMap getMap() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    return this.dungeonMap;
  }

  @Override
  public Player getPlayer() {
    if (dungeonMap == null || player == null) {
      throw new IllegalStateException();
    }
    return this.player;
  }

  @Override
  public void reset() {
    Location[][] l = this.dungeonMap.getLocations();
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        l[i][j].clearSmell();
      }
    }
    for (int i = 0; i < dungeonMap.getHeight(); i++) {
      for (int j = 0; j < dungeonMap.getWidth(); j++) {
        l[i][j].restore();
      }
    }
    this.player = new PlayerImpl(dungeonMap.getStart());
    this.gameEnded = false;
  }
}