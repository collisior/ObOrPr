package quest;

import java.util.Random;

public class Main {
		
	public static void main(String[] args) {
		
		Random random = new Random();
		int randomNum = random.nextInt(99);
		System.out.println("\nWelcome to the game!\n : " +randomNum);
		CSVFilesHandler.setData();
		System.out.println("\nWelcome to the game!\n");
		// Hero 
//		QuestBoard board = new QuestBoard(5,5);
//		board.displayBoard();
//		board.getBoard()[0][0].placePiece(new SimplePiece('M'));
//		board.displayBoard();
//		board.getBoard()[0][0].placePiece(new SimplePiece('T'));
//		board.displayBoard();
//		board.getBoard()[1][0].placePiece(new SimplePiece('$'));
//		board.displayBoard();
//		System.exit(0);
		Quest game = new Quest();
		game.startGame();
//		Board board = new QuestBoard(8,8);
//		board.displayBoard();
	}
	
}
