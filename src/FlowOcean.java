import java.util.ArrayList;
import java.util.List;

/**
 * Created by lintaoc on 1/6/2016.
 */

class Point {
    final int x;
    final int y;
    boolean visited;
    boolean pacific;
    boolean atlantic;
    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
public class FlowOcean {
    int m;
    int n;
    final int[][] direct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<Point> findPoints(int[][] grid) {
        List<Point> ret = new ArrayList<>();
        m = grid.length;
        if(m == 0) return ret;
        n = grid[0].length;
        if(n == 0) return ret;
        Point[][] points = new Point[m][n];
        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
               points[i][j] = new Point(i, j);
            }
        }

        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(points[i][j].visited) continue;
                dfs(grid, points, i, j);
                points[i][j].visited = true;
            }
        }

        for (int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                if(points[i][j].pacific && points[i][j].atlantic) ret.add(points[i][j]);
            }
        }

        return ret;
    }

    void dfs(int[][] grid, Point[][] points, int i, int j)
    {
        if(i == 0 || j == 0) points[i][j].pacific = true;
        if(i == m - 1 || j == n - 1) points[i][j].atlantic = true;

        for(int[] di : direct)
        {
            int x = i + di[0];
            int y = j + di[1];
            if(x >= 0 && x < m && y >= 0 && y < n &&
                    grid[x][y] < grid[i][j])
            {
                if(!points[x][y].visited)
                {
                    dfs(grid, points, x, y);
                    points[x][y].visited = true;
                }
                points[i][j].pacific = points[i][j].pacific || points[x][y].pacific;
                points[i][j].atlantic = points[i][j].atlantic || points[x][y].atlantic;
            }
        }
    }
}
