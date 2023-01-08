import java.util.*;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.ArrayList;

public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void test1() {
		Graph g = new ListGraph();
		g.addNode("a");
		g.addNode("b");
		if (g.hasNode("a")) {
			System.out.println("add has node success");
		}

		g.addEdge("a", "b");
		if (g.hasEdge("a", "b")) {
			System.out.println("add has edge success");
		}

		g.addNode("c");
		g.removeNode("c");
		if (!g.hasNode("c")) {
			System.out.println("remove node success");
		}

		g.removeEdge("a", "b");
		if (!g.hasEdge("a", "b")) {
			System.out.println("remove edge success");
		}
		g.addEdge("a", "b");

		System.out.println("the nodes are:");
		List<String> list = g.nodes();
		for (String node : list) {
			System.out.println(node);
		}
	
		g.addNode("c");
		g.addEdge("a", "c");
		g.addNode("d");
		g.addEdge("a", "d");
		g.addNode("e");
		g.addEdge("e", "c");
		System.out.println("the succ nodes are:");
		list = g.succ("a");
		for (String node : list) {	
			System.out.println(node);
		}

		System.out.println("the pred nodes are:");
		list = g.pred("c");
		for (String node : list) {
			System.out.println(node);
		}
		g.union(g);
		g.addNode("1");
		g.addNode("2");
		g.addNode("3");
		g.addNode("4");
		g.addEdge("1", "2");
		g.addEdge("2", "3");	
		g.addEdge("3", "1");	

		if (g.connected("1", "3")) {
			System.out.println("is connected");
		}
	}

    public static void test2() {
		Graph g = new ListGraph();
		g.addNode("x");
		g.addNode("a");
		g.addNode("b");
		g.addNode("c");
		g.addNode("x");

		EdgeGraph eg = new EdgeGraphAdapter(g);
		Edge e = new Edge("x", "a");
		eg.addEdge(e);
		e = new Edge("a", "b");
		eg.addEdge(e);
		e = new Edge("b", "c");
		eg.addEdge(e);
		e = new Edge("c", "x");
		eg.addEdge(e);

		if (eg.hasNode("a")) {
			System.out.println("eg.hasNode() works");
		};
		eg.addEdge(e);
		e = new Edge("a", "c");
		if (eg.hasEdge(e)) {
			System.out.println("eg.hasEdge() works");
		}

		List<Edge> list = eg.outEdges("v");
		System.out.println("the outEdges are:");
		for (Edge edge : list) {
			System.out.println(edge.getSrc() + "-" + edge.getDst());
		}

		System.out.println("the inEdges are:");
		list = eg.inEdges("c");
		for (Edge edge : list) {
			System.out.println(edge.getSrc() + "-" + edge.getDst());
		}

		System.out.println("the Edges are:");
		list = eg.edges();
		for (Edge edge : list) {
			System.out.println(edge.getSrc() + "-" + edge.getDst());
		}

		eg.union(eg);

		List<Edge> edgelist = new LinkedList<>();
		e = new Edge("a", "b");
		edgelist.add(e);
		e = new Edge("b", "c");
		edgelist.add(e);
		e = new Edge("c", "x");
		edgelist.add(e);
		e = new Edge("x", "a");
		edgelist.add(e);

		if (eg.hasPath(edgelist)) {
			System.out.println("has path method passed");
		}
    }

    
    public static void main(String[] args) {
		test1();
		test2();
    }

}