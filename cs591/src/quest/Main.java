package quest;

public class Main {
		
	public static void main(String[] args) {
		
		CSVFilesHandler.setData();
		System.out.println("\nWelcome to the game!\n");

		Quest game = new Quest();
		game.startGame();
//		Board board = new QuestBoard(8,8);
//		board.displayBoard();
	}
	
}