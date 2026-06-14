import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.Collectors;

// This program simulates the Washington State Lottery. 
// It allows the user to define how many games they want to play, and whether they want to enter the play numbers manually
// or have the program generate them for them (Quick Pick).
// The program then generates the drawn numbers for each game played and compares them to the user's numbers.
// During the compare process the program keeps track of the money spent, the money wont, and the most right numbers in a single game.
// The data for each game is printed to the console and at the end of the game the statitics are printed out also.
class walottery {
    // set up class variables that are used across the program.
    int noOfGames;              // number of games the user wants to play
    String inputType;           // how the user wants to enter the game numbers
    int[][] gameNumbersArr;     // contains the game numbers that have been entered or created for the user
    int[][] drawnNumbersArr;    // contains the drawn numbers for each game
    int gameBound = 6;          // how many numbers are there in a single game
    int lotteryBound = 49;      // the max number that can be drawn in the lottery

    /**
     * getUserData - Prints out the game name, and prompts the user for the number of games the user would like to play.
     * It also prompts the user for whether they want to enter the game numbers (m)anually, or use (q)uick pick to have
     * the program generate the game numbers.
     */
    public void getUserData() {
        // Print out the game name and prompt the user for # of games
        System.out.println("WA LOTTERY SIMULATOR");
        System.out.println("How many games would you like to play?");

        // Read user input for the number of games and return an error message if the input is not a valid number.
        Scanner scanner = new Scanner(System.in);
        try {
            this.noOfGames = scanner.nextInt();
            scanner.nextLine(); 
        } catch (Exception e) {
            // Print out the error message and tell user what input is expected.
            System.out.println("Invalid input for number of games. Please enter a valid integer.");
            // Exit the program with an error.
            System.exit(1);
        }
        
        // Print out question for game number input type.
        System.out.println("Use (Q)uick Pick? Or enter games (m)anually? [qm]");

        // Read user input and return error message for invalid input.
        try {
            this.inputType = scanner.nextLine();
            // Validate that the input is not empty.
            if (this.inputType.length() == 0) {
                throw new Exception("Input type cannot be empty.");
            }
            // Validate that the input is a single character.
            if (this.inputType.length() > 1) {
                throw new Exception("Input type must be a single character.");
            }
            // Validate that the input is either 'q' or 'm'.
            if (!this.inputType.equalsIgnoreCase("q") && !this.inputType.equalsIgnoreCase("m")) {
                throw new Exception("Invalid input for game type.");
            }
        } catch (Exception e) {
            // Print out the error message and tell user what input is expected.
            System.out.println(e.getMessage() + " Please enter 'q' for Quick Pick or 'm' for Manual entry.");
            // Exit the program with an error.
            System.exit(1);
        }
    }
    
    /**
     * fillArrayWithRandomNumbers - Takes a 2D array as an argument and fills it with random unique numbers between 1 and 49.
     * The numbers for each array are limited to 6 numbers by the gameBound variable.
     * The total number of arrays filled is limited by the noOfGames variable.
     * 
     * @param targetArr
     */
    public void fillArrayWithRandomNumbers(int[][] targetArr) {
        // Create a random number generator.
        Random rand = new Random();

        // Loop for each game in the noOfGames variable.
        for (int currentGame = 0; currentGame < this.noOfGames; currentGame++) {
            // Create a hash set to store the numbers in for the current game.
            Set<Integer> gameNumbersSet = new HashSet<Integer>();
            // Loop until the set has 6 unique numbers in it.
            while (true) {
                // Generate a random number between 1 and 49 and add it to the set.
                int n = rand.nextInt(lotteryBound) + 1;
                // Add the number to the set. Duplicate numbers will not be added to the set.
                gameNumbersSet.add(n);

                // Are there six numbers in the set?
                if (gameNumbersSet.size() == gameBound) {
                    // yes, let's leave the inner loop.
                    break;
                }
            }

            // Convert the set of numbers to an array and store it in the targetArr variable for the current game.
            targetArr[currentGame] = gameNumbersSet.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        }
    }

    /**
     * fillGamesWithManualInput - Prompt the user to enter six comma separated game numbers for each game.
     */
    public void fillGamesWithManualInput() {
        // Print out an empty line for spacing.
        System.out.println("");
        
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Loop through each game within the noOfGames variable
        for (int currentGame = 0; currentGame < this.noOfGames; currentGame++) {
            try {
                // Print out another empty line between each game for spacing and then prompt the user to enter the current game numbers.
                System.out.println("");
                System.out.println(String.format("Please enter six comma separated lotto game numbers (1-49) for each game %d.%n", currentGame + 1));

                // Read the user input and store it.
                String gameNumbers = scanner.nextLine();
                // Remove all whitespace characters from the input.
                String gameNumbersTrimmed = gameNumbers.replaceAll("\\s+", "");

                // Ensure the input is valid by verifying it only contains numbers and commas.
                boolean validContent = gameNumbersTrimmed.matches("[[0-9],]+");
                // Is the input valid?
                if (!validContent) {
                    // No, let's throw an error.
                    throw new Exception("The input only can contain numbers [0-9] and commas.");
                }

                // Split the input at the commas, convert them into numbers, and store them into a set to ensure that they are unique.
                Set<Integer> gameNumbersSet = Arrays.stream(gameNumbersTrimmed.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
                
                // Test that the numbers are between 1 and 49.
                boolean validRange = gameNumbersSet.stream()
                    .allMatch(n -> n >= 1 && n <= 49);
                
                // Does the input contains only numbers inside of the range?
                if (!validRange) {
                    // No, let's throw an error.
                    throw new Exception("The input can only contain numbers between 1 and 49.");
                }

                // Are there six unique numbers?
                if (gameNumbersSet.size() != 6) {
                    // No, let's throw an error.
                    throw new Exception("The input needs to be six non repeating numbers for each game.");
                }

                // Convert the set to an array and store it in gameNumbersArr for the current game.
                gameNumbersArr[currentGame] = gameNumbersSet.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
            } catch (Exception e) {
                // If an error is thrown, print out the error message.
                System.out.println(e.getMessage());
                // Exit the program with an error.
                System.exit(1);
            }
        }
    }

    /**
     * fillGamesWithQuickPick - Helper method to fill the gameNumbersArr with random numbers for the users selected games.
     */
    public void fillGamesWithQuickPick() {
        fillArrayWithRandomNumbers(this.gameNumbersArr);
    }

    /**
     * createGames - Helper method to create the game and drawn numbers arrays.
     * The gameNumbersArray will then be filled with numbers for each game.
     */
    public void createGames() {
        this.gameNumbersArr = new int[this.noOfGames][6];
        this.drawnNumbersArr = new int[this.noOfGames][6];

        // Did the user select quick pick?
        if (this.inputType.equalsIgnoreCase("q")) {
            // Yes, call the method that will fill the gameNumbersArr programmatically.
            fillGamesWithQuickPick();
        // Did the user select manual input?
        } else if (this.inputType.equalsIgnoreCase("m")) {
            // Yes, call the method that will prompt the user to fill the gameNumbersArr.
            fillGamesWithManualInput();
        }   
    }

    /**
     * createDrawnNumbers - Helper method that will fill the drawnNumbersArr programmatically.
     */
    public void createDrawnNumbers() {
        fillArrayWithRandomNumbers(this.drawnNumbersArr);
    }

    /**
     * checkLotteryNumbers - Will compare the game numbers to the drawn numbers for all games.
     * It will also keep track of the money spent, the money wont, and the most right numbers per game.
     */
    public void checkLotteryNumbers() {
        // Lookup array for the money won based on the matching numbers.
        // Entry 0 is zero, as zero matching numbers wins nothing.
        // Entry 1 is zero, as one matching number wins nothing.
        // Entry 2 is zero, as two matching numbers wins nothing.
        // Entry 3 is 3, as three matching numbers wins $3.
        // Entry 4 is 30, as four matching numbers wins $30.
        // Entry 5 is 1000, as five matching numbers wins $1000.
        // Entry 6 is 1000000, as six matching numbers wins $1000000.
        int[] numbersWonLookup = {0, 0, 0, 3, 30, 1000, 1000000};

        // Set up variables that keep track of the money spent, won, and the most numbers won in per game.
        double moneySpent = 0.0;
        int moneyWonTotal = 0;
        int mostNumbersWon = 0;

        // Loop through each game in the noOfGames variable.
        for (int currentGame = 0; currentGame < this.noOfGames; currentGame++) {
            // set up variables for the current game
            // How many matching numbers have their been for this game?
            int numbersWonPerGame = 0;
            // How much money has been won for this game?
            int moneyWonThisGame = 0;
            // Increase the money spent by 50 cents for each game.
            moneySpent += 0.5;

            // Loop through each of the drawn numbers of the current game.
            for (int number : drawnNumbersArr[currentGame]) {
                // Check if the current drawn number matches a number in the game array of the current game.
                boolean isMatch = Arrays.stream(gameNumbersArr[currentGame])
                    .anyMatch(n -> n == number);
                // Is there a match?
                if (isMatch) {
                    // Yes, increase numbersWonPerGame variable by one.
                    numbersWonPerGame++;
                }
            }

            // Is the number won in this game larger than the numbers won in previous games?
            if (numbersWonPerGame > mostNumbersWon) {
                // Yes, update the mostNumbersWon variable to the current games numbers won.
                mostNumbersWon = numbersWonPerGame;
            }
            // Determine the money won for this game by looking up the numbersWonPerGame value
            // in the numbersWonLookup array and assign it to moneyWonThisGame.
            moneyWonThisGame = numbersWonLookup[numbersWonPerGame];
            // Increase the moneyWonTotal variable by the money won in this game.
            moneyWonTotal += moneyWonThisGame;

            // Print out the statistics for the current game.
            System.out.println(String.format("Your numbers: %s.", Arrays.toString(gameNumbersArr[currentGame])));
            System.out.println(String.format("The drawn numbers: %s", Arrays.toString(drawnNumbersArr[currentGame])));
            System.out.println(String.format("Matching numbers: %d", numbersWonPerGame));
            System.out.println(String.format("Money won this game: $%d %n", moneyWonThisGame));
        }

        // Print out the statistics for all games.
        System.out.println(String.format("Total money spent: $%.2f", moneySpent));
        System.out.println(String.format("Total money won: $%d", moneyWonTotal));
        System.out.println(String.format("Most numbers won in a single game: %d", mostNumbersWon));
    }

    /**
     * main - Entry point of the program.
     */
    public static void main(String[] args) {
        // create the lottery object
        walottery lottery = new walottery();

        // Call the getUserData method.
        lottery.getUserData();
        // Call the createGames method.
        lottery.createGames();
        // Call the createDrawnNumbers method.
        lottery.createDrawnNumbers();
        // Call the checkLotteryNumbers method.
        lottery.checkLotteryNumbers();

        // Exit the program with a success status.
        System.exit(0);
    }
}

