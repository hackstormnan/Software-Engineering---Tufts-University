import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    public boolean addNode(String n) {
        if (!nodes.containsKey(n)) {
            nodes.put(n, new LinkedList<String>());
            return true;
        }
        return false;
    }

    public boolean addEdge(String n1, String n2) {
        if (!nodes.containsKey(n1) || !nodes.containsKey(n2)) {
           throw new NoSuchElementException();
        }
        if (!nodes.get(n1).contains(n2)) {
           nodes.get(n1).add(n2);
           return true;
        }
        
        return false;
   }

    public boolean hasNode(String n) {
        if (nodes.containsKey(n)) {
            return true;
        }
        return false;
    }

    public boolean hasEdge(String n1, String n2) {
	     if (nodes.get(n1) != null && nodes.get(n1).size() != 0 && nodes.get(n1).contains(n2)) {
            return true;
         }
         return false;
    }

    public boolean removeNode(String n) {
	     if (nodes.containsKey(n)) {
            nodes.remove(n);
            for (String k : nodes.keySet()) {
                if (nodes.get(k) != null && nodes.get(k).size() != 0 && nodes.get(k).contains(n)) {
                    nodes.get(k).remove(n);
                }
            }
            return true;
         }
         return false;
    }

    public boolean removeEdge(String n1, String n2) {
        if (!nodes.containsKey(n1) || !nodes.containsKey(n2)) {
            throw new NoSuchElementException();
        }
        if (nodes.get(n1).contains(n2)) {
            nodes.get(n1).remove(n2);
            return true;
        }

        return false;
    }

    public List<String> nodes() {
	     List<String> list = new LinkedList<>();
         for (String node : nodes.keySet()) {
            list.add(node);
         }

         return list;
    }

    public List<String> succ(String n) {
	    if (!nodes.containsKey(n)) {
            throw new NoSuchElementException();
        }
        return nodes.get(n);
    }

    public List<String> pred(String n) {
        List<String> list = new LinkedList<>();
	    for (String node : nodes.keySet()) {
            if (nodes.get(node).contains(n)) {
                list.add(node);
            }
        }

        return list;
    }

    public Graph union(Graph g) {
        Graph ret_graph = new ListGraph();
        List<String> list1 = nodes();
        List<String> list2 = g.nodes();
        for (String node : list1) {
            ret_graph.addNode(node);
        }
        for (String node : list2) {
            ret_graph.addNode(node);
        }
        for (String node : list1) {
            for (String element : succ(node)) {
                ret_graph.addEdge(node, element);
            }
        }
        for (String node : list2) {
            for (String element : g.succ(node)) {
                ret_graph.addEdge(node, element);
            }
        }

        return ret_graph;
    }

    public Graph subGraph(Set<String> nodes) {
        Graph new_graph = new ListGraph();
        for (String node : nodes) {
            if (this.nodes.containsKey(node)) {
                new_graph.addNode(node);
            }
        }

        return new_graph;
    }

    public boolean connected(String n1, String n2) {
        if (!nodes.containsKey(n1) || !nodes.containsKey(n2)) {
            throw new NoSuchElementException();
        }
        if (n1.equals(n2)) {
            return true;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(n1);
        HashSet<String> set = new HashSet<>();

        while (!queue.isEmpty()) {
            String pop = queue.poll();
            if (!set.contains(pop)) {
                set.add(pop);
            } else {
                continue;
            }
            for (String element : nodes.get(pop)) {
                if (element == n2) {
                    return true;
                }
                queue.offer(element);
            }
        }

        return false;
    }
}
