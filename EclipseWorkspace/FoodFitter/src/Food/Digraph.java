package Food;

import java.util.ArrayList;

import Food.GroupInfo;

public class Digraph {
	private final int V;
	private int E = 0;
	private ArrayList<Integer>[] adj;
	int[] indegree;

	public Digraph() {
        this.V = GroupInfo.GROUP_AMOUNT;
        this.E = 0;
        indegree = new int[V];
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
    }


	public int V() {
		return V;
	}

	public int E() {
		return E;
	}


	public Iterable<Integer> adj(int v) {
		return adj[v];
	}


	public int outdegree(int v) {
		return adj[v].size();
	}


	public int indegree(int v) {
		return indegree[v];
	}

	
	public Digraph reverse() {
		Digraph reverse = new Digraph();
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
	
	public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }
}
