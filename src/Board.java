import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board {
    private final ArrayList<Peg> pegs;

    public Board() {
        pegs = new ArrayList<>();
        fillBoard();
    }

    public String toString() {
        return "| " + pegs.stream().map(Object::toString)
                .collect(Collectors.joining(" | ")) + " |\n" +
                "| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |";
    }

    public int move(int idx) {
        // 0 for no movement, 1 for move 1 step, 2 for 2 steps
        Peg p = piece(idx);
        if (!canMove(idx)) {
            // Can't move
            return 0;
        }
        if (p.getMovesRight() && piece(idx + 1) == Pegs.EMPTY.copy()) {
            pegs.set(idx + 1, pegs.set(idx, Pegs.EMPTY.copy()));
            return 1;
        } else if (!p.getMovesRight() && piece(idx - 1) == Pegs.EMPTY.copy()) {
            pegs.set(idx - 1, pegs.set(idx, Pegs.EMPTY.copy()));
            return 1;
        } else if (p.getMovesRight() && piece(idx + 2) == Pegs.EMPTY.copy()) {
            pegs.set(idx + 2, pegs.set(idx, Pegs.EMPTY.copy()));
            return 2;
        } else if (!p.getMovesRight() && piece(idx - 2) == Pegs.EMPTY.copy()) {
            pegs.set(idx - 2, pegs.set(idx, Pegs.EMPTY.copy()));
            return 2;
        }

        return 0;
    }

    public boolean isOver(boolean isRed) {
        boolean blueCanMove = false;
        boolean redCanMove = false;
        for (int i = 0; i < pegs.size(); i++) {
            if (canMove(i)) {
                if (piece(i).isRed()) {
                    redCanMove = true;
                } else {
                    blueCanMove = true;
                }
            }
        }
        return !isRed && !blueCanMove || isRed && !redCanMove;
    }

    public boolean isWin() {
        String win = "BBBB  RRRR";
        boolean isWin = true;
        for (int i = 0; i < pegs.size(); i++) {
            if (!piece(i).isSymbol(win.substring(i, i + 1))) isWin = false;
        }
        return isWin;
    }

    public boolean canMove(int idx) {
        // Pegs can jump over those of the same color
        Peg p = piece(idx);
        boolean wontLeaveBoard = !(idx == pegs.size() - 1 && p.getMovesRight() || idx == 0 && !p.getMovesRight() || p == Pegs.EMPTY.copy());
        boolean canJumpRight = p.getMovesRight() && (
                idx + 1 <= pegs.size() - 1 && piece(idx + 1) == Pegs.EMPTY.copy() ||
                        idx + 2 <= pegs.size() - 1 && piece(idx + 2) == Pegs.EMPTY.copy()
        );
        boolean canJumpLeft = !p.getMovesRight() && (
                idx - 1 >= 0 && piece(idx - 1) == Pegs.EMPTY.copy() ||
                        idx - 2 >= 0 && piece(idx - 2) == Pegs.EMPTY.copy()
        );
        return wontLeaveBoard && (canJumpRight || canJumpLeft);
    }

    public Peg piece(int idx) {
        return pegs.get(idx);
    }

    private void fillBoard() {
        for (int i = 0; i < 4; i++) {
            pegs.add(Pegs.RED.copy());
        }
        for (int i = 0; i < 2; i++) {
            pegs.add(Pegs.EMPTY.copy());
        }
        for (int i = 0; i < 4; i++) {
            pegs.add(Pegs.BLUE.copy());
        }
    }
}
