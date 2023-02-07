public enum Pegs {
    RED(0, "R", true),
    BLUE(1, "B", false),
    EMPTY(2, " ", true);

    private final Peg peg;

    Pegs(int rbo, String symbol, boolean movesRight) {
        this.peg = new Peg(rbo, symbol, movesRight);
    }

    public Peg copy() {
        return peg;
    }
}
