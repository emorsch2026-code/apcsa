import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RockPaperScissors {

    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.startGame();
    }
}

//  Stores the player's name and their win/loss record, selectAction() gets input from human or picks randomly for the computer.
class Player {
    private String name;
    private boolean isComputer;
    private int wins;
    private int losses;

    public Player(String name, boolean isComputer) {
        this.name = name;
        this.isComputer = isComputer;
        this.wins = 0;
        this.losses = 0;
    }

    // Returns "Rock", "Paper", or "Scissors"
    public String selectAction(Scanner scanner) {
        if (isComputer) {
            // Computer picks a random number 0-2 and maps it to an action
            String[] choices = {"Rock", "Paper", "Scissors"};
            Random rand = new Random();
            return choices[rand.nextInt(3)];
        }
        // Human types 1, 2, or 3
        System.out.println("Pick your move:  1) Rock   2) Paper   3) Scissors");
        System.out.print("Your choice: ");
        String input = scanner.nextLine().trim();

        if (input.equals("1")) return "Rock";
        if (input.equals("2")) return "Paper";
        if (input.equals("3")) return "Scissors";

        // Default to Rock if they typed something unexpected
        System.out.println("(Invalid input — defaulting to Rock)");
        return "Rock";
    }

    // Getters
    public String getName()    { return name; }
    public int getWins()       { return wins; }
    public int getLosses()     { return losses; }

    // Setters: used by FileManager when loading saved data
    public void setWins(int w)   { this.wins = w; }
    public void setLosses(int l) { this.losses = l; }

    public void addWin()  { wins++; }
    public void addLoss() { losses++; }
}

class FileManager {
    // The names of the 3 text files
    private static final String STATS_FILE   = "folder/player_stats.txt"; // FILE 1
    private static final String HISTORY_FILE = "folder/game_history.txt"; // FILE 2
    private static final String SCORES_FILE  = "folder/scores.txt";       // FILE 3

    // FILE 1: player_stats.txt
    // SAVE: writes name, wins, losses to the file
    public void savePlayerStats(Player player) {
        try {
            // FIX: Read all existing lines, update the matching player, then rewrite.
            // This prevents wiping out other players' stats stored in the same file.
            java.util.List<String> lines = new java.util.ArrayList<>();
            boolean found = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(STATS_FILE))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3 && parts[0].equals(player.getName())) {
                        // Overwrite this player's line with updated stats
                        lines.add(player.getName() + "," + player.getWins() + "," + player.getLosses());
                        found = true;
                    } else {
                        lines.add(line); // keep other players' lines unchanged
                    }
                }
            } catch (IOException e) {
                // File doesn't exist yet - that's fine, we'll create it below
            }

            if (!found) {
                // First time saving this player - append a new line
                lines.add(player.getName() + "," + player.getWins() + "," + player.getLosses());
            }

            // Rewrite the whole file with the updated list
            PrintWriter writer = new PrintWriter(new FileWriter(STATS_FILE));
            for (String l : lines) {
                writer.println(l);
            }
            writer.close();
            System.out.println("[Saved] Player stats written to " + STATS_FILE);
        } catch (IOException e) {
            System.out.println("Could not save player stats.");
        }
    }

    // FILE 1: player_stats.txt
    // FIX: Search every line for a name match instead of only checking the first line.
    public void loadPlayerStats(Player player) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(player.getName())) {
                    player.setWins(Integer.parseInt(parts[1].trim()));
                    player.setLosses(Integer.parseInt(parts[2].trim()));
                    System.out.println("[Loaded] Found saved stats for " + player.getName());
                    return; // found - stop searching
                }
            }
            // Fell through without finding the player - that's fine, they start fresh
            System.out.println("[New Player] No saved stats found for " + player.getName() + " - starting fresh.");
        } catch (IOException e) {
            System.out.println("[New Player] No saved stats found - starting fresh.");
        }
    }

    // FILE 2: game_history.txt
    // APPEND: adds one line per round (never overwrites old rounds)
    public void appendRoundHistory(int roundNum, String humanMove,
                                   String computerMove, String result) {
        try {
            // true = append mode, so old rounds are kept
            PrintWriter writer = new PrintWriter(new FileWriter(HISTORY_FILE, true));

            String line = "Round " + roundNum + ": "
                        + "Human chose " + humanMove + ", "
                        + "Computer chose " + computerMove
                        + " -> " + result;

            writer.println(line);
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not write round history.");
        }
    }

    // FILE 3: scores.txt
    // SAVE: writes the current series win totals
    public void saveScores(int humanSeriesWins, int computerSeriesWins) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(SCORES_FILE));

            writer.println("Human Series Wins: " + humanSeriesWins
                         + "  |  Computer Series Wins: " + computerSeriesWins);

            writer.close();
            System.out.println("[Saved] Scores written to " + SCORES_FILE);
        } catch (IOException e) {
            System.out.println("Could not save scores.");
        }
    }

    // FILE 3: scores.txt
    public int[] loadScores() {
        int[] scores = {0, 0};  // default: both start at 0

        try {
            BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE));
            String line = reader.readLine();
            reader.close();

            if (line != null) {
                // Example line: "Human Series Wins: 4  |  Computer Series Wins: 2"
                // Split on "|" to get the two halves
                String[] halves = line.split("\\|");
                // Each half looks like "Human Series Wins: 4  "
                // Split on ":" to separate label from number
                scores[0] = Integer.parseInt(halves[0].split(":")[1].trim());
                scores[1] = Integer.parseInt(halves[1].split(":")[1].trim());
                System.out.println("[Loaded] Previous scores found.");
            }
        } catch (IOException e) {
            System.out.println("[New Game] No saved scores found - starting at 0-0.");
        }

        return scores;
    }
}

