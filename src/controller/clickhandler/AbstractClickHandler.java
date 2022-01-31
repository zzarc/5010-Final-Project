package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.View;

/**
 * The Click handler abstract class. It calls the controller to execute the logic inside the handle
 * method.
 */
public abstract class AbstractClickHandler extends MouseAdapter implements ClickHandler {

  protected Controller controller;

  /**
   * Default constructor.
   *
   * @param controller the controller.
   */
  public AbstractClickHandler(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    this.controller = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e == null) {
      throw new IllegalArgumentException();
    }
    super.mouseClicked(e);
    controller.handleClick(this);
  }

  public void handle(Dungeon model, View view) {
    throw new UnsupportedOperationException();
  }
}
