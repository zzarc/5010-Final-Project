package controller.keyhandler;

import controller.Controller;
import dungeon.Direction;
import dungeon.Dungeon;
import view.View;

/**
 * Handle the player move key.
 */
public class MoveKeyHandler extends AbstractKeyHandler {

  private Direction direction;

  /**
   * Constructor.
   *
   * @param controller the controller.
   * @param d          the direction.
   */
  public MoveKeyHandler(Controller controller, Direction d) {
    super(controller);
    if (d == null) {
      throw new IllegalArgumentException();
    }
    this.direction = d;
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    if (!model.isGameEnd()) {
      view.displayMessage(model.move(direction));
      view.refresh(model.getPlayer(), model.getMap());
    }
  }
}
