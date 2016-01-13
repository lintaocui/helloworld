package gg;

import common.*;

/**
 * Created by lintaocui on 1/12/16.
 */
public class CompleteTree {

    // bfs
    public static boolean isCompleteTreeBFS() {

    }

    public static boolean isCompleteTreeDFS() {

    }

    public static TreeNode findLastLeaf(TreeNode root) {
        if(root == null) return null;
        int left = depth(root.left);
        int right = depth(root.right);
        if(left == 0 || right == 0) return left == 0 ? root : root.left;

        if(left == right) return findLastLeaf(root.right);
        else return findLastLeaf(root.left);
    }

    private static int depth(TreeNode root) {
        if(root == null) return 0;
        return 1 + depth(root.left);
    }
}
