package view;

import controller.Controller;
import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import java.io.IOException;
import java.util.List;

/**
 * view implementation. It can output information to both console and GUI.
 */
public class ViewImpl implements View {

  private Appendable output;
  private MainWindow gameWindow;

  /**
   * Default constructor.
   */
  public ViewImpl() {
    this.output = System.out;
    gameWindow = new MainWindow();
  }

  @Override
  public void setOutput(Appendable output) {
    this.output = output;
  }

  @Override
  public void addClickListener(Controller clickListener) {
    this.gameWindow.setListener(clickListener);
  }

  /**
   * display the help message.
   */
  public void displayHelpMessage() {
    String s = "Available commands:\n";
    s += "player - show player information\n";
    s += "location - show current location information\n";
    s += "map - show dungeon map\n";
    s += "move <direction> - player move (move north, move south, move east, move west)\n";
    s += "shoot <direction> <distance> - shoot arrow (shoot north 2)\n";
    s += "pick - pick up items\n";
    s += "quit - quit game\n";
    s += "help - show help message\n";
    append(s + "\n");
  }

  /**
   * Display a message.
   *
   * @param s the message to display.
   */
  public void displayMessage(String s) {
    append(s + "\n");
    gameWindow.updateText(s);
  }

  /**
   * Display the item list.
   *
   * @param items the item list.
   */
  public void displayItemsPickedUp(List<?> items) {
    String s = "";
    if (items.size() == 0) {
      s += ("You picked up nothing\n");
    } else {
      s += ("You picked up:  ");
      for (Object t : items) {
        s += (t.toString() + "  ");
      }
      s += ("\n\n");
    }
    append(s);
    gameWindow.updateText(s);
  }

  /**
   * Helper method to append a string to the output, with exception control.
   *
   * @param s the string to be appended
   */
  private void append(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Appending string cannot be null");
    }
    try {
      this.output.append(s);
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  @Override
  public void showStartScreen() {
    gameWindow.showStartScreen();
  }

  @Override
  public void showSettingScreen() {
    gameWindow.showSettingScreen();
  }

  @Override
  public void showGameScreen() {
    gameWindow.showGameScreen();
  }

  @Override
  public void showMenuScreen() {
    gameWindow.showMenuScreen();
  }

  @Override
  public void enterShootMode() {
    gameWindow.enterShootMode();
    String s = ""
        + "You try to fire an arrow\n"
        + "Use wsad to choose direction\n";
    gameWindow.updateText(s);
  }

  @Override
  public void exitShootMode() {
    gameWindow.exitShootMode();
  }

  @Override
  public void refresh(Player player, DungeonMap map) {
    if (player == null || map == null) {
      throw new IllegalArgumentException();
    }
    gameWindow.refresh(player, map);
  }

  @Override
  public void displayHelpMessageGraphics() {
    String s = ""
        + "Welcome to the Dungeon\n"
        + "wsad - move\n"
        + "h - help, j - pick,  k - shoot, l - location info \n";
    gameWindow.updateText(s);
  }
}
