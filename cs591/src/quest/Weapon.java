package quest;

public class Weapon extends Ammunition {
	private double damage;
	private int required_hands;
	
	public Weapon(String name, int cost, int required_level, double damage, int required_hands) {
		super(name, cost, required_level);
		this.setDamage(damage);
		this.setRequiredHands(required_hands);
		// TODO Auto-generated constructor stub
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	public int getRequiredHands() {
		return required_hands;
	}

	public void setRequiredHands(int required_hands) {
		this.required_hands = required_hands;
	}

	@Override
	protected void applyExtraDamage(Monster monster) {
		// TODO Auto-generated method stub
	}
	
	@Override
	protected String image() {
		String image = 
				" ,-.______________,=========,\n" + 
				"[|  )_____________)#######((_\n" + 
				" /===============.-.___,--\" _\\\n" + 
				"\"-._,__,__[JW]____\\########/\n" + 
				"          \\ (  )) )####O##(\n" + 
				"           \\ \\___/,.#######\\\n" + 
				"            `====\"  \\#######\\\n" + 
				"                     \\#######\\\n" + 
				"                      )##O####|\n" + 
				"                      )####__,\"\n" + 
				"                      `--\"\"\n";
		return image;
	}
	
}
