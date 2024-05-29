import DataBeans.TicTacToe;
import DataBeans.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    TicTacToe ticTacToe;
    List<Player> playerList;
    boolean hasGameEnded;

    public void startGame(int rows, int columns) {

        this.ticTacToe = new TicTacToe(rows, columns);
        this.playerList = new ArrayList<>();
        this.hasGameEnded = false;
        ticTacToe.printBoard();
    }

    public Player addPlayer(String name, char symbol) {

        if(ticTacToe.getSymbolsInUse().contains(symbol)) {
            throw new IllegalArgumentException("Symbol already in use. Please use different symbol.");
        }

        Player player = new Player(name, symbol);
        playerList.add(player);
        ticTacToe.getSymbolsInUse().add(symbol);

        return player;
    }

    public void move(Player player, int x, int y) {

        char[][] board = ticTacToe.getBoard();

        if(hasGameEnded) {
            throw new IllegalArgumentException("Game has already ended.");
        }

        if(board[x][y]>='A' && board[x][y]<='Z') {
            throw new IllegalArgumentException("Invalid move.");
        }

        board[x][y] = player.getSymbol();
        ticTacToe.setEmptySpaces(ticTacToe.getEmptySpaces() - 1);
        ticTacToe.printBoard();

        if(checkIfPlayerHasWon(player.getSymbol(), x, y)) {
            hasGameEnded = true;
            System.out.println(player.getName() + " has Won!");
            return;
        }

        if(ticTacToe.getEmptySpaces()==0) {
            hasGameEnded = true;
            System.out.println("Draw!");
            return;
        }

        System.out.println("Next move");
    }

    public boolean checkIfPlayerHasWon(char symbol, int x, int y) {

        char[][] board = ticTacToe.getBoard();
        int sizeOfBoard = board.length;
        int countRow = 0;
        int countColumn = 0;
        int countForwardDiag = 0;
        int countBackwardDiag = 0;

        if(x + y == board.length) {
            countForwardDiag = ticTacToe.getForwardDiagonalToCharacterCount().getOrDefault(symbol, 0) + 1;
            ticTacToe.getForwardDiagonalToCharacterCount().put(symbol, countForwardDiag);
        }

        if(x == y) {
            countBackwardDiag = ticTacToe.getBackwardDiagonalToCharacterCount().getOrDefault(symbol, 0) + 1;
            ticTacToe.getBackwardDiagonalToCharacterCount().put(symbol, countBackwardDiag);
        }

        Map<Character, Integer> row = ticTacToe.getRowNumberToCharacterCount().getOrDefault(x+1, new HashMap<>());
        countRow = row.getOrDefault(symbol, 0) + 1;
        row.put(symbol, countRow);
        ticTacToe.getRowNumberToCharacterCount().put(x+1, row);

        Map<Character, Integer> column = ticTacToe.getColumnNumberToCharacterCount().getOrDefault(y+1, new HashMap<>());
        countColumn = column.getOrDefault(symbol, 0) + 1;
        column.put(symbol, countColumn);
        ticTacToe.getColumnNumberToCharacterCount().put(y+1, column);

        //System.out.println(countRow + " " + countColumn + " " + countForwardDiag + " " + countBackwardDiag);
        return countRow == sizeOfBoard || countColumn == sizeOfBoard || countForwardDiag == sizeOfBoard || countBackwardDiag == sizeOfBoard;
    }


    public static void main(String args[]) {

        Game game = new Game();

        game.startGame(3,3);

        Player sankha = game.addPlayer("Sankhadeep", 'X');
        Player anisha = game.addPlayer("Anisha", 'Y');


        game.move(sankha, 0, 0);
        game.move(anisha, 1, 1);

        game.move(sankha, 0, 2);
        game.move(anisha, 2, 2);

        game.move(sankha, 2, 0);
        game.move(anisha, 1, 0);

        game.move(sankha, 1, 2);
        game.move(anisha, 0, 1);

        game.move(sankha, 2, 1);

    }
}
