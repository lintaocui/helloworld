/**
 * Created by lintaoc on 10/16/2015.
 */
import java.util.*;

public class TopSort {
    List<Integer> order = new ArrayList<Integer>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return dfsSol(numCourses, prerequisites);
    }

    // DFS, using stack or recursive
    private int[] dfsSol(int numCourses, int[][] prerequisites)
    {
        int[] ret = new int[numCourses];

        // build graph, in this case, we only care about outdegree
        List<Integer>[] outdegree = new List[numCourses];
        for(int i = 0; i < numCourses; i++)
            outdegree[i] = new ArrayList<Integer>();
        for(int[] req : prerequisites)
        {
            outdegree[req[1]].add(req[0]);
        }

        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++)
        {
            if(visited[i] == 0 && !dfs(i, outdegree, visited))
            {
                break;
            }
        }
        if(order.size() != numCourses)
            return new int[0];

        for(int i = numCourses - 1; i >= 0; i--)
            ret[numCourses - 1 - i] = order.get(i);
        return ret;
    }

    private boolean dfs(int index, List<Integer>[] outdegree, int[] visited)
    {
        visited[index] = 1;

        for(int i = 0; i < outdegree[index].size(); i++)
        {
            int child = outdegree[index].get(i);
            if(visited[child] == 1) return false;
            if(visited[child] == 0 && !dfs(child, outdegree, visited))
                return false;
        }

        visited[index] = 2;
        order.add(index);
        return true;
    }

    //BFS, using queue, Topological sort
    private int[] bfs(int numCourses, int[][] prerequisites)
    {
        int[] ret = new int[numCourses];

        // build graph
        int[] indegree = new int[numCourses];
        List<Integer>[] outdegree = new List[numCourses];
        for(int i = 0; i < numCourses; i++)
            outdegree[i] = new ArrayList<Integer>();
        for(int[] req : prerequisites)
        {
            outdegree[req[1]].add(req[0]);
            indegree[req[0]]++;
        }

        // Choose zero indegree nodes
        Deque<Integer> queue = new ArrayDeque<Integer>();
        for(int i = 0; i < numCourses; i++)
        {
            if(indegree[i] == 0) queue.offer(i);
        }

        int count = 0;
        while(!queue.isEmpty())
        {
            int index = queue.poll();
            ret[count] = index;
            count++;
            for(int i = 0; i < outdegree[index].size(); i++)
            {
                int child = outdegree[index].get(i);
                if((--indegree[child]) == 0)
                    queue.offer(child);
            }
        }

        return count == numCourses ? ret : new int[0];
    }
}