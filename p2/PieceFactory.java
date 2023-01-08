public interface PieceFactory {
    public char symbol();
    public Piece create(Color c);
}