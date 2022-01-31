package controller.clickhandler;

import dungeon.Dungeon;
import view.View;

/**
 * The click handler interface. Implement this interface for handlers of user clicks.
 */
public interface ClickHandler {

  void handle(Dungeon model, View view);
}
