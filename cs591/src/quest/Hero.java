package quest;

public class Hero extends QuestCharacter {
	
	private int strength, dexterity, agility, mana, experience, money;
	
	Hero(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
		super(name, 1*100, 1); //default starting level = 1
		setMana(mana);
		setStrength(strength);
		setAgility(agility);
		setDexterity(dexterity);
		setMoney(money);
		setExperience(experience);
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getStrength(int strength) {
		return this.strength;
	}
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	public int getDexterity(int dexterity) {
		return this.dexterity;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getAgility(int agility) {
		return this.agility;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	
}
