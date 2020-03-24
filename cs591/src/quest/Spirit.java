package quest;

public class Spirit extends Monster {
	// spirits have higher dodge chance
	Spirit(String name, int level, int damage, int defense, double dodgeChance) {
		super(name, level, damage, defense, dodgeChance);
		// TODO Auto-generated constructor stub
	}

	public String image() {
		String image =
				"                                   ╓#²▀╙`\"≈,                                    \n" + 
				"                                 █Γ         █          ╓Φ═⌐≈φ╖                  \n" + 
				"                                ▌ ╓      ,   █       ╒█         ╙▀w             \n" + 
				"                               ▌ ███▌ ▄████  █       █              ╙╗          \n" + 
				"                  ,╓═⌐¼       ╫ ▓████ █████  █      █                  ▀w       \n" + 
				"                Φ▀    ]█      █ ▀▀▀└   └└╙   ▌     ╒▌                    ▀▄     \n" + 
				"             ▄▀        █      █  ▄█████▄     █    ,█                  ╓▀\"\"▀█    \n" + 
				"           ▄▀           █     █ █████████    └█-▄█                   ▄█         \n" + 
				"         ,█              ╙▓  ▐▌ ████████▌                          █            \n" + 
				"        ▓╨                      ██    ▀█                           █            \n" + 
				"       █                                                    ,▄   ,Æ█╕           \n" + 
				"      █                                                  ,- █  ██               \n" + 
				"     █▄                                                  █  █                   \n" + 
				"     `╙▌                                                 █                      \n" + 
				"       █  ,Γ██▀█                                        █                       \n" + 
				"       █ ╒      █ ▄Γ ██▀▓                              ▓\"                       \n" + 
				"       █ █       █       ▀              ╒            ╓█                         \n" + 
				"       ██⌐                 ╙«                     a█▌                           \n" + 
				"        ╙                      \"²≈┐   ,,,▄          µ                           \n" + 
				"                                     ╙█            ▓                            \n" + 
				"                                      █          ▄▀                             \n" + 
				"                                     ▓         ▄▀                               \n" + 
				"                                     █         █                                \n" + 
				"                                     └└ █       █                               \n" + 
				"                                               ,█                               \n" + 
				"                                        ▐    ,▌                                 \n" + 
				"                                         █,  █                                  \n" + 
				"                                           █  ▌                                 \n" + 
				"                                            █ █                                 \n" + 
				"                                            ██▀                                 \n" + 
				"                                            ▀    ";
		return (color + image + RESET);
	}
}
