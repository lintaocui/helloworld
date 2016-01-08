import java.util.ArrayList;
import java.util.HashSet;
import java.util.*;

/**
 * Created by lintaoc on 12/30/2015.
 */

// Problems
// 1. check is s1 is a subsequence of s2
// 2. find the longest common subsequence between two strings
// 3. find the longest common substring between two strings
// 4. find the longest increasing subsequence of a string

public class Strings {

    // Check if s1 is a subsequence of s2
    // two pointers traverse each string
    // T: O(n)
    public boolean isSubsequence(String s1, String s2)
    {
        if(s1.length() > s2.length()) return false;

        int i = 0;
        int j = 0;
        while(i < s1.length() && j < s2.length())
        {
            if(s1.charAt(i) == s2.charAt(j))
            {
                i++;
                j++;
            }
            else
            {
                j++;
            }
        }

        return i == s1.length();
    }

    // find the longest common substring between two strings
    // dp
    // T: O(M*N), S: O(M*N)
    public int longestCommonSubstring(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++)
            dp[i][0] = 0;
        for(int j = 0; j < n; j++)
            dp[0][j] = 0;

        int max = 0;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
                else
                {
                    dp[i][j] = 0;
                }
            }
        }

        return max;
    }

    public Set<String> printLCSubstring(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++)
            dp[i][0] = 0;
        for(int j = 0; j < n; j++)
            dp[0][j] = 0;

        int max = 0;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
                else
                {
                    dp[i][j] = 0;
                }
            }
        }

        Set<String> ret = new HashSet<>();
        for (int i = m; i >= max; i--)
        {
            for(int j = n; j >= max; j--)
            {
                if(dp[i][j] == max)
                {
                    ret.add(s1.substring(i - max, i));
                }
            }
        }

        return ret;
    }

    // Find the longest Common subsequence between two strings
    // T: O(N*M)
    // DP
    public int longestCommonSubsequence(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++)
            dp[i][0] = 0;
        for(int j = 0; j < n; j++)
            dp[0][j] = 0;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // Print all longest common subsequence between two strings
    // DP + Backtracking
    // Non polinomial
    public Set<String> printLCS(String s1, String s2)
    {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++)
            dp[i][0] = 0;
        for(int j = 0; j < n; j++)
            dp[0][j] = 0;
        for(int i = 1; i <= m; i++)
        {
            for(int j = 1; j <= n; j++)
            {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return backTracking(s1, s2, dp, m, n);
    }

    private Set<String> backTracking(String s1, String s2, int[][] dp, int m, int n)
    {
        Set<String> ret = new HashSet<>();
        if(m == 0 || n == 0)
        {
            ret.add("");
            return ret;
        }

        if(s1.charAt(m - 1) == s2.charAt(n - 1))
        {
            Set<String> next = backTracking(s1, s2, dp, m - 1, n - 1);
            for (String str : next)
            {
                ret.add(str + s1.charAt(m - 1));
            }
        }
        else
        {
            if(dp[m - 1][n] >= dp[m][n - 1])
                ret.addAll(backTracking(s1, s2, dp, m - 1, n));
            if(dp[m][n - 1] >= dp[m - 1][n])
                ret.addAll(backTracking(s1, s2, dp, m, n - 1));
        }

        return ret;
    }

    public int longestIncreasingSubsequence(String s)
    {
        int n = s.length();
        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            dp[i] = 1;

        int max = 1;
        for(int i = 1; i < n; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(s.charAt(i) > s.charAt(j))
                {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(dp[i], max);
                }
            }
        }

        return max;
    }
}
