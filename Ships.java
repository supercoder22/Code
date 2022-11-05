import java.util.Scanner;
public class Ships{
    public static void main(String[] args){
        Scanner input = new Scanner (System.in);
        //create the matrix holding the ships locations
        String[][] bot = {
                {" ", "1", "2", "3", "4", "5", "6"},
                {"A", "x", ".", ".", ".", ".", "."},
                {"B", "x", ".", ".", ".", "y", "."},
                {"C", ".", ".", ".", ".", "y", "."},
                {"D", ".", ".", ".", ".", "y", "."},
                {"E", ".", ".", ".", ".", ".", "."},
                {"F", ".", ".", "z", "z", "z", "z"}
            };
        //create the players matrix 
        String[][] player = {
                {" ", "1", "2", "3", "4", "5", "6"},
                {"A", ".", ".", ".", ".", ".", "."},
                {"B", ".", ".", ".", ".", ".", "."},
                {"C", ".", ".", ".", ".", ".", "."},
                {"D", ".", ".", ".", ".", ".", "."},
                {"E", ".", ".", ".", ".", ".", "."},
                {"F", ".", ".", ".", ".", ".", "."}
            };
        //create counters to ensure that the ship sunk messages print only once
        int pbCount = 0;
        int destroyCount = 0;
        int bsCount = 0;
        //create boolean variable to stop the game from running once the player has won
        boolean won = false;
        while(!won){
            System.out.println("Enter a guess in the form 'A1'");
            String guess = input.next();

            //Split up the string answer and convert the letter into a number correlating to
            //the array the row expressed in the guess represents
            char x = guess.charAt(0);
            int guessRow= (int) x - 64;
            char y = guess.charAt(1);
            int guessCol = (int) y - 48;

            //check whether or not the guess matches with an ship location on the 
            //ship location board
            if(bot[guessRow][guessCol] == "x" || bot[guessRow][guessCol] == "y" || 
            bot[guessRow][guessCol] == "z"){
                //if it is a hit, remove the ship piece from the ship location baord, mark the
                //hit on the players board, and let the player know it was a hit
                bot[guessRow][guessCol] = ".";
                player[guessRow][guessCol] = "X";
                System.out.println(guess + " is a hit");
                //check if the ship has been sunk, and let the player know if it has
                //if this if statement has been satisfied before, do not run
                if(!matrixContains(bot, "x") && pbCount == 0){
                    System.out.println("You sunk the Patrol Boat");
                    pbCount++;
                }
                
                if(!matrixContains(bot, "y") && destroyCount == 0){
                    System.out.println("You sunk the Destroyer");
                    destroyCount ++;
                }
                
                if(!matrixContains(bot, "z") && bsCount ==0){
                    System.out.println("You sunk the Battleship");
                    bsCount ++;
                }
                //check if all the boats have been sunk and if they have, let the player know
                //they won and change the boolean variable so the game does not run again
                if(!matrixContains(bot, "x") && !matrixContains(bot, "y") &&
                !matrixContains(bot, "z")){
                    System.out.println("Game over, You Win!");
                    won = true;
                }
            //if the players guess does not match with a ship location, mark it on their board
            //and let them know
            } else {
                player[guessRow][guessCol] = "O";
                System.out.println(guess + " is a miss");
            }
            //print the players board so they can see what they have done
            printArray(player);
            System.out.println();
        }

    }
    //method for printing arrays
    public static void printArray(String[][] array){
        array[0][0] = " ";
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }
    //method to check is if a string array contains a specified value
    public static boolean matrixContains(String[][] array, String x){
        int stringCount = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] == x){
                    stringCount ++;
                }
            }
        }
     if(stringCount > 0){
        return true; 
     } else {
         return false;
     }
    }

}