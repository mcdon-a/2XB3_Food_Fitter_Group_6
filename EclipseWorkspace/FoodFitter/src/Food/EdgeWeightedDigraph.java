package Food;

import java.util.ArrayList;

public class EdgeWeightedDigraph {
	private final int V;
	private int E = 0;
	private ArrayList<DirectedEdge>[] adj;
	int[] indegree;

	public EdgeWeightedDigraph() {
        this.V = GroupInfo.GROUP_AMOUNT;
        this.E = 0;
        indegree = new int[V];
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<DirectedEdge>();
        }
    }


	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }
	
	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}


	public int outdegree(int v) {
		return adj[v].size();
	}


	public int indegree(int v) {
		return indegree[v];
	}

	
	public EdgeWeightedDigraph reverse() {
		EdgeWeightedDigraph reverse = new EdgeWeightedDigraph();
		for (int v = 0; v < V; v++) {
			for (DirectedEdge e : adj(v)) {
				reverse.addEdge(new DirectedEdge(e.to(), e.from(), e.weight()));
			}
		}
		return reverse;
	}

	public Iterable <DirectedEdge> edges(){
		ArrayList<DirectedEdge> list = new ArrayList<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				list.add(e);
		return list;
	}
	
}
