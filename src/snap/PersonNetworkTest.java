package snap;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by on 2/22/2016.
 */
public class PersonNetworkTest {

    @Test
    public void testPrintFriendsGraph() throws Exception {
        PersonNetwork personNetwork = new PersonNetwork("PersonNetwork.txt");
        personNetwork.printFriendsGraph("Mike");
    }
}