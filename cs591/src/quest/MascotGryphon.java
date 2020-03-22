package quest;

public class MascotGryphon extends QuestMascot {
	
	MascotGryphon(String name, double power, double luck, int canDamage) {
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
	
	public void image() {
		String image = 
				"        _____,    _..-=-=-=-=-====--,\n" + 
				"     _.'a   /  .-',___,..=--=--==-'`\n" + 
				"    ( _     \\ /  //___/-=---=----'\n" + 
				"     ` `\\    /  //---/--==----=-'\n" + 
				"  ,-.    | / \\_//-_.'==-==---='\n" + 
				" (.-.`\\  | |'../-'=-=-=-=--'\n" + 
				"  (' `\\`\\| //_|-\\.`;-~````~,        _\n" + 
				"       \\ | \\_,_,_\\.'        \\     .'_`\\\n" + 
				"        `\\            ,    , \\    || `\\\\\n" + 
				"          \\    /   _.--\\    \\ '._.'/  / |\n" + 
				"          /  /`---'   \\ \\   |`'---'   \\/\n" + 
				"         / /'          \\ ;-. \\\n" + 
				"jgs   __/ /           __) \\ ) `|\n" + 
				"    ((='--;)         (,___/(,_/";
		System.out.println(YELLOW + image + RESET);
	}
	
}
