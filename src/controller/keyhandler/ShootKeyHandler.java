package controller.keyhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handler for shoot key press.
 */
public class ShootKeyHandler extends AbstractKeyHandler {

  /**
   * Constructor.
   *
   * @param controller the controller.
   */
  public ShootKeyHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    if (!model.isGameEnd()) {
      view.enterShootMode();
      view.refresh(model.getPlayer(), model.getMap());
    }
  }
}
