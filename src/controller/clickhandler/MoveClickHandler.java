package controller.clickhandler;

import controller.Controller;
import dungeon.Direction;
import dungeon.Dungeon;
import view.View;

/**
 * Handle click on move buttons.
 */
public class MoveClickHandler extends AbstractClickHandler {

  private final Direction direction;

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public MoveClickHandler(Controller controller, Direction d) {
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
