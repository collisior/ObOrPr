package quest;

public class FireSpell extends Spell {

	public FireSpell(String name, int cost, int required_level, int damage, int mana_cost) {
		super(name, cost, required_level, damage, mana_cost);
		// TODO Auto-generated constructor stub
	}

	/*
	 * A fire spell, apart from the damage it causes it also reduces the defense
	 * level of the enemy.
	 */
	
	protected void applyExtraDamage(Monster monster) {
		monster.setDefense(monster.getDefense() * (1.0 - skillDeterioration));
	}

}
