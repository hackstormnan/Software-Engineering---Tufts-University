import java.util.*; // Makes List available

public interface Graph {
    boolean addNode(String n);
    boolean addEdge(String n1, String n2);
    boolean hasNode(String n);
    boolean hasEdge(String n1, String n2);
    boolean removeNode(String n);
    boolean removeEdge(String n1, String n2);
    List<String> nodes();
    List<String> succ(String n);
    List<String> pred(String n);
    Graph union(Graph g);
    Graph subGraph(Set<String> nodes);
    boolean connected(String n1, String n2);
}
