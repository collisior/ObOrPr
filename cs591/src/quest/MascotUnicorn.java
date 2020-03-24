package quest;

public class MascotUnicorn extends QuestMascot {
	
	MascotUnicorn(String name, double power, double luck, int canDamage) {
		super(name, power, luck, canDamage);
	}

	@Override
	protected void empower(Hero hero) {
		hero.setStrength(hero.getStrength() + hero.getStrength() * (getPower() / 100));
	}


	@Override
	protected double damage(Hero hero, double damage) {
		return damage + damage * (getPower() / 100);
	}
	
	public String image() {
		String image = 
				"              \\                          \n" + 
				"               \\                         \n" + 
				"                \\\\                      \n" + 
				"                 \\\\                     \n" + 
				"                  >\\/7                   \n" + 
				"              _.-(6'  \\                  \n" + 
				"             (=___._/` \\                 \n" + 
				"                  )  \\ |                 \n" + 
				"                 /   / |                  \n" + 
				"                /    > /                  \n" + 
				"               j    < _\\                 \n" + 
				"           _.-' :      ``.                \n" + 
				"           \\ r=._\\        `.            \n" + 
				"          <`\\\\_  \\         .`-.        \n" + 
				"           \\ r-7  `-. ._  ' .  `\\       \n" + 
				"            \\`,      `-.`7  7)   )\\     \n" + 
				"             \\/         \\|  \\'  / `-._ \n" + 
				"                        ||    .'          \n" + 
				"                         \\\\  (          \n" + 
				"                          >\\  >          \n" + 
				"                      ,.-' >.'            \n" + 
				"                     <.'_.''              \n" + 
				"                       <'";
		return (CYAN + image + RESET);
	}
	
}