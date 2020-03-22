package quest;

public class Dragon extends Monster {
	//Dragons have a higher damage,
	Dragon(String name, int level, int damage, int defense, double dodgeChance) {
		super(name, level, damage, defense, dodgeChance);
		// TODO Auto-generated constructor stub
	}
	
	public void image() {
		String image = 
				"             __                  __\n" + 
				"            ( _)                ( _)\n" + 
				"           / / \\\\              / /\\_\\_\n" + 
				"          / /   \\\\            / / | \\ \\\n" + 
				"         / /     \\\\          / /  |\\ \\ \\\n" + 
				"        /  /   ,  \\ ,       / /   /|  \\ \\\n" + 
				"       /  /    |\\_ /|      / /   / \\   \\_\\\n" + 
				"      /  /  |\\/ _ '_| \\   / /   /   \\    \\\\\n" + 
				"     |  /   |/  0 \\0\\    / |    |    \\    \\\\\n" + 
				"     |    |\\|      \\_\\_ /  /    |     \\    \\\\\n" + 
				"     |  | |/    \\.\\ o\\o)  /      \\     |    \\\\\n" + 
				"     \\    |     /\\\\`v-v  /        |    |     \\\\\n" + 
				"      | \\/    /_| \\\\_|  /         |    | \\    \\\\\n" + 
				"      | |    /__/_ `-` /   _____  |    |  \\    \\\\\n" + 
				"      \\|    [__]  \\_/  |_________  \\   |   \\    ()\n" + 
				"       /    [___] (    \\         \\  |\\ |   |   //\n" + 
				"      |    [___]                  |\\| \\|   /  |/\n" + 
				"     /|    [____]                  \\  |/\\ / / ||\n" + 
				"    (  \\   [____ /     ) _\\      \\  \\    \\| | ||\n" + 
				"     \\  \\  [_____|    / /     __/    \\   / / //\n" + 
				"     |   \\ [_____/   / /        \\    |   \\/ //\n" + 
				"     |   /  '----|   /=\\____   _/    |   / //\n" + 
				"  __ /  /        |  /   ___/  _/\\    \\  | ||\n" + 
				" (/-(/-\\)       /   \\  (/\\/\\)/  |    /  | /\n" + 
				"               (/\\/\\)           /   /   //\n" + 
				"                      _________/   /    /\n" + 
				"                     \\____________/    (\n";
		System.out.println(color + image + RESET);
	}
}
