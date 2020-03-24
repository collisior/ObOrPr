package quest;

public class Paladin extends Hero {
//	Paladins are favored on the strength and the dexterity
	Paladin(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
		super(name, mana, strength, agility, dexterity, money, experience);
	}

	public void upgradeSkills() {
		setStrength(getStrength() + getStrength() * (0.05 + 0.05));
		setAgility(getAgility() + getAgility() * 0.05);
		setDexterity(getDexterity()+ getDexterity() * (0.05 + 0.05));
	}
	public String image() {
		String image =  
				" /\\                            \n" + 
				" ||                            \n" + 
				" ||                            \n" + 
				" ||                            \n" + 
				" ||           {}               \n" + 
				" ||          .--.              \n" + 
				" ||         /.--.\\             \n" + 
				" ||         |====|             \n" + 
				" ||         |`::`|             \n" + 
				"_||_    .-;`\\..../`;_.-^-._   \n" + 
				" /\\\\   /  |...::..|`   :   `|  \n" + 
				" |:'\\ |   /'''::''|   .:.   |  \n" + 
				"  \\ /\\;-,/\\   ::  |..:::::.|   \n" + 
				"   \\ <` >  >._::_.| ':::::' |  \n" + 
				"    `\"\"`  /   ^^  |   ':'   |  \n" + 
				"          |       \\    :    /  \n" + 
				"          |        \\   :   /   \n" + 
				"          |___/\\___|`-.:.-`    \n" + 
				"           \\_ || _/    `       \n" + 
				"           <_ >< _>            \n" + 
				"           |  ||  |            \n" + 
				"           |  ||  |            \n" + 
				"          _\\.:||:./_           \n" + 
				"         /____/\\____\\          \n";
		
		return (color + image + RESET);
	}
}