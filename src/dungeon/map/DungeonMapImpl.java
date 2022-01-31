package dungeon.map;

import dungeon.Direction;
import dungeon.Rnd;
import dungeon.entity.Location;
import dungeon.entity.LocationImpl;
import dungeon.entity.LocationType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * The implementation of Grid interface. Grid is the object that represents the map of the dungeon.
 * Grid consists of locations, in which treasures can be generated at. Grid will be generated with
 * given n, m, interconnectivity, and wrapping. Start and End cell will be generated randomly.
 */
public class DungeonMapImpl implements DungeonMap {

  private final Location[][] grid;
  private final int height;
  private final int width;
  private final int interconnectivity;
  private final boolean wrapping;
  private Location start;
  private Location end;

  /**
   * Constructor for GridImpl, in which a map will be generated.
   *
   * @param n                 map height
   * @param m                 map width
   * @param interconnectivity map interconnectivity
   * @param wrapping          map wrapping or not
   */
  public DungeonMapImpl(int n, int m, int interconnectivity, boolean wrapping, boolean cheat) {
    this.grid = new Location[n][m];
    this.height = n;
    this.width = m;
    this.interconnectivity = interconnectivity;
    this.wrapping = wrapping;
    start = null;
    end = null;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        grid[i][j] = new LocationImpl(i, j);
        if (cheat) {
          grid[i][j].visit();
        }
      }
    }
    generateGrid(n, m, interconnectivity, wrapping);
    generateStartEnd();
  }

  /**
   * Get the cell location according to the given coordinate.
   *
   * @param x cell x
   * @param y cell y
   * @return the location.
   */
  @Override
  public Location getLocationAt(int x, int y) {
    return this.grid[x][y];
  }

  /**
   * Get the starting cell.
   *
   * @return the starting cell
   */
  @Override
  public Location getStart() {
    return start;
  }

  /**
   * Get the end cell.
   *
   * @return the end cell
   */
  @Override
  public Location getEnd() {
    return end;
  }

  /**
   * Get the grid's height.
   *
   * @return grid's height
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the grid's width.
   *
   * @return grid's width
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Get grid's interconnectivity.
   *
   * @return grid's interconnectivity
   */
  @Override
  public int getInterconnectivity() {
    return this.interconnectivity;
  }

  /**
   * Get if grid is wrapped or not.
   *
   * @return true if grid is wrapped else false
   */
  @Override
  public boolean getWrapping() {
    return this.wrapping;
  }

  /**
   * Helper method to generate the map using Kruskal's algorithm.
   *
   * @param n                 map height
   * @param m                 map width
   * @param interconnectivity map interconnectivity
   * @param wrapping          map wrapping or not
   */
  private void generateGrid(int n, int m, int interconnectivity, boolean wrapping) {
    List<Edge> edges = new ArrayList<>();

    // connect all cells at the beginning
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (i != n - 1 || (i == n - 1 && wrapping)) {
          edges.add(new Edge(grid[i][j], grid[(i + 1) % n][j]));
        }
        if (j != m - 1 || (j == m - 1 && wrapping)) {
          edges.add(new Edge(grid[i][j], grid[i][(j + 1) % m]));
        }
      }
    }

    // shuffle the edges for map randomness
    if (Rnd.getQueueSize() == 0) {
      Collections.shuffle(edges);
    }

    // apply Kruskal algorithm to eliminate loops
    Map<Location, Location> parent = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        parent.put(grid[i][j], null);
      }
    }
    List<Edge> selectedEdges = new ArrayList<>();
    for (Edge e : edges) {
      Location rootA = findRoot(e.a, parent);
      Location rootB = findRoot(e.b, parent);
      // only accept edge if two cells are not in the same set
      if (!rootA.equals(rootB)) {
        // merge sets
        parent.put(rootB, rootA);
        // connect two cells
        connectCells(e.a, e.b);
        // remove edge from list
        selectedEdges.add(e);
      }
    }
    edges.removeAll(selectedEdges);

    // shuffle the edges for map randomness
    if (Rnd.getQueueSize() == 0) {
      Collections.shuffle(edges);
    }

    // randomly add some edges based on interconnectivity
    for (int i = 0; i < interconnectivity && i < edges.size(); i++) {
      Edge e = edges.get(i);
      connectCells(e.a, e.b);
    }
  }

  /**
   * Helper method for Kruskal algorithm. Find which root cell the given cell belongs to.
   *
   * @param c      the given cell
   * @param parent the parent set in kruskal implementation
   * @return the root cell of the given cell
   */
  private Location findRoot(Location c, Map<Location, Location> parent) {
    Location root = c;
    while (parent.get(root) != null) {
      root = parent.get(root);
    }
    return root;
  }

  /**
   * Connect two adjacent cells, assign walkable directions to them.
   *
   * @param a the first cell
   * @param b the second cell
   */
  private void connectCells(Location a, Location b) {
    if (a.getX() == b.getX() && (a.getY() < b.getY() || a.getY() - b.getY() > 1)) {
      a.addLocationFrom(Direction.EAST, b);
      b.addLocationFrom(Direction.WEST, a);
    } else if (a.getX() == b.getX() && (a.getY() > b.getY() || b.getY() - a.getY() > 1)) {
      a.addLocationFrom(Direction.WEST, b);
      b.addLocationFrom(Direction.EAST, a);
    } else if (a.getY() == b.getY() && (a.getX() < b.getX() || a.getX() - b.getX() > 1)) {
      a.addLocationFrom(Direction.SOUTH, b);
      b.addLocationFrom(Direction.NORTH, a);
    } else if (a.getY() == b.getY() && (a.getX() > b.getX() || b.getX() - a.getX() > 1)) {
      a.addLocationFrom(Direction.NORTH, b);
      b.addLocationFrom(Direction.SOUTH, a);
    }
  }

  /**
   * Private helper class that represent an edge.
   */
  private static class Edge {

    Location a;
    Location b;

    Edge(Location a, Location b) {
      this.a = a;
      this.b = b;
    }
  }

  /**
   * Private helper class that represent a start and end pair.
   */
  private static class Pair {

    Location a;
    Location b;

    Pair(Location a, Location b) {
      this.a = a;
      this.b = b;
    }
  }

  /**
   * Randomly select start and end cell with BFS algorithm.
   */
  private void generateStartEnd() {
    // get all caves
    List<Location> caves = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (grid[i][j].getType() == LocationType.CAVE) {
          caves.add(grid[i][j]);
        }
      }
    }

    List<Pair> startEndPairs = new ArrayList<>();
    for (int i = 0; i < caves.size(); i++) {
      for (int j = i + 1; j < caves.size(); j++) {
        if (calDistance(caves.get(i), caves.get(j)) >= 5) {
          startEndPairs.add(new Pair(caves.get(i), caves.get(j)));
        }
      }
    }

    if (startEndPairs.size() == 0) {
      throw new IllegalArgumentException("cannot find start end with length at least 5");
    }

    // choose a random pair as start-end
    Pair p = startEndPairs.get(Rnd.rndInt(startEndPairs.size()));
    this.start = p.a;
    this.end = p.b;
  }

  /**
   * Get the shortest distance between two cells.
   *
   * @param a Cell a
   * @param b Cell b
   * @return the distance between cell a and cell b
   */
  protected int calDistance(Location a, Location b) {

    if (a == null || b == null) {
      throw new IllegalArgumentException("Given cells are null");
    }

    // apply BFS to get the shortest distance from cell a to b
    boolean[][] visited = new boolean[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        visited[i][j] = false;
      }
    }

    Queue<BFSPair> bfsQueue = new LinkedList<>();
    bfsQueue.add(new BFSPair(a, 0));

    while (!bfsQueue.isEmpty()) {
      BFSPair current = bfsQueue.poll();
      if (current.location.equals(b)) {
        return current.distance;
      }
      if (visited[current.location.getX()][current.location.getY()]) {
        continue;
      }
      visited[current.location.getX()][current.location.getY()] = true;
      List<Direction> availableDirections = current.location.getConnectedDirections();
      for (Direction d : availableDirections) {
        bfsQueue.add(new BFSPair(current.location.getLocationFrom(d), current.distance + 1));
      }
    }

    return -1;
  }

  private class BFSPair {

    private Location location;
    private int distance;

    public BFSPair(Location l, int d) {
      this.location = l;
      this.distance = d;
    }
  }


  /**
   * Get all locations.
   *
   * @return the location 2d array.
   */
  @Override
  public Location[][] getLocations() {
    return this.grid;
  }
}