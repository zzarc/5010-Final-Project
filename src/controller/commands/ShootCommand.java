package controller.commands;

import dungeon.Direction;
import dungeon.Dungeon;
import view.View;

/**
 * Shoot command for model.
 */
public class ShootCommand implements Command {

  private int distance;
  private Direction direction;

  /**
   * Shoot command constructor.
   *
   * @param direction the direction to shoot.
   * @param dis       the distance to shoot.
   */
  public ShootCommand(String direction, String dis) {
    if (dis == null || direction == null) {
      throw new IllegalArgumentException();
    }
    try {
      this.distance = Integer.parseInt(dis);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
    if (direction.equals("north")) {
      this.direction = Direction.NORTH;
    } else if (direction.equals("south")) {
      this.direction = Direction.SOUTH;
    } else if (direction.equals("west")) {
      this.direction = Direction.WEST;
    } else if (direction.equals("east")) {
      this.direction = Direction.EAST;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void execute(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.displayMessage(model.shoot(direction, distance));
  }
}
