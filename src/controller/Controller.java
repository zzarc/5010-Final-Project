package controller;

import controller.clickhandler.ClickHandler;
import controller.keyhandler.KeyHandler;
import java.io.InputStream;

/**
 * The controller for dungeon game. The controller keeps reading user input and call dungeon
 * methods, then update to the view.
 */
public interface Controller {

  /**
   * start playing the game, read user input, parse commands and interact with model and view.
   *
   * @param in  input
   * @param out output
   */
  void playGame(InputStream in, Appendable out);

  /**
   * Handle user button clicks.
   *
   * @param handler the handler that handles clicks.
   */
  void handleClick(ClickHandler handler);

  /**
   * Handle user key presses.
   *
   * @param handler the handler that handles clicks.
   */
  void handleKey(KeyHandler handler);
}
