public class RookFactory implements PieceFactory {
    public char symbol() { return 'r'; }
    public Piece create(Color c) { return new Rook(c); }
}