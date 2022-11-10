package drone_simulation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//import javax.lang.model.util.ElementScanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class DroneInterface {
	
	private Scanner s;								// scanner used for input from user
    private DroneArena myArena;						// arena in which drones are shown
    JFileChooser chooser = new JFileChooser();
    
    /**
    	 * constructor for DroneInterface
    	 * sets up scanner used for input and the arena
    	 * then has main loop allowing user to enter commands
     */
    
    public DroneInterface() {
    	 s = new Scanner(System.in);			// set up scanner for user input
    	 myArena = new DroneArena(3, 3);	// create arena of size 20*6
    	 int UserArenaLength = 0;
    	 int UserArenaWidth = 0;
    	
        char ch = ' ';
        do {
        	System.out.print("Enter (A)to add drone, get (I)nformation or (X) to exit > ");
        	ch = s.next().charAt(0);
        	s.nextLine();
        	switch (ch) {
    			case 'A' :
    			case 'a' :
        					myArena.addDrone();	// add a new drone to arena
        					break;
        		case 'I' :
        		case 'i' :
        					System.out.print(myArena.toString()); // displays the information about each drone within the arena
							break;
        		case 'D' : 
        		case 'd' : 
        					DoDisplay(); // displays the drones within the arena onto the terminal
        					break;
        					
        		case 'M' :
        		case 'm' :
        					if (myArena.DroneList.isEmpty()== false) { // Moves all the drones 
        						myArena.MoveAllDrones(myArena);
        						DoDisplay();
        					}
        					else if (myArena.DroneList.isEmpty() == true) {
        						System.err.println("Insert Drones in order to Move");
        					}
        					
        		case 'q':
        		case 'Q':
		        			if (myArena.DroneList.isEmpty() == false) {
		        				for (int i=0;i<10;i++) {
		        					myArena.MoveAllDrones(myArena);
									DoDisplay();
									System.out.println(myArena.toString());
									try {
										TimeUnit.MILLISECONDS.sleep(200);
									} catch (InterruptedException e) {
										System.err.format("IOException: %s%n",e);
									}
		        				}
								DroneInterface r = new DroneInterface();
								break;
					}
		        			
        		case 'S':
        		case 's':
        					SaveFile();
        					break;
        					
        		case 'L':
        		case 'l':
        					LoadFile();
        					break;
		        			
        		case 'N':
        		case 'n':
		        			
		        			System.out.print("Enter the dimensions for your new arena:\n");
		        			System.out.print("Enter the length of the Arena:");
		        			try {
		        				UserArenaLength = s.nextInt();
		        				while (UserArenaLength < 0) {
		        					//System.err.println("Invalid Input!");
		        					System.out.println("\n Enter the length of the Arena:");
		        					UserArenaLength = s.nextInt();
		        				}
		        			}
		        			catch (Exception e) {
		        				System.err.println("Invalid Input please try again.");
		        				System.out.println("\n Enter the length of the Arena:");
		        				UserArenaLength = s.nextInt();
		        			}
		        			
		        			System.out.print("Enter the width of the Arena:");
		        			try {
		        				UserArenaWidth = s.nextInt();
		        				while (UserArenaWidth < 0) {
		        					System.err.println("Invalid Input!");
		        					System.out.println("\n Enter the width of the Arena:");
		        					UserArenaWidth = s.nextInt();
		        				}
		        			}
		        			catch (Exception e) {
		        				System.err.println("Invalid Input please try again.");
		        				System.out.println("\n Enter the width of the Arena:");
		        				UserArenaWidth = s.nextInt();
		        			}
		        			myArena = new DroneArena(UserArenaLength, UserArenaWidth);
		        			break;
        					
        		case 'X' :
        		case 'x' : 	ch = 'X';				// when X detected program ends
        					break;
        	}
    		} while (ch != 'X');						// test if end
        
       s.close();									// close scanner
    }
    
	// Saves the arena and the drones to a file
    void SaveFile() {
		JFrame frame = new JFrame();
    	int returnVal = chooser.showSaveDialog(frame);
    	if (returnVal == JFileChooser.APPROVE_OPTION) {
    		File selFile = chooser.getSelectedFile();
    		if(!selFile.getAbsolutePath().endsWith(".ser")) {
    			selFile = new File(selFile.getAbsolutePath() + ".ser");
    		}
    		try
    		{
    			FileOutputStream fileout = new FileOutputStream(selFile);
    			ObjectOutputStream out = new ObjectOutputStream(fileout);
    			out.writeObject(myArena);
    			out.close();
    			fileout.close();
    		}
    		catch(IOException a){
    			System.out.println("Error");
    		}
    		System.out.println("saved your file");
    			
    	}
    	
    }
    
	//loads a '.ser' file and creates the arena's and drone positions from the file
    void LoadFile() {
    	int returnValue = chooser.showOpenDialog(null);
    	if (returnValue == JFileChooser.APPROVE_OPTION) {
    		File selFile = chooser.getSelectedFile();
    		if(selFile.isFile()) {
	    		try
	    		{
	    			FileInputStream filein = new FileInputStream(selFile);
	    			ObjectInputStream in = new ObjectInputStream(filein);
	    			myArena = (DroneArena) in.readObject();
	    			in.close();
	    			filein.close();
	    		}
	    		catch(IOException | ClassNotFoundException e){
	    			System.out.println("Error");
	    		}
	    	System.out.println("loaded your file");
    		}
    			
    	}
    }
    
    
    void DoDisplay() {
		ConsoleCanvas feild = new ConsoleCanvas(myArena.GetArenaLength()+2, myArena.GetArenaWidth() + 2);
			myArena.ShowDrones(feild);
			System.out.println(feild.toString());
		}
    
	public static void main(String[] args) {
		DroneInterface r = new DroneInterface();	// just call the interface
	}
	
}

