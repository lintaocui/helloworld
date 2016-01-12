package gg;

import java.util.*;
/**
 * Rains on the sidewalk.
 一个sidewalk可以用[0, 1]的闭区间表示，一个raindrop可以用[a, a+0.01]的闭区间表示，其中a是随机在[0,1]中产生的数。
 写一段程序simulate雨点打湿sidewalk的过程，并返回整个sidewalk被打湿所需要的时间。
 自己设计sidewalk的class和raindrop的class
 并实现两个函数:
 (1) 随机产生新的雨点并根据雨点的位置更新sidewalk的状态.
 (2) 判断sidewalk是否被全部cover。两个函数都要求是O(1)
 */

public class SideWalk {
    double totalLength = 0;

    // Method 2, like bucket sort, divide the whole range into 100 buckets
    // each bucket maintain two variables: one for the covered len from the bucket's left boundary,
    // the other one from the right boundary
    // O(1)
    Status[] status = new Status[100];
    public SideWalk() {
        for(int i = 0; i < 100; i++)
            status[i] = new Status(0.0, 0.0);
    }

    public void addRainDrop2(RainDrop rainDrop)
    {
        int start = (int) rainDrop.start;
        int end = (int) rainDrop.end;
        if(status[start].length() < 1)
        {
            status[start].right = Math.max(status[start].right, end - rainDrop.start);
            if(status[start].length() >= 1) totalLength += 1;
        }

        if(status[end].length() < 1)
        {
            status[end].left = Math.max(status[end].left, rainDrop.end - end);
            if(status[end].length() >= 1) totalLength += 1;
        }
    }

    class Status {
        double left;
        double right;
        Status(double left, double right) {
            this.left = left;
            this.right = right;
        }

        double length() {
            return left + right;
        }
    }

    // Method(1) Keep a sorted(by start) list of intervals, and use insert intervals
    // T: O(n)
    List<RainDrop> drops = new LinkedList<>();
    public void addRainDrop(RainDrop rainDrop) {
        int i = 0;
        while(i < drops.size())
        {
            RainDrop curr = drops.get(i);
            if(curr.end < rainDrop.start)
            {
                i++;
                continue;
            }

            if(curr.start > rainDrop.end)
            {
                drops.add(i, rainDrop);
                totalLength += rainDrop.end - rainDrop.start;
                rainDrop = null;
                break;
            }

            drops.remove(i);
            totalLength -= (curr.end - curr.start);
            double start = Math.min(rainDrop.start, curr.start);
            double end = Math.max(rainDrop.end, curr.end);
            rainDrop = new RainDrop(start, end);
        }

        if(rainDrop != null)
        {
            drops.add(rainDrop);
            totalLength += rainDrop.end - rainDrop.start;
        }
    }

    // O(1)
    public boolean isCoverred() {
        return totalLength >= 100;
    }

    public double totalLength(){
        return totalLength;
    }

    public static class RainDrop {
        final double start;
        final double end;
        public RainDrop(double start, double end) {
            this.start = start;
            this.end = end;
        }
    }
}
