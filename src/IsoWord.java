import java.util.*;

public class IsoWord {
    Map<Integer, Integer> set = new HashMap<Integer, Integer>();

    // Add the number to an internal data structure.
	public void add(int number) {
        set.put(number, set.containsKey(number) ? 2 : 1);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for(Integer val : set.keySet())
	    {
	        if(set.containsKey(value - val))
	        {
	            if((val == value - val) && set.get(val) == 1)
	                return false;
	            else
	                return true;
	        }
	    }
	    return false;
	}
}