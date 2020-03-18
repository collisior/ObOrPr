package quest;

public class Warrior extends Hero {
	// Warriors are favored on the strength and the agility.
	Warrior(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
		super(name, mana, strength, agility, dexterity, money, experience);
	}

	public void upgradeSkills() {
		setStrength(getStrength() + getStrength() * (0.05 + 0.05));
		setAgility(getAgility() + getAgility() * (0.05 + 0.05));
		setDexterity(getDexterity() + getDexterity() * 0.05);
	}
}
