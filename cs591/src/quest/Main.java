package quest;

public class Main implements Vizualization {
		
	public static void main(String[] args) {
		
		CSVFilesHandler.setData();
		System.out.println(WELCOME);

		Quest game = new Quest();
		game.startGame();
//		Board board = new QuestBoard(8,8);
//		board.displayBoard();
	}
	
}