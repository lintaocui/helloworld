package graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by on 2/18/2016.
 */
public class ShortestPathTest {
    private ShortestPath sp;
    public ShortestPathTest() {
        String str =
                "8\n" +
                        "15\n" +
                        "4 5 0.35\n" +
                        "5 4 0.35\n" +
                        "4 7 0.37\n" +
                        "5 7 0.28\n" +
                        "7 5 0.28\n" +
                        "5 1 0.32\n" +
                        "0 4 0.38\n" +
                        "0 2 0.26\n" +
                        "7 3 0.39\n" +
                        "1 3 0.29\n" +
                        "2 7 0.34\n" +
                        "6 2 0.40\n" +
                        "3 6 0.52\n" +
                        "6 0 0.58\n" +
                        "6 4 0.93";

        sp = new ShortestPath(new EdgeWeightedDigraph(new Scanner(str)), 0);
    }

    @Test
    public void testDistTo() throws Exception {
        Assert.assertEquals(sp.distTo(1), 1.05, 0.00001);
        Assert.assertEquals(sp.distTo(3), 0.99, 0.00001);
    }

    @Test
    public void testPathTo() throws Exception {

    }
}