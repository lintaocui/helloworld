package snap;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by on 2/22/2016.
 */
public class OrgStructureTest {

    @Test
    public void testPrintStructure() throws Exception {
        OrgStructure solution = new OrgStructure();
        try {
            solution.printStructure("OrgSalesRecord.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}