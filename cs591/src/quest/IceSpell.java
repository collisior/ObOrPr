package quest;

public class IceSpell extends Spell {

	public IceSpell(String name, int cost, int required_level, int damage, int mana_cost) {
		super(name, cost, required_level, damage, mana_cost);
	}

	/*
	 * An ice spell, apart from the damage it causes it also reduces the damage
	 * range of the enemy.
	 */

	protected void applyExtraDamage(Monster monster) {
		monster.setDamage(monster.getDamage() * (1.0 - skillDeterioration));
	}
}
