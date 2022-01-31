package view;

import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import javax.swing.JPanel;

/**
 * InnerScreen Abstract class. InnerScreen is the content that will be shown in the JFrame.
 */
public abstract class InnerScreen extends JPanel {

  /**
   * The default refresh method to refresh the display content according to model information.
   *
   * @param player the player
   * @param map    the map.
   */
  public void refresh(Player player, DungeonMap map) {
    // default refresh basically does nothing
  }

}
