public class QueenFactory implements PieceFactory {
    public char symbol() { return 'q'; }
    public Piece create(Color c) { return new Queen(c); }
}