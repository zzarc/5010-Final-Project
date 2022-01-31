import controller.Controller;
import controller.ControllerImpl;
import dungeon.Dungeon;
import dungeon.DungeonImpl;
import view.View;
import view.ViewImpl;

/**
 * The drive class to simulate the dungeon game. It provides commands to show map/location/player
 * information and other commands for players, as well as a GUI.
 */
public class Drive {

  /**
   * main function to run the drive.
   *
   * @param args string args.
   */
  public static void main(String[] args) {
    Dungeon d = new DungeonImpl();
    View v = new ViewImpl();
    Controller c = new ControllerImpl(d, v);
    c.playGame(System.in, System.out);
  }
}