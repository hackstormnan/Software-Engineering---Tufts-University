import java.util.*;

public class King extends Piece {

    public King(Color c) {
        color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wk" : "bk";
    }

    public List<String> moves(Board b, String loc) {
        List<String> list = new ArrayList<>();
        char col = loc.charAt(0);
        char row = loc.charAt(1);


        if (col > 'a') {
            col -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col += 1;
        }
        if (col < 'h') {
            col += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col -= 1;
        }
        if (row > '1') {
            row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            row += 1;
        }
        if (row < '8') {
            row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            row -= 1;
        }
        if (col > 'a' && row > '1') {
            col -= 1;
            row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col += 1;
            row += 1;
        }
        if (col > 'a' && row < '8') {
            col -= 1;
            row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col += 1;
            row -= 1;
        }
        if (col < 'h' && row > '1') {
            col += 1;
            row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col -= 1;
            row += 1;
        }
        if (col < 'h' && row < '8') {
            col += 1;
            row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
            col -= 1;
            row -= 1;
        }

        return list;
    }

}