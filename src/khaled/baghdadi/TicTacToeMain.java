package khaled.baghdadi;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeMain {

private static char playersymbol,computersymbol;
private static int row,col,player;
static int human = TicTacToe.HUMAN;
static int computer = TicTacToe.COMPUTER;
static int empty = TicTacToe.EMPTY;
static boolean flag  =true; //test if game is still going
static Scanner scan;
static TicTacToe game = new TicTacToe();// Creating a TicTacToe object named m
static Random random; //Used to generate computer move
static String input;
static boolean setSymbolFirstTime=true;
static boolean symbolcheck=true;
	public static void main(String[] args) {
		System.out.println("To play this game: \nFirst enter your column (a,b,c) where leftmost column is a");
		System.out.println("Second enter the row number (1,2,3) where 3 is the  upper row");
		System.out.println("Then enter a space with your symbol (x or o)");
		System.out.println("A sample input is: a3 X");
		System.out.println("Note that your symbol will be set from the first time and you won't be able to change it even if you attempt to.");
		random = new Random(); 
		
		 scan = new Scanner(System.in);
		 System.out.print("Enter a move >: ");
			input=scan.nextLine(); //take input
			//Changing input to lowerCase
			input=input.toLowerCase();
			
		//Continuously taking input until somebody wins or a draw is reached
		while(flag) {
			
			if(input.length()==4&&isAMove(input))  {
				setMove(input);	
				}
			//Check input to start a new game
			if(input.equals("new")) {
				game.clearBoard();
				dump(game);
				
				} //end of if statement to new game
			
			
			if(input.equals("quit")) {
				quit(game);
				//End while loop and quit the game
			}
			
			

			//Print Current state of the board
			if(input.equals("dump")) {
				dump(game); //calling dump method to print board	
		}
			
		
			if(flag) {
			System.out.print("Enter a move >: ");
			input=scan.nextLine(); //take input
			//Changing input to lowerCase
			input=input.toLowerCase();
			} //If game still going take input
			
		}	//end of while statement
	} //end of main method
	
	
public static void dump(TicTacToe m) {
	for( int i = 0; i < 3; i++ ){
		for( int j = 0; j < 3; j++ ){
		int currentstate=m.getBoard()[i][j];
		switch(currentstate) {
		case 0: {System.out.print(playersymbol+"   ");break;}
		case 1: {System.out.print(computersymbol+"   ");break;}
		case 2: {System.out.print("-   ");break;}
		
		}
		//ended switch above
		} //ended inner for loop
		//Printing new line
		System.out.println();
	} //ended outer loop
	
}//end dump method 

public static void quit(TicTacToe m) {
flag=false;
m.clearBoard();
}

public static boolean isAMove(String input) {
	//checking if the input is a valid move
	char col =input.charAt(0);
	int row = input.charAt(1)-48;
	char piece = input.charAt(3);
if((col=='a'||col=='b'||col=='c')&&(row==1||row==2||row==3)&&(piece=='x'||piece=='o')) {
	return true;
}
return false;
} //end of isAMove method 



public static int getColumn (String input) {
return input.charAt(0)-97;
} //getColumn method ends 


public static int getRow (String input) {
int val= input.charAt(1)-48-1;

return val; 
} //getRow method ends



public static void setSymbol(boolean firsttime, String input)
{   if(firsttime)
	if(symbolcheck&&input.charAt(3)=='x') {playersymbol='x';computersymbol='o';}
	else if(symbolcheck&&input.charAt(3)=='o') { playersymbol='o';computersymbol='x';}
	symbolcheck=false;

}//setSymbol method ends




public static void setMove(String localinput) {
	
	row=getRow(localinput);
	col = getColumn(localinput);
	setSymbol(setSymbolFirstTime,localinput);
	
	//This is to make 3 the first row and 1 the last row
	if(row==2) row=0;
	else {
		if(row==0)row=2;
		else {row=1;}
	}
	
	//Get row and column to set human move
	

	
//The while loop is to keep taking input from user until he enter a valid move
//A move is valid if format is correct and it is an empty space
while(! (game.playMove(human, row, col)))
{
	System.out.println("Wrong move");
	localinput=scan.nextLine();
	row=getRow(localinput);
	col = getColumn(localinput);
	setSymbolFirstTime=false;
	//This is to make 3 the first row and 1 the last row
		if(row==2) row=0;
		else {
			if(row==0)row=2;
			else {row=1;}
		}

} // End of while loop

//setting computer's move
while(!game.boardIsFull()&&!(game.playMove(computer, row, col))) {
/*     
 * row= random.nextInt(3);
 * col= random.nextInt(3);
 * 
 * 
 * 
 * */
	
	
	Best computerbest = game.chooseMove(computer,Integer.MIN_VALUE,Integer.MAX_VALUE); //Getting Computers optimal move
	row=computerbest.row; //Getting the row of optimal move
	col= computerbest.column; //Getting column of optimal move
	
} 
if(game.isAWin(human)) { 
System.out.println("You WIN"); 
dump(game);
System.out.println("New or Quit");
input=scan.nextLine();
while(!input.toLowerCase().equals("new")&&!input.toLowerCase().equals("quit")) {
System.out.println("Enter new or quit only");
input=scan.nextLine();
}
}
if(game.isAWin(computer)) {System.out.println("Computer WINs");
dump(game);
System.out.println("New or Quit");
input=scan.nextLine();
while(!input.toLowerCase().equals("new")&&!input.toLowerCase().equals("quit")) {
System.out.println("Enter new or quit only");
input=scan.nextLine();
}
}
if(!game.isAWin(human)&&!game.isAWin(computer)&&game.boardIsFull()) { 
System.out.println("Draw");
dump(game);
System.out.println("New or Quit");
input=scan.nextLine();
while(!(input.equalsIgnoreCase("new"))&&!(input.equalsIgnoreCase("quit"))) {
System.out.println("Enter new or quit only");
input=scan.nextLine();
}

}

dump(game);
	
} //setMove method ends



} //class ends