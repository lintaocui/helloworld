import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<CacheNode> nodes = new ArrayList<>();
        for (int i = 0; i < 3; ++i)
        {
            nodes.add(new CacheNode(String.valueOf(i)));
        }
        ConsistentHashing dht = new ConsistentHashing(3, nodes);
        dht.put("1", 1);
        dht.put("2", 2);
        dht.put("11", 11);
        dht.put("21", 21);
        System.out.println(dht.get("1"));
        System.out.println(dht.get("2"));
        System.out.println(dht.get("11"));
        System.out.println(dht.get("21"));
        dht.removeNode(nodes.get(0));
        dht.put("1", 1);

        LongestSubword ls = new LongestSubword();
        String[] words = new String[] {"ap", "apl"};
        String word = ls.findLongest("appple", new HashSet<>(Arrays.asList(words)));

        WordAbbreviation wa = new WordAbbreviation();
        String[] inputs = new String[]
                {"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"};
        List<String> was = wa.getAbbreviation(new ArrayList<>(Arrays.asList(inputs)));
    }
}
