package gg;

import java.util.*;

/**
 * Created by lintaocui on 1/10/16.
 * 一个股票的stream，每个时间点(timeline)都有股票的更新，实现两个function：
 1.给一个timeline，删掉当前点的股票
 2.求出历史股票数据的最大值和最小值
 */
public class StockStreaming {
    // heap + hashmap
    // hashmap keeps all delted nodes
    // when get max, keep polling hte heap until the max item is not deleted.
    PriorityQueue<StockPrice> minHeap = new PriorityQueue<>();
    PriorityQueue<StockPrice> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));
    Set<Integer> deletedSet = new HashSet<>();

    // O(1)
    public void deleteStockPrice(int time) {
        deletedSet.add(time);
    }

    // O(logn)
    public void addStockPrice(StockPrice sp) {
        minHeap.add(sp);
        maxHeap.add(sp);
    }

    // O(1) for not deleted max, worst case O(nlogn). i.e. all items are deleted if the max is deleted
    public int getMax() {
        while(!maxHeap.isEmpty() && deletedSet.contains(maxHeap.peek().time))
            maxHeap.poll();
        return maxHeap.isEmpty() ? -1 : maxHeap.peek().price;
    }

    public int getMin() {
        while(!minHeap.isEmpty() && deletedSet.contains(minHeap.peek().time))
            minHeap.poll();
        return minHeap.isEmpty() ? -1 :  minHeap.peek().price;
    }


    // Method two, using a single BST tree to keep track of max/min
    SortedSet<StockPrice> sortedPrice = new TreeSet<>();
    Map<Integer, StockPrice> prices = new HashMap<Integer, StockPrice>();
    int maxPrice = -1;
    int minPrice = Integer.MAX_VALUE;

    // O(logn)
    public void deleteStockPrice2(int time) {
        StockPrice sp = prices.get(time);
        sortedPrice.remove(prices.get(time));
        if(sortedPrice.isEmpty())
        {
            maxPrice = -1;
            minPrice = Integer.MAX_VALUE;
        }

        if(sp.price == maxPrice)
            maxPrice = sortedPrice.last().price;
        if(sp.price == minPrice)
            minPrice = sortedPrice.first().price;
    }

    // O(logn)
    public void addStockPrice2(StockPrice sp){
        prices.put(sp.time, sp);
        sortedPrice.add(sp);
        if(maxPrice < sp.price)
            maxPrice = sp.price;
        if(minPrice > sp.price)
            minPrice = sp.price;
    }

    // O(1)
    public int getMax2() {
        return maxPrice;
    }

    // O(1)
    public int getMin2() {
        return minPrice;
    }

    public static class StockPrice implements Comparable<StockPrice>{
        final int time;
        final int price;
        public StockPrice(int time, int price) {
            this.time = time;
            this.price = price;
        }

        @Override
        public int hashCode() {
            return Objects.hash(time, price);
        }

        @Override
        public boolean equals(Object other) {
            if(other == null) return false;
            if(this.getClass() != other.getClass()) return false;
            StockPrice b = (StockPrice) other;
            return this.price == b.price && this.time == b.time;
        }

        @Override
        public int compareTo(StockPrice other) {
            int ret = Integer.compare(this.price, other.price);

            return ret == 0 ? Integer.compare(this.time, other.time) : ret;
        }
    }
}
