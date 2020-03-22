package quest;

public class LightningSpell extends Spell {

	public LightningSpell(String name, int cost, int required_level, int damage, int mana_cost) {
		super(name, cost, required_level, damage, mana_cost);
		// TODO Auto-generated constructor stub
	}

	/*
	 * A lightning spell, apart from the damage it causes it also reduces the dodge
	 * chance of the enemy.
	 */
	
	protected void applyExtraDamage(Monster monster) {
		monster.setDodgeChance(monster.getDodgeChance() * (1.0 - skillDeterioration));
	}

}
