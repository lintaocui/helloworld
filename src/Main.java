import common.*;
import gg.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<WeightedInterval.Alarm> alarms = new ArrayList<>();
        alarms.add(new WeightedInterval.Alarm(1, 3, 1));
        alarms.add(new WeightedInterval.Alarm(5, 10, 6));
        alarms.add(new WeightedInterval.Alarm(6, 10, 6));
        alarms.add(new WeightedInterval.Alarm(8, 15, 5));
        alarms.add(new WeightedInterval.Alarm(6, 10, 5));
        alarms.add(new WeightedInterval.Alarm(13, 20, 10));
        alarms.add(new WeightedInterval.Alarm(15, 25, 4));
        alarms.add(new WeightedInterval.Alarm(16, 18, 3));
        WeightedInterval wi = new WeightedInterval();
        wi.removeAlarms(alarms);

        SideWalk sw = new SideWalk();
//        sw.addRainDrop(new SideWalk.RainDrop(1, 2));
//        double len = sw.totalLength();
//        sw.addRainDrop(new SideWalk.RainDrop(3, 5));
//        len = sw.totalLength();
//        sw.addRainDrop(new SideWalk.RainDrop(2, 4));
//        len = sw.totalLength();
//        sw.addRainDrop(new SideWalk.RainDrop(2, 3));
//        len = sw.totalLength();
        sw.addRainDrop2(new SideWalk.RainDrop(1.1, 2.1));
        sw.addRainDrop2(new SideWalk.RainDrop(1.2, 2.2));
        sw.addRainDrop2(new SideWalk.RainDrop(0.3, 1.3));
        sw.addRainDrop2(new SideWalk.RainDrop(0.3, 1.2));
        double len = sw.totalLength();

        StockStreaming ss = new StockStreaming();
        ss.addStockPrice2(new StockStreaming.StockPrice(0, 10));
        ss.addStockPrice2(new StockStreaming.StockPrice(2, 4));
        ss.addStockPrice2(new StockStreaming.StockPrice(5, 9));
        ss.addStockPrice2(new StockStreaming.StockPrice(6, 8));
        int maxPrice = ss.getMax2();
        int minPrice = ss.getMin2();
        ss.deleteStockPrice2(0);
        maxPrice = ss.getMax2();
        minPrice = ss.getMin2();
        ss.deleteStockPrice2(5);
        maxPrice = ss.getMax2();
        minPrice = ss.getMin2();
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

        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        TreeNode lastLeaf = CompleteTree.findLastLeaf(root);
        root.left.left = new TreeNode(3);
        lastLeaf = CompleteTree.findLastLeaf(root);
    }
}
