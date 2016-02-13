package unittest;

import gg.MaxXor;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lintaoc on 2/5/2016.
 */
public class MaxXorTest {

    @Test
    public void testMaxXorOfTwoItems() throws Exception {
        assertEquals(0, MaxXor.maxXorOfTwoItems(new int[] {0,0}));
        assertEquals(15, MaxXor.maxXorOfTwoItems(new int[] {8, 1, 2, 7}));
        assertEquals(15, MaxXor.maxXorOfSubarray(new int[]{8, 1, 2, 12}));
        assertEquals(15, MaxXor.maxXorOfSubarray(new int[] {8, 1, 2, 4}));
    }
}