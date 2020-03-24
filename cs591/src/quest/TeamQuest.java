package quest;

public class TeamQuest extends Team {
	int totalFightsLost = 0;
	int totalFightsWon = 0;

	public TeamQuest(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public int getFightsLost() {
		return totalFightsLost;
	}

	public void addFightsLost() {
		this.totalFightsLost++;
	}

	public int getFightsWon() {
		return totalFightsWon;
	}

	public void addFightsWon() {
		this.totalFightsWon++;
	}

	/*
	 * Return false if at least one player in this Team is less than given level.
	 */
	public boolean allTeamReachedLevel(int level) {
		for (Player player : this.getTeam()) {
			if (((Hero) player.getHero()).level < level) {
				return false;
			}
		}
		return true;
	}
	
	public void resetTeam() {
		for (Player player : this.getTeam()) {
			SetupQuestHelper.chooseQuestHero(player);
		}
		setCurrentRow(0);
		setCurrentCol(0);
	}
}
