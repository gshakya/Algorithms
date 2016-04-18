package binarySearchTree;

public class Node {
	private Node parent;
	private Node left;
	private Node right;
	private int data;

	public Node(int i) {
		this.data = i;
	}

	public void insertNode(Node n) {

		Node searchNode = this;
		while (searchNode.nextNode(n) != null) {
			searchNode = searchNode.nextNode(n);
		}

		n.parent = searchNode;

		if (n.data < searchNode.data) {
			searchNode.setLeft(n);

			return;
		}

		searchNode.setRight(n);
		;
	}

	public Node left() {
		return this.left;
	}

	public Node right() {
		return this.right;
	}

	private Node nextNode(Node n) {
		if (n.data < this.data) {
			return this.left;
		}
		return this.right;
	}

	private void setLeft(Node n) {
		this.left = n;
	}

	private void setRight(Node n) {
		this.right = n;
	}

	public void inOrder() {
		inOrder(this);
	}

	public static void inOrder(Node n) {
		if (n.left != null) {
			inOrder(n.left);
		}
		System.out.println("-->" + n.data);

		if (n.right != null) {
			inOrder(n.right);
		}

	}

	public Node searchData(int i) {
		return searchData(i, this);
	}

	public Node searchData(int i, Node n) {
		if (n == null) {
			return null;
		}

		if (n.data == i) {
			return n;
		}
		if (i < n.data) {
			return searchData(i, n.left);
		}
		return searchData(i, n.right);
	}

	private static Node minNode(Node n) {
		Node searchNode = n;
		while (searchNode.left != null) {
			searchNode = searchNode.left;
		}
		return searchNode;

	}

	private Node minNode() {

		return minNode(this);

	}

	public void print() {
		System.out.println(data);
	}

	public Node sucessor(Node n) {
		if (n.right != null) {
			return minNode(n.right);
		}
		Node succ = n.parent;
		while (succ != null && n == succ.right) {
			n = succ;
			succ = succ.parent;
		}
		return succ;

	}

	public Node sucessor() {
		return sucessor(this);
	}

	public static void main(String[] args) {
		Node n = new Node(15);

		n.insertNode(new Node(6));
		n.insertNode(new Node(8));
		n.insertNode(new Node(3));
		n.insertNode(new Node(7));
		n.insertNode(new Node(17));
		n.insertNode(new Node(2));
		n.insertNode(new Node(4));
		n.insertNode(new Node(13));
		n.insertNode(new Node(9));
		n.inOrder();

		n.searchData(13).sucessor().print();

	}
}
