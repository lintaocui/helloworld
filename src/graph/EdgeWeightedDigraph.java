package graph;

import java.util.*;

/**
 * Created by  on 2/18/2016.
 */
public class EdgeWeightedDigraph {
    // number of vertices
    private final int V;

    // number of Edges
    private int E;

    // adjacent list for outgoing edges
    private List<List<DirectedEdge>> adjacentLists;

    private int[] indegree;

    public EdgeWeightedDigraph(Scanner str) {
        this(str.nextInt());
        int edges = str.nextInt();

        for(int i = 0; i < edges; i++) {
            addEdge(new DirectedEdge(str.nextInt(), str.nextInt(), str.nextDouble()));
        }
    }

    public EdgeWeightedDigraph(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("V could not be less than 0");
        }
        V = v;
        E = 0;
        indegree = new int[V];
        adjacentLists = new ArrayList<>();

        for(int i = 0; i < v; i++) {
            adjacentLists.add(i, new ArrayList<DirectedEdge>());
        }
    }

    public void addEdge(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        validateVertex(from);
        validateVertex(to);

        adjacentLists.get(from).add(edge);
        indegree[to]++;
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public List<DirectedEdge> edges() {
        List<DirectedEdge> list = new ArrayList<>();
        for(int  v = 0;  v < V; v++) {
            for(DirectedEdge edge : adjacentLists.get(v)) {
                list.add(edge);
            }
        }

        return list;
    }

    public List<DirectedEdge> neighbors(int v) {
        validateVertex(v);
        return adjacentLists.get(v);
    }

    private void validateVertex(int v) {
        if(v < 0 || v >= V) {
            throw new IndexOutOfBoundsException("v is not in a valid range");
        }
    }
}
