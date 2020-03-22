package quest;

/*
 * Ignore this class. This class is dedicated for testing purposes.
 */
public class QuestTest {
	static Quest q = new Quest();
	static QuestBoard board = new QuestBoard(4, 4);
	
	public static void tmpSetup() {
		System.out.println(">>>> QuestTest: Tmp setup  ");

		Ammunition a1 = new Armor("Armor1", 150, 1, 200);
		Ammunition a2 = new Armor("Armor2", 350, 1, 600);
		Ammunition b1 = new Weapon("Weapon1", 500, 1, 500, 2);
		Ammunition b2 = new Weapon("Weapon2", 330, 1, 800, 1);
		Ammunition p1 = new Potion("Healing_Potion", 250, 1, 222);
		Ammunition p2 = new Potion("Luck_Elixir", 298, 1, 75);
		Ammunition s1 = new Spell("Spell1", 500, 1, 650, 250);
		Ammunition s2 = new Spell("Spell2", 400, 1, 450, 100);
				
		Team team = new Team(1);
		Player player = q.addPlayer();
		Player player2 = q.addPlayer();
		team.addPlayer(player);
		team.addPlayer(player2);
	
		SetupQuestHelper.setupTeam(team);
		team.setPiece(new SimplePiece('T'));
		q.addTeam(team);
		
		board.getBoard()[0][0].placePiece(team.getPiece());
		
		player.getHero().getStorage().add(a1);
		player.getHero().getStorage().add(b1);
		player.getHero().getStorage().add(p1);
		player.getHero().getStorage().add(s1);
		player2.getHero().getStorage().add(a2);
		player2.getHero().getStorage().add(b2);
		player2.getHero().getStorage().add(p2);
		player2.getHero().getStorage().add(s2);
		
		System.out.println(">>>> Player 1 storage = ");
		player.getHero().getStorage().showStorage();
		System.out.println("\n>>>> Player 2 storage = ");
		player2.getHero().getStorage().showStorage();
		
	}
	
	public static void setupStorage(Team team) {
		return;
	}
	
	private static void initFight(Team team) {
		setupStorage(team);
		
		Fight.fightCountdown();
		Fight.fight(team);
		return;
	}
	
	public static void main(String[] args) {
		CSVFilesHandler.setData();
		
		tmpSetup(); 
		
		q.setGamersQueue(q.getTeams());
		board.displayBoard();
		Team team = (Team) q.getNextInQueue(q.mapTeams);
		q.cellTypeHandler(team, '$'); //Testing shopping in Market
		initFight(team);
		
	}
	
}
