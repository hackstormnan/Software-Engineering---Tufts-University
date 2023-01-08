import java.util.*;

public class Pawn extends Piece {

    public Pawn(Color c) {
        color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wp" : "bp";
    }

    public List<String> moves(Board b, String loc) {
        Piece p = b.getPiece(loc);

        List<String> list = new ArrayList<>();
        char col = loc.charAt(0);
        char row = loc.charAt(1);
        Board board = Board.theBoard();

        if (color == Color.WHITE) {
            if (row < '7') {
                StringBuilder sb = new StringBuilder();
                sb.append(col);
                row += 2;
                sb.append(row);
                list.add(sb.toString());
                sb = new StringBuilder();
                sb.append(col);
                row -= 1;
                sb.append(row);
                list.add(sb.toString());
                row -= 1;
            } else if (row < '8') {
                StringBuilder sb = new StringBuilder();
                row += 1;
                sb.append(col);
                sb.append(row);
                row -= 1;
                list.add(sb.toString());
            }

            if (row < '8' && col < 'h') {
                StringBuilder sb = new StringBuilder();
                col += 1;
                row += 1;
                sb.append(col);
                sb.append(row);
                p = board.getPiece(sb.toString());
                if (p != null && p.color() == Color.BLACK) {
                    list.add(sb.toString());
                }
                col -= 1;
                row -= 1;
            }
            if (row < '8' && col > 'a') {
                StringBuilder sb = new StringBuilder();
                col -= 1;
                row += 1;
                sb.append(col);
                sb.append(row);
                p = board.getPiece(sb.toString());
                if (p != null && p.color() == Color.BLACK) {
                    list.add(sb.toString());
                }
                col += 1;
                row -= 1;
            }
        } else {
            if (row > '2') {
                StringBuilder sb = new StringBuilder();
                row -= 2;
                sb.append(col);
                sb.append(row);
                list.add(sb.toString());
                sb = new StringBuilder();
                row += 1;
                sb.append(col);
                sb.append(row);
                list.add(sb.toString());
                row += 1;
            } else if (row > '1') {
                StringBuilder sb = new StringBuilder();
                row -= 1;
                sb.append(col);
                sb.append(row);
                list.add(sb.toString());
                row += 1;
            }

            if (row > '1' && col > 'a') {
                StringBuilder sb = new StringBuilder();
                col -= 1;
                row -= 1;
                sb.append(col);
                sb.append(row);
                p = board.getPiece(sb.toString());
                if (p != null && p.color() == Color.WHITE) {
                    list.add(sb.toString());
                }
                col += 1;
                row += 1;
            }
            if (row > '1' && col < 'h') {
                StringBuilder sb = new StringBuilder();
                col += 1;
                row -= 1;
                sb.append(col);
                sb.append(row);
                p = board.getPiece(sb.toString());
                if (p != null && p.color() == Color.WHITE) {
                    list.add(sb.toString());
                }
                col -= 1;
                row += 1;
            }
        }

        return list;
    }

}