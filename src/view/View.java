package view;

import controller.Controller;
import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import java.util.List;

/**
 * The View interface. It can display the information from controller to console and GUI.
 */
public interface View {

  /**
   * display the help message.
   */
  void displayHelpMessage();

  /**
   * Display a message.
   *
   * @param s the message to display.
   */
  void displayMessage(String s);

  /**
   * Display the item list.
   *
   * @param items the item list.
   */
  void displayItemsPickedUp(List<?> items);

  /**
   * Set the output stream.
   *
   * @param output the stream to output.
   */
  void setOutput(Appendable output);

  /**
   * Show start screen.
   */
  void showStartScreen();

  /**
   * Show setting screen.
   */
  void showSettingScreen();

  /**
   * Show game screen.
   */
  void showGameScreen();

  /**
   * Show menu screen.
   */
  void showMenuScreen();

  /**
   * Add click listener.
   *
   * @param controller the click listener.
   */
  void addClickListener(Controller controller);

  /**
   * Display the help message.
   */
  void displayHelpMessageGraphics();

  /**
   * Enter shoot mode.
   */
  void enterShootMode();

  /**
   * Exit shoot mode.
   */
  void exitShootMode();

  /**
   * Refresh the content with given model information.
   *
   * @param p   the player
   * @param map the dungeon map
   */
  void refresh(Player p, DungeonMap map);
}
