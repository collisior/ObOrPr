package quest;

public abstract class QuestCharacter implements Color {
	protected String name;
	protected double hp;
	protected int level;
	protected boolean dodgedAttack = false;
	protected String color = GREEN;
	
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
		if(hp < 0) {
			this.hp = 0;
		}
		else {
			this.hp = hp;
		}
	}

	public void resetHp() {
		this.hp = 100 * this.level;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean hasDodgedAttack() {
		return dodgedAttack;
	}

	public void setHasDodgedAttack(boolean dodgedAttack) {
		this.dodgedAttack = dodgedAttack;
	}

	public boolean isAlive() {
		if (getHp() <= 0) {
			return false;
		}
		return true;
	}

	public String toString() {
		return name ;
	}

	public abstract String displayDetails();

	/*
	 * Return true if Hero/Monster are lucky to dodge an incoming attack.
	 */
	public boolean dodgeAttack() {
		double chance = getDodgeChance();
		if (Quest.random.nextInt(100) <= chance) {
			setHasDodgedAttack(true);
			return true;
		}
		setHasDodgedAttack(false);
		return false;
	}

	protected abstract double getDodgeChance();
	/*
	 * Return damage calculated that must be caused on the passed character.
	 */
	protected abstract double damageCalculation(QuestCharacter character);

	protected void applyDamage(double damage) {	
		setHp(getHp() - damage);
	}

	protected abstract void upgradeSkills();

	protected abstract PersonalStorage getStorage();

	protected abstract void showStorage();

	protected abstract void levelUp();

	protected abstract void information();
	protected abstract String image() ;

}
