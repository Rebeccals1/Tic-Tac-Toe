package tictactoe;

/** A source of moves. Implementations do not mutate the board. */
public abstract class Player {
    private final Mark mark;
    protected Player(Mark mark) { this.mark = mark; }
    public Mark mark() { return mark; }
    /** Return a legal 1–9 position. */
    public abstract int nextMove(Board board);
}
