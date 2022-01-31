package controller.keyhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;

/**
 * The abstract class for key handler. It calls the controller to execute the logic inside handle
 * method.
 */
public abstract class AbstractKeyHandler implements KeyHandler {

  protected Controller controller;

  /**
   * Constructor.
   *
   * @param controller the controller.
   */
  public AbstractKeyHandler(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    this.controller = controller;
  }

  /**
   * Default handle class, throw exception, concrete class must override.
   *
   * @param model the dungeon model.
   * @param view  the dungeon view.
   */
  public void handle(Dungeon model, View view) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void run() {
    controller.handleKey(this);
  }
}
