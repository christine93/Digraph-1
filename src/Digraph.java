package directedgraph;
import java.util.*;

class Neighborhood {
    private Collection<Arc> inArcs;
    private Collection<Arc> outArcs;

    public Neighborhood() {
        inArcs  = new HashSet<Arc>();
        outArcs = new HashSet<Arc>();
    }

    public void addInArc(Arc a) {
        inArcs.add(a);  
    }

    public void addOutArc(Arc a) {
        outArcs.add(a);  
    }

    public void deleleInArc(Arc a) {
        inArcs.remove(a);  
    }

    public void deleteOutArc(Arc a) {
        outArcs.remove(a);  
    }

    public Collection<Arc> getInArcs() {
        return inArcs;  
    }

    public Collection<Arc> getOutArcs() {
        return outArcs;  
    }

    public boolean containsInArc(Arc a) {
        return inArcs.contains(a);  
    }

    public boolean containsOutArc(Arc a) {
        return outArcs.contains(a);  
    }

    public int size() {
        return inArcs.size() + outArcs.size();  
    }

    public int numInArcs() {
        return inArcs.size();  
    }

    public int numOutArcs() {
        return outArcs.size();  
    }
    
    public Collection<Integer> getInNeighbors() {
        Collection<Integer> inNeighbors = new HashSet<Integer>();
        for (Arc a : inArcs) {
            inNeighbors.add(a.from());  
        }
        
        return inNeighbors;
    }
    
    public Collection<Integer> getOutNeighbors() {
        Collection<Integer> outNeighbors = new HashSet<Integer>();
        for (Arc a : outArcs) {
            outNeighbors.add(a.to());
        }

        return outNeighbors;
    }
}


public class Digraph {
    private final Map<Integer, Neighborhood> adjacencyList;

    public Digraph() {
        adjacencyList = new Hashtable<Integer, Neighborhood>();  
    }

    public void addVertex(int u) {
        Neighborhood nh = new Neighborhood();
        adjacencyList.add(u, nh);
    }

    public boolean isVertex(int u) {
        return adjacencyList.containsKey(u);  
    }

    public void addArc(int u, int v) {
          
    }

}
