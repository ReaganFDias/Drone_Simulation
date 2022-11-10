package drone_simulation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class DroneArena implements Serializable{
	private int ArenaLength = 3;
	private int ArenaWidth = 3;
	Random randomGenerator;
	ArrayList<Drone> DroneList;
	private int NumberOfDrones;
	private int SizeOfArena = ArenaLength * ArenaWidth; 
	
	//Constructor for the drone Arena
	DroneArena(int ArenaLength, int ArenaWidth){
		this.ArenaLength = ArenaLength;
		this.ArenaWidth = ArenaWidth;
		randomGenerator = new Random();
		DroneList = new ArrayList<Drone>();
	
	}
	
	//Shows all the drones onto the terminal
	public void ShowDrones(ConsoleCanvas c) {
		for (Drone d: DroneList) {
			d.DisplayDrone(c);
		}
	}
	
	//File returns true or fales depending on if a drone can move into an available space
	public boolean CanMoveHere(int X, int Y) {
		if (GetDroneAt(X,Y) != null || X >= ArenaLength || Y >= ArenaWidth || X < 0 || Y < 0) {
			return false;
		} 
		else {
			return true;
		}
	}
	
	//Gets the drone at a certain position 
	public Drone GetDroneAt(int x, int y) {
		Drone Temporary = null;
		for (Drone Example: DroneList) {
			if (Example.CheckSpaceOccupied(x,y) == true) {
				Temporary = Example;
			}
		}
		return Temporary;
	}
	
	//Getter that gets the Arena Length 
	public int GetArenaLength() {
		return ArenaLength;
	}
	
	//Getter that gets the Arena Width
	public int GetArenaWidth() {
		return ArenaWidth;
	}

	//Adds the drone to the arena at a random position 
	public void addDrone(){		int RandomY;
		int RandomX;
		do {
			RandomY = randomGenerator.nextInt(ArenaWidth);
			RandomX = randomGenerator.nextInt(ArenaLength);
		} while (GetDroneAt(RandomX,RandomY) != null);
		Drone anyplace = new Drone(RandomX,RandomY,Direction.GetRandomDirection());
		DroneList.add(anyplace);
	}
		
	//Returns a string which is the size of the Arena
	public String toString() {
		String Position = "";
		if (DroneList.isEmpty() == false) {
			Position = "";
			Position += "The size of the Arena is: " + SizeOfArena;
			for (int i=0; i<DroneList.size(); i++) {
				Position += "\n" + DroneList.get(i).toString();
			}
		}
		return Position;
		
		}
	
	//Moves all the drones given an Arena
	public void MoveAllDrones(DroneArena A) {
		for (Drone d: DroneList)
			d.TryToMove(A);
	}
		
		
	}
	