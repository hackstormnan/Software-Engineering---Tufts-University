public class Edge {
    private String src, dst; // source, destination
    public Edge(String src, String dst) {
	this.src = src; this.dst = dst;
    }
    String getSrc() { return src; }
    String getDst() { return dst; }
    public boolean equals(Object o) {
	if (o instanceof Edge oth) {
	    return oth.src.equals(src) && oth.dst.equals(dst);
	}
	return false;
    }
    public int hashCode() {
	return src.hashCode() * 31 + dst.hashCode();
    }
}