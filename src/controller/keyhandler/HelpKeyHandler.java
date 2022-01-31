package controller.keyhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * The handler for help key press.
 */
public class HelpKeyHandler extends AbstractKeyHandler {

  /**
   * Constructor.
   *
   * @param controller the controller.
   */
  public HelpKeyHandler(Controller controller) {
    super(controller);
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    if (!model.isGameEnd()) {
      view.displayHelpMessageGraphics();
    }
  }
}
