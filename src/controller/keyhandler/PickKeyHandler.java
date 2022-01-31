package controller.keyhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handler for pick key press.
 */
public class PickKeyHandler extends AbstractKeyHandler {

  /**
   * Constructor.
   *
   * @param controller the controller.
   */
  public PickKeyHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    if (!model.isGameEnd()) {
      view.displayItemsPickedUp(model.pick());
      view.refresh(model.getPlayer(), model.getMap());
    }
  }
}
