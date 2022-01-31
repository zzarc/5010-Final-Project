package view.game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import view.Assets;

/**
 * Display a single item, including the icon and a number.
 */
public class SingleItemDisplay extends JLabel {

  private JLabel itemImage;
  private JLabel itemNumber;

  /**
   * Default constructor.
   *
   * @param itemName the item's name.
   */
  public SingleItemDisplay(String itemName) {

    initImageLabel(itemName);
    initNumberLabel();

    this.itemImage.setLocation(0, 0);
    this.itemNumber.setLocation(60, 0);

    this.setSize(150, 100);
    this.add(itemImage);
    this.add(itemNumber);
  }

  private void initImageLabel(String itemName) {
    if (itemName == null) {
      throw new IllegalArgumentException();
    }
    this.itemImage = new JLabel();

    if (itemName.equals("Ruby")) {
      this.itemImage.setIcon(new ImageIcon(Assets.getRubyImage()));
    } else if (itemName.equals("Diamond")) {
      this.itemImage.setIcon(new ImageIcon(Assets.getDiamondImage()));
    } else if (itemName.equals("Arrow")) {
      this.itemImage.setIcon(new ImageIcon(Assets.getArrowImage()));
    } else if (itemName.equals("Sapphire")) {
      this.itemImage.setIcon(new ImageIcon(Assets.getSapphireImage()));
    }

    this.itemImage.setSize(50, 50);
    this.itemImage.setVisible(true);
  }

  private void initNumberLabel() {
    this.itemNumber = new JLabel("X " + String.valueOf(0));
    itemNumber.setHorizontalAlignment(SwingConstants.LEFT);
    itemNumber.setVerticalAlignment(SwingConstants.CENTER);
    itemNumber.setFont(Assets.getFont().deriveFont(Font.PLAIN, 50));
    itemNumber.setForeground(Color.WHITE);
    itemNumber.setSize(150, 80);
    itemNumber.setVisible(true);
  }

  /**
   * Change the number of the item.
   *
   * @param number the number.
   */
  public void setNumber(int number) {
    this.itemNumber.setText("X " + String.valueOf(number));
  }
}
