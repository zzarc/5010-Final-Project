package dungeon.entity;

/**
 * The Monster Interface. Monster can be spawned in caves and hurt player if player enters its
 * location. Monster can be killed by player if player hit it twice with arrows. Monster hit once
 * will become injured and player has 50% chance to escape if in the same location. Monster will
 * emit odor to nearby locations and will stop after being killed.
 */
public interface Monster extends DungeonEntity {


  /**
   * Get the name of monster.
   */
  String getName();

  /**
   * Get the health of monster.
   *
   * @return monster's health
   */
  int getHealth();

  /**
   * Damage the monster.
   */
  void damage();
}
