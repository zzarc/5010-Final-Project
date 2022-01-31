package controller.keyhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handler for location key press.
 */
public class LocationKeyHandler extends AbstractKeyHandler {

  /**
   * Constructor.
   *
   * @param controller the controller.
   */
  public LocationKeyHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    if (!model.isGameEnd()) {
      view.displayMessage(model.getPlayerLocationDescription());
    }
  }
}
