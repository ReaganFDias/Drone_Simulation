package drone_simulation;

import java.util.Random;

// Enum holding the different directions that drones can move in 
public enum Direction {
	North,East,South,West;
	
	// Gets a random direction the drone can move in 
	public static Direction GetRandomDirection() {
		Random random = new Random();
		return values()[random.nextInt(values().length)];
	}
	
	// Gets the next available direction the drone can move in
	public Direction NextDirection() {
		int change = Direction.values().length -1;
		if (this.ordinal() == change)
			return values()[0];
		else
			return values()[this.ordinal() + 1];
	}
}