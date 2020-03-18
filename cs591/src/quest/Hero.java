package quest;

public class Hero extends QuestCharacter {

	private double strength, dexterity, agility, mana, experience, money;
	private PersonalStorage storage;
	private double dodgeProbability = 0.2;
	private Ammunition currentAmmunition;

	Hero(String name, double mana, double strength, double agility, double dexterity, double money, double experience) {
		super(name, 1); // default starting level = 1
		setMana(mana);
		setStrength(strength);
		setAgility(agility);
		setDexterity(dexterity);
		setMoney(money);
		setExperience(experience);
		storage = new PersonalStorage();
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public double getStrength() {
		return this.strength;
	}

	public void setDexterity(double dexterity) {
		this.dexterity = dexterity;
	}

	public double getDexterity() {
		return this.dexterity;
	}

	public void setAgility(double agility) {
		this.agility = agility;
	}

	public double getAgility() {
		return this.agility;
	}

	public double getExperience() {
		return experience;
	}

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getDodgeChance() {
		return getAgility() * dodgeProbability;
	}

	public Ammunition getCurrentAmmunition() {
		return currentAmmunition;
	}

	public void setCurrentWeapon(Ammunition item) {
		this.currentAmmunition = item;
	}
	/*
	 * During a round of the fight, when it is the turn of the heroes, the player
	 * chooses for each hero whether they will do a regular attack or if they will
	 * cast a spell or if they will use a potion or if they will change their
	 * armor/weapon.
	 */

	@Override
	protected double damageCalculation(QuestCharacter monster) {
		double damage = 0;
		if (currentAmmunition == null) {
			damage = 0.5;
		} else if (currentAmmunition instanceof Weapon) {
			damage = (this.strength + currentAmmunition.getDamage()) * 0.05;
		} else if (currentAmmunition instanceof Spell) {
			setMana(getMana() - ((Spell) currentAmmunition).getManaCost());
			damage = currentAmmunition.getDamage() + this.dexterity / 10000 * currentAmmunition.getDamage();
		} else if (currentAmmunition instanceof Potion) {
			Potion potion = (Potion) currentAmmunition;
			potion.usePotion(this);
			storage.remove(currentAmmunition);
		} else if (currentAmmunition instanceof Spell) {
			damage = currentAmmunition.getDamage() * ((Spell) currentAmmunition).skillDeterioration;
		}
		return damage;

	}

	public void levelUp() {
		if (this.level * 10 >= getExperience()) {
			setLevel(level++);
			setMana(mana + mana * 0.1);
			upgradeSkills();
		}
	}

	public void showStorage() {
		storage.showStorage();
	}

	public Ammunition chooseFromStorage() {
		showStorage();
		System.out.println("Which item do you want to use? Type index # of item to attack.");
		int index = InputHandler.getInteger(0, storage.size() - 1);
		return storage.get(index);
	}

	public PersonalStorage getStorage() {
		return storage;
	}

	public void setStorage(PersonalStorage storage) {
		this.storage = storage;
	}

	/*
	 * The heroes regain some of their hp at every round if they are still alive.
	 * You can assume that during every round of a fight the heroes regain 5% of
	 * their hp and 5% of their mana.
	 */
	public void endOfRound() {
		if (isAlive()) {
			setHp(getHp() + getHp() * 0.05);
			setMana(getMana() + getMana() * 0.05);
		}
	}

	/*
	 * You can assume that after every successful fight each hero who did not faint
	 * gains 150 coins and 2 exp for their troubles.
	 */
	@Override
	protected void endOfFight() {
		if (isAlive()) {
			setMoney(getMoney() + 150);
			setExperience(getExperience() + 2);
		} else { // if hero died during fight
			resurrect();
		}
	}

	/*
	 * Resurrect fainted hero. Call this method upon exiting the fight into new
	 * cell.
	 */
	public void resurrect() {
		setHp();
		levelUp();
	}

	/*
	 * Upgrade skills when Hero levels up. Each hero has its own skill upgrade.
	 */
	public void upgradeSkills() {
	}

	@Override
	public String displayDetails() {
		// private double strength, dexterity, agility, mana, experience, money;
		return "Name: " + name + "\nHp: " + hp + "\nLevel: " + level + "\nStrength: " + strength + "\nDexterity: "
				+ dexterity + "\nAgility: " + agility + "\nMana: " + mana + "\nExperience: " + experience + "\nMoney: "
				+ money + "\nCurrent Ammunition: " + currentAmmunition;

	}

}
