package quest;

public class Armor extends Ammunition {
	private int damage_reduction;
	
	public Armor(String name, int cost, int required_level, int damage_reduction) {
		super(name, cost, required_level);
		setDamageReduction(damage_reduction);
	}
	
	public void setDamageReduction(int damage_reduction) {
		this.damage_reduction = damage_reduction;
	}
	
	public int getDamageReduction(){
		return this.damage_reduction;
	}
	
}
