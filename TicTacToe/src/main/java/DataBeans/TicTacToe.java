package DataBeans;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class TicTacToe {

    private char[][] board;
    private Map<Integer, Map<Character, Integer>> rowNumberToCharacterCount;
    private Map<Integer, Map<Character, Integer>> columnNumberToCharacterCount;
    private Map<Character, Integer> forwardDiagonalToCharacterCount;
    private Map<Character, Integer> backwardDiagonalToCharacterCount;
    Set<Character> symbolsInUse;
    int emptySpaces;

    public TicTacToe(int n, int m) {
        this.board = new char[n][m];
        this.rowNumberToCharacterCount = new HashMap<>();
        this.columnNumberToCharacterCount = new HashMap<>();
        this.forwardDiagonalToCharacterCount = new HashMap<>();
        this.backwardDiagonalToCharacterCount = new HashMap<>();
        this.symbolsInUse = new HashSet<>();
        this.emptySpaces = n*m;
    }

    public void printBoard() {

        int rows = board.length;
        int columns = board.length;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j< columns; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
