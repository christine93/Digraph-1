package directedgraph;

public class Arc {
	private final int tail;			// tail of arc
	private final int head;			// head of arc
	private final double weight;	// weight of arc
	
	public Arc(int tail, int head, double weight) {
		this.tail = tail;
		this.head = head;
		this.weight = weight;
	}
	
	public Arc(int tail, int head) {
		this.tail = tail;
		this.head = head;
		this.weight = 1;			// the default arc weight is 1
	}
	
	public Arc(Arc e) {
		if (e == null) 
			throw new IllegalArgumentException();
		this.tail = e.tail;
		this.head = e.head;
		this.weight = e.weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int tail() {
		return tail;
	}
	
	public int head() {
		return head;
	}
	
	public int compareTo(Arc that) {
		if (that == null) 
			throw new IllegalArgumentException();
			
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return +1;
		else
			return 0;
			
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Arc) {
            Arc e = (Arc) obj;
            return (e.tail == tail && e.head == head && e.weight == weight);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 2147483647 * tail + head;  
    }

	public String toString() {
		return String.format("%d -> %d weight: %.2f", tail, head, weight);
	}
	
	public static void main(String[] args) {
		Arc a = new Arc(1, 2, 4);
        Arc b = null;
		System.out.println(a.equals(new Arc(1, 2, 4)));
        System.out.println(a.toString());
        System.out.println(a.equals(b));
	}
}
