package dungeon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Helper class wrapped Random and provides methods for randomness control.
 */
public class Rnd {

  private static final Queue<Integer> nextRandoms = new LinkedList<>();

  /**
   * Give a random integer or a specified number.
   *
   * @param bound maximum bound
   * @return
   */
  public static int rndInt(int bound) {
    if (nextRandoms.size() != 0) {
      return nextRandoms.remove();
    } else {
      Random r = new Random();
      return r.nextInt(bound);
    }
  }

  /**
   * Add an integer to the queue for future returned random value.
   *
   * @param value the specified value
   */
  public static void addNextRandom(int value) {
    nextRandoms.add(value);
  }

  /**
   * Clear the fixed integer queue and resume to random integer.
   */
  public static void reset() {
    nextRandoms.clear();
  }

  /**
   * Get how many integers left for pseudo random.
   *
   * @return
   */
  public static int getQueueSize() {
    return nextRandoms.size();
  }
}
