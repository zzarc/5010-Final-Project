package view.game;

import dungeon.Direction;
import dungeon.entity.Location;
import dungeon.entity.SmellLevel;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import view.Assets;

/**
 * Display for single location.
 */
public class SingleLocationDisplay extends JLabel {

  private Location location;

  /**
   * Default constructor.
   *
   * @param location the location.
   */
  public SingleLocationDisplay(Location location) {
    if (location == null) {
      throw new IllegalArgumentException();
    }
    this.location = location;

    refresh();
  }

  private void refresh() {
    this.removeAll();
    this.setSize(50, 50);
    List<Direction> directions = location.getConnectedDirections();

    if (location.getSmellLevel() == SmellLevel.WEAK) {
      JLabel odor = new JLabel();
      odor.setSize(50, 50);
      this.add(odor);
      odor.setIcon(new ImageIcon(Assets.getWeakStenchImage()));
    } else if (location.getSmellLevel() == SmellLevel.STRONG) {
      JLabel odor = new JLabel();
      odor.setSize(50, 50);
      odor.setIcon(new ImageIcon(Assets.getStrongStenchImage()));
      this.add(odor);
    }

    if (location.getMonster() != null) {
      JLabel monster = new JLabel();
      monster.setBounds(7, 7, 30, 30);
      monster.setIcon(new ImageIcon(Assets.getOtyughImage()));
      this.add(monster);
    }

    if (location.getItems().size() != 0) {
      JLabel treasure = new JLabel();
      treasure.setBounds(13, 13, 25, 25);
      treasure.setIcon(new ImageIcon(Assets.getChestImage()));
      this.add(treasure);
    }

    String conn = "";
    for (Direction e : directions) {
      conn += e.toString().charAt(0);
    }

    if (!conn.equals("")) {
      this.setIcon(new ImageIcon(Assets.getImage(conn)));
    }

    this.repaint();
  }


  // allow components to overlap
  @Override
  public boolean isOptimizedDrawingEnabled() {
    return false;
  }
}
