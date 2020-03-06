package quest;

public class Monster extends QuestCharacter {
	
	private int damage, defense;
	private double dodgeChance;
	
	Monster(String name, int level, int damage, int defense, double dodgeChance) {
		super(name, 1*100, 1);
		setDamage(damage);
		setDefense(defense);
		setDodgeChance(dodgeChance);
	}

	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public double getDodgeChance() {
		return dodgeChance;
	}
	public void setDodgeChance(double dodgeChance) {
		this.dodgeChance = dodgeChance;
	}
	
}
