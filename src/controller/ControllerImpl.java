package controller;

import controller.commands.Command;
import controller.commands.PickCommand;
import controller.commands.QuitGameCommand;
import controller.commands.ShootCommand;
import controller.commands.ShowHelpMessageCommand;
import controller.commands.ShowLocationCommand;
import controller.commands.ShowMapCommand;
import controller.commands.ShowPlayerCommand;
import controller.commands.MoveCommand;
import controller.clickhandler.ClickHandler;
import controller.keyhandler.KeyHandler;
import dungeon.Dungeon;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import view.View;


/**
 * The implementation of controller. The controller keeps reading user input and call dungeon's
 * methods in the model, then update the information to the view.
 */
public class ControllerImpl implements Controller {

  private Dungeon model;
  private View view;

  private Scanner scanner;
  private Map<String, Function<Scanner, Command>> knownCommands;

  /**
   * Constructor for controller.
   *
   * @param model the dungeon model.
   * @param view  the view.
   */
  public ControllerImpl(Dungeon model, View view) {
    this.model = model;
    this.view = view;

    knownCommands = new HashMap<>();
    knownCommands.put("pick", s -> new PickCommand());
    knownCommands.put("move", s -> new MoveCommand(s.next()));
    knownCommands.put("shoot", s -> new ShootCommand(s.next(), s.next()));
    knownCommands.put("map", s -> new ShowMapCommand());
    knownCommands.put("player", s -> new ShowPlayerCommand());
    knownCommands.put("location", s -> new ShowLocationCommand());
    knownCommands.put("help", s -> new ShowHelpMessageCommand());
    knownCommands.put("quit", s -> new QuitGameCommand());
  }

  @Override
  public void playGame(InputStream input, Appendable output) {
    this.scanner = new Scanner(input);
    this.view.addClickListener(this);
    this.view.showStartScreen();
    if (input == null || output == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    Scanner scan = new Scanner(input);
    while (scan.hasNext()) {
      String in = scan.next();
      executeCommand(in);
    }
  }

  private void executeCommand(String command) {
    Function<Scanner, Command> cmd = knownCommands.getOrDefault(command, null);
    if (cmd != null) {
      try {
        Command c = cmd.apply(this.scanner);
        if (!model.isGameEnd() || c instanceof QuitGameCommand) {
          c.execute(model, view);
          view.refresh(model.getPlayer(), model.getMap());
        }
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println("COMMAND ERROR");
      }
    } else {
      view.displayMessage("WRONG COMMAND");
    }
  }

  /**
   * Handle user button clicks.
   *
   * @param handler the handler that handles clicks.
   */
  @Override
  public void handleClick(ClickHandler handler) {
    if (handler == null) {
      throw new IllegalArgumentException();
    }
    handler.handle(model, view);
  }

  /**
   * Handle user key presses.
   *
   * @param handler the handler that handles clicks.
   */
  @Override
  public void handleKey(KeyHandler handler) {
    if (handler == null) {
      throw new IllegalArgumentException();
    }
    handler.handle(model, view);
  }
}
