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

        System.out.println(b);


        // Stop game if none of one color can move

    }
}