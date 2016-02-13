package gg;

/**
 * Created by lintaoc on 2/5/2016.
 */

class TrieNode {
    TrieNode[] children = new TrieNode[2];
}

class Trie {
    private TrieNode root = new TrieNode();
    private int msb = 1;
    public Trie(int[] array) {
        // find msb among arrays
        for(int i = 0; i < array.length; i++) {
            int count = 0;
            int num = array[i];
            while(num > 0) {
                num >>= 1;
                count++;
            }
            msb = Math.max(count, msb);
        }

        // put all numbers into a trie
        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            int pos = msb - 1;
            TrieNode node = root;
            while(pos >= 0) {
                if((num & (1 << pos)) == 0) {
                    if(node.children[0] == null)
                        node.children[0] = new TrieNode();
                    node = node.children[0];
                }
                else {
                    if(node.children[1] == null)
                        node.children[1] = new TrieNode();
                    node = node.children[1];
                }
                pos--;
            }
        }
    }

    public int findMaxXor() {
        return findMaxXor(root.children[0], root.children[1], msb - 1);
    }

    private int findMaxXor(TrieNode zero, TrieNode one, int pos)
    {
        if(zero != null && one != null) {
            int ret1 = findMaxXor(zero.children[0], one.children[1], pos - 1);
            int ret2 = findMaxXor(zero.children[1], one.children[0], pos - 1);
            int ret = (1 << (pos)) + Math.max(ret1, ret2);
            return ret;
        }

        if(zero != null)
        {
            return findMaxXor(zero.children[0], zero.children[1], pos - 1);
        }

        if(one != null)
        {
            return findMaxXor(one.children[0], one.children[1], pos - 1);
        }

        return 0;
    }
}

public class MaxXor {
    public static int maxXorOfTwoItems(int[] array) {
        Trie trie = new Trie(array);
        int ret = trie.findMaxXor();
        return ret;
    }

    public static int maxXorOfSubarray(int [] array) {
        int[] prefixXor = new int[array.length + 1];
        int xor = 0;
        for(int i = 0; i < array.length; i++) {
            xor ^= array[i];
            prefixXor[i + 1] = xor;
        }

        Trie trie = new Trie(prefixXor);
        int ret = trie.findMaxXor();
        return ret;
    }
}
