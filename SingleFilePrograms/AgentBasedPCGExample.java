/*
 * 	Cory Nelson
 * 	AgentBasedPCGExample.java
 * 
 * 	Java implementation of the Agent-based Dungeon Growing Algorithm
 * 	as found in "Procedural Content Generation in Games", Chapter 3, page 39
 * 
 * Summary:
 * 		Given a space of width X and height Y,
 * 			generate a dungeon in which every open tile is accessible 
 */

import java.util.*;

enum Direction{
	UP, DOWN, LEFT, RIGHT;
}

public class AgentBasedPCGExample {

	public static void main(String args[]) {
		int Pc = 5; // chance of changing direction
		int Pr = 5; // chance of adding room
		int Nc;
		int Nr;
		int dungeonWidth = 30;
		int dungeonHeight = 30;
		Direction dir;
		
		// initialize Dungeon
		char [][] dungeon = new char[dungeonHeight][dungeonWidth];
		for(int i = 0; i < dungeonHeight; i++) {
			for(int j = 0; j < dungeonWidth; j++) {
				dungeon[i][j] = '#';
			}
		}
		
		int dungeonSize = 0;
		int desiredSize = 150;
		//place the digger at a dungeon tile and randomize its direction
		int agentX = (int) (dungeonWidth/2); //initializes agent location at center of dungeon
		int agentY = (int) (dungeonHeight/2);
		int dirInt;
		int dAgent[];
		Random rand = new Random();
		
		//WHILE DUNGEON IS BELOW A CERTAIN SIZE
		while(dungeonSize < desiredSize) { //desiredSize
			//random number
			dirInt = rand.nextInt(4) + 1; // random number between 1 and 4
			dir = chooseDirection(dirInt);
			//dig along that direction
			dAgent = convertDirection(dir);
			if(agentX + dAgent[0] < dungeonWidth && agentX + dAgent[0] > 0)
				agentX += dAgent[0];
			if(agentY + dAgent[1] < dungeonHeight && agentY + dAgent[1] > 0)
				agentY += dAgent[1];
			dungeon = dungeonDig(dungeon,dungeonWidth, dungeonHeight, agentX,agentY,dir);


			Nc = rand.nextInt(101); // random int between 0 and 100
			if(Nc < Pc) {
				dirInt = rand.nextInt(4) + 1; // random number between 1 and 4
				dir = chooseDirection(dirInt);				
				Pc = 0;
			}
			else {
				Pc += 5;
			}
			
			Nr = rand.nextInt(101);
			if(Nr < Pr) {
				//TODO PLACE ROOMS
				Pr = 0;
			}
			else {
				Pr += 5;
			}
			
			//get dungeon size
			dungeonSize = getSize(dungeon, dungeonWidth, dungeonHeight);
			
		}
		
		// print dungeon
		for(int i = 0; i < dungeonHeight; i++) {
			for(int j = 0; j < dungeonWidth; j++) {
				System.out.print(dungeon[i][j]);
			}
			System.out.print('\n');
		}
		
	}
	
	static Direction chooseDirection(int x) {
		switch(x) {
		case 1:
			return Direction.UP;
		case 2:
			return Direction.DOWN;
		case 3:
			return Direction.LEFT;
		default:
			return Direction.RIGHT;	
		}
	}
	
	static int getSize(char [][] dungeon, int maxX,int maxY) {
		int size = 0;
		for(int i = 0; i < maxY; i++) {
			for(int j=0;j < maxX;j++) {
				if(dungeon[i][j] == ' ')
					size++;
			}
		}
		return size;
	}
	
	static int[] convertDirection(Direction dir) {
		int [] arr = {0,0}; // X, Y
		switch(dir) {
		case UP:
			arr[0] = 0;
			arr[1] = -1;
			break;
		case DOWN:
			arr[0] = 0;
			arr[1] = 1;
			break;
		case LEFT:
			arr[0] = -1;
			arr[1] = 0;
			break;
		default:
			arr[0] = 1;
			arr[1] = 0;	
		}
		return arr;
	}
	
	static char [][] dungeonDig(char[][]dungeon, int dungeonX, int dungeonY,int agent_x, int agent_y, Direction dir){
		
		
		if(isOutOfBounds(agent_x, agent_y, dungeonX, dungeonY)) {
			return dungeon;
		}
		else {
			dungeon[agent_y][agent_x] = ' ';
			return dungeon;
		}
	}
	
	static char[][] placeRoom(char[][]dungeon, int dungeonX, int dungeonY,int agent_x, int agent_y){
		//TODO
		//		randomize room width and length between 3 and 7
		//		place room around current agent position
		int roomWidth;
		int roomHeight;
		Random rand = new Random();
		roomWidth = rand.nextInt(3) + 1;
		roomHeight = rand.nextInt(7) + 1;
		
		//Build out to Width
		
		
		return dungeon;
	}
	
	static boolean isOutOfBounds(int x, int y, int maxX, int maxY) {
		if(x < 0 || x >= maxX || y < 0 || y >= maxY)
			return true;
		else {
			return false;
		}	
	}
}
