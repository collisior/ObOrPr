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
	protected void sellFromStorage() {
		// TODO: nothing
	}

	@Override
	protected Ammunition chooseFromStorage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attack(Ammunition itemToAttack, QuestCharacter monster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double damageCalculation(Ammunition itemToAttack, QuestCharacter character) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void endFightUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void upgradeSkills() {
		// TODO Auto-generated method stub
		
	}

}
