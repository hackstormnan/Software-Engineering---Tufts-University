public class BishopFactory implements PieceFactory {
    public char symbol() { return 'b'; }
    public Piece create(Color c) { return new Bishop(c); }
}