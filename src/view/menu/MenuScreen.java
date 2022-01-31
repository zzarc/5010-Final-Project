package view.menu;

import controller.Controller;
import controller.clickhandler.NewGameClickHandler;
import controller.clickhandler.QuitClickHandler;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import view.Assets;
import view.InnerScreen;
import controller.clickhandler.RestartClickHandler;
import controller.clickhandler.ReturnClickHandler;

/**
 * The menu screen. In the menu player can choose to start a new game, restart with same map, quit
 * or return.
 */
public class MenuScreen extends InnerScreen {

  private JButton newGameButton;
  private JButton restartButton;
  private JButton quitButton;
  private JButton returnButton;

  /**
   * Menu constructor.
   *
   * @param listener the listener.
   */
  public MenuScreen(Controller listener) {

    if (listener == null) {
      throw new IllegalArgumentException();
    }
    this.setSize(1024, 768);
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setVisible(true);

    int initX = 390;
    int initY = 180;
    int gap = 100;

    newGameButton = new JButton("New Game");
    newGameButton.setBounds(initX, initY, 220, 50);
    newGameButton.addMouseListener(new NewGameClickHandler(listener));

    restartButton = new JButton("Restart");
    restartButton.setBounds(initX, initY + gap, 220, 50);
    restartButton.addMouseListener(new RestartClickHandler(listener));

    quitButton = new JButton("Quit");
    quitButton.setBounds(initX, initY + gap * 2, 220, 50);
    quitButton.addMouseListener(new QuitClickHandler(listener));

    returnButton = new JButton("Return");
    returnButton.setBounds(initX, initY + gap * 3, 220, 50);
    returnButton.addMouseListener(new ReturnClickHandler(listener));

    initButtonStyle(newGameButton);
    initButtonStyle(restartButton);
    initButtonStyle(quitButton);
    initButtonStyle(returnButton);

    this.add(newGameButton);
    this.add(restartButton);
    this.add(quitButton);
    this.add(returnButton);
  }

  private void initButtonStyle(JButton button) {
    if (button == null) {
      throw new IllegalArgumentException();
    }
    button.setFont(Assets.getFont().deriveFont(Font.PLAIN, 50));
    button.setForeground(Color.GRAY);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        button.setForeground(Color.WHITE);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        button.setForeground(Color.GRAY);
      }
    });
  }

  /**
   * Repaint this component.
   */
  public void refresh() {
    newGameButton.repaint();
    restartButton.repaint();
    quitButton.repaint();
    returnButton.repaint();
  }
}
