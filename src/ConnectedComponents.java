import java.util.ArrayList;
import java.util.*;

/**
 * Created by lintaocui on 12/21/15.
 */

public class ConnectedComponents {
    public static List<List<Integer>> findConnected(int[][] edges, int n)
    {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        int[] nodes = new int[n];
        for(int i = 0; i < n; i++) {
            nodes[i] = i;
        }

        for(int[] edge : edges) {
            int p1 = find(nodes, edge[0]);
            int p2 = find(nodes, edge[1]);

            // union them together
            nodes[p1] = p2;
        }

        Map<Integer, Set<Integer>> set = new HashMap<>();
        for(int i = 0; i < n; i++)
        {
            int p = find(nodes, i);
            if(!set.containsKey(p))
            {
                set.put(p, new HashSet<>());
            }

            set.get(p).add(i);
        }

        for (Set<Integer> list : set.values())
        {
            ret.add(new ArrayList<>(list));
        }
        return ret;
    }

    private static int find(int[] nodes, int i)
    {
        while(nodes[i] != i )
        {
            i = nodes[i];
        }

        return i;
    }
}
