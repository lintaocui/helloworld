package graph;

import java.util.*;

/**
 * Created by lintaoc on 2/18/2016.
 */
public class ShortestPath {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private PriorityQueue<Pair> minHeap;

    public  ShortestPath(EdgeWeightedDigraph graph, int s) {
        // validate all edges' weight is non-negative
        for (DirectedEdge edge : graph.edges()) {
            if(edge.getWeight() < 0) {
                throw new IllegalArgumentException("Weight could not be negative");
            }
        }

        // Initialization
        distTo = new double[graph.V()];
        edgeTo = new DirectedEdge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        minHeap = new PriorityQueue<>(graph.V());
        minHeap.offer(new Pair(s, 0.0));
        while(!minHeap.isEmpty()) {
            Pair pair = minHeap.poll();
            for (DirectedEdge edge : graph.neighbors(pair.getV())) {
               relax(edge);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        int from = edge.getFrom();
        int to = edge.getTo();
        if(distTo[to] > distTo[from] + edge.getWeight()) {
            distTo[to] = distTo[from] + edge.getWeight();
            edgeTo[to] = edge;
            Pair newPair = new Pair(to, distTo[to]);
            if(minHeap.contains(newPair)) {
                minHeap.remove(newPair);
            }
            minHeap.offer(newPair);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.getFrom()]) {
            path.push(e);
        }
        return path;
    }
}

class Pair implements Comparable<Pair> {
    private final int v;
    private final double distance;

    Pair(int v, double distance) {
        if(v < 0) throw new IllegalArgumentException("v could not be negative!");
        this.v = v;
        this.distance = distance;
    }

    int getV() {
        return v;
    }

    double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Pair o) {
        return Double.compare(this.distance, o.distance);
    }

    @Override
    public int hashCode() {
        return v;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Pair)) {
            return false;
        }

        return ((Pair) other).v == this.v;
    }
}
