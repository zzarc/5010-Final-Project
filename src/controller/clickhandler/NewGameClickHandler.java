package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handle click on NewGame button.
 */
public class NewGameClickHandler extends AbstractClickHandler {

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public NewGameClickHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.showSettingScreen();
  }
}
