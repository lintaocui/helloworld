import java.util.*;
public class Solution {
    Map<String, Set<String>> graph = new HashMap<String, Set<String>>();

    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        // first build a graph by using bfs
        // then search all path by using dfs

        // step 1: build graph
        Deque<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<String>();
        visited.add(beginWord);
        boolean found = false;
        while(!queue.isEmpty() && !found)
        {
            Deque<String> next = new ArrayDeque<String>();
            Set<String> set = new HashSet<String>();
            while(!queue.isEmpty())
            {
                String word = queue.poll();
                graph.put(word, new HashSet<String>());
                for(int i = 0; i < word.length(); i++)
                {
                    StringBuilder sb = new StringBuilder(word);
                    for(int j = 0; j < 26; j++)
                    {
                        sb.setCharAt(i, (char)('a' + j));
                        String newWord = sb.toString();
                        if(newWord.equals(endWord))
                        {
                            graph.get(word).add(newWord);
                            found = true;
                            break;
                        }

                        if(wordList.contains(newWord) && !visited.contains(newWord))
                        {
                            next.add(newWord);
                            set.add(newWord);
                            graph.get(word).add(newWord);
                        }
                    }
                }
            }
            visited.addAll(set);
            queue = next;
        }

        List<String> prefix = new ArrayList<String>();
        List<List<String>> ret = new ArrayList<List<String>>();
        prefix.add(beginWord);
        dfs(ret, beginWord, endWord, prefix);
        return ret;
    }

    private void dfs(List<List<String>> ret, String beginWord, String endWord, List<String> prefix)
    {
        if(beginWord.equals(endWord))
        {
            ret.add(prefix);
            return;
        }

        for(String word : graph.get(beginWord))
        {
            prefix.add(word);
            dfs(ret, word, endWord, prefix);
            prefix.remove(word);
        }
    }
}