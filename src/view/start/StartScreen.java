package view.start;

import controller.Controller;
import controller.clickhandler.NewGameClickHandler;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.Assets;
import view.InnerScreen;

/**
 * Start screen. Start screen includes a title for the game and a new game button.
 */
public class StartScreen extends InnerScreen {

  private JButton newGameButton;
  private JLabel title;
  private Controller listener;

  /**
   * Default constructor.
   *
   * @param listener the listener.
   */
  public StartScreen(Controller listener) {
    if (listener == null) {
      throw new IllegalArgumentException();
    }
    this.listener = listener;
    initNewGameButton();
    initTitle();

    this.setSize(1024, 768);
    this.setBackground(Color.BLACK);
    this.setLayout(null);
    this.add(title);
    this.add(newGameButton);
  }

  private void initTitle() {
    this.title = new JLabel("Dungeon Adventure");
    this.title.setFont(Assets.getFont().deriveFont(Font.PLAIN, 100));
    this.title.setForeground(Color.WHITE);
    this.title.setBounds(120, 200, 1000, 100);
  }

  private void initNewGameButton() {
    this.newGameButton = new JButton("New Game");
    this.newGameButton.setFont(Assets.getFont().deriveFont(Font.PLAIN, 50));
    this.newGameButton.setForeground(Color.GRAY);
    this.newGameButton.setBounds(380, 400, 220, 50);

    this.newGameButton.setFocusPainted(false);
    this.newGameButton.setBorderPainted(false);
    this.newGameButton.setContentAreaFilled(false);

    newGameButton.addMouseListener(new NewGameClickHandler(listener));
    newGameButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        newGameButton.setForeground(Color.WHITE);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        newGameButton.setForeground(Color.GRAY);
      }
    });
  }
}
