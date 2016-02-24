package snap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by on 2/23/2016.
 */
public class AmicableNumber {
    public static List<int[]> findAmicablePairs(int n) {
        List<int[]> ret = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for(int i = 2; i <= n; i++) {
            if(visited.contains(i)) continue;
            int sumFactors1 = findSumOfProperDivisor(i);
            if(i != sumFactors1 && i == findSumOfProperDivisor(sumFactors1)) {
                ret.add(new int[]{i, sumFactors1});
            }
            visited.add(i);
            visited.add(sumFactors1);
        }

        return ret;
    }

    public static int findSumOfProperDivisor(int num) {
        int sum = 1;
        for(int i = 2; i <= (int)Math.sqrt(num); i++) {
            if((num % i) == 0) {
                sum += i;
                if((num / i) != i ) {
                    sum += num / i;
                }
            }
        }
        return sum;
    }
}
