package view.game;

import controller.Controller;
import controller.keyhandler.FireArrowHandler;
import dungeon.Direction;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Keyboard listener for shoot mode.
 */
public class ShootModeListener implements KeyListener {

  private Map<Integer, Runnable> keyPressedMap;

  /**
   * Default constructor.
   *
   * @param handler the controller.
   */
  public ShootModeListener(Controller handler) {
    if (handler == null) {
      throw new IllegalArgumentException();
    }
    this.keyPressedMap = new HashMap<>();
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('w'),
        new FireArrowHandler(handler, Direction.NORTH));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('s'),
        new FireArrowHandler(handler, Direction.SOUTH));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('a'),
        new FireArrowHandler(handler, Direction.WEST));
    keyPressedMap.put(KeyEvent.getExtendedKeyCodeForChar('d'),
        new FireArrowHandler(handler, Direction.EAST));
  }


  /**
   * This is called when the view detects that a key has been typed. Find if anything has been
   * mapped to this key character and if so, execute it.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // don't care
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
    // don't care
  }
}
