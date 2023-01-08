public class BoardPrinter implements BoardInternalIterator {
    public void visit(String loc, Piece p) {
		if (p != null) {
			System.out.println(loc + "=" + p.toString());
		}
    }
}