package smash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Alec Pierce
 * Created date: 05/18/2019
 * Simulates Smashdown from Super Smash Bros. Ultimate through the console
 **/
public class Smashdown {
	
	static List<String> charList;
	static Player p1;
	static Player p2;
	
	public static void main (String[] args) {
		
		try {
			// read in file
			// create a list from file
			FileReader chars = new FileReader("...\\smash\\SSBU-chars.txt");
			BufferedReader charReader = new BufferedReader(chars);
			String character;
			charList = new ArrayList<String>();
			while ((character = charReader.readLine()) != null) {
				charList.add(character);
			}
			
			Random rand = new Random();
			long seed = generateSeed();
			rand.setSeed(seed);
			Collections.shuffle(charList, rand);
			Random rand2 = new Random();
			long seed2 = generateSeed();
			rand2.setSeed(seed);
			int distance = rand2.nextInt((int) seed2);
			Collections.rotate(charList, distance);
			Collections.shuffle(charList);
			
			// read in player names from user
			Scanner input = new Scanner(System.in);
			System.out.print("Player 1: ");
	        String player1Name = input.nextLine();
	        System.out.print("\nPlayer 2: ");
	        String player2Name = input.nextLine();
	        System.out.println();
	        
			// create player 1
	        p1 = new Player(player1Name);
			// create player 2
	        p2 = new Player(player2Name);
			
	        List<String> tempCharList = new ArrayList<String>();
	        tempCharList = charList;
	        List<String> charListForLoop = new ArrayList<String>();
	        charListForLoop = charList;
			int charCounter = 0;
			int charListSize = charListForLoop.size();
			while (charCounter < charListSize-1) {
				String char1 = "";
				String char2 = "";
				boolean matchFound = false;
				if ((tempCharList.size() - 2) < 2) {
					char1 = tempCharList.remove(0);
					if (!tempCharList.isEmpty()) {
						char2 = tempCharList.remove(0);
						matchFound = true;
					}
				} else {
					char1 = getCharacter(tempCharList);
					char2 = getCharacter(tempCharList);
					matchFound = true;
				}
				
				charCounter+=2;
				
				if (matchFound) {
					// print out the characters for the match
					// a (player 1's name) vs. b (player 2's name)
					System.out.println(
							"\t" + char1 + " " + "(" + p1.getName() + ")" + "\tVS." +
							"\t" + char2 + " " + "(" + p2.getName() + ")");
					
					// ask user who won (1 or 2)
					System.out.print("\nWho won the match? (1 for " + p1.getName() + " or 2 for " + p2.getName() + "): ");
					String winner = input.nextLine();
					
					// winner earns points
					if (winner.equals("1")) {
						p1.wins();
					} else if (winner.equals("2")) {
						p2.wins();
					} else {
						System.out.println("Selected winner is not correct, throwing out the score for that round.");
					}
					
					System.out.println("\nScores:\t" + 
							p1.getName() + ": " + p1.getScore() + "\t" +
							p2.getName() + ": " + p2.getScore());
					
					System.out.println("\nCharacters used: " + charCounter);
				}
			}
			
			// print out "Smashdown is over!"
			// see who's the winner by comparing scores
			// print out "THE WINNER IS... [player]!"
			System.out.println("\n\nSmashdown is over!");
			Player winner;
			if (p1.getScore() > p2.getScore()) {
				winner = p1;
			} else if (p1.getScore() < p2.getScore()) {
				winner = p2;
			} else {
				winner = new Player("No one wins...");
			}
			System.out.println("THE WINNER IS... " + winner.getName().toUpperCase() + "!");
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			System.out.println("\n\nSomething weird happened");
			System.out.println("Here are the player's scores...");
			System.out.println("Player 1: " + p1.getScore());
			System.out.println("Player 2: " + p2.getScore());
			ioException.printStackTrace();
		}
	}

	private static String getCharacter(List<String> tempCharList) {
		String character;
		Random random = new Random();
		long seed = generateSeed();
		random.setSeed(seed);
		int index = random.nextInt((int) seed); // creates a pseudo-random index
		while (index >= tempCharList.size()) {
			random = new Random();
			seed = generateSeed();
			random.setSeed(seed);
			index = random.nextInt((int) seed);
		}
		character = tempCharList.remove(index);
		return character;
	}

	/**
	 * Generate random seed for character select
	 * @return
	 */
	private static long generateSeed() {
		long seed = System.nanoTime();
		while (seed > charList.size()-1) {
			seed = (seed/2);
		}
		return seed;
	}
}