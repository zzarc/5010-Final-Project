package controller.commands;

import dungeon.Dungeon;
import view.View;

/**
 * Pick command for model.
 */
public class PickCommand implements Command {

  @Override
  public void execute(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    view.displayItemsPickedUp(model.pick());
  }
}
