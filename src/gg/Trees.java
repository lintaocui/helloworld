package gg;

import common.*;
import java.util.*;

/**
 * Created by on 1/12/16.
 */
public class Trees {

    public static boolean isFullBinaryTree(TreeNode root) {
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        if(root.left != null && root.right != null) return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);
        return false;
    }

    // bfs, if found a null child, then there should be non-null child after it.
    // If found another non-null child after null child, then return false
    public static boolean isCompleteTreeBFS(TreeNode root) {
        if(root == null) return true;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean found = false;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left == null)
            {
                found = true;
            }
            else
            {
                if(found) return false;
                queue.offer(node.left);
            }

            if(node.right == null)
            {
                found = true;
            }
            else
            {
                if(found) return false;
                queue.offer(node.right);
            }
        }

        return true;
    }

    // dfs, if we represent a complete binary tree in an array,
    // the index should be less than the size of the array
    public static boolean isCompleteTreeDFS(TreeNode root) {
        int count = count(root);
        return dfsHelper(root, 0, count);
    }

    private static boolean dfsHelper(TreeNode root, int index, int count) {
        if(root == null) return true;
        if(index >= count) return false;
        return dfsHelper(root.left, 2 * index + 1, count) &&
               dfsHelper(root.right, 2 * index + 2, count);
    }

    private static int count(TreeNode root) {
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    // find the last/rightmost leaf node in complete tree
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

    public static TreeNode findLastLeafIterative(TreeNode root) {
        if(root == null) return null;
        while(root != null)
        {
            int left = depth(root.left);
            int right = depth(root.right);
            if(right == 0 || left == 0) return left == 0 ? root : root.left;
            if(left == right) root = root.right;
            else root = root.left;
        }

        return null;
    }
}
