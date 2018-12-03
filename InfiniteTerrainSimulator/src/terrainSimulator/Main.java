package terrainSimulator;

import java.util.Scanner;

/*
Instead of test encounter for fight, compare grid coords to player coords to check if 
grid[i][j] == 2/3 to set fights/ events relative to tiles.
also do the same on moving to check for tile blocks (grid[i][j] == 4)
update fill grid to include chances of setting fights/events/tile blocks.

Add a quest system and point to compass that instead of treasure.
once all quests are finished, point to treasure.

expand on fight system. have different enemies and different class to handle.


Final boss?
Currency?
	Shop?
More Potions?
Different weapons?
Abilites?
*/

public class Main {

	public static String[] locationDescriptions = new String[1]; 
	public static String[] winningMessages = new String[1]; 
	public static String[] hostileDescription = new String[2];
	
	public static int[][] grid;
	
	public static int difficulty = 0;
	
	public static int playerPosX;
	public static int playerPosY;
	
	public static int targetPosX;
	public static int targetPosY;
	
	public static int treasurePosX;
	public static int treasurePosY;

	public static boolean isTreasureSet = false;
	
	public static boolean gameRunning;
	
	public static boolean testing = false;
	
	public static boolean compassUnlocked = false;
	public static boolean isQuestAssigned = false;

	public static int playerMaxHealth = 20;
	public static int playerHealth = 20;
	public static int healthPotions = 2;
	public static float playerEXP = 0;
	public static int playerLevel = 1;
	
	public static int questCount = 0;
	public static int questReward = 0;
	
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

		System.out.println(questCount);
		
		fillDescriptions();
		initPlayer();
		
