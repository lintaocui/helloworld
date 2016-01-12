/**
 * Created by lintaoc on 1/8/2016.
 */
import  java.util.*;
public class WordAbbreviation {
    class Node{
        final char val;
        int count = 0;
        boolean isWord;
        Node[] children;
        Node(char val){
            this.val = val;
            children = new Node[26];
        }
    }

    class Trie {
        Node root = new Node((char)0);
        public void add(String word)
        {
            Node node = root;
            for(int i = 0; i < word.length(); i++)
            {
                int index = word.charAt(i) - 'a';
                if(node.children[index] == null)
                {
                    node.children[index] = new Node(word.charAt(i));
                }
                node = node.children[index];
                node.count++;
            }
            node.isWord = true;
        }

        public String getKey(String word) {
            Node node = root.children[word.charAt(0) - 'a'];
            StringBuilder prefix = new StringBuilder(String.valueOf(word.charAt(0)));
            for(int i = 1; i < word.length() && node.count > 1; i++)
            {
                node = node.children[word.charAt(i) - 'a'];
                prefix .append(node.val);
            }

            prefix.append("" + (word.length() - 2) + word.charAt(word.length() - 1));
            if(prefix.length() >= word.length())
                return word;
            else
                return prefix.toString();
        }

        public void remove(String word)
        {
            Node node = root;
            for(int i = 0; i < word.length(); i++)
            {
                int index = word.charAt(i) - 'a';
                node = node.children[index];
                node.count--;
                if(node.count == 0)
                {
                    node.children[index] = null;
                    break;
                }
            }

            node.isWord = false;
        }
    }
    List<String> getAbbreviation(List<String> words)
    {
        List<String> ret = new ArrayList<>();
        Map<String, Trie> map = new HashMap<>();
        // for each abbrev group,build a map to trie
        for (String word : words)
        {
            if(word.length() <= 3) continue;
            String abbrev = "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
            if(!map.containsKey(abbrev))
                map.put(abbrev, new Trie());
            map.get(abbrev).add(word);
        }

        for(String word : words)
        {
            if(word.length() <= 3){
                ret.add(word);
                continue;
            }
            String abbrev = "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
            ret.add(map.get(abbrev).getKey(word));
        }

        return ret;
    }
}
