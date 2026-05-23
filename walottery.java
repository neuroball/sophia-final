import java.util.Scanner;

class walottery {
    int noOfGames;
    String inputType;

    public void getUserData() {
        System.out.println("WA LOTTERY SIMULATOR");
        System.out.println("How many games would you like to play?");
        System.out.println("Use (Q)uick Pick? Or enter games (m)anually? [qQmM]");
        Scanner scanner = new Scanner(System.in);
        this.noOfGames = scanner.nextInt();
        this.inputType = scanner.nextLine();

    }


    public void createGames() {
    }

}
// Write out program name "WA LOTTERY SIMULATOR"
// Write out the question "How many games would you like to play?"Wait for and then store user input in the 'noOfGames' variable.
// Write out the question "Use (Q)uick Pick? Or enter games (m)anually? [qQmM]"
// Wait for and then store user input in the 'inputType' variable.
// If variable 'inputType' contains 'q' in lower case, start loop to create array with six unique random numbers between 1 and 49.
//   For each game 'currentGame' from 1-'noOfGames'
//     Create variable 'gameNumbersSet' as an empty Set.
//     Loop
//       Create variable 'rand' and create a random number from 1-49.
//       Check if 'gameNumbersSet' contains 'rand'.
//         If not, add it to 'gameNumbersSet'.
//       Exit loop if 'gameNumbersSet' length is 6.
//     Convert 'gameNumbersSet' to an array and assign it to the current game.
//     Increment 'currentGame by 1

// If the variable 'inputType' contains 'm' in lower case, start a loop for each game.  For each game from 1-'noOfGames'    Read list of comma separated game numbers from user input and store it in 'gameNumbers' variable.
//     Clean input by removing spaces from 'gameNumbers' variable.
//     Clean input by removing commas and store it in the 'verifyInput' variable.
//     Check that the 'verifyInput' variable only contains numbers between 0-9 and nothing else.
//       If something else is found, throw an error and display the error message to only use numbers when entering games.
//     Break up the 'gameNumbers' variable by splitting it by commas into an array named 'gameNumbersArr'..
//     Verify length of 'gameNumbersArr' to be of length equaling 6.
//       If the length is not 6, throw an error and display an error message to the user saying that each game has exactly 6 numbers.
//     Assign 'gameNumbersArr' to the current game.
//       Increment 'currentGame by 1

// Create a drawnNumbers array of arrays of six integers.
// Start a loop to create the drawn lotto numbers:

//   For each game from 1-'noOfGames'
//     Create an empty Set 'drawnNumbersSet'.
//     Create a random number between 1 and 49 called 'draw'.
//     Check 'drawnNumbersSet' for the number in 'draw'.
//       If 'draw' doesn't exist in 'drawnNumbersSet'?
//         Add it.
//       Else
//         If it already exists go back to draw a number… 
//     Is the length of 'drawNumbersSet' 6?
//       No
//         Draw another number.
//       Yes
//         Convert it to an array called 'drawnNumbersArr'.
//         Push 'drawnNumbersArr' to 'drawnNumbers.
//    
// Define double variable 'moneySpent'.
// Define 'numbersWonLookup' as array of int and set it to [0, 0, 3, 30, 1000, 1000000]Define int variable 'moneyWon'.
// Define int variable 'moneyWonThisGame

//   For each game from 1-'noOfGames'
//     Define int variable 'numbersWonPerGame' and set it to 0 (zero).
//     Create variable int currentGame
//     Add .50 to 'moneySpent'
//     Check for each of the numbers in the drawnNumbersArr[currentNumber] variable to the gameNumbersArr[currentNumber].
//       If there is a match, increment the 'numbersWonPerGame' variable.
//       If currentNumber is 6:
//         Lookup numbersWonPerGame in numbersWonLookup and store in 'moneyWonThisGame'.
//         Add the 'moneyWonThisGame' to 'moneyWon'
//   Print out Drawn numbers.  Print out Game numbers.
//   Print out moneyWonThisGame.

// Print out 'noOfGames', 'moneySpent', and 'moneyWon'
