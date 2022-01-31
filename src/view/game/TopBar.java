package view.game;

import controller.Controller;
import dungeon.entity.Arrow;
import dungeon.entity.CollectableEntity;
import dungeon.entity.Diamond;
import dungeon.entity.Player;
import dungeon.entity.Ruby;
import dungeon.entity.Sapphire;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import view.Assets;
import java.util.Map;
import controller.clickhandler.MenuClickHandler;

/**
 * The top bar of game screen. It has a menu button, the items players collected and a wheel for
 * player to click to move.
 */
public class TopBar extends JPanel {

  private JButton settingButton;
  private SingleItemDisplay arrow;
  private SingleItemDisplay ruby;
  private SingleItemDisplay sapphire;
  private SingleItemDisplay diamond;
  private ArrowKeyDisplay arrowKeys;

  private Controller listener;

  /**
   * Default constructor.
   *
   * @param listener the controller.
   */
  public TopBar(Controller listener) {

    this.listener = listener;

    this.setSize(1024, 70);
    this.setLayout(null);
    this.setBackground(new Color(20, 20, 20));

    initSettingButton();
    initItems();

    arrowKeys = new ArrowKeyDisplay(listener);
    arrowKeys.setLocation(910, 10);

    this.add(settingButton);
    this.add(arrow);
    this.add(ruby);
    this.add(sapphire);
    this.add(diamond);
    this.add(arrowKeys);
  }

  private void initSettingButton() {
    this.settingButton = new JButton();
    this.settingButton.setIcon(new ImageIcon(Assets.getSettingButtonImage()));
    this.settingButton.setRolloverIcon(new ImageIcon(Assets.getSettingButtonHoverImage()));
    this.settingButton.setBounds(10, 10, 50, 50);

    this.settingButton.setFocusPainted(false);
    this.settingButton.setBorderPainted(false);
    this.settingButton.setContentAreaFilled(false);
    this.settingButton.setOpaque(false);
    this.settingButton.setVisible(true);

    settingButton.addMouseListener(new MenuClickHandler(listener));
  }

  private void initItems() {
    int initX = -70;
    int initY = 10;
    int xGaps = 200;

    this.arrow = new SingleItemDisplay("Arrow");
    this.ruby = new SingleItemDisplay("Ruby");
    this.sapphire = new SingleItemDisplay("Sapphire");
    this.diamond = new SingleItemDisplay("Diamond");

    this.arrow.setLocation(initX + xGaps, initY);
    this.ruby.setLocation(initX + xGaps * 2, initY);
    this.sapphire.setLocation(initX + xGaps * 3, initY);
    this.diamond.setLocation(initX + xGaps * 4, initY);
  }

  /**
   * Refresh the component to show the item numbers of the given player.
   *
   * @param player the player.
   */
  public void refresh(Player player) {
    if (player == null) {
      throw new IllegalArgumentException();
    }
    Map<Class<? extends CollectableEntity>, Integer> items = player.getAllItems();
    this.arrow.setNumber(0);
    this.ruby.setNumber(0);
    this.sapphire.setNumber(0);
    this.diamond.setNumber(0);
    for (Class name : items.keySet()) {
      int num = items.get(name);
      if (name == Arrow.class) {
        this.arrow.setNumber(num);
      } else if (name == Ruby.class) {
        this.ruby.setNumber(num);
      } else if (name == Sapphire.class) {
        this.sapphire.setNumber(num);
      } else if (name == Diamond.class) {
        this.diamond.setNumber(num);
      }
    }
    arrowKeys.refresh();
  }
}
