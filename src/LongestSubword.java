/**
 * Created by lintaoc on 1/8/2016.
 */

import java.util.*;

public class LongestSubword {
    // bfs
    public String findLongest(String input, Set<String> words)
    {
        if(words.contains(input)) return input;
        if(words.isEmpty()) return "";

        Set<String> queue = new HashSet<>();
        queue.add(input);
        while(!queue.isEmpty())
        {
            Set<String> next = new HashSet<>();
            Iterator<String> iterator = queue.iterator();
            while(iterator.hasNext())
            {
                String word = iterator.next();
                // remove a single letter
                for(int i = 0; i < word.length(); i++)
                {
                    String newWord = word.substring(0, i) + word.substring(i + 1);
                    if(words.contains(newWord)) return newWord;
                    next.add(newWord);
                }
            }

            queue = next;
        }

        return "";
    }
}
