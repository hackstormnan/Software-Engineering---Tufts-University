import java.util.ArrayList;
import java.util.List;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private static Board board;
    List<BoardListener> states = new ArrayList<>();

    private Board() {
    }
    
    public static Board theBoard() {
        if (board == null) {
            board = new Board();
        }

        return board;
    }

    // Returns piece at given loc or null if no such piece
    // exists
    public Piece getPiece(String loc) {
        int row = loc.charAt(1)-'1';
        int col = loc.charAt(0)-'a';
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new NullPointerException();
        }
        return pieces[row][col];
    }

    public void addPiece(Piece p, String loc) {
        int row = loc.charAt(1)-'1';
        int col = loc.charAt(0)-'a';
        if (row < 0 || row > 7 || col < 0 || col > 7 || pieces[row][col] != null) {
            throw new NullPointerException();
        }

        pieces[row][col] = p;
    }

    public void movePiece(String from, String to) {
        int from_row = from.charAt(1)-'1';
        int from_col = from.charAt(0)-'a';
        if (from_row < 0 || from_row > 7 || from_col < 0 || from_col > 7 || pieces[from_row][from_col] == null) {
            throw new NullPointerException();
        }
        int to_row = to.charAt(1)-'1';
        int to_col = to.charAt(0)-'a';
        if (to_row < 0 || to_row > 7 || to_col < 0 || to_col > 7) {
            throw new UnsupportedOperationException();
        }

        if (!pieces[from_row][from_col].moves(board, from).contains(to)) {
            throw new NullPointerException();
        }
        if (pieces[from_row][from_col] == null) {
            throw new NullPointerException();
        }

        if (!states.isEmpty()) {
            for (BoardListener bl : states) {
                bl.onMove(from, to, pieces[from_row][from_col]);
                if (pieces[to_row][to_col] != null && pieces[from_row][from_col].color != pieces[to_row][to_col].color) {
                    bl.onCapture(pieces[from_row][from_col], pieces[to_row][to_col]);
                }
            }
        }

        pieces[to_row][to_col] = pieces[from_row][from_col];
        pieces[from_row][from_col] = null;
    }

    public void clear() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[0].length; j++) {
                pieces[i][j] = null;
            }
        }
    }

    public void registerListener(BoardListener bl) {

        states.add(bl);
    }

    public void removeListener(BoardListener bl) {
        if (!states.isEmpty() && states.contains(bl)) {
            states.remove(bl);
        }
    }

    public void removeAllListeners() {
        states.clear();
    }

    public void iterate(BoardInternalIterator bi) {
	    for (int i = 0; i < pieces.length; i++) {
            char col = 'a';
            for (int j = 0; j < pieces.length; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(col);
                int row = i+1;
                sb.append(row);
                bi.visit(sb.toString(), pieces[i][j]);
                col += 1;
            }
        }
    }
}