package directedgraph;
import java.util.*;

public class DirectedDFS {
    private Collection<Integer> reachable;

    public DirectedDFS(Digraph G) {
        reachable = new HashSet<Integer>(G.size());  
    }

    public DirectedDFS(Digraph G, int s) {
        reachable = new HashSet<Integer>(G.size());
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Collection<Integer> sources) {
        reachable = new HashSet<Integer>(G.size());

        for (Integer v : sources) {
            if (!reachable.contains(v))
                dfs(G, v);

        }  
    }

    public boolean isStronglyConnected(Digraph G) {
        Collection<Integer> vertices = G.getVertexSet();
        reachable.clear();

        for (Integer v : vertices) {
            dfs(G, v);
            if (reachable.size() != G.size())
                return false;
            reachable.clear();
        }
        return true;
    }

    private void dfs(Digraph G, int s) {
        reachable.add(s);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);

        while (!queue.isEmpty()) {
            Integer next = queue.remove();
           
            for (Integer u : G.getOutNeighbors(next)) {
                if (!reachable.contains(u)) {
                    reachable.add(u);
                    queue.add(u);
                }
            }
        }
    }

    public static void main(String[] args) {
        Digraph d = new Digraph();
        d.addVertex(1);
        d.addVertex(2);
        d.addVertex(3);
        d.addVertex(4);
        d.addVertex(5);
        d.addArc(1, 2);
        d.addArc(2, 3);
        d.addArc(3, 4);
        d.addArc(4, 1);
        d.addArc(1, 5);
        d.addArc(5, 2);

        DirectedDFS reach = new DirectedDFS(d);
        System.out.println(reach.isStronglyConnected(d));
    }
}
