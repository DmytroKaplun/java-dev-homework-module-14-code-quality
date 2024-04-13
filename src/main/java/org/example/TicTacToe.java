package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class TicTacToe {
    private static final Logger logger = Logger.getLogger(TicTacToe.class.getName());

    public void run() {
        Scanner scanner = new Scanner(System.in);

        char[][] board = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };
        printBoard(board);
        setEmptyBoard(board);
        logger.info("Enter box number to select. Enjoy!");

        boolean isFinished = false;
        while(!isFinished) {
            playerTurn(board, scanner);
            isFinished = isGameFinished(board);

            if (!isFinished) {
                computerTurn(board);
                printBoard(board);
                isFinished = isGameFinished(board);
                if (!isFinished) {
                    logger.info("It's your turn to move.");
                }
            }
        }
        scanner.close();
    }

    private void playerTurn(char[][] board, Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int userInput = scanner.nextByte();

                if (userInput > 0 && userInput < 10) {
                    if (isSpaceEmpty(userInput, board)) {
                        placeSymbol(board, userInput, 'X');
                        break;
                    } else {
                        logger.warning("That position is already in use. Enter another.");
                    }
                }
            } else {
                logger.warning("Invalid input. Please enter numbers only.");
                scanner.next();
            }
        }
    }

    private void computerTurn(char[][] board) {
        byte randomNumber;
        do {
            randomNumber = (byte) (Math.random() * (9 - 1 + 1) + 1);
        } while (!isSpaceEmpty(randomNumber, board));
        placeSymbol(board, randomNumber, 'O');
    }

    private boolean isGameFinished(char[][] board) {
        if (isWinner(board, 'X')) {
            printBoard(board);
            logger.info("You won the game!\nThanks for playing!");
            return true;
        }

        if (isWinner(board, 'O')) {
            printBoard(board);
            logger.info("You lost the game!\nTry once more");
            return true;
        }

        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    return false;
                }
            }
        }
        logger.info("The game ended in a tie!");
        return true;
    }

    private boolean isWinner(char[][] board, char symbol) {
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private void placeSymbol(char[][] board, int num, char symbol) {
        int row = (num - 1) / 3;
        int col = (num - 1) % 3;
        board[row][col] = symbol;
    }

    private void printBoard(char[][] board) {
        for (String s : Arrays.asList(board[0][0] + " | " + board[0][1] + " | " + board[0][2],
                "---------", board[1][0] + " | " + board[1][1] + " | " + board[1][2], "---------",
                board[2][0] + " | " + board[2][1] + " | " + board[2][2])) {
            logger.info(s);
        }
    }

    private void setEmptyBoard(char[][] board) {
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
    }

    private boolean isSpaceEmpty(int input, char[][] board) {
        int row = (input - 1) / 3;
        int col = (input - 1) % 3;
        return board[row][col] == ' ';
    }
}
