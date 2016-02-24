package snap;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by on 2/22/2016.
 */
public class BigFloatTest {

    @Test
    public void testAdd() throws Exception {
        BigFloat num1 = new BigFloat("5.6");
        BigFloat result = num1.add(new BigFloat("8.532"));
        assertEquals("14.132", result.toString());

        num1 = new BigFloat("2");
        assertEquals("3", num1.add(new BigFloat("1")).toString());

        num1 = new BigFloat("0.23");
    }
}