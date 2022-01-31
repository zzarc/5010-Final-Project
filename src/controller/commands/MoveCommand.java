package controller.commands;

import dungeon.Direction;
import dungeon.Dungeon;
import view.View;

/**
 * Move command for model.
 */
public class MoveCommand implements Command {

  private Direction direction;

  /**
   * Constructor for move command.
   *
   * @param direction the direction to move
   */
  public MoveCommand(String direction) {
    if (direction == null) {
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
    view.displayMessage(model.move(direction));
  }
}
