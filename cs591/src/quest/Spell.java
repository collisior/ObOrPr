package quest;

public class Spell extends Ammunition {
	private int damage, mana_cost;
	
	public Spell(String name, int cost, int required_level, int damage, int mana_cost) {
		super(name, cost, required_level);
		setDamage(damage);
		setManaCost(mana_cost);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getManaCost() {
		return mana_cost;
	}

	public void setManaCost(int mana_cost) {
		this.mana_cost = mana_cost;
	}

	
}
