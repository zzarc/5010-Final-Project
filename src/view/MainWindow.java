package view;

import controller.Controller;
import dungeon.entity.Player;
import dungeon.map.DungeonMap;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import view.game.GameScreen;
import view.game.GameScreenListener;
import view.game.ShootModeListener;
import view.menu.MenuScreen;
import view.setting.SettingScreen;
import view.start.StartScreen;

/**
 * The main window to display the game content.
 */
public class MainWindow extends JFrame {

  private Controller listener;
  private InnerScreen screen;
  private static int height = 768;
  private static int width = 1024;

  private Map<Class<? extends InnerScreen>, InnerScreen> screenCache;

  /**
   * Default constructor.
   */
  public MainWindow() {
    this.screenCache = new HashMap();

    this.setSize(width, height);
    this.setLayout(null);
    this.setLocationRelativeTo(null); // center window on screen
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(Color.BLACK);

    this.setIconImage(Assets.getOtyughImage());
    this.setTitle("Dungeon Adventure");

    this.setVisible(true);
  }

  /**
   * Show the start screen.
   */
  public void showStartScreen() {
    clearWindow();
    this.screen = new StartScreen(listener);
    this.add(screen);
    this.repaint();
  }

  /**
   * Show the settings screen.
   */
  public void showSettingScreen() {
    clearWindow();
    if (!screenCache.keySet().contains(SettingScreen.class)) {
      screenCache.put(SettingScreen.class, new SettingScreen(listener));
    }
    this.screen = screenCache.get(SettingScreen.class);
    this.add(screen);
    this.repaint();
  }

  /**
   * Show the game screen.
   */
  public void showGameScreen() {
    clearWindow();

    if (!screenCache.keySet().contains(GameScreen.class)) {
      screenCache.put(GameScreen.class, new GameScreen(listener));
    }

    this.screen = screenCache.get(GameScreen.class);
    this.add(screen);

    this.addKeyListener(new GameScreenListener(listener));
    this.setFocusable(true);
    this.requestFocus();

    this.repaint();
  }

  /**
   * Show the menu screen.
   */
  public void showMenuScreen() {
    clearWindow();
    if (!screenCache.keySet().contains(MenuScreen.class)) {
      screenCache.put(MenuScreen.class, new MenuScreen(listener));
    }
    this.screen = screenCache.get(MenuScreen.class);
    this.add(screen);
    this.repaint();
  }

  /**
   * Set the click listener.
   *
   * @param listener the listener
   */
  public void setListener(Controller listener) {
    this.listener = listener;
  }

  /**
   * Refresh the content with given model info.
   *
   * @param player the player
   * @param map    the map
   */
  public void refresh(Player player, DungeonMap map) {
    this.screen.refresh(player, map);
  }

  /**
   * Enter the shoot mode to replace keyboard listener.
   */
  public void enterShootMode() {
    for (KeyListener kl : this.getKeyListeners()) {
      removeKeyListener(kl);
    }
    this.addKeyListener(new ShootModeListener(listener));
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Exit the shoot mode to replace keyboard listener.
   */
  public void exitShootMode() {
    for (KeyListener kl : this.getKeyListeners()) {
      removeKeyListener(kl);
    }
    this.addKeyListener(new GameScreenListener(listener));
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * Update the text to display.
   *
   * @param result the result text to display
   */
  public void updateText(String result) {
    if (result == null) {
      throw new IllegalArgumentException();
    }
    if (!(this.screen instanceof view.game.GameScreen)) {
      throw new IllegalStateException();
    }
    view.game.GameScreen p = (view.game.GameScreen) this.screen;
    p.updateResultText(result);
    this.requestFocus();
  }

  private void clearWindow() {
    this.getContentPane().removeAll();
    for (KeyListener kl : this.getKeyListeners()) {
      removeKeyListener(kl);
    }
  }
}