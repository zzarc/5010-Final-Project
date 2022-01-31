package view.setting;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import view.Assets;

/**
 * The setting component of int value.
 */
public class SettingValue extends JLabel {

  private JButton decrease;
  private JButton increase;
  private JLabel text;
  private int min;
  private int max;
  private int value;

  /**
   * Default constructor.
   *
   * @param min   minimum number
   * @param max   maximum number
   * @param value default value
   */
  public SettingValue(int min, int max, int value) {
    this.min = min;
    this.max = max;
    this.value = value < min ? min : value;

    initPanel();
  }

  /**
   * Alternative constructor with default value to be the minimum.
   *
   * @param min minimum value
   * @param max maximum value
   */
  public SettingValue(int min, int max) {
    this(min, max, min);
  }

  private void initPanel() {
    initButtons();
    initLabel();

    this.setSize(100, 50);
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setVisible(true);

    decrease.setBounds(0, 0, 50, 50);
    text.setBounds(40, 0, 50, 50);
    increase.setBounds(80, 0, 50, 50);

    this.add(decrease);
    this.add(increase);
    this.add(text);
  }

  private void initButtons() {
    decrease = new JButton("<");
    increase = new JButton(">");

    initButtonStyle(decrease);
    initButtonStyle(increase);

    decrease.setVisible(true);
    increase.setVisible(true);

    decrease.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        decrease.setForeground(Color.WHITE);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        decrease.setForeground(Color.GRAY);
      }

      public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (value > min) {
          value--;
          text.setText(String.valueOf(value));
        }
      }
    });

    increase.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        increase.setForeground(Color.WHITE);
      }

      public void mouseExited(java.awt.event.MouseEvent evt) {
        increase.setForeground(Color.GRAY);
      }

      public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (value < max) {
          value++;
          text.setText(String.valueOf(value));
        }
      }
    });
  }

  private void initButtonStyle(JButton button) {
    button.setFont(Assets.getFont().deriveFont(Font.PLAIN, 30));
    button.setForeground(Color.GRAY);
    button.setVisible(true);

    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
  }

  private void initLabel() {
    this.text = new JLabel(String.valueOf(value));
    text.setHorizontalAlignment(SwingConstants.CENTER);
    text.setVerticalAlignment(SwingConstants.CENTER);
    text.setFont(Assets.getFont().deriveFont(Font.PLAIN, 30));
    text.setForeground(Color.WHITE);
  }

  /**
   * Get the value of this setting component.
   *
   * @return the value
   */
  public int getValue() {
    return this.value;
  }
}