//  CLASS: GameEngine
//  Controls the game loop. Asks players for moves, decides the winner, and tells FileManager when to save/load.
class GameEngine {

    private Player human;
    private Player computer;
    private FileManager fileManager;

    public GameEngine() {
        fileManager = new FileManager();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ROCK, PAPER, SCISSORS");
        System.out.println("Best of 3 Rounds");
        System.out.println("-----------------------------------");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";

        // Create the two players
        human    = new Player(name, false);
        computer = new Player("Computer", true);

        // FILE 1 + FILE 3: Load saved data at the start
        fileManager.loadPlayerStats(human);
        int[] savedScores = fileManager.loadScores();
        int humanSeriesWins    = savedScores[0];
        int computerSeriesWins = savedScores[1];

        System.out.println("\nWelcome back, " + human.getName() + "!");
        System.out.println("Your overall record - Wins: " + human.getWins()
                         + "  Losses: " + human.getLosses());
        System.out.println("Series score - You: " + humanSeriesWins
                         + "  Computer: " + computerSeriesWins);
        System.out.println();

        // Play best-of-3 rounds
        int humanRoundWins    = 0;
        int computerRoundWins = 0;
        int roundNumber       = 0;

        while (humanRoundWins < 2 && computerRoundWins < 2) {
            roundNumber++;
            System.out.println("--- Round " + roundNumber + " ---");

            String humanMove    = human.selectAction(scanner);
            String computerMove = computer.selectAction(null);

            System.out.println("Computer chose: " + computerMove);

            // Decide who won this round
            String result = decideWinner(humanMove, computerMove);

            if (result.equals("You WIN!")) {
                humanRoundWins++;
                System.out.println(">>> " + result + " <<<\n");
            } else if (result.equals("You LOSE.")) {
                computerRoundWins++;
                System.out.println(">>> " + result + " <<<\n");
            } else {
                System.out.println(">>> " + result + " - play again! <<<\n");
                roundNumber--;  // Tie: don't count this round
            }

            // FILE 2: Append this round's result to history
            fileManager.appendRoundHistory(roundNumber, humanMove, computerMove, result);
        }

        // Series result -
        if (humanRoundWins == 2) {
            System.out.println("YOU WIN THE SERIES! Nice work!");
            human.addWin();
            computer.addLoss();
            humanSeriesWins++;
        } else {
            System.out.println("Computer wins the series. Better luck next time!");
            human.addLoss();
            computer.addWin();
            computerSeriesWins++;
        }

        // FILE 1 + FILE 3: Save updated data at the end
        fileManager.savePlayerStats(human);
        fileManager.saveScores(humanSeriesWins, computerSeriesWins);

        System.out.println("\nFinal record - Wins: " + human.getWins()
                         + "  Losses: " + human.getLosses());
        System.out.println("------------------------------------------");

        scanner.close();
    }

    // Returns "You WIN!", "You LOSE.", or "It's a TIE."
    private String decideWinner(String humanMove, String computerMove) {
        if (humanMove.equals(computerMove)) {
            return "It's a TIE.";
        }

        if ((humanMove.equals("Rock")     && computerMove.equals("Scissors")) ||
            (humanMove.equals("Paper")    && computerMove.equals("Rock"))     ||
            (humanMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "You WIN!";
        }

        return "You LOSE.";
    }
}
