package quest;

public class Hero extends QuestCharacter {

	private double strength, dexterity, agility, mana, experience, money;
	private PersonalStorage storage;
	private double dodgeChance = 0.2;

	Hero(String name, double mana, double strength, double agility, double dexterity, double money, double experience) {
		super(name, 1); // default starting level = 1
		setMana(mana);
		setStrength(strength);
		setAgility(agility);
		setDexterity(dexterity);
		setMoney(money);
		setExperience(experience);
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
		return getAgility() * 0.02;
	}

	@Override
	public double damageCalculation(Ammunition itemToAttack, QuestCharacter monster) {
		if (itemToAttack instanceof Weapon) {
			return (this.strength + itemToAttack.getDamage()) * 0.05;
		} else if (itemToAttack instanceof Spell) {
			itemToAttack.applyExtraDamage((Monster) monster);
			return itemToAttack.getDamage() + this.dexterity / 10000 * itemToAttack.getDamage();
		}
		return 0.0;
	}

	@Override
	protected void attack(Ammunition itemToAttack, QuestCharacter monster) {
		if ((itemToAttack instanceof Weapon) || (itemToAttack instanceof Spell)) {
			double damage = damageCalculation(itemToAttack, monster);
			monster.applyDamage(damage);
		}
	}

	public void levelUp() {
		if (this.level * 10 >= getExperience()) {
			setLevel(level++);
			setMana(mana + mana * 0.1);
			// current_mana + current_mana*0.1
			// increase 5%
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

	public void sellFromStorage() {
		storage.showStorage();
		System.out.println("Which item do you want to sell? Type index # of item to sell.");
		int index = InputHandler.getInteger(0, storage.size() - 1);
		Ammunition item = storage.get(index);
		setMoney(getMoney() + item.cost / 2);
		storage.remove(index);
		System.out.println("You sold " + item + " for $" + item.cost / 2 + ". Do you want to continue selling?");
		if (InputHandler.YesOrNo()) {
			if (storage.size() > 0) {
				sellFromStorage();
			} else {
				System.out.println("Your storage is empty! No items to sell.");
			}
		}
	}

	public void buyFromMarket() {
		Market.showcase();
		Ammunition item = Market.chooseItem();
		if ((item.cost <= this.money) && (item.required_level <= this.level)) {
			setMoney(getMoney() - item.cost);
			System.out.println("You successfully purchased " + item + " for $" + item.cost);
		} else if ((item.cost > this.money) && (item.required_level <= this.level)) {
			System.out.println("You can't afford this Ammuntion:" + item);
		} else if ((item.required_level > this.level)) {
			System.out.println("You can't purchase this Ammunition because higher level reqiured!");
		}
		System.out.println("Do you want to continue shopping?");
		if (InputHandler.YesOrNo()) {
			buyFromMarket();
		}
	}

	/*
	 * You can assume that after every successful fight each hero who did not faint
	 * gains 150 coins and 2 exp for their troubles.
	 */
	@Override
	protected void endFightUpdate() {
		if (getHp() > 0) { // alive
			setMoney(getMoney() + 150);
			setExperience(getExperience() + 2);
		}
		rehabilitation();
	}

	/*
	 * Call this method upon exiting the fight into new cell.
	 */
	public void rehabilitation() {

	}

	/*
	 * Upgrade skills when Hero levels up.
	 */
	public void upgradeSkills() {

	}

}
