package snap;

/**
 * Created by on 2/23/2016.
 */
// [1, [3, 4]], 类似这样的数据结构，计算和，最外层权重1，内层2，越往内权重递增。这个例子就是1*1 + 2*(3+4) = 15
// followup，权重反过来，也就是最外层最大，问如何解决。

public class WeightedSum {
    public static int ReverseWeightedSum(String exp) {
        exp = exp.trim();
        if(exp.length() == 0 || exp.equals("")) {
            return 0;
        }

        int ret = 0;
        int count = 0;
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == '[') {
                count++;
            } else if(Character.isDigit(ch)) {
                // parse the number
                int end = i + 1;
                while(end < exp.length() && Character.isDigit(exp.charAt(end))){
                    end++;
                }

                int num = Integer.parseInt(exp.substring(i, end));
                ret += num;
                i = end;
            }
        }

        return ret * (count + 1)- WeightedSum(exp);
    }

    public static int WeightedSum(String exp) {
        exp = exp.trim();
        if(exp.length() == 0 || exp.equals("")) {
            return 0;
        }

        int ret = 0;
        int count = 0;
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == '[') {
                count++;
            } else if(ch == ']') {
                count--;
                if(count < 0) {
                    throw new IllegalArgumentException(exp + "is invalid.");
                }
            } else if(Character.isDigit(ch)) {
                // parse the number
                int end = i + 1;
                while(end < exp.length() && Character.isDigit(exp.charAt(end))){
                    end++;
                }

                int num = Integer.parseInt(exp.substring(i, end));
                ret += num * (count);
                i = end;
            }
        }

        return ret;
    }
}
