package drone_simulation;

public class ConsoleCanvas{
	private int BlockX;
	private int BlockY;
	private char [][] Block;

	// Constructor for the canvasd (outside walls of the arena)
	public ConsoleCanvas(int X, int Y) {
		BlockX = X;
		BlockY = Y;
		Block = new char[X][Y];
		for (int i=0;i < BlockX; i++) {
			for (int j=0;j < BlockY; j++) {
				if (i == 0 || i == BlockX -1 ) {
					Block[i][j] = '#';
				}
				else if (j == 0 || j == BlockY -1 ) {
					Block[i][j] = '#';
				}
				else {
					Block[i][j] = ' ';
				}
			}
		}
	}
	
	public void ShowIt(int x, int y, char ch) {
		Block[x+1][y+1] = ch;
	}
	
	// prints out the empty spaces within the arena
	public String toString() {
		String print = "";
		for (int i = 0; i< BlockX; i++) {
			for (int j = 0; j<BlockY; j++) {
				print += Block[i][j] + " ";
			}
			print += "\n";
		}
	return print;
	}
}