package quest;

public class Hero extends QuestCharacter {

	private int strength, dexterity, agility, mana, experience, money;
	private PersonalStorage storage;

	Hero(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
		super(name, 1 * 100, 1); // default starting level = 1
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
		if (item.cost <= this.money) {
			setMoney(getMoney() - item.cost);
			System.out.println("You successfully purchased " + item + " for $" + item.cost);
		} else {
			System.out.println("You can't afford this Ammuntion:" + item);
		}
		System.out.println("Do you want to continue shopping?");
		if (InputHandler.YesOrNo()) {
			buyFromMarket();
		}
	}
}
