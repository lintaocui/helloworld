package gg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lintaocui on 1/9/16.
 * 一个file里面有很多alarm，每个alarm有三个数值：起始时间、终止时间、优先度
 。把那些从没成为过最高优先度的alarm删除
 */

public class WeightedInterval {

    // Sweep-line algorithm.
    // Use heap to maintain the most weighted alarm. Get all the time points in the alarms, and sort them.
    // Traverse sorted time points, for alarm start event, add the corresponding event to heap.
    // For alarm end event, remove the alarm from the heap.
    public List<Alarm> removeAlarms(List<Alarm> alarms)
    {
        SortedMap<Integer, List<Alarm>> times = new TreeMap<>();
        for(Alarm alarm : alarms)
        {
            if(!times.containsKey(alarm.start)) times.put(alarm.start, new ArrayList<>());
            times.get(alarm.start).add(alarm);
            if(!times.containsKey(alarm.end)) times.put(alarm.end, new ArrayList<>());
            times.get(alarm.end).add(alarm);
        }

        List<Alarm> ret = new ArrayList<>();
        PriorityQueue<Alarm> pq = new PriorityQueue<>();

        for(Map.Entry<Integer, List<Alarm>> time : times.entrySet())
        {
            for(Alarm alarm : time.getValue())
            {
                if(alarm.start == time.getKey()) pq.add(alarm);
                else pq.remove(alarm);
            }

            if(!pq.isEmpty()) pq.peek().remove = false;
        }

        ret.addAll(alarms.stream().filter(alarm -> alarm.remove).collect(Collectors.toList()));

        return ret;
    }

    public static class Alarm implements Comparable<Alarm> {
        final int start;
        final int end;
        final int weight;
        boolean remove;
        public Alarm(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            remove = true;
        }

        // Better to override equals and hashCode, even if they are not used in TreeSet
        @Override
        public boolean equals(Object other) {
            if(other == null) return false;
            if(this.getClass() != other.getClass()) return false;
            Alarm b = (Alarm) other;
            return this.start == b.start && this.end == b.end && this.weight == b.weight;
        }

        @Override
        public int hashCode() {
            return start * 51 + end * 37 + weight;
        }

        @Override
        public int compareTo(Alarm b) {
            if (this.weight == b.weight) return Integer.compare(this.start, b.start);
            return Integer.compare(b.weight, this.weight);
        }
    }
}
