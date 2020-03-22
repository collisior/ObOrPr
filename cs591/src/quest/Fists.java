package quest;

public class Fists extends Ammunition {

	public Fists(String name, double cost, int required_level) {
		super(name, cost, required_level);
		// TODO Auto-generated constructor stub
	}
	
	public boolean canSell() {
		return false;
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
