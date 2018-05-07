package org.saurav.simpletests.ds;

/**
 * Creates a binary tree and traverses that.
 * 
 * @author i054564
 *
 */
public class BinaryTree {

	static Node root;

	public BinaryTree(int data) {
		root = new Node(data);
	}

	private void add(Node parent, Node node, String position) {
		if ("left".equals(position)) {

			parent.setLeft(node);
		} else {

			parent.setRight(node);
		}
	}

	public static void main(String a[]) {

		buildTree();
		traverseTree(root,"inorder"); 
	}

	/**
	 * Builds the tree. Returns the root of the tree
	 */
	private static void buildTree() {
		System.out.println("Start building tree::");

		BinaryTree bt = new BinaryTree(10);
		Node n1 = new Node(15);
		Node n2 = new Node(5);
		Node n3 = new Node(4);
		Node n4 = new Node(9);
		Node n5 = new Node(25);

		bt.add(root, n1, "left");
		bt.add(n1, n2, "left");
		bt.add(n2, n3, "left");
		bt.add(root, n4, "right");
		bt.add(n4, n5, "left");
		

	}
	/**
	 * Pass the type of traversal you want to perform
	 * @param node
	 * @param operation
	 */
	private static void traverseTree(Node node,String operation) {
		if("inorder".equals(operation)) {
			traverseTreeInOrder(node);
		}else if("preorder".equals(operation)) {
			traverseTreePreOrder(node);
		}else if ("postorder".equals(operation)) {
			traverseTreePostOrder(node);
		}
	}

	/**
	 * Traverses the tree in in order format. Left- Root- Right
	 * 
	 * @param node
	 */
	private static void traverseTreeInOrder(Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			System.out.println(node.a);
			return;
		}

		traverseTreeInOrder(node.left);
		System.out.println(node.a);
		traverseTreeInOrder(node.right);
	}

	/**
	 * Traverses the tree in pre order format. Root -Left-Right
	 * 
	 * @param node
	 */
	private static void traverseTreePreOrder(Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			System.out.println(node.a);
			return;
		}
		System.out.println(node.a);
		traverseTreePreOrder(node.left);

		traverseTreePreOrder(node.right);
	}

	/**
	 * Traverses the tree in post order format. Left- Right-Post
	 * 
	 * @param node
	 */
	private static void traverseTreePostOrder(Node node) {
		if (node == null) {
			return;
		}
		if (node.left == null && node.right == null) {
			System.out.println(node.a);
			return;
		}

		traverseTreePostOrder(node.left);

		traverseTreePostOrder(node.right);
		System.out.println(node.a);
	}

	static class Node {
		Node left;
		Node right;
		int a;

		Node(int a) {
			this.left = null;
			this.right = null;
			this.a = a;
		}

		public void setLeft(Node leftNode) {
			this.left = leftNode;
		}

		public void setRight(Node rightNode) {
			this.right = rightNode;
		}
	}
}
