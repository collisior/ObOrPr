package quest;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Fight implements Color, Vizualization {

	private static ArrayList<Monster> aliveMonsters = new ArrayList<Monster>();
	private static ArrayList<Monster> deadMonsters = new ArrayList<Monster>();
	private static int monsterTurnIndex = 0;

	public static void fight(Team team) {
		aliveMonsters = SetupQuestHelper.generateMonsters(team);
//		Monster m1 = new Dragon("Natsunomeryu", 1, 100, 200, 10);
//		aliveMonsters.set(0, m1);
		GenericMethods.shuffle(aliveMonsters);
		boolean fightStop = false;
		while (!fightStop) {

			if (fightContinues(team)) {
				Hero hero = nextAliveHero(team);
				Monster monster = nextAliveMonster();
				System.out.println(GREEN + "\nHero "+ hero +" VERSUS Monster "+ monster+ RESET+"\n");
				if (hero.isAlive() && monster.isAlive()) {
					System.out.println(team.color+team.getCurrentTeamPlayer()+RESET +", it's your turn to attack " + monster+".");
					round(hero, monster);
					hero.endOfRound();
				}
			} else {
				showWinner(team);
				fightStop = true;
			}
		}
		exitFight(team);
//		for(Player player: team.getNextTeamPlayer())
	}

	/*
	 * Update stats for each hero. Resurrect heroes. If the monsters win the fight
	 * the heroes lose half of their money.
	 */
	private static void exitFight(Team team) {
		// if monsters win, half of money are taken away from each player
		System.out.println("\n\n\n>>>>>>>>>>>>> totalTeamHpValue(team)" + totalTeamHpValue(team));
		if (totalTeamHpValue(team) <= 0) {
			for (Player player : team.getTeam()) {
				Hero hero = (Hero) player.getHero();
				hero.setMoney(hero.getMoney() / 2);
			}
			System.out.println(YOU + "\n" + LOST + "\n" + FIGHT);
		} else {
			System.out.println(VICTORY + "\n" + MONSTERS + "\n" + DEFEATED);
		}
		for (Player player : team.getTeam()) {
			Hero hero = (Hero) player.getHero();
			hero.exitFight();
		}
		// totalDefeatedInFight
		monsterCleaning();
	}

	/*
	 * 
	 */
	private static void round(Hero hero, Monster monster) {
		System.out.println(hero.image());
		heroAttacks(hero, monster);
		System.out.println(GREEN + "Monster's turn to attack ...\n\n" + RESET);
		System.out.println(monster.image());
		countdown(2);
		if (monster.isAlive()) {
			monsterAttacks(hero, monster);
		} else {
			hero.getDefeatedMonstersFight().add(monster);
			deadMonsters.add((Monster) monster);
			aliveMonsters.remove(monster);
			if (!aliveMonsters.isEmpty()) {
				System.out.println( MONSTER + "\n" + DEFEATED);
			}
		}
		hero.setHasDodgedAttack(false);
		monster.setHasDodgedAttack(false);
	}

	public static void heroAttacks(Hero hero, Monster monster) {
		hero.chooseCurrentAmmunition();
		System.out.println("Your attack: "+hero.getCurrentAmmunition()+"\n"+hero.getCurrentAmmunition().image());
		double damage = hero.damageCalculation(monster);

		if (monster.dodgeAttack()) {
			// monster escapes
			System.out.println(GREEN + "Oh no... Monster dodged your attack!\n\n" + RESET);
		} else {
			monster.applyDamage(damage);
		}
	}

	/*
	 * 
	 */
	public static void monsterAttacks(Hero hero, Monster monster) {

		if (hero.dodgeAttack()) {
			// hero escapes
			System.out.println(GREEN + "Lucky son of a witch!!! You just dodged monster's attack!" + RESET);
		} else {
			double damage = monster.damageCalculation(hero);
			hero.applyDamage(damage);
			System.out.println(PUNCH);
			System.out.println(GREEN + "OUCHH...Monster attacked! and you got hurt...\n" + RESET);

		}
	}

	private static Monster nextAliveMonster() {
		Monster monster = aliveMonsters.get((monsterTurnIndex + 1) % aliveMonsters.size());
		return monster;
	}

	private static Hero nextAliveHero(Team team) {
		Hero hero = (Hero) team.getNextTeamPlayer().getHero();
		if (hero.isAlive()) {
			return hero;
		}
		return nextAliveHero(team);
	}

	private static void showWinner(Team team) {
		if ((totalTeamHpValue(team) > 0) && (totalMonstersHpValue() <= 0)) {
			((TeamQuest) team).addFightsWon();
			System.out.println("Team wins the fight!");
		} else {
			((TeamQuest) team).addFightsLost();
			System.out.println("Monsters wins the fight!");
		}
	}

	/*
	 * Return checkWinner
	 */
	public static boolean fightContinues(Team team) {
		if ((totalTeamHpValue(team) > 0) && (totalMonstersHpValue() > 0)) {
			return true;
		}
		return false;
	}

	/*
	 * Return total HP for this team.
	 */
	public static int totalTeamHpValue(Team team) {
		int totalHp = 0;
		for (Player player : team.getTeam()) {
			totalHp += player.getHero().getHp();
		}
		return totalHp;
	}

	/*
	 * Return total HP for this team.
	 */
	public static int totalMonstersHpValue() {
		if (aliveMonsters.isEmpty()) {
			return 0;
		}
		int totalHp = 0;
		for (Monster monster : aliveMonsters) {
			totalHp += monster.getHp();
		}
		return totalHp;
	}

	/*
	 * Get Monster's max level from this fight.
	 */
	public static int getMonstersLevel() {
		int level = 0;
		for (Monster monster : deadMonsters) {
			if (level < monster.getLevel()) {
				level = monster.getLevel();
			}
		}
		return level;
	}

	public static void monsterCleaning() {
		aliveMonsters.clear();
		deadMonsters.clear();
		monsterTurnIndex = 0;
	}

	public static void fightCountdown() {
		Timer timer = new Timer();
		int seconds = 3;
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = seconds;

			public void run() {
				if (i == 0) {
					System.out.println(FIGHT);
				} else if (i == 1) {
					System.out.println(ONE);
				} else if (i == 2) {
					System.out.println(TWO);
				} else if (i == 3) {
					System.out.println(THREE);
				}
				i--;
				if (i < 0)
					timer.cancel();
			}
		}, 0, 1000);
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return;
	}
	public static void countdown(int seconds) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = seconds;

			public void run() {
				i--;
				System.out.print("");
				if (i < 0)
					timer.cancel();
			}
		}, 0, 1000);
		try {
			Thread.sleep(seconds * 750);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return;
	}

}