package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * Handle click on Quit button.
 */
public class QuitClickHandler extends AbstractClickHandler {

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public QuitClickHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    System.exit(0);
  }
}
