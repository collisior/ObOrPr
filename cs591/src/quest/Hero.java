package quest;

import java.util.ArrayList;

public class Hero extends QuestCharacter {

	private double strength, dexterity, agility, mana, experience, money;
	private PersonalStorage storage;
	private double dodgeProbability = 0.2;
	private Ammunition regularAttack = new Fists("Fists", 0, 0);
	private Ammunition currentAmmunition = null;
	private int fightsWon = 0;
	private ArrayList<Monster> totalDefeatedMonsters = new ArrayList<Monster>();
	private ArrayList<Monster> defeatedMonstersFight = new ArrayList<Monster>();
	private QuestMascot mascot = null;

	Hero(String name, double mana, double strength, double agility, double dexterity, double money, double experience) {
		super(name, 1); // default starting level = 1
		setMana(mana);
		setStrength(strength);
		setAgility(agility);
		setDexterity(dexterity);
		setMoney(money);
		setExperience(experience);
		setCurrentAmmunition(regularAttack);
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

	public void setCurrentAmmunition(Ammunition item) {
		this.currentAmmunition = item;
	}

	private void chooseMascot() {
		setMascot(SetupQuestHelper.chooseMascot(this));
	}

	/*
	 * During a round of the fight, when it is the turn of the heroes, the player
	 * chooses for each hero whether they will do a regular attack or if they will
	 * cast a spell or if they will use a potion or if they will change their
	 * armor/weapon.
	 */
	public void chooseCurrentAmmunition() {
		Ammunition item = null;
		boolean choiceAccepted = false;
		do {
			System.out.println(
					"Choose an attack: \n [0]: Regular attack. \n [1]: Use different ammunition from my storage. \n [2]: Get random ammunition from my storage.");
			int choice = InputHandler.getInteger(0, 2);

			switch (choice) {
			case 0:
				choiceAccepted = true;
				break;
			case 1:
				if (getStorage().size() > 0) {
					item = getStorage().chooseFromStorage(this);
					choiceAccepted = true;
					break;
				}
				System.out.println("Your storage is empty!");
			case 2:
				if (getStorage().size() > 0) {
					item = getStorage().getRandomAmmunition(this);
					choiceAccepted = true;
					break;
				}
				System.out.println("Your storage is empty! Can't choose random ammunnition.");
			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (!choiceAccepted);

		setCurrentAmmunition(item);
	}

	@Override
	protected double damageCalculation(QuestCharacter monster) {
		double damage = getStrength() * 0.05;
		if (currentAmmunition instanceof Weapon) {
			damage = (this.strength + currentAmmunition.getDamage()) * 0.05;
		} else if (currentAmmunition instanceof Spell) {
			setMana(getMana() - ((Spell) currentAmmunition).getManaCost());
			damage = currentAmmunition.getDamage() + this.dexterity / 10000 * currentAmmunition.getDamage();
			// if monster don't dodge, do applyExtraDamage(monster)
		} else if (currentAmmunition instanceof Potion) {
			Potion potion = (Potion) currentAmmunition;
			potion.usePotion(this);
			storage.remove(currentAmmunition);
		} else if (currentAmmunition instanceof Armor) {
			// increase
		}
		damage = applyMascotPower(damage);
		return damage;

	}

	private double applyMascotPower(double damage) {
		if ((mascot != null) && (mascot.canDamage())) {
			return mascot.damage(this, damage);
		}
		return damage;
	}

	public void showStorage() {
		storage.showStorage();
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
	 * Upon exiting fight Hero who did not faint gains 150 coins and 2 experience
	 * points, then resurrects, and levels up. Total Defeated Monsters in fight
	 * resets.
	 */
	protected void exitFight() {
		if (isAlive()) {
			setMoney(getMoney() + 100 * Fight.getMonstersLevel());
			setExperience(getExperience() + 2);
			setFightsWon(getFightsWon() + 1);
		}
		levelUp();
		resurrect();
		for (Monster monster : getDefeatedMonstersFight()) {
			getTotalDefeatedMonsters().add(monster);
		}
		getDefeatedMonstersFight().clear();
	}

	/*
	 * 
	 */
	public void levelUp() {
		if (level == 10) {
			return;
		}
		if (level * 10 >= getExperience()) {
			setLevel(level++);
			setMana(mana + mana * 0.1);
			upgradeSkills();
		}
		if (level == 9) {
			chooseMascot();
			mascot.empower(this);
		}
	}

	/*
	 * Resurrect fainted hero. Call this method upon exiting the fight into new cell
	 * to reset Health power.
	 */
	public void resurrect() {
		setHp();
	}

	/*
	 * Upgrade skills when Hero levels up. Each type of Hero has its own skill
	 * upgrade.
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

	public void information() {
		System.out.println();
		String format = "%-20s%-12s%-20s%-12s%-20s%-12s";
		System.out.println(
				String.format(format, "Statistics:", "", "Skills/Ammunition info:", "Storage information:", "", ""));
		System.out.println();
		System.out.println(String.format(format, "Level:", (int) level, "Strength:", (int) strength, "Total items:",
				getStorage().size()));
		System.out.println(String.format(format, "Health Power:", (int) hp, "Agility:", (int) agility, "Armors:",
				getStorage().totalArmors()));
		System.out.println(String.format(format, "Mana:", (int) mana, "Dexterity:", (int) dexterity, "Weapons:",
				getStorage().totalWeapons()));
		System.out.println(String.format(format, "Experience:", (int) experience, "Current Ammunition:",
				getCurrentAmmunition(), "Spells:", getStorage().totalSpells()));
		System.out
				.println(String.format(format, "Money:", (int) money, "Mascot", mascot, "Potions:", getStorage().totalPotions()));
		System.out.println();
		System.out.println("Total Monsters defeated: " + getTotalDefeatedMonsters().size());

	}

	public ArrayList<Monster> getTotalDefeatedMonsters() {
		return totalDefeatedMonsters;
	}

	public void setTotalDefeatedMonsters(ArrayList<Monster> totalDefeatedMonsters) {
		this.totalDefeatedMonsters = totalDefeatedMonsters;
	}

	public ArrayList<Monster> getDefeatedMonstersFight() {
		return defeatedMonstersFight;
	}

	public void setDefeatedMonstersFight(ArrayList<Monster> defeatedMonstersFight) {
		this.defeatedMonstersFight = defeatedMonstersFight;
	}

	public int getFightsWon() {
		return fightsWon;
	}

	public void setFightsWon(int fightsWon) {
		this.fightsWon = fightsWon;
	}

	public QuestMascot getMascot() {
		return mascot;
	}

	public void setMascot(QuestMascot mascot) {
		this.mascot = mascot;
	}

}
