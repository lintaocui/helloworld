package graph;

/**
 * Created by on 2/18/2016.
 */
public class DirectedEdge {
    private final int from;
    private final int to;
    private final double weight;

    public DirectedEdge(int from, int to, double weight) {
        if(from < 0 || to < 0) {
            throw new IndexOutOfBoundsException("Vetex name could not negative integers");
        }

        if(Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight could not be NaN");
        }

        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }
}
