package controller.commands;

import dungeon.Dungeon;
import view.View;

/**
 * Quit Command.
 */
public class QuitGameCommand implements Command {

  @Override
  public void execute(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    model.quitGame();
  }
}
