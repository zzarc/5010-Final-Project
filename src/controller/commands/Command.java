package controller.commands;

import dungeon.Dungeon;
import view.View;

/**
 * The dungeon command.
 */
public interface Command {

  /**
   * Execute the command.
   */
  void execute(Dungeon model, View view);
}
