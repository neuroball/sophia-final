import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.Collectors;

class walottery {
    int noOfGames;
    String inputType;
    int[][] gameNumbersArr;
    int[][] drawnNumbersArr;
    int[] numbersWonLookup = {0, 0, 3, 30, 1000, 1000000};
    int gameBound = 6;
    int lotteryBound = 49;

    public void getUserData() {
        System.out.println("WA LOTTERY SIMULATOR");
        System.out.println("How many games would you like to play?");
        System.out.println("Use (Q)uick Pick? Or enter games (m)anually? [qm]");
        Scanner scanner = new Scanner(System.in);
        try {
            this.noOfGames = scanner.nextInt();
            scanner.nextLine(); 
        } catch (Exception e) {
            System.out.println("Invalid input for number of games. Please enter a valid integer.");
            return;
        }
        
        try {
            this.inputType = scanner.nextLine();
            if (this.inputType.length() == 0) {
                throw new Exception("Input type cannot be empty.");
            }
            if (this.inputType.length() > 1) {
                throw new Exception("Input type must be a single character.");
            }
            if (!this.inputType.equalsIgnoreCase("q") && !this.inputType.equalsIgnoreCase("m")) {
                throw new Exception("Invalid input for game type.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Please enter 'q' for Quick Pick or 'm' for Manual entry.");
            return;
        }
    }
    
    public void fillArrayWithRandomNumbers(int[][] targetArr) {
        Random rand = new Random();

        for (int currentGame = 0; currentGame < noOfGames; currentGame++) {
            Set<Integer> gameNumbersSet = new HashSet<Integer>();
            while (true) {
                int n = rand.nextInt(lotteryBound);
                if (n > 0) {
                    gameNumbersSet.add(n);
                }
                if (gameNumbersSet.size() == gameBound) {
                    break;
                }
            }
            targetArr[currentGame] = gameNumbersSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }
    }

    public void fillGamesWithManualInput() {
        System.out.println("");
        
        Scanner scanner = new Scanner(System.in);
        for (int currentGame = 0; currentGame < noOfGames; currentGame++) {
            try {
                System.out.println("");
                System.out.println(String.format("Please enter six comma separated lotto game numbers (1-49) for each game %d.%n", currentGame + 1));

                String gameNumbers = scanner.nextLine();
                String gameNumbersTrimmed = gameNumbers.replaceAll("\\s+", "");

                boolean validContent = gameNumbersTrimmed.matches("[[0-9],]+");
                if (!validContent) {
                    throw new Exception("The input only can contain numbers [0-9] and commas.");
                }

                Set<Integer> gameNumbersSet = Arrays.stream(gameNumbersTrimmed.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
                
                if (gameNumbersSet.size() != 6) {
                    throw new Exception("The input needs to be six non repeating numbers for each game.");
                }

                gameNumbersArr[currentGame] = gameNumbersSet.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void fillGamesWithQuickPick() {
        fillArrayWithRandomNumbers(this.gameNumbersArr);
    }

    public void createGames() {
        this.gameNumbersArr = new int[this.noOfGames][6];
        this.drawnNumbersArr = new int[this.noOfGames][6];

        if (this.inputType.equalsIgnoreCase("q")) {
            fillGamesWithQuickPick();
        } else if (this.inputType.equalsIgnoreCase("m")) {
            fillGamesWithManualInput();
        }   
    }

    public void createDrawnNumbers() {
        fillArrayWithRandomNumbers(this.drawnNumbersArr);
    }

}




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
