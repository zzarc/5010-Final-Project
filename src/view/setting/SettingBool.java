package view.setting;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.Assets;

/**
 * The setting value component of bool type.
 */
public class SettingBool extends JPanel {

  private JButton decrease;
  private JButton increase;
  private JLabel text;
  private boolean value;

  /**
   * Default constructor.
   *
   * @param value value of the component.
   */
  public SettingBool(boolean value) {
    this.value = value;
    initPanel();
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
        if (value) {
          value = false;
          text.setText("NO");
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
        if (!value) {
          value = true;
          text.setText("YES");
        }
      }
    });
  }

  private void initButtonStyle(JButton button) {
    if (button == null) {
      throw new IllegalArgumentException();
    }
    button.setFont(Assets.getFont().deriveFont(Font.PLAIN, 30));
    button.setForeground(Color.GRAY);
    button.setVisible(true);

    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
  }

  private void initLabel() {
    if (!this.value) {
      this.text = new JLabel("NO");
    } else {
      this.text = new JLabel("YES");
    }
    text.setHorizontalAlignment(SwingConstants.CENTER);
    text.setVerticalAlignment(SwingConstants.CENTER);
    text.setFont(Assets.getFont().deriveFont(Font.PLAIN, 30));
    text.setForeground(Color.WHITE);
  }

  /**
   * Get the value of this component.
   *
   * @return the value.
   */
  public boolean getValue() {
    return this.value;
  }
}
