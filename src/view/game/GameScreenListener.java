package view.game;

import controller.Controller;
import dungeon.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import controller.keyhandler.HelpKeyHandler;
import controller.keyhandler.LocationKeyHandler;
import controller.keyhandler.MoveKeyHandler;
import controller.keyhandler.PickKeyHandler;
import controller.keyhandler.ShootKeyHandler;

/**
 * Keyboard listener for game normal mode.
 */
public class GameScreenListener implements KeyListener {

  private Map<Integer, Runnable> keyPressedMap;

  /**
   * Default Constructor.
   *
   * @param handler the handler.
   */
  public GameScreenListener(Controller handler) {
    if (handler == null) {
      throw new IllegalArgumentException();
    }
    this.keyPressedMap = new HashMap<>();
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('w'),
        new MoveKeyHandler(handler, Direction.NORTH));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('s'),
        new MoveKeyHandler(handler, Direction.SOUTH));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('a'),
        new MoveKeyHandler(handler, Direction.WEST));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('d'), new MoveKeyHandler(handler,
        Direction.EAST));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('h'), new HelpKeyHandler(handler));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('j'), new PickKeyHandler(handler));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('k'), new ShootKeyHandler(handler));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('l'), new LocationKeyHandler(handler));
  }

  /**
   * This is called when the view detects that a key has been typed. Find if anything has been
   * mapped to this key character and if so, execute it.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // don't care about key typed
  }

  /**
   * This is called when the view detects that a key has been pressed. Find if anything has been
   * mapped to this key code and if so, execute it.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (keyPressedMap.containsKey(e.getKeyCode())) {
      keyPressedMap.get(e.getKeyCode()).run();
    }
  }

  /**
   * This is called when the view detects that a key has been released. Find if anything has been
   * mapped to this key code and if so, execute it.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    // don't care about key released
  }
}
