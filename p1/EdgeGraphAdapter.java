import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

    private Graph g;

    public EdgeGraphAdapter(Graph g) { this.g = g; }

    public boolean addEdge(Edge e) {
        String src = e.getSrc();
        String dst = e.getDst();
        if (!g.hasNode(src)) {
          g.addNode(src);
        }
        if (!g.hasNode(dst)) {
          g.addNode(dst);
        }
        if (g.addEdge(src, dst)) {
          return true;
        }
        return false;
    }

    public boolean hasNode(String n) {
        if (g.hasNode(n)) {
          return true;
        }
        return false;
    }

    public boolean hasEdge(Edge e) {
        String src = e.getSrc();
        String dst = e.getDst();
        if (g.hasEdge(src, dst)) {
          return true;
        }

        return false;
    }

    public boolean removeEdge(Edge e) {
        EdgeGraph eg = new EdgeGraphAdapter(g);
        String src = e.getSrc();
        String dst = e.getDst();
        if (eg.hasNode(src) && eg.hasNode(dst) && eg.hasEdge(e)) {
          g.removeEdge(src, dst);
          return true;
        }

        return false;
    }

    public List<Edge> outEdges(String n) {
        List<Edge> list = new LinkedList<>();
        if (!g.hasNode(n)) {
          return list;
        }

        for (String node : g.succ(n)) {
          Edge e = new Edge(n, node);
          list.add(e);
        }
        return list;
    }
    
    public List<Edge> inEdges(String n) {
        List<Edge> list = new LinkedList<>();
        if (!g.hasNode(n)) {
          return list;
        }

        for (String node : g.pred(n)) {
          Edge e = new Edge(node, n);
          list.add(e);
        }

        return list;
    }

    public List<Edge> edges() {
      List<Edge> list = new LinkedList<>();
      List<String> node_list = g.nodes();
      for (String node : node_list) {
        for (Edge edge : outEdges(node)) {
          list.add(edge);
        }
      }

      return list;
    }

    public EdgeGraph union(EdgeGraph g) {
      EdgeGraph ret_graph = new EdgeGraphAdapter(this.g);
      List<Edge> list1 = edges();
      List<Edge> list2 = g.edges();
      for (Edge edge : list1) {
        if (!ret_graph.hasEdge(edge)) {
          ret_graph.addEdge(edge);
        }
      }
      for (Edge edge : list2) {
        if (!ret_graph.hasEdge(edge)) {
          ret_graph.addEdge(edge);
        }
      }

      return ret_graph;
    }

    public boolean hasPath(List<Edge> e) {
	    for (int i = 0; i < e.size()-1; i++) {
        String pre_edge_dst = e.get(i).getDst();
        String next_edge_src = e.get(i+1).getSrc();
        
        if (!pre_edge_dst.equals(next_edge_src)) {
          throw new BadPath();
        }
      }

      for (Edge edge : e) {
        if (!hasEdge(edge)) {
          return false;
        }
      }

       return true;
    }

}
