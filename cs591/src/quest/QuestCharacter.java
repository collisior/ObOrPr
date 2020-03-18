package quest;

import java.util.Random;

public abstract class QuestCharacter {
	protected String name;
	protected double hp;
	protected int level;

	QuestCharacter(String name, int level) {
		setName(name);
		setHp(level * 100);
		setLevel(level);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public void setHp() {
		this.hp = 100 * this.level;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isAlive() {
		if (getHp() <= 0) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "Name: " + name + "|  Hp: " + hp + "|  Level: " + level;
	}

	public abstract String displayDetails();

	/*
	 * Return true if Hero/Monster are lucky to dodge an incoming attack.
	 */
	public boolean dodgeAttack() {
		Random random = new Random();
		double chance = getDodgeChance();
		if (random.nextInt(100) <= chance) {
			return true;
		}
		return false;
	}

	protected abstract double getDodgeChance();

	protected abstract Ammunition chooseFromStorage();

	/*
	 * Return damage calculated that must be caused on the passed character.
	 */
	protected abstract double damageCalculation(QuestCharacter character);

	protected void applyDamage(double damage) {
		setHp(getHp() - damage);
	}

	protected abstract void endOfFight();

	protected abstract void upgradeSkills();

	protected abstract PersonalStorage getStorage();

	protected abstract void showStorage();

	protected abstract void levelUp();

}
