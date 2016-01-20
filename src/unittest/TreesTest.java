package unittest;

import common.TreeNode;
import gg.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by on 1/15/16.
 */
public class TreesTest {

    @Test
    public void testIsFullTree() throws Exception {
        TreeNode root = new TreeNode(0);
        assertTrue(Trees.isFullBinaryTree(root));

        root.left = new TreeNode(1);
        assertFalse(Trees.isFullBinaryTree(root));

        root.right = new TreeNode(2);
        assertTrue(Trees.isFullBinaryTree(root));
    }

    @Test
    public void testIsCompleteTreeBFSDFS() throws Exception {
        TreeNode root = new TreeNode(0);
        assertTrue(Trees.isCompleteTreeBFS(root));
        assertTrue(Trees.isCompleteTreeDFS(root));

        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        assertTrue(Trees.isCompleteTreeBFS(root));
        assertTrue(Trees.isCompleteTreeDFS(root));

        root.left.left = new TreeNode(3);
        assertTrue(Trees.isCompleteTreeBFS(root));
        assertTrue(Trees.isCompleteTreeDFS(root));

        root.right.left = new TreeNode(4);
        assertFalse(Trees.isCompleteTreeBFS(root));
        assertFalse(Trees.isCompleteTreeDFS(root));
    }

    @Test
    public void testFindLastLeaf() throws Exception {
        assertNull(Trees.findLastLeaf(null));
        assertNull(Trees.findLastLeafIterative(null));

        TreeNode root = new TreeNode(0);
        assertEquals(Trees.findLastLeaf(root), root);
        assertEquals(Trees.findLastLeafIterative(root), root);

        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(Trees.findLastLeaf(root), root.right);
        assertEquals(Trees.findLastLeafIterative(root), root.right);

        root.left.left = new TreeNode(3);
        assertEquals(Trees.findLastLeaf(root), root.left.left);
        assertEquals(Trees.findLastLeafIterative(root), root.left.left);
    }
}