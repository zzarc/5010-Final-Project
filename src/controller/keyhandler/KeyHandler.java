package controller.keyhandler;

import dungeon.Dungeon;
import view.View;

/**
 * The key handler interface. Implement this interface for handlers of user key presses.
 */
public interface KeyHandler extends Runnable {

  void handle(Dungeon model, View view);
}
