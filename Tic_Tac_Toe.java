import java.util.Scanner;

public class Tic_Tac_Toe {

    private static Scanner sc = new Scanner(System.in);

    public static void ConstBoard(int board[]) {
        System.out.println("Current State of the Board : \n\n");
        for (int i = 0; i < 9; i++) {
            if ((i > 0) && (i % 3 == 0)) {
                System.out.println("\n");
            }
            if (board[i] == 0) {
                System.out.print("_ ");
            }
            if (board[i] == -1) {
                System.out.print("X ");
            }
            if (board[i] == 1) {
                System.out.print("O ");
            }
        }
        System.out.println("\n\n");
    }

    public static void User1Turn(int board[]) {

        System.out.print("Enter X's Position from [1,2,3,....,9] : ");
        int pos = sc.nextInt();
        if (board[pos - 1] != 0) {
            System.out.println("This is a Wrong Move. Try again.");
            User1Turn(board);
        } else {
            board[pos - 1] = -1;
        }
    }

    public static void User2Turn(int board[]) {

        System.out.print("Enter O's Position from [1,2,3,....,9] : ");
        int pos = sc.nextInt();
        if (board[pos - 1] != 0) {
            System.out.println("This is a Wrong Move. Try again.");
            User2Turn(board);
        } else {
            board[pos - 1] = 1;
        }

    }

    public static int minmax(int board[], int player) {
        int x = analyzeboard(board);
        if (x != 0) {
            return x * player;
        }
        int pos = -1;
        int value = -2;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = player;
                int score = -minmax(board, player * -1);
                board[i] = 0;
                if (score > value) {
                    value = score;
                    pos = i;
                }
            }
        }
        if (pos == -1) {
            return 0;
        }
        return value;
    }

    public static void CompTurn(int board[]) {
        int pos = -1;
        int value = -2;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = 1;
                int score = -minmax(board, -1);
                board[i] = 0;
                if (score > value) {
                    value = score;
                    pos = i;
                }
            }
        }
        board[pos] = 1;
    }

    public static int analyzeboard(int board[]) {
        int cb[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 },
                { 2, 4, 6 } };

        for (int i = 0; i < 8; i++) {
            if (board[cb[i][0]] != 0 && board[cb[i][0]] == board[cb[i][1]] && board[cb[i][0]] == board[cb[i][2]]) {
                return board[cb[i][0]];
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        System.out.print("Enter 1 for Single Player, 2 for Multi-Player: ");
        int choice = sc.nextInt();
        int board[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        if (choice == 1) {
            System.out.println("Computer : O Vs. You : X");
            System.out.print("Enter to play 1(st) or 2(nd): ");
            int player = sc.nextInt();
            for (int i = 0; i < 9; i++) {
                if (analyzeboard(board) != 0) {
                    break;
                }
                if ((i + player) % 2 == 0) {
                    CompTurn(board);
                } else {
                    ConstBoard(board);
                    User1Turn(board);
                }
            }
        } else {
            for (int i = 0; i < 9; i++) {
                if (analyzeboard(board) != 0) {
                    break;
                }
                if (i % 2 == 0) {
                    ConstBoard(board);
                    User1Turn(board);
                } else {
                    ConstBoard(board);
                    User2Turn(board);
                }
            }
        }

        int x = analyzeboard(board);
        ConstBoard(board);
        if (x == 0) {
            System.out.println("Draw!\n");
        } else if (x == -1) {
            System.out.println("Player X Wins! O Loses!\n");
        } else if (x == 1) {
            System.out.println("Player O Wins! X Loses!\n");
        }

    }
}
