package org.saurav.simpletests.ds;

/**
 * https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
 */
public class BinaryTreeSame {

    public static void main(String a[]) {
        BinaryTreeSame binaryTreeSame = new BinaryTreeSame();

        TreeNode treeNodeP = binaryTreeSame.constructTree(new Integer[] { 1, 2, 3 });

        TreeNode treeNodeQ = binaryTreeSame.constructTree(new Integer[] { 1, 2, 3 });

        System.out.println(" Binary tree is same ? : " + binaryTreeSame.isSameTree(treeNodeP, treeNodeQ));
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        //both empty
        boolean result = false;
        if (p == null && q == null) {
            result = true;
            return result;
        }
        if (p != null) {

            if (p.val > 100000 || p.val < -100000) {
                System.out.println("Node value out of range");
                result = false;
            }

        }
        if (q != null) {

            if (q.val > 100000 || q.val < -100000) {
                System.out.println("Node value out of range");
                result = false;
            }

        }

        if (p != null && q != null) {
            if ((p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
                result = true;
            }
        }
        return result;
    }

    private TreeNode constructTree(Integer[] nodeArray) {
        TreeNode treeNode = new TreeNode(nodeArray[0]);
        treeNode.left = new TreeNode(nodeArray[1]);
        treeNode.right = new TreeNode(nodeArray[2]);
        return treeNode;
    }

}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {this.val = val;}

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
