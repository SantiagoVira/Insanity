import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board b = new Board();
        int piece;
        boolean isRed = true;
        while (!b.isOver(isRed)) {
            System.out.println(b);

            do {
                System.out.printf("Move a %s piece\n> ", isRed ? "\u001B[31mRed\u001B[0m" : "\u001B[34mBlue\u001B[0m");
                piece = input.nextInt();
            } while (piece < 0 || piece >= 10 || !b.canMove(piece) || b.piece(piece).isRed() != isRed);

            b.move(piece);
            isRed = !isRed;
        }
        if (b.isWin()) System.out.println("You win!");
        System.out.println(b);


        // Try to solve
        // UNSOLVABLE WITH THESE RULES
//        Board firstBoard = new Board();
//        recursive_solve(firstBoard, true, -1, 0);

    }

    public static void recursive_solve(Board b, boolean isRed, int piece, int depth) {

        System.out.println(b);
        if (piece >= 0) b.move(piece);
        System.out.printf("Depth: %s, Red: %s, Piece Moved: %s\n", depth, isRed, piece);
        if (b.isOver(isRed)) {
            System.out.println("bad");
            return;
        }
        System.out.println(b + "\n");

        for (int i = 0; i < 10; i++) {
            if (b.piece(i) != Pegs.EMPTY.copy() && b.canMove(i) && b.piece(i).isRed() == isRed) {
                recursive_solve(new Board(b), !isRed, i, depth + 1);
            }
        }
        if (b.isWin()) {
            System.out.println("You win!");
            System.exit(1);
        }

    }

}