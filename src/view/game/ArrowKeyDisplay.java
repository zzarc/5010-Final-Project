package view.game;

import controller.Controller;
import controller.clickhandler.MoveClickHandler;
import dungeon.Direction;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.Assets;

/**
 * Display the arrow keys for players to click to move around.
 */
public class ArrowKeyDisplay extends JLabel {

  private final JButton up;
  private final JButton down;
  private final JButton left;
  private final JButton right;

  private final int width = 100;
  private final int height = 100;

  /**
   * Default constructor.
   *
   * @param listener the controller.
   */
  public ArrowKeyDisplay(Controller listener) {
    if (listener == null) {
      throw new IllegalArgumentException();
    }
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setSize(width, height);

    up = new JButton();
    initButtonStyle(up);
    up.setLocation(20, 0);
    up.setIcon(new ImageIcon(Assets.getImage("up")));
    up.setRolloverIcon(new ImageIcon(Assets.getImage("up_hover")));
    this.up.addMouseListener(new MoveClickHandler(listener, Direction.NORTH));

    down = new JButton();
    initButtonStyle(down);
    down.setLocation(20, 40);
    down.setIcon(new ImageIcon(Assets.getImage("down")));
    down.setRolloverIcon(new ImageIcon(Assets.getImage("down_hover")));
    this.down.addMouseListener(new MoveClickHandler(listener, Direction.SOUTH));

    left = new JButton();
    initButtonStyle(left);
    left.setLocation(0, 20);
    left.setIcon(new ImageIcon(Assets.getImage("left")));
    left.setRolloverIcon(new ImageIcon(Assets.getImage("left_hover")));
    this.left.addMouseListener(new MoveClickHandler(listener, Direction.WEST));

    right = new JButton();
    initButtonStyle(right);
    right.setLocation(40, 20);
    right.setIcon(new ImageIcon(Assets.getImage("right")));
    right.setRolloverIcon(new ImageIcon(Assets.getImage("right_hover")));
    this.right.addMouseListener(new MoveClickHandler(listener, Direction.EAST));

    this.add(up);
    this.add(down);
    this.add(left);
    this.add(right);
  }

  private void initButtonStyle(JButton button) {
    if (button == null) {
      throw new IllegalArgumentException();
    }
    button.setSize(20, 20);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setOpaque(false);
  }


  /**
   * Refresh component.
   */
  public void refresh() {
    up.repaint();
    down.repaint();
    left.repaint();
    right.repaint();
  }

}
