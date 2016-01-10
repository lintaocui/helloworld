/**
 * Created by lintaoc on 12/21/2015.
 */

import javafx.util.Pair;
import java.util.*;

class TrieNode {
    public PriorityQueue<Pair<String, Integer>> top3;
    public TrieNode[] children;
    public char val;
    public TrieNode(char val) {
        this.val = val;
        top3 = new PriorityQueue<>(3, (x, y) ->{ return x.getValue() - y.getValue(); });
        children = new TrieNode[26];
    }

    public void weight(Pair word) {
        top3.add(word);
        if(top3.size() > 3)
            top3.poll();
    }
}

public class RankTrie {
    TrieNode root;
    public RankTrie(){
        root = new TrieNode(' ');
    }

    public void add(Pair<String, Integer> word)
    {
        String str = word.getKey();
        root.weight(word);
        TrieNode node = root;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode(ch);
            }
            node = node.children[ch - 'a'];
            node.weight(word);
        }
    }

    public List<String> find(String word)
    {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            node = node.children[word.charAt(i) - 'a'];
        }

        List<String> ret = new ArrayList<String>();
        for(Pair<String, Integer> pair : node.top3) {
            ret.add(pair.getKey());
        }

        return ret;
    }
}
