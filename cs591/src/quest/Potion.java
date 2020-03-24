package quest;

public class Potion extends Ammunition {
	private double attribute_increase;
	
	public Potion(String name, double cost, int required_level, double attribute_increase) {
		super(name, cost, required_level);
		setAttributeIncrease(attribute_increase);
		// TODO Auto-generated constructor stub
	}

	public double getAttributeIncrease() {
		return attribute_increase;
	}

	public void setAttributeIncrease(double attribute_increase) {
		this.attribute_increase = attribute_increase;
	}
	
	/*
	 * Increase assigned Hero's power.
	 */
	public void usePotion(Hero hero) {
		System.out.println("Potion to be used: ");
		switch(this.name) {
		case "Healing_Potion":
			hero.setHp(hero.getHp() + getAttributeIncrease());
			break;
		case "Strength_Potion":
			hero.setStrength(hero.getStrength() + getAttributeIncrease());
			break;
		case "Magic_Potion":
			hero.setMana(hero.getMana() + getAttributeIncrease());
			break;
		case "Luck_Elixir":
			hero.setDexterity(hero.getDexterity() + getAttributeIncrease());
			break;
		case "Mermaid_Tears":
			hero.setAgility(hero.getAgility() + getAttributeIncrease());
			break;
		case "Ambrosia":
			hero.setHp(hero.getHp() + getAttributeIncrease());
			hero.setStrength(hero.getStrength() + getAttributeIncrease());
			hero.setMana(hero.getMana() + getAttributeIncrease());
			hero.setDexterity(hero.getDexterity() + getAttributeIncrease());
			hero.setAgility(hero.getAgility() + getAttributeIncrease());
			break;
		default:
			System.out.println("This potion is non-existent in this universe...");
		}
		
	}
	@Override
	protected double getDamage() {
		return 0;
	}

	@Override
	protected void setDamage(double damage) {}

	@Override
	protected void applyExtraDamage(Monster monster) {}

	@Override
	protected String image() {
		String image = 
				"      _____\n" + 
				"     `.___,'\n" + 
				"      (___)\n" + 
				"      <   >\n" + 
				"       ) (\n" + 
				"      /`-.\\\n" + 
				"     /     \\\n" + 
				"    / _    _\\\n" + 
				"   :,' `-.' `:\n" + 
				"   |         |\n" + 
				"   :         ;\n" + 
				"    \\       /\n" + 
				"     `.___.'   \n";
		return image;
	}

}
