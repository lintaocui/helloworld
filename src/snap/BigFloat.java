package snap;

/**
 * Created by on 2/21/2016.
 * Big Float addition
 */
public class BigFloat {
    private final String intPart;
    private final String fracPart;

    public BigFloat(String str) {
        if(str.length() == 0 || str.equals("")) {
            throw new IllegalArgumentException(str + "is invalid.");
        }

        String[] strs = str.split("\\.");
        if(strs.length > 2) {
            throw new IllegalArgumentException(str + "is invalid, can not be empty or containing more than one dot.");
        }

        // More validation is requied
        intPart = strs[0];
        fracPart = strs.length == 2 ? strs[1] : "";
    }

    public BigFloat add(BigFloat other) {
        int carry = 0;
        int otherIndex = other.fracPart.length() - 1;
        int index = this.fracPart.length() - 1;
        StringBuilder fract = new StringBuilder();
        while(otherIndex >= 0 && index >= 0) {
            char otherChar = other.fracPart.charAt(otherIndex);
            char ch = fracPart.charAt(index);
            char newCh;
            if(otherIndex > index) {
                newCh = otherChar;
                otherIndex--;
            } else if(otherIndex < index){
                newCh = ch;
                index--;
            } else {
                int val = (otherChar - '0') + (ch - '0') + carry;
                carry = val / 10;
                val %= 10;
                newCh = (char)(val + '0');
                otherIndex--;
                index--;
            }
            fract.insert(0, newCh);
        }

        otherIndex = other.intPart.length() - 1;
        index = intPart.length() - 1;
        StringBuilder newInt = new StringBuilder();
        while(otherIndex >= 0 || index >= 0 || carry > 0) {
            char otherChar = otherIndex >= 0 ? other.intPart.charAt(otherIndex) : '0';
            char ch = index >= 0 ? this.intPart.charAt(index) : '0';
            int val = (otherChar - '0') + (ch - '0') + carry;
            carry = val / 10;
            char newCh = (char)(val %10 + '0');
            otherIndex--;
            index--;
            newInt.insert(0, newCh);
        }

        return new BigFloat(newInt + (fract.length() > 0 ? "." + fract : ""));
    }

    @Override
    public String toString() {
        return intPart + (fracPart.length() > 0 ? "." + fracPart : "");
    }
}
