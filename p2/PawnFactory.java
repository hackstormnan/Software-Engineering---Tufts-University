public class PawnFactory implements PieceFactory {
    public char symbol() { return 'p'; }
    public Piece create(Color c) { return new Pawn(c); }
}