package tictactoe;

public class Main {
	
	
	public static void main(String[] args) {
		System.out.println("\nWelcome to the game!\n");
//		TicTacToe tt1 = new TicTacToe();
//		tt1.startGame();
		char game = Game.ChooseGame();
		
		switch (game) {
		case '0':
			System.out.println();
			TicTacToe ttt = new TicTacToe();
			ttt.startGame();
			break;
		case '1':
			System.out.println();
			ttt = new TicTacToe();
			ttt.classicStyleTTT();
			ttt.endGameHandler();
			break;
		case 'Q':
			System.out.println("\nPain is temporary. Quitting is forever...\n");
		}
		
		System.out.println("Have a noice day!");
	}
}
