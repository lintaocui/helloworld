import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Solution sol = new Solution();
        String[] list = new String[]{"a", "b", "c"};
        Set<String> set = new HashSet<>(Arrays.asList(list));
        sol.findLadders("a", "c", set);
    }
}
