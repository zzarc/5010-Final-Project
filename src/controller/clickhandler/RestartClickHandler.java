package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handle click on Restart button.
 */
public class RestartClickHandler extends AbstractClickHandler {

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public RestartClickHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    model.reset();
    view.showGameScreen();
    view.refresh(model.getPlayer(), model.getMap());
  }
}
