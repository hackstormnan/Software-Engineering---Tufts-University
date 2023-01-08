import java.util.*;

abstract public class Piece {
    private static HashMap<Character, PieceFactory> map = new HashMap<>();
    Color color;

    public static void registerPiece(PieceFactory pf) {
        char symbol = pf.symbol();

        map.put(symbol, pf);
    }

    public static Piece createPiece(String name) {
        if (name.length() != 2) {
            throw new NullPointerException();
        }

        char piece_color = name.charAt(0);
        char piece_kind = name.charAt(1);

        if (piece_color != 'w' && piece_color != 'b') {
            throw new NullPointerException();
        }

        PieceFactory pf = map.get(piece_kind);

        if (piece_color == 'w') {
            return pf.create(Color.WHITE);
        } else {
            return pf.create(Color.BLACK);
        }
    }

    public Color color() {
        // You should write code here and just inherit it in
        // subclasses. For this to work, you should know
        // that subclasses can access superclass fields.
        return color;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);
}