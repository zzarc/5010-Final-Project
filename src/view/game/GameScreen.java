package view.game;

import controller.Controller;
import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import java.awt.Color;
import view.InnerScreen;

/**
 * Game screen. It's where player can actually play the game use keyboard and mouse clicks. It shows
 * the treasure player has collected and the map, plus the result text.
 */
public class GameScreen extends InnerScreen {

  private TopBar topBar;
  private MapDisplay map;
  private ArrowKeyDisplay arrowKeys;
  private TextArea textArea;

  /**
   * Default Constructor.
   *
   * @param listener the controller.
   */
  public GameScreen(Controller listener) {
    if (listener == null) {
      throw new IllegalArgumentException();
    }

    this.setSize(1024, 768);
    this.setLayout(null);
    this.setBackground(Color.BLACK);

    this.topBar = new TopBar(listener);
    topBar.setLocation(0, 0);

    this.map = new MapDisplay();
    map.setLocation(0, 80);

    this.arrowKeys = new ArrowKeyDisplay(listener);
    arrowKeys.setLocation(map.getWidth() / 2 - 25, 305);

    this.textArea = new TextArea();
    textArea.setLocation(0, map.getY() + map.getHeight());

    this.add(map);
    this.add(topBar);
    this.add(textArea);
  }


  @Override
  public void refresh(Player p, DungeonMap map) {
    if (p == null || map == null) {
      throw new IllegalArgumentException();
    }
    this.map.refresh(p.getCurrentLocation(), map.getLocations());
    topBar.refresh(p);
    arrowKeys.refresh();
  }

  /**
   * Show the text on text area.
   *
   * @param result the result text to update.
   */
  public void updateResultText(String result) {
    if (result == null) {
      throw new IllegalArgumentException();
    }
    this.textArea.refresh(result);
  }

  // allow components to overlap
  @Override
  public boolean isOptimizedDrawingEnabled() {
    return false;
  }
}