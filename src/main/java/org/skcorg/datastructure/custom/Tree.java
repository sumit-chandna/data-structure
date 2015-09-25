package org.skcorg.datastructure.custom;

public class Tree {
	private int preIndex = 0;
	private Node root;

	private Node _buildTree(String inOrder, String preOrder) {
		if (preOrder.length() == preIndex)
			return null;
		char data = preOrder.charAt(preIndex++);
		Node node = new Node(data);
		int inInorder = inOrder.indexOf(data);

		if (inOrder.length() > 1) {
			node.setLeft(_buildTree(inOrder.substring(0, inInorder), preOrder));
			node.setRight(_buildTree(inOrder.substring(inInorder + 1), preOrder));
		} else {
			node.setLeft(null);
			node.setRight(null);
		}
		return node;
	}

	public Tree buildTree(String inOrder, String preOrder) {
		root = _buildTree(inOrder, preOrder);
		preIndex = 0;
		Tree tree = new Tree();
		tree.root = root;
		return tree;
	}

	public String inOrder() {
		StringBuilder builder = new StringBuilder();
		_inOrder(root, builder);
		return builder.toString();
	}

	private void _inOrder(Node node, StringBuilder builder) {
		if (node != null) {
			_inOrder(node.getLeft(), builder);
			builder.append(node.getData());
			_inOrder(node.getRight(), builder);
		}
	}

	public String preOrder() {
		StringBuilder builder = new StringBuilder();
		_preOrder(root, builder);
		return builder.toString();
	}

	private void _preOrder(Node node, StringBuilder builder) {
		if (node != null) {
			builder.append(node.getData());
			_preOrder(node.getLeft(), builder);
			_preOrder(node.getRight(), builder);
		}
	}

	public String postOrder() {
		StringBuilder builder = new StringBuilder();
		_postOrder(root, builder);
		return builder.toString();
	}

	private void _postOrder(Node node, StringBuilder builder) {
		if (node != null) {
			_postOrder(node.getLeft(), builder);
			_postOrder(node.getRight(), builder);
			builder.append(node.getData());
		}
	}

	private class Node {
		private char data;
		private Node left, right;

		public char getData() {
			return data;
		}

		public Node(char data) {
			super();
			this.data = data;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}
	}

	
}
