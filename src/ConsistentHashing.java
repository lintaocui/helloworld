import sun.misc.Cache;

import java.util.*;

/**
 * Created by lintaoc on 1/7/2016.
 * This is a simple implementation of distributed cache using consistent hashing algorithm
 */

class CacheNode {
    public final String id;
    final Map<String, Integer> cache;
    public CacheNode(String id){
        this.id = id;
        // Change this to a LRU cache later
        cache = new HashMap<>();
    }
}

public class ConsistentHashing {
    private final SortedMap<Integer, CacheNode> circle;
    private final int numOfReplicas;
    private Map<CacheNode, List<Integer>> nodePoints;
    public ConsistentHashing(int num, Collection<CacheNode> nodes)
    {
        circle = new TreeMap<>();
        nodePoints = new HashMap<>();
        numOfReplicas = nodes.size();
        for(CacheNode node : nodes)
            addNode(node);

    }

    public void addNode(CacheNode node) {
        nodePoints.put(node, new ArrayList<>());
        for(int i = 0; i < numOfReplicas; i++)
        {
            int point = (node.id + i).hashCode();
            circle.put(point, node);
            nodePoints.get(node).add(point);
        }
    }

    public void removeNode(CacheNode node) {
        for(int i = 0; i < numOfReplicas; i++)
        {
            int point = (node.id + i).hashCode();
            circle.remove(point);
            nodePoints.remove(node);
        }
    }

    public void put(String key, int value)
    {
        CacheNode node = getNode(key);
        node.cache.put(key, value);
        System.out.println(key  + " is put on node: " + node.id);
    }

    public Integer get(String key) {
        CacheNode node = getNode(key);
        return node == null ? null : node.cache.get(key);
    }

    private CacheNode getNode(String key)
    {
        if(circle.isEmpty()) return null;
        int point = key.hashCode();
        if(!circle.containsKey(point))
        {
            SortedMap<Integer, CacheNode> map = circle.tailMap(point);
            point = map.isEmpty()? circle.firstKey() : map.firstKey();
        }

        return circle.get(point);
    }
}
