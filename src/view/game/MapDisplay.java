package view.game;

import dungeon.entity.Location;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.Assets;

/**
 * Display the map area.
 */
public class MapDisplay extends JPanel {

  private SingleLocationDisplay[][] singleLocationDisplays;
  private final int width = 1024;
  private final int height = 500;
  private final int cellSize = 50;

  /**
   * Default constructor.
   */
  public MapDisplay() {
    singleLocationDisplays = null;
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setSize(width, height);
  }

  /**
   * Refresh the map area.
   *
   * @param playerLocation player's location.
   * @param grid           the map data.
   */
  public void refresh(Location playerLocation, Location[][] grid) {
    if (playerLocation == null || grid == null) {
      throw new IllegalArgumentException();
    }
    singleLocationDisplays = new SingleLocationDisplay[grid.length][grid[0].length];
    this.removeAll();

    // fix player at the center of screen
    int playerScreenX = width / 2;
    int playerScreenY = height / 2;

    // take offsetX and Y as origin to center the player
    int offsetX = playerScreenX - playerLocation.getY() * cellSize - 15;
    int offsetY = playerScreenY - playerLocation.getX() * cellSize - 15;

    JLabel playerDisplay = new JLabel();
    playerDisplay.setBounds(playerScreenX, playerScreenY, 20, 20);
    playerDisplay.setIcon(new ImageIcon(Assets.getPlayerImage()));
    this.add(playerDisplay);

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        Location l = grid[i][j];
        if (l.getVisited()) {
          int screenX = l.getY() * cellSize + offsetX;
          int screenY = l.getX() * cellSize + offsetY;
          singleLocationDisplays[i][j] = new SingleLocationDisplay(l);
          singleLocationDisplays[i][j].setLocation(screenX, screenY);
          singleLocationDisplays[i][j].setBackground(Color.CYAN);
          this.add(singleLocationDisplays[i][j]);
        }
      }
    }

    this.repaint();
  }

  // allow components to overlap
  @Override
  public boolean isOptimizedDrawingEnabled() {
    return false;
  }
}
