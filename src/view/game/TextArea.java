package view.game;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.Assets;

/**
 * The text area to show the result of action.
 */
public class TextArea extends JPanel {

  private final int width = 1024;
  private final int height = 150;

  /**
   * Constructor.
   */
  public TextArea() {
    this.setSize(width, height);
    this.setLayout(null);
    this.setBackground(new Color(20, 20, 20));
  }

  private void initText(JLabel text) {
    if (text == null) {
      throw new IllegalArgumentException();
    }
    text.setSize(width, 50);
    text.setHorizontalAlignment(SwingConstants.LEFT);
    text.setVerticalAlignment(SwingConstants.TOP);
    text.setFont(Assets.getFont().deriveFont(Font.PLAIN, 25));
    text.setForeground(Color.WHITE);
    text.setVisible(true);
  }

  /**
   * Refresh the component with given text.
   *
   * @param resultText the text to display.
   */
  public void refresh(String resultText) {
    if (resultText == null) {
      throw new IllegalArgumentException();
    }
    this.removeAll();
    int initX = 10;
    int initY = 10;
    int gaps = 30;

    String[] lines = resultText.split("[\\r\\n]+");
    for (int i = 0; i < lines.length; i++) {
      JLabel text = new JLabel();
      initText(text);
      text.setLocation(initX, initY + gaps * i);
      text.setText(lines[i].replace('\n', '.'));
      this.add(text);
    }
    this.repaint();
  }
}
