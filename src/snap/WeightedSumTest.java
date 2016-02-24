package snap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by on 2/23/2016.
 */
public class WeightedSumTest {

    @Test
    public void testWeightedSum() throws Exception {
        int ret = WeightedSum.WeightedSum("[1, [3, 4]]");
        assertEquals(15, ret);
        ret = WeightedSum.WeightedSum("[1, [13, [12]]]");
        assertEquals(63, ret);

        ret = WeightedSum.ReverseWeightedSum("[1, [3, 4]]");
        assertEquals(9, ret);
    }
}