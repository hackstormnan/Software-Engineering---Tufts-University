public interface BoardListener {
    void onMove(String from, String to, Piece p);
    void onCapture(Piece attacker, Piece captured);
}