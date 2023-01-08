public class Logger implements BoardListener {
    public void onMove(String from, String to, Piece p) {
	    System.out.println("Moved " + p.toString() + " from " + from + " to " + to);
    }
    public void onCapture(Piece attacker, Piece captured) {
	    System.out.println(attacker.toString() + " captures " + captured.toString());
    }
}