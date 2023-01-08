import java.util.*;

public class Queen extends Piece {

    public Queen(Color c) {
        color = c;
    }
    // implement appropriate methods

    public String toString() {
	    return color == Color.WHITE ? "wq" : "bq";
    }

    public List<String> moves(Board b, String loc) {
        List<String> list = new ArrayList<>();
        char col = loc.charAt(0);
        char row = loc.charAt(1);
        char temp_col = col;
        char temp_row = row;

        while (temp_col > 'a') {
            temp_col -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;
        while (temp_col < 'h') {
            temp_col += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;

        while (temp_row > '1') {
            temp_row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;
        while (temp_row < '8') {
            temp_row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;



        while (temp_col > 'a' && temp_row > '1') {
            temp_col -= 1;
            temp_row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;

        while (temp_col > 'a' && temp_row < '8') {
            temp_col -= 1;
            temp_row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;

        while (temp_col < 'h' && temp_row > '1') {
            temp_col += 1;
            temp_row -= 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;

        while (temp_col < 'h' && temp_row < '8') {
            temp_col += 1;
            temp_row += 1;
            StringBuilder sb = new StringBuilder();
            sb.append(temp_col);
            sb.append(temp_row);
            if (b.getPiece(sb.toString()) != null) {
                if (b.getPiece(loc).color != b.getPiece(sb.toString()).color) {
                    list.add(sb.toString());
                }
                break;
            } else {
                list.add(sb.toString());
            }
        }
        temp_col = col;
        temp_row = row;

        return list;
    }

}