package controller.commands;

import dungeon.Dungeon;
import view.View;

/**
 * Show Player command.
 */
public class ShowPlayerCommand implements Command {

  @Override
  public void execute(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.displayMessage(model.getPlayerDescription());
  }
}
