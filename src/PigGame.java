import java.util.*;


/**
 * PIG Game:
https://en.wikipedia.org/wiki/Pig_(dice_game)

----Rules----
Each turn, a player repeatedly rolls a die until either a 1 is rolled or the player decides to "hold":
If the player rolls a 1, they score nothing and it becomes the next player's turn.
If the player rolls any other number, it is added to their turn total and the player's turn continues.
If a player chooses to "hold", their turn total is added to their score, and it becomes the next player's turn.

The first player to score 100 or more points wins.

For example, the first player, Ann, begins a turn with a roll of 5.
Ann could hold and score 5 points, but chooses to roll again.
Ann rolls a 2, and could hold with a turn total of 7 points, but chooses to roll again.
Ann rolls a 1, and must end her turn without scoring.
The next player, Bob, rolls the sequence 4-5-3-5-5, after which he chooses to hold, and adds his turn total of 22 points to his score.

----Requirements----
1. Must support 2-4 players
Take the number of players as an argument from terminal where the program is executed as
java pig [# players]
example:
java pig 4
There will be 4 players
if a passed in argument is less than 2 or greater than 4, exit immediately

2. Must take user input from terminal.
Commands:
ROLL - rolls the die
HOLD - hold with current score
QUIT - exit the game
PRINT - shows the current score

3. Must use a method for RollingDice

4. Must use a method to update score

5. Scores must be kept in an appropriate data structure(array or ArrayList)

6. At the end of the game (when someone has reachad 100 points),
you must ask: "Do you want to play again."
The game will be reset if the user enters "yes" or "y" in upper or lower case and you can replay the game.

7. Communicate to the user on what he/she should do. For instance, if the program needs a command then print: "What would you like to do?"

----Additional Topics----
Random Numbers in Java
https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java

While Loop
https://docs.oracle.com/javase/tutorial/java/nutsandbolts/while.html

----HINT----
Normally, games have a boolean called playing where:
	playing = true as long as the game is in progress
	AND
	no one has won the game

For this game, playing should be equal to true as long as no player has a score greater than 100

Example:
while ( playing == true){
	roll the dice, take commands, add score, etc...
	if (player_score > 100){
		playing = false;
	}
	if(command == QUIT){
		playing = false;
	}
}

*/




public class PigGame {
	
	static int numberOfPlayers = 0; // number of players <- input from terminal
	static Random rand = new Random();
	
	final static int MAXSCORE = 100; 
	final static int ROLL = 1; //		ROLL - rolls the die
	final static int HOLD = 2; //		HOLD - hold with current score
	final static int QUIT = 3; //		QUIT - exit the game
	final static int PRINT = 4;//		PRINT - shows the current score
	static Scanner console = new Scanner(System.in);
	static List<Integer> totalScore; 
	
	
	
	public static void main(String args[]) {
	
		
 	    numberOfPlayers = Integer.parseInt(args[0]);  // prod version
	//	numberOfPlayers = 2;  // testing 
		totalScore = new ArrayList<Integer>(numberOfPlayers);
		for (int i = 0; i < numberOfPlayers; i++) {
		totalScore.add(i, 0);
		}
		int player_score = 0; // current score in current round 
		int player = 0; // current player
		int command = 0;
		boolean playing = true; // playing should be equal to true as long as no player has a score greater than 100
		
		while (playing){
			if (command == 0) {
				System.out.println("New GAME! :)");
			}
			command = GetCommand(player);
							
			if (command == ROLL) {
				int dice = RollingDise ();
				if(dice == 1) {
					player = (player+1)%numberOfPlayers;
					player_score = 0;
					System.out.println("Next player!");
				}
				else if (GetScore(player_score + dice, player) >= MAXSCORE){
					
					System.out.println("Winner is: "+ (player+1) + "\nGame over! :P");
					System.out.println("\nType \"yes\" or \"y\" if you want to play again!"); // 
					
					String cmd = console.next().toLowerCase();
					
					if (cmd.equals("yes") || cmd.equals("y")) {
						for (int i = 0; i < numberOfPlayers; i++) {
							totalScore.set(i, 0);
							}
						player_score = 0; // current score in current round 
						player = 0; // current player
						command = 0;
					 }
					else {
						playing = false;
						System.out.println("Terminated!");
					}
				}
				else {
					player_score += dice;
					System.out.println("Total score is " + GetScore(player_score, player));
				}
					
			}
			else if (command == HOLD) {
				UpdateScore(player_score, player);
				player = (player+1)%numberOfPlayers;
				player_score = 0;
			}
			else if (command == PRINT) {
				int printS = GetScore(player_score, player);
				System.out.println("Your current score is: " + printS);
				
			}			
			else if (command == QUIT){
				playing = false;
				System.out.println("Terminated!");
				
			}
			else {
				playing = false;
				System.out.println("Terminated!");
			}
		}
		

		
		
		
	}// end main
	
	
	public static int RollingDise () { // method returns random number between 1 and 6

		int dise = rand.nextInt(6) + 1;
		System.out.println("Current score is " + dise);
		return dise;
	}
	
	public static void UpdateScore (int player_score, int player) { // method updates total score for every player
		int sum = totalScore.get(player) + player_score;
		totalScore.set(player, sum);
		
		
	}
		
		public static int GetCommand (int player) {
			System.out.println("Player" + (player+1) + ", type the command :\n1 - to roll the die\n" + 
					"2 - to hold with current score\n" + 
					"3 - to exit the game\n" + 
					"4 - to show the current score");
			int cmd = console.nextInt();
			return cmd; 
		
		}
	
		public static int GetScore (int player_score, int player) { 
			
			int sum = totalScore.get(player) + player_score;
			return sum;
			
		}
			
		
	


} //end class
