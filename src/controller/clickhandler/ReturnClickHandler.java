package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;


/**
 * Handle click on Return button.
 */
public class ReturnClickHandler extends AbstractClickHandler {

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public ReturnClickHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.showGameScreen();
    view.refresh(model.getPlayer(), model.getMap());
  }
}
