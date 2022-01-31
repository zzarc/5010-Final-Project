package controller.clickhandler;

import controller.Controller;
import dungeon.Dungeon;
import view.View;
import view.setting.SettingBool;
import view.setting.SettingValue;

/**
 * Handle click on Start button.
 */
public class StartGameClickHandler extends AbstractClickHandler {

  private final SettingValue height;
  private final SettingValue width;
  private final SettingValue interconnectivity;
  private final SettingBool wrapping;
  private final SettingValue treasureRatio;
  private final SettingValue monsterNum;
  private final SettingBool cheat;

  /**
   * Constructor.
   *
   * @param controller the listener.
   */
  public StartGameClickHandler(Controller controller, SettingValue height, SettingValue width,
      SettingValue interconnectivity, SettingBool wrapping, SettingValue treasureRatio,
      SettingValue monsterNum, SettingBool cheat) {
    super(controller);
    this.height = height;
    this.width = width;
    this.interconnectivity = interconnectivity;
    this.wrapping = wrapping;
    this.treasureRatio = treasureRatio;
    this.monsterNum = monsterNum;
    this.cheat = cheat;
  }

  @Override
  public void handle(Dungeon model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException();
    }
    model.generateMap(height.getValue(), width.getValue(), interconnectivity.getValue(),
        wrapping.getValue(), treasureRatio.getValue(), monsterNum.getValue(), cheat.getValue());
    view.showGameScreen();
    view.refresh(model.getPlayer(), model.getMap());
    view.displayHelpMessageGraphics();
  }
}
