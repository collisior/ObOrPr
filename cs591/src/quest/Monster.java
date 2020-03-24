package quest;

public class Monster extends QuestCharacter {

	private double damage, defense, dodgeChance;
	private double damageReducer = 0.8;

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
		return dodgeChance * damageReducer;
	}

	public void setDodgeChance(double dodgeChance) {
		if(dodgeChance <= 100) {
			this.dodgeChance = dodgeChance;
		}
	}

	/*
	 * Calculates Final Damage this Monster will cause to given Hero. 
	 */
	public double damageCalculation(Hero hero) {
		double finalDamage = getDamage();
		if (hero.getCurrentAmmunition() instanceof Armor) {
			Armor armor = (Armor) hero.getCurrentAmmunition();
			if ((getDamage() - armor.getDamageReduction()) > 0) {
				finalDamage = getDamage() - armor.getDamageReduction();
				System.out.print("\n>>>>>>>>>>>> ARMOR " + finalDamage);
			}
		} else if ((dodgedAttack == false) && (hero.getCurrentAmmunition() instanceof Spell)) {
			Spell spell = (Spell) hero.getCurrentAmmunition();
			finalDamage = getDamage() * spell.skillDeterioration;
			spell.applyExtraDamage(this);
		}

		return finalDamage;
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

	@Override
	protected void information() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String image() {
		// TODO Auto-generated method stub
		return null;
	}

}
