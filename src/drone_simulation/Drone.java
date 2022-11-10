package drone_simulation;

import java.io.Serializable;

public class Drone implements Serializable{
	private int x;
	private int y;
	private Direction DroneDirection;
	private int DroneIdentifier;
	public static int counter = -1;

	//Constructor for the Drone Class
	Drone(int x, int y, Direction direction){
		this.x = x;
		this.y = y;
		DroneDirection = direction;
		DroneIdentifier = counter ++;
	}

	//Getter to retrieve the x co-ordinate of the drone
	public int GetX() {
		return x;
	}
	
	//Getter to gretrieve the y co-ordinate of the drone
	public int GetY() {
		return y;
	}
	
	//Represents the Drone as the character 'D' within the terminal 
	public void DisplayDrone(ConsoleCanvas c) {
		char DroneRepresentation = 'D';
		c.ShowIt(x,y,DroneRepresentation);
	}
	
	//Checks if the x and y co-ordinate is equal to the location of the drone
	public boolean CheckSpaceOccupied(int sx, int sy) {
		if (sx == x && sy == y) 
			return true;
		else
			return false;
	}
	
	//Checks if the drone can move the a certain location and if it can it moves there but
	// if it can't then it will check for the next direction
	public void TryToMove(DroneArena A) {
		switch(DroneDirection) {
			case North:
				if (A.CanMoveHere(x-1,y))
					x = x-1;	
				else
					DroneDirection = DroneDirection.NextDirection();
				break;
			case East:
				if (A.CanMoveHere(x,y+1))
					y = y+1;	
				else
					DroneDirection = DroneDirection.NextDirection();
				break;
			case South:
				if (A.CanMoveHere(x+1,y))
					x = x+1;	
				else
					DroneDirection = DroneDirection.NextDirection();
				break;
			case West:
				if (A.CanMoveHere(x,y-1))
					y = y-1;	
				else
					DroneDirection = DroneDirection.NextDirection();
				break;
		}
	}
	
	//Returns the droneidentifier and the position of the drone as well as the direction the drone is facing
	public String toString() {
		return "Drone " + DroneIdentifier + " is at position " + x + "," + y  + ". Facing:" + DroneDirection.toString() + "\n";
	}
	
}
