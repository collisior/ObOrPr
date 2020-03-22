package quest;

public class TeamQuest extends Team {
	int totalFights = 0;
	int totalFightsWon = 0;
	
	
	public TeamQuest(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	

	public int getTotalFights() {
		return totalFights;
	}

	public void setTotalFights(int totalFights) {
		this.totalFights = totalFights;
	}
	
	public int getTotalFightsWon() {
		return totalFightsWon;
	}

	public void totalFightsWon(int totalFightsWon) {
		this.totalFightsWon = totalFightsWon;
	}


}
