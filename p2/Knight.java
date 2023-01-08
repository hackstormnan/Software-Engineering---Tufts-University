import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) {
        color = c;
    }
    // implement appropriate methods

    public String toString() {
        return color == Color.WHITE ? "wn" : "bn";
    }

    public List<String> moves(Board b, String loc) {
        List<String> list = new ArrayList<>();
        char col = loc.charAt(0);
        char row = loc.charAt(1);
        char store_col = col;
        char store_row = row;

        if (row < '7' && col > 'a') {
            StringBuilder sb = new StringBuilder();
            col -= 1;
            row += 2;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
        }
        col = store_col;
        row = store_row;

        if (row < '8' && col > 'b') {
            StringBuilder sb = new StringBuilder();
            col -= 2;
            row += 1;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
        }
        col = store_col;
        row = store_row;

        if (row > '1' && col > 'b') {
            StringBuilder sb = new StringBuilder();
            col -= 2;
            row -= 1;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
        }
        col = store_col;
        row = store_row;

        if (row > '2' && col > 'a') {
            StringBuilder sb = new StringBuilder();
            col -= 1;
            row -= 2;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }
        }
        col = store_col;
        row = store_row;


        if (row < '7' && col < 'h') {
            StringBuilder sb = new StringBuilder();
            col += 1;
            row += 2;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }        }
        col = store_col;
        row = store_row;

        if (row < '8' && col < 'g') {
            StringBuilder sb = new StringBuilder();
            col += 2;
            row += 1;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }        }
        col = store_col;
        row = store_row;

        if (row > '1' && col < 'g') {
            StringBuilder sb = new StringBuilder();
            col += 2;
            row -= 1;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }        }
        col = store_col;
        row = store_row;

        if (row > '2' && col < 'h') {
            StringBuilder sb = new StringBuilder();
            col += 1;
            row -= 2;
            sb.append(col);
            sb.append(row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
            } else {
                list.add(sb.toString());
            }        }
        col = store_col;
        row = store_row;

        return list;
    }
}