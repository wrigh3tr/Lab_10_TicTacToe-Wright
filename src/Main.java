import java.util.Scanner;



static boolean playAgain;
private static final int ROWS = 3;
private static final int COLS = 3;
private static String[][] board = new String[ROWS][COLS];
private static final Scanner console = new Scanner(System.in);

public static void main(String[] args) {

    do {
        clearBoard();
        String Player = "X";
        int moveCount = 0;
        boolean gameWon = false;

        while (moveCount < ROWS * COLS) {
            displayBoard();
            System.out.println("Player " + Player + "'s turn.");
            int row = SafeInput.getRangedInt(console, "Enter row (1-3)", 1, 3) - 1;
            int col = SafeInput.getRangedInt(console, "Enter col (1-3)", 1, 3) - 1;

            while (!isValidMove(row, col)) {
                System.out.println("Invalid move! Try again.");
                row = SafeInput.getRangedInt(console, "Enter row (1-3)", 1, 3) - 1;
                col = SafeInput.getRangedInt(console, "Enter col (1-3)", 1, 3) - 1;
            }

            board[row][col] = Player;
            moveCount++;

            if (moveCount >= 5 && isWin(Player)) {
                displayBoard();
                System.out.println("Player " + Player + " wins!");
                gameWon = true;
                break;
            }

            if (moveCount == ROWS * COLS && isTie()) {
                displayBoard();
                System.out.println("It's a tie!");
                break;
            }

            Player = Player.equals("X") ? "O" : "X";
        }

        playAgain = SafeInput.getYNConfirm(console, "Do you want to play again?");
    } while (playAgain);

    System.out.println("Thanks for playing!");
}


private static boolean isValidMove(int row, int col) {
    return board[row][col].equals(" ");
}



private static boolean isRowWin(String player) {
    for (int row = 0; row < ROWS; row++) {
        if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
            return true;
        }
    }
    return false;
}

private static boolean isColWin(String player) {
    for (int col = 0; col < COLS; col++) {
        if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
            return true;
        }
    }
    return false;
}

private static boolean isDiagonalWin(String player) {
    return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
            (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
}

private static boolean isWin(String player) {
    return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
}


private static boolean isTie() {
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            if (board[row][col].equals(" ")) return false;
        }
    }
    return true;
}




private static void clearBoard() {
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            board[row][col] = " ";
        }
    }
}

public static void displayBoard() {
    for (int row = 0; row < ROWS; row++) {
        for (int col = 0; col < COLS; col++) {
            System.out.print(board[row][col]);
            if (col < COLS - 1) System.out.print(" | ");
        }
        System.out.println();
        if (row < ROWS - 1) System.out.println("---------");
    }
}



