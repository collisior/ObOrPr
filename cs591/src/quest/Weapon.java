package quest;

public class Weapon extends Ammunition {
	private int damage, required_hands;
	
	public Weapon(String name, int cost, int required_level, int damage, int required_hands) {
		super(name, cost, required_level);
		this.setDamage(damage);
		this.setRequiredHands(required_hands);
		// TODO Auto-generated constructor stub
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRequiredHands() {
		return required_hands;
	}

	public void setRequiredHands(int required_hands) {
		this.required_hands = required_hands;
	}
	
}
