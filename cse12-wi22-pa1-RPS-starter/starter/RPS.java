/**
 * Implementation of the RPS class. Akhil Pillai, CSE 12, January 11, 2022
 * Name: Akhil Pillai
 * ID: A16724533
 * Email: avpillai@ucsd.edu
 * Sources used: None
 * This file contains the win determination logic, as well as the executable portion of this Rock-Paper-Scissors implementation.
 */

import java.util.Scanner;
/**
 * This file contains the win determination logic, as well as the executable portion of this Rock-Paper-Scissors implementation.
 */
public class RPS extends RPSAbstract {
    
    /**
     * RPS class constructor. 
     * @param moves - list of possible moves, in order of advantage.
     * For example, for Rock-Paper-Scissors, moves would be {"scissors", "paper", "rock"}, since
     * scissors beats paper, paper beats rock, and rock beats scissors.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    /**
     * Takes the user move, the CPU move, and determines who wins.
     * @param playerMove - move of the player
     * @param cpuMove - move of the CPU
     * @return -1 for invalid move, 0 for tie, 1 for player win, 2 for cpu win
     */
    public int determineWinner(String playerMove, String cpuMove)
    {
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)) return INVALID_INPUT_OUTCOME;
        int pIndex = 0, cIndex = 0;
        for (int i = 0; i < possibleMoves.length; i++) {
            if (playerMove.equals(possibleMoves[i])) {
                pIndex = i;
            }
            if (cpuMove.equals(possibleMoves[i])) {
                cIndex = i;
            }
        }
        if ((pIndex + 1) % possibleMoves.length == cIndex) return PLAYER_WIN_OUTCOME;
        if ((cIndex + 1) % possibleMoves.length == pIndex) return CPU_WIN_OUTCOME;
        return TIE_OUTCOME;
    }

    /**
     * Main method that reads user input, generates cpu move, and plays game
     * 
     * @param args - arguments passed in from command line in String form
     */
    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work!
        String pMove = "";
        while (!pMove.equals(QUIT)) {
            System.out.println(PROMPT_MOVE);
            pMove = in.next();
            if (pMove.equals(QUIT)) continue;
            String cMove = game.genCPUMove();
            if (game.determineWinner(pMove, cMove) != INVALID_INPUT_OUTCOME) game.play(pMove, cMove);
            else {
                System.out.println(INVALID_INPUT);
                continue;
            }
        }
        game.end();
        in.close();
    }
}
