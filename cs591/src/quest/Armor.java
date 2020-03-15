package quest;

public class Armor extends Ammunition {
	private double damage_reduction;
	
	public Armor(String name, double cost, int required_level, double damage_reduction) {
		super(name, cost, required_level);
		setDamageReduction(damage_reduction);
	}
	
	public void setDamageReduction(double damage_reduction) {
		this.damage_reduction = damage_reduction;
	}
	
	public double getDamageReduction(){
		return this.damage_reduction;
	}

	@Override
	protected double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void setDamage(double damage) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void applyExtraDamage(Monster monster) {
		// TODO Auto-generated method stub
		
	}
	
}
