package view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * Assets loader helper class.
 */
public class Assets {

  private static ClassLoader loader = ClassLoader.getSystemClassLoader();
  private static Font font = null;
  private static Image settingButton = null;
  private static Image settingButtonHover = null;
  private static Image ruby = null;
  private static Image sapphire = null;
  private static Image diamond = null;
  private static Image arrow = null;
  private static Image weakStench = null;
  private static Image strongStench = null;
  private static Image otyughImage = null;
  private static Image playerImage = null;
  private static Image chestImage = null;

  /**
   * Load an image.
   *
   * @param name the name of the image.
   * @return the image.
   */
  public static Image getImage(String name) {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    Image result = null;
    InputStream f = loader.getResourceAsStream(name + ".png");
    try {
      result = ImageIO.read(f);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.out.println("Load image failed");
    }
    return result;
  }

  /**
   * Load font resource.
   *
   * @return the font
   */
  public static Font getFont() {
    if (font == null) {
      InputStream f = loader.getResourceAsStream("pixeboy.ttf");
      try {
        font = Font.createFont(Font.TRUETYPE_FONT, f);
      } catch (IOException e) {
        System.out.println(e.getMessage());
        System.out.println("Load font failed");
      } catch (FontFormatException e) {
        System.out.println(e.getMessage());
        System.out.println("Load font failed");
      }
    }
    return font;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getSettingButtonImage() {
    if (settingButton == null) {
      settingButton = getImage("setting");
    }
    return settingButton;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getSettingButtonHoverImage() {
    if (settingButtonHover == null) {
      settingButtonHover = getImage("setting_hover");
    }
    return settingButtonHover;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getRubyImage() {
    if (ruby == null) {
      ruby = getImage("ruby");
    }
    return ruby;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getSapphireImage() {
    if (sapphire == null) {
      sapphire = getImage("sapphire");
    }
    return sapphire;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getDiamondImage() {
    if (diamond == null) {
      diamond = getImage("diamond");
    }
    return diamond;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getArrowImage() {
    if (arrow == null) {
      arrow = getImage("arrow");
    }
    return arrow;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getStrongStenchImage() {
    if (strongStench == null) {
      strongStench = getImage("stench_strong");
    }
    return strongStench;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getWeakStenchImage() {
    if (weakStench == null) {
      weakStench = getImage("stench_weak");
    }
    return weakStench;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getOtyughImage() {
    if (otyughImage == null) {
      otyughImage = getImage("otyugh");
    }
    return otyughImage;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getPlayerImage() {
    if (playerImage == null) {
      playerImage = getImage("player");
    }
    return playerImage;
  }

  /**
   * Load resource.
   *
   * @return the resource image
   */
  public static Image getChestImage() {
    if (chestImage == null) {
      chestImage = getImage("chest");
    }
    return chestImage;
  }
}