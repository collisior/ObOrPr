package quest;

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

	public String toString() {
		return name;
	}

	protected abstract double getDodgeChance();
	
	protected abstract void sellFromStorage();

	protected abstract Ammunition chooseFromStorage();

	protected abstract void attack(Ammunition itemToAttack, QuestCharacter character);

	protected abstract double damageCalculation(Ammunition itemToAttack, QuestCharacter character);

	protected void applyDamage(double damage) {
		setHp(getHp() - damage);

	}
	protected abstract void endFightUpdate();

	protected abstract void upgradeSkills();

}
