import java.util.*;

class Neighborhood {
    private HashSet<Arc> inArcs;
    private HashSet<Arc> outArcs;

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

    public void deleteInArc(Arc a) {
        inArcs.remove(a);
    }

    public void deleteOutArc(Arc a) {
        outArcs.remove(a);
    }

    public HashSet<Arc> getInArcs() {
        return inArcs;  
    }

    public HashSet<Arc> getOutArcs() {
        return outArcs;  
    }

    public boolean isInArc(Arc a) {
        return inArcs.contains(a);
    }

    public boolean isOutArc(Arc a) {
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
            inNeighbors.add(a.tail());  
        }
        
        return inNeighbors;
    }
    
    public Collection<Integer> getOutNeighbors() {
        Collection<Integer> outNeighbors = new HashSet<Integer>();
        for (Arc a : outArcs) {
            outNeighbors.add(a.head());
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
        adjacencyList.put(u, nh);
    }

    public boolean isVertex(int u) {
        return adjacencyList.containsKey(u);  
    }

    public void addArc(int u, int v) {
        if (!isVertex(u) || !isVertex(v))
            throw new IllegalArgumentException("One endpoint is not a vertex.");

        Arc e = new Arc(u, v);
        adjacencyList.get(u).addOutArc(e);      // add e to the out-arcs set of u
        adjacencyList.get(v).addInArc(e);       // add e to the in-arcs set of v  
    }

    public boolean isArc(Arc a) {
        int aHead = a.head();
        return adjacencyList.get(aHead).isInArc(a);
    }

    public void deleteVertex(int v) {
       if (!isVertex(v))
            throw new IllegalArgumentException("Not a vertex.");
       
       Neighborhood nbrHood = adjacencyList.get(v);
       HashSet<Arc> inArcs = nbrHood.getInArcs();   
       HashSet<Arc> outArcs = nbrHood.getOutArcs();

       adjacencyList.remove(v);

       for (Arc e : inArcs) {
            int tail = e.tail();
            Neighborhood tailNbrHood = adjacencyList.get(tail);
            tailNbrHood.deleteOutArc(e);
       }

       for (Arc e : outArcs) {
            int head = e.head();
            Neighborhood headNbrHood = adjacencyList.get(head);
            headNbrHood.deleteInArc(e);
       }

    }

    public void deleteArc(int u, int v) {
        if (!isVertex(u) || !isVertex(v))
            throw new IllegalArgumentException("One of the endpoints is not a vertex.");
        
        Arc e = new Arc(u,v);
        if (!isArc(e))
            throw new IllegalArgumentException("Not an arc.");
        adjacencyList.get(u).deleteOutArc(e);
        adjacencyList.get(v).deleteInArc(e);
    }

    public int size() {
        return adjacencyList.size();  
    }

    public String toString() {
        String graph = "";
        for (Integer v : adjacencyList.keySet()) {
              graph += "Vertex: " + v + "\n";
              Neighborhood nbr = adjacencyList.get(v);
              graph += "In arcs: \n";
              for (Arc e : nbr.getInArcs()) {
                    graph += e.toString() + ", "; 
              }

              graph += "\n" + "Out Arcs: \n";
              for (Arc e : nbr.getOutArcs()) {
                    graph += e.toString() + ", ";
              }

              graph += "\n";
        }  

        return graph;
    }

    public static void main(String[] args) {
        Digraph d = new Digraph();
        d.addVertex(1);
        d.addVertex(2);
        d.addArc(1,2);
        d.addVertex(3);
        d.addArc(2,3);
        d.addVertex(4);
        d.addArc(3,4);
        d.addArc(4,1);
        d.addArc(1,3);
        //System.out.println(d.toString());
        d.deleteArc(3,4);
        System.out.println(d.toString());
    }
}
