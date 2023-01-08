import java.util.*;

public interface EdgeGraph {
    boolean addEdge(Edge e);
    boolean hasNode(String n);
    boolean hasEdge(Edge e);
    boolean removeEdge(Edge e);
    List<Edge> outEdges(String n);
    List<Edge> inEdges(String n);
    List<Edge> edges();
    EdgeGraph union(EdgeGraph g);
    boolean hasPath(List<Edge> l);
}
