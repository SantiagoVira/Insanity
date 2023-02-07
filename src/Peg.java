public class Peg {
    private final String color;
    private final String symbol;
    private final Boolean movesRight;

    public Peg(int rbo, String symbol, boolean movesRight) {
        this.symbol = symbol;
        this.movesRight = movesRight;
        // rbo - 0: red, 1: blue, 2: off
        this.color = rbo == 0 ? "\u001B[31m" : rbo == 1 ? "\u001B[34m" : "\u001B[37m";
    }

    public String toString() {
        return color + symbol + "\u001B[0m";
    }

    public Boolean getMovesRight() {
        return movesRight;
    }

    public Boolean isRed() {
        return color.equals("\u001B[31m");
    }
}
