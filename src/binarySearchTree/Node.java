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

	public Node sucessorOf(Node n) {
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
		return sucessorOf(this);
	}

	public void deleteNode(int o) {
		deleteNode(o, this);
	}

	public void deleteNode(int o, Node n) {
		Node auxNode = new Node(999999999);
		auxNode.setRight(n);
		Node targetNode = auxNode.searchData(o);
		if (targetNode == null) {
			return;
		}
		// if the target has no child or a single child
		if (targetNode.right == null) {
			if (targetNode.parent.left == targetNode) {
				targetNode.parent.left = targetNode.left;
			}

			if (targetNode.parent.right == targetNode) {
				targetNode.parent.right = targetNode.left;
			}

			if (targetNode.left != null) {
				targetNode.left.parent = targetNode.parent;
			}
			targetNode = null;
		}

		else {
			Node sucessor = auxNode.sucessorOf(targetNode);
			if (sucessor.parent.left == sucessor) {
				sucessor.parent.left = sucessor.right;
			}

			if (sucessor.parent.right == sucessor) {
				sucessor.parent.right = sucessor.right;
			}
			if (sucessor.right != null) {
				sucessor.right.parent = sucessor.parent;
			}

			sucessor.right = null;
			sucessor.parent = null;

			// sucessor right now shouldn't have any left right or parent node

			// swaping sucessor with target
			sucessor.parent = targetNode.parent;
			sucessor.left = targetNode.left;
			targetNode.left.parent = sucessor;
			sucessor.right = targetNode.right;
			targetNode.right.parent = sucessor;

			// refactoring the target's parent
			if (targetNode.parent == null) {
				// detecting the position of target with respect to parent
				auxNode= sucessor;
			}
			
			else{

				if (targetNode.parent.left == targetNode) {
					targetNode.parent.left = sucessor;
				}
				if (targetNode.parent.right == targetNode) {
					targetNode.parent.right = sucessor;
				}
			}
			targetNode = null;
		}
	}

	public static void main(String[] args) {
		Node n = new Node(15);
		n.insertNode(new Node(6));
		n.insertNode(new Node(18));
		n.insertNode(new Node(3));
		n.insertNode(new Node(7));
		n.insertNode(new Node(17));
		n.insertNode(new Node(2));
		n.insertNode(new Node(4));
		n.insertNode(new Node(13));
		n.insertNode(new Node(9));
		// n.inOrder();

		n.deleteNode(17, n);
		// System.out.println("---After Delete---");
		n.inOrder();

	}
}
