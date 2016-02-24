package snap;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by on 2/23/2016.
 */
public class AmicableNumberTest {

    @Test
    public void testFindAmicablePairs() throws Exception {
        List<int[]> ret = AmicableNumber.findAmicablePairs(66992);
        for (int i = 0; i < ret.size(); i++) {
            int[] ints = ret.get(i);
            System.out.print(ints[0] + " , ");
            System.out.println(ints[1]);
        }
    }
}