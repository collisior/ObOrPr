package quest;

public class Monster extends QuestCharacter {

	private double damage, defense, dodgeChance;

	Monster(String name, int level, double damage, double defense, double dodgeChance) {
		super(name, 1);
		setDamage(damage);
		setDefense(defense);
		setDodgeChance(dodgeChance);
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getDefense() {
		return defense;
	}

	public void setDefense(double defense) {
		this.defense = defense;
	}

	@Override
	public double getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(double dodgeChance) {
		this.dodgeChance = dodgeChance;
	}

	@Override
	protected Ammunition chooseFromStorage() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * 
	 */
	public double damageCalculation(Hero hero) {
		double finalDamage = 0;
		if (hero.getCurrentAmmunition() instanceof Armor) {
			Armor armor = (Armor) hero.getCurrentAmmunition();
			System.out.println(">>>> Armor defense = " + armor.getDamageReduction());
			System.out.println(">>>> Monster damage = " + getDamage());
			if ((getDamage() - armor.getDamageReduction()) > 0) {
				finalDamage = getDamage() - armor.getDamageReduction();
				System.out.println(">>>> finalDamage > 0 = " + finalDamage);
			}
		} else {
			finalDamage = getDamage();
		}
		return finalDamage;
	}

	@Override
	protected void endOfFight() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void upgradeSkills() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void levelUp() {
		// TODO Auto-generated method stub
	}

	@Override
	protected PersonalStorage getStorage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void showStorage() {
		// TODO Auto-generated method stub

	}

	public String displayDetails() {
		return "Name: " + name + "\nHp: " + hp + "\nLevel: " + level + "\nDefense: " + defense + "\nDamage: " + damage;
	}

	@Override
	protected double damageCalculation(QuestCharacter character) {
		// TODO Auto-generated method stub
		return 0;
	}

}
