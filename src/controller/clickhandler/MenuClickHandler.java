package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;


/**
 * Handle user click on Menu button.
 */
public class MenuClickHandler extends AbstractClickHandler {

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public MenuClickHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.showMenuScreen();
  }
}
