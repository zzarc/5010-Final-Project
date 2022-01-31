package controller.commands;

import dungeon.Dungeon;
import view.View;

/**
 * Display the help message with given command.
 */
public class ShowHelpMessageCommand implements Command {

  @Override
  public void execute(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.displayHelpMessage();
  }
}
