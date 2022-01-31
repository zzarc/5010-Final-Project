package view.setting;

import controller.Controller;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.Assets;
import view.InnerScreen;
import controller.clickhandler.StartGameClickHandler;

/**
 * The setting screen. It has several settings that allow the player to customize the parameters for
 * dungeon generation.
 */
public class SettingScreen extends InnerScreen {

  private Controller listener;

  private JLabel title;

  private JLabel heightLabel;
  private JLabel widthLabel;
  private JLabel interconnectivityLabel;
  private JLabel wrappingLabel;
  private JLabel treasureRatioLabel;
  private JLabel monsterNumberLabel;
  private JLabel cheatLabel;

  private SettingValue heightValue;
  private SettingValue widthValue;
  private SettingValue interconnectivityValue;
  private SettingBool wrappingValue;
  private SettingValue treasureRatioValue;
  private SettingValue monsterNumberValue;
  private SettingBool cheatValue;

  private JButton startButton;

  /**
   * Default Constructor.
   *
   * @param listener the listener.
   */
  public SettingScreen(Controller listener) {
    if (listener == null) {
      throw new IllegalArgumentException();
    }

    this.listener = listener;

    this.setSize(1024, 768);
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setVisible(true);

    initTitle();
    initLabels();
    initInputFields();
    initStartButton();

    this.add(title);

    this.add(heightLabel);
    this.add(widthLabel);
    this.add(interconnectivityLabel);
    this.add(wrappingLabel);
    this.add(treasureRatioLabel);
    this.add(monsterNumberLabel);
    this.add(cheatLabel);

    this.add(heightValue);
    this.add(widthValue);
    this.add(interconnectivityValue);
    this.add(wrappingValue);
    this.add(treasureRatioValue);
    this.add(monsterNumberValue);
    this.add(cheatValue);

    this.add(startButton);
  }

  private void initTitle() {
    this.title = new JLabel("Settings");
    this.title.setFont(Assets.getFont().deriveFont(Font.PLAIN, 80));
    this.title.setForeground(Color.WHITE);
    this.title.setBounds(340, 70, 1000, 100);
  }

  private void initLabels() {
    this.heightLabel = new JLabel("Height");
    this.widthLabel = new JLabel("Width");
    this.interconnectivityLabel = new JLabel("Connectivity");
    this.wrappingLabel = new JLabel("Wrapping");
    this.treasureRatioLabel = new JLabel("Treasures%");
    this.monsterNumberLabel = new JLabel("Monsters");
    this.cheatLabel = new JLabel("Cheat");

    int initX = 40;
    int initY = 150;

    initLabelStyle(heightLabel);
    heightLabel.setBounds(initX + 300, initY + 50, 100, 50);

    initLabelStyle(widthLabel);
    widthLabel.setBounds(initX + 300, initY + 100, 100, 50);

    initLabelStyle(interconnectivityLabel);
    interconnectivityLabel.setBounds(initX + 300, initY + 150, 200, 50);

    initLabelStyle(wrappingLabel);
    wrappingLabel.setBounds(initX + 300, initY + 200, 200, 50);

    initLabelStyle(treasureRatioLabel);
    treasureRatioLabel.setBounds(initX + 300, initY + 250, 200, 50);

    initLabelStyle(monsterNumberLabel);
    monsterNumberLabel.setBounds(initX + 300, initY + 300, 200, 50);

    initLabelStyle(cheatLabel);
    cheatLabel.setBounds(initX + 300, initY + 350, 200, 50);
  }

  private void initInputFields() {
    this.heightValue = new SettingValue(5, 10, 5);
    this.widthValue = new SettingValue(5, 10, 5);
    this.interconnectivityValue = new SettingValue(0, 100, 0);
    this.wrappingValue = new SettingBool(false);
    this.treasureRatioValue = new SettingValue(20, 100, 60);
    this.monsterNumberValue = new SettingValue(1, 20, 3);
    this.cheatValue = new SettingBool(false);

    int initX = 240;
    int initY = 150;

    heightValue.setBounds(initX + 300, initY + 50, 200, 50);
    widthValue.setBounds(initX + 300, initY + 100, 200, 50);
    interconnectivityValue.setBounds(initX + 300, initY + 150, 200, 50);
    wrappingValue.setBounds(initX + 300, initY + 200, 200, 50);
    treasureRatioValue.setBounds(initX + 300, initY + 250, 200, 50);
    monsterNumberValue.setBounds(initX + 300, initY + 300, 200, 50);
    cheatValue.setBounds(initX + 300, initY + 350, 200, 50);
  }

  private void initLabelStyle(JLabel label) {
    if (label == null) {
      throw new IllegalArgumentException();
    }
    label.setFont(Assets.getFont().deriveFont(Font.PLAIN, 30));
    label.setForeground(Color.WHITE);
  }

  private void initStartButton() {
    this.startButton = new JButton("Start");
    this.startButton.setFont(Assets.getFont().deriveFont(Font.PLAIN, 50));
    this.startButton.setForeground(Color.GRAY);
    this.startButton.setBounds(390, 600, 220, 50);

    this.startButton.setFocusPainted(false);
    this.startButton.setBorderPainted(false);
    this.startButton.setContentAreaFilled(false);

    startButton.addMouseListener(
        new StartGameClickHandler(listener, heightValue, widthValue, interconnectivityValue,
            wrappingValue, treasureRatioValue, monsterNumberValue, cheatValue));
    startButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        startButton.setForeground(Color.WHITE);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        startButton.setForeground(Color.GRAY);
      }
    });
  }
}