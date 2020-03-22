package quest;

public class Spell extends Ammunition {
	private double damage, mana_cost;
	public double skillDeterioration = 0.1; //reduce enemy's skills by 10%
	
	public Spell(String name, int cost, int required_level, int damage, int mana_cost) {
		super(name, cost, required_level);
		setDamage(damage);
		setManaCost(mana_cost);
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getManaCost() {
		return mana_cost;
	}

	public void setManaCost(double mana_cost) {
		this.mana_cost = mana_cost;
	}
	@Override
	protected void applyExtraDamage(Monster monster) {
	}
	/*
	 * Hero must have enough mana to use this Spell.
	 */
	public boolean canUse(Hero hero) {
		if(hero.getMana() >= this.mana_cost) {
			return true;
		}
		return false;
	}
	
}