		while(gameRunning) {
			playGame();
		}
	}

	public static void playerMove() {
	
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
			if(playerPosX != (difficulty - 1)) {
				System.out.println("You have moved East");
				playerPosX++;
			} else {
				System.out.println("You are as far East as you can go. Try another Direction.\n");
			}
		} else if (playerMove.equals("South") || playerMove.equals("south")){
			if(playerPosY != (difficulty - 1)) {
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
		} else if (playerMove.equals("exp")){
			System.out.println("Your level is: " + playerLevel + ", your EXP is: " + playerEXP + ", to level up you need: " + (playerLevel * 60));
		} else {

		}
		
		if(grid[playerPosX][playerPosY] == 2) {
			questManager(1);
		}
		
		
	}
	
	public static void questManager(int handle) {
		if(handle == 1) {
			Scanner questChoice = new Scanner(System.in);
			System.out.println("Some sort of quest handle message, with accept/decline option and reward system. (50 exp)");
			String qChoice = questChoice.nextLine();
			
			if(qChoice.equals("Yes") || qChoice.equals("yes")){
				System.out.println("You have accepted this task");
				questReward = 1550;
				System.out.println(questReward);
				
				targetPosX = (int)(Math.random() * difficulty);
				targetPosY = (int)(Math.random() * difficulty);
				
				isQuestAssigned = true;
			} else if (qChoice.equals("No") || qChoice.equals("no")) {
				System.out.println("You have declined this task");
				grid[playerPosX][playerPosY] = 1; 
		}
		} else if (handle == 2) {
			System.out.println("Quest complete message and shit.");
			playerEXP += questReward;
			checkPlayerLevel();
			System.out.println(questReward);
			System.out.println("Your level is: " + playerLevel + ", your EXP is: " + playerEXP);
			isQuestAssigned = false;
		}
		
	}
	
	public static void testEncounter() {

		//use difficulty to create new values to compare against. increasing chance of fight and decreasing chance of compass.
		
		int compass = (int)(Math.random() * 20);
		int fight = (int)(Math.random() * 25);
		
		if(compass > 17 && !compassUnlocked) {
			System.out.println(eventHandler(4));
		} else if(fight > 21) {
			fight(1);
		}
	}
	
	public static void heal(boolean isPotion, int amount) {
		
		if(!isPotion) {
			playerHealth += amount;
			if(playerHealth > playerMaxHealth) {
				playerHealth = playerMaxHealth;
			}
		} else if(isPotion) {
			playerHealth += amount;
			healthPotions --;
			if(playerHealth > playerMaxHealth) {
				playerHealth = playerMaxHealth;
			}
		}
		
	}
	
	public static void fight(int fDifficulty) {
		
		int enemyHealth = 10;
		
		while(enemyHealth > 0) {
			if(fDifficulty == 1) {
				System.out.println("Fight Difficulty 1, set new message after testing: ");

				System.out.println("Will you: ||Attack|| ||Heal:(" + healthPotions + ")|| ||Run||");
			Scanner fInput = new Scanner(System.in);
			String fChoice = fInput.nextLine();
			
				if(fChoice.equals("Attack") || fChoice.equals("attack")) {
					int attackDMG = (int)(Math.random() * 8);
					enemyHealth -= attackDMG;
					if(enemyHealth <= 0) {
						enemyHealth = 0;
						playerEXP += (playerLevel * (int)(Math.random() * 100));
						checkPlayerLevel();
						System.out.println("You defeated the enemy!" + " Your level is: " + playerLevel + ", your EXP is: " + playerEXP);
						} else {
							System.out.println("You attacked for " + attackDMG + ", the enemy has: " + enemyHealth + " health remaning.");
							takeDamage();
						}
					} else if (fChoice.equals("Heal") || fChoice.equals("heal")) {
						if(healthPotions > 0) {
							heal(true, 8);
							takeDamage();
						} else {
							System.out.println("Healing failed. You have no health potions left!");
						}
					} else if (fChoice.equals("Run") || fChoice.equals("run")) {
						int runAttempt = (int)(Math.random() * 10);
						if(runAttempt > 9) {
							enemyHealth = 0;
						} else {
							System.out.println("Running failed.");
							takeDamage();
						}
				} 
			}
		}	
	}
	
	public static void checkPlayerLevel() {
		while(playerEXP >= (playerLevel * 60)) {
			playerEXP -= (playerLevel * 60);
			playerLevel ++;
		}
	}
	
	public static void playGame() {

		int tempPosX = playerPosX;
		int tempPosY = playerPosY;
			playerMove();
			
		if(playerPosX != tempPosX || playerPosY != tempPosY) {
			testEncounter();
		}
		
		checkPlayerLevel();
		
		if(calculateDistanceInt() == 0) {
			System.out.println(eventHandler(1));
			gameRunning = false;
		}		
		
		if(testing) {
			System.out.println("Player Pos: X:" + playerPosX + ", Y:" + playerPosY);
			System.out.println("Treasure Pos: X:" + treasurePosX + ", Y:" + treasurePosY);
		}
		
		if(compassUnlocked) {
			if(isQuestAssigned) {
				System.out.println(calculateDistance(targetPosX, targetPosY));
			} else {
				System.out.println(calculateDistance(treasurePosX, treasurePosY));
			}
		}
		
	}
	
	public static String calculateDistance(int x, int y) {

		int calcX;
		int calcY;

		if(playerPosX == x) {
			calcX = 0;
		}
		if(playerPosY == y) {
			calcY = 0;
		}
		
		if(playerPosX > x) {
			calcX = playerPosX - x;
		} else {
			calcX = x - playerPosX;
		}
		if(playerPosY > y) {
			calcY = playerPosY - y;
		} else {
			calcY = y - playerPosY;
		}
		
		return "Distance to your Brother: " + (calcX + calcY) + "m";
	}
	
	public static int calculateDistanceInt() {

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
		
		return calcX + calcY;
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
				double quest = Math.random();
				if(quest >= 0.985) {
					grid[i][j] = 2;
					questCount++;
				}
				
				double shop = Math.random() * 10;
				if(shop >= 9.9) {
					grid[i][j] = 3;
				}
				
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