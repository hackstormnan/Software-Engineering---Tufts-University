public class KingFactory implements PieceFactory {
    public char symbol() { return 'k'; }
    public Piece create(Color c) { return new King(c); }
}