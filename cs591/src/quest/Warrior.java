package quest;

public class Warrior extends Hero {
	// Warriors are favored on the strength and the agility.
	Warrior(String name, float mana, float strength, float agility, float dexterity, float money, float experience) {
		super(name, mana, strength, agility, dexterity, money, experience);
	}

	public void upgradeSkills() {
		setStrength(getStrength() + getStrength() * (0.05 + 0.05));
		setAgility(getAgility() + getAgility() * (0.05 + 0.05));
		setDexterity(getDexterity() + getDexterity() * 0.05);
	}
	
	public String image() {
		String image ="  ,   A           {}\n" + 
				" / \\, | ,        .--.\n" + 
				"|    =|= >      /.--.\\\n" + 
				" \\ /` | `       |====|\n" + 
				"  `   |         |`::`|\n" + 
				"      |     .-;`\\..../`;-.\n" + 
				"     /\\\\/  /  |...::...|  \\\n" + 
				"     |:'\\ |   /'''::'''\\   |\n" + 
				"      \\ /\\;-,/\\   ::   /\\--;\n" + 
				"      |\\ <` >  >._::_.<,<__>\n" + 
				"      | `\"\"`  /   ^^   \\|  |\n" + 
				"      |       |        |\\::/\n" + 
				"      |       |        |/|||\n" + 
				"      |       |___/\\___| '''\n" + 
				"      |        \\_ || _/\n" + 
				"      |        <_ >< _>\n" + 
				"      |        |  ||  |\n" + 
				"      |        |  ||  |\n" + 
				"      |       _\\.:||:./_\n" + 
				"      | jgs  /____/\\____\\";
		return (color + image + RESET);
	}
}
