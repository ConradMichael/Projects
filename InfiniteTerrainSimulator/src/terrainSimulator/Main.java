package terrainSimulator;

import java.util.Scanner;

public class Main {

	public static String[] locationDescriptions = new String[1]; 
	public static String[] winningMessages = new String[1]; 
	public static String[] hostileDescription = new String[2];
	
	public static int[][] grid;
	
	public static int difficulty = 0;
	
	public static int playerPosX;
	public static int playerPosY;
	
	public static int treasurePosX;
	public static int treasurePosY;

	public static boolean isTreasureSet = false;
	
	public static boolean gameRunning;
	
	public static boolean testing = false;
	
	public static boolean compassUnlocked = false;

	public static int playerHealth = 10;
	public static int healthPotions = 2;
	
	public static void main(String[] args) {
		
		while (difficulty == 0) {
			Scanner input = new Scanner(System.in);
			System.out.println("How difficult would you like your game to be? (Scale: 1-5)");
			int temp = input.nextInt();
			if(temp >= 1 &&temp <= 5) {
				difficulty = temp * 10;
			} else {
				System.out.println("Choose a difficulty between 1-5");
			}
		}
		
		grid = new int[difficulty][difficulty];
		
		if(!isTreasureSet) {
			fillGrid();
		}
		
		fillDescriptions();
		initPlayer();
		
		while(gameRunning) {
			playGame();
		}
	}

	
	public static void playGame() {
		String playerMove;
		Scanner getInput = new Scanner(System.in);
		System.out.println("Where would you like to go? (North | East | South | West)\n");
		playerMove = getInput.nextLine();
		
		if(playerMove.equals("North") || playerMove.equals("north")) {
			if(playerPosY != 0) {
				System.out.println("You have moved North");
				playerPosY--;
			} else {
				System.out.println("You are as far North as you can go. Try another Direction.\n");
			}
		} else if (playerMove.equals("East") || playerMove.equals("east")){
			if(playerPosX != difficulty) {
				System.out.println("You have moved East");
				playerPosX++;
			} else {
				System.out.println("You are as far East as you can go. Try another Direction.\n");
			}
		} else if (playerMove.equals("South") || playerMove.equals("south")){
			if(playerPosY != difficulty) {
				System.out.println("You have moved South");
				playerPosY++;
			} else {
				System.out.println("You are as far South as you can go. Try another Direction.\n");
			}
		} else if (playerMove.equals("West") || playerMove.equals("west")){
			if(playerPosX != 0) {
				System.out.println("You have moved West");
				playerPosX--;
			} else {
				System.out.println("You are as far West as you can go. Try another Direction.\n");
			}
		} else {

		}
		
		int encounter = (int)(Math.random() * 24);
		if(encounter > 22) {
			eventHandler(3);
		}
		
		int compass = (int)(Math.random() * 20);
		if(compass > 17) {
			System.out.println(eventHandler(4));
		}


		if(calculateDistance() == "Distance to your Brother: 0m") {
			eventHandler(1);
			gameRunning = false;
		}		
		
		if(testing) {
			System.out.println("Player Pos: X:" + playerPosX + ", Y:" + playerPosY);
			System.out.println("Treasure Pos: X:" + treasurePosX + ", Y:" + treasurePosY);
		}
		
		if(compassUnlocked) {
			System.out.println(calculateDistance());
		}
		
	}
	
	public static String calculateDistance() {

		int calcX;
		int calcY;

		if(playerPosX == treasurePosX) {
			calcX = 0;
		}
		if(playerPosY == treasurePosY) {
			calcY = 0;
		}
		
		if(playerPosX > treasurePosX) {
			calcX = playerPosX - treasurePosX;
		} else {
			calcX = treasurePosX - playerPosX;
		}
		if(playerPosY > treasurePosY) {
			calcY = playerPosY - treasurePosY;
		} else {
			calcY = treasurePosY - playerPosY;
		}
		
		return "Distance to your Brother: " + (calcX + calcY) + "m";
	}
	
	public static void initPlayer() {
		System.out.println("You wake up just barely remembering the events of the past few hours\n"
				+ "you look around you to find your brother is missing\n"
				+ "he's been kidnapped, you need to go find him.\n");
		
		System.out.println("Health: " + playerHealth + "\n");
		
		gameRunning = true;
		double tempPos = Math.random() * difficulty;
		playerPosX = (int)tempPos;
		
		tempPos = Math.random() * difficulty;
		playerPosY = (int)tempPos;
		
		if(testing) {
			System.out.println("Player Pos: X:" + playerPosX + ", Y:" + playerPosY);
		}
	}
	
	public static void fillDescriptions() {
		
		locationDescriptions[0] = "Description";
		
		winningMessages[0] = "You have found your Brother!";

		hostileDescription[0] = "Bandits are in your way. \nYou must try fight them to get to your brother.";
		hostileDescription[1] = "Goblins are demanding money to let you take the road ahead. \nFight them off and clear the way.";
		
	}
	
	public static void takeDamage() {

		double dmgCalc = Math.random() * (playerHealth / 2.3);
		int dmg = (int)dmgCalc;
		
		playerHealth -= dmg;
		if(playerHealth > 1) {
			System.out.println("You have defeated the enemies!");
			System.out.println("Your health is now: " + playerHealth);
		} else {
			System.out.println("Game Over! You are dead.");
			gameRunning = false;
		}
	}
	
	public static String eventHandler(int eventType) {
		
		if(eventType == 1) {
			double eMessageD = Math.random() * winningMessages.length;
			int eMessage = (int)eMessageD;
			
			return (winningMessages[eMessage]);
			
		} else if(eventType == 2) {
			double eMessageD = Math.random() * locationDescriptions.length;
			int eMessage = (int)eMessageD;
			
			return (locationDescriptions[eMessage]);
			
		} else if(eventType == 3){
			double eMessageD = Math.random() * hostileDescription.length;
			int eMessage = (int)eMessageD;
			
			takeDamage();
			
			return (hostileDescription[eMessage]);
		} else if(eventType == 4){
			
			compassUnlocked = true;
			return "insert Compass message blahh blahh";
		} else {
			return "Faulty event";
		}
	}
	
	public static void fillGrid() {
		
		treasurePosX = 0;
		treasurePosY = 0;
		
		for(int i = 0; i < difficulty; i++) {
			for(int j = 0; j < difficulty; j++) {
				if(!isTreasureSet) {
					double t = Math.random();
					if(t > 0.99) {
						grid[i][j] = 1;
						treasurePosX = i;
						treasurePosY = j;
						isTreasureSet = true;
					} else {
						grid[i][j] = 0;
					}
				} else {
					grid[i][j] = 0;
				}
			}
		}
		if(testing) {
			System.out.println("Treasure Pos: X:" + treasurePosX + ", Y:" + treasurePosY);
		}
	}
}
