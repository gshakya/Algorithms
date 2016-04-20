package binarySearchTree;

import java.util.Random;

public class BST {
	Node root;

	class Node {
		private Node parent;
		private Node left;
		private Node right;
		private Object data;

		public Node(Object i) {
			this.data = i;			
		}

		public Node left() {
			return this.left;
		}

		public Node right() {
			return this.right;
		}

		@Override
		public String toString() {
			return "Node [data=" + data + "]";
		}

		// public void setLeft(Node n) {
		// this.left = n;
		// }
		//
		// private void setRight(Node n) {
		// this.right = n;
		public void insertNode(Object n) {
			Node searchNode = this;			
			while (searchNode.nextNode(n) != null) {
				searchNode = searchNode.nextNode(n);
			}
			Node newNode = new Node(n);
			newNode.parent = searchNode;

			if ((int) newNode.data < (int) searchNode.data) {
				searchNode.left = newNode;
				return;
			}
			searchNode.right = newNode;		
		}

		private Node nextNode(Object n) {
			if ((int) n < (int) this.data) {
				return this.left;
			}
			return this.right;
		}


	}

	public BST(Object data) {
		root = new Node(data);
	}

	
	public void inOrder() {
		inOrder(root);
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
		return searchData(i, root);
	}

	public Node searchData(int i, Node n) {
		if (n == null) {
			return null;
		}

		if ((int) n.data == (int) i) {
			return n;
		}
		if ((int) i < (int) n.data) {
			return searchData(i, n.left);
		}
		return searchData(i, n.right);
	}

	private static Node minNode(Node n) {
		if (n.left == null)
			return n;
		else
			return minNode(n.left);

	}

	private Node minNode() {

		return minNode(root);

	}
	
	public void insertNode(Object o){
		root.insertNode(o);
	}

	public void print() {
		System.out.println(root);
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
		return sucessorOf(root);
	}

	// public void deleteNode(int o) {
	// deleteNode(o, this);
	// }

	public void deleteNode(int o) {
		// Node auxNode = -999999999);
		// auxNode.setRight(n);
		// n.parent = auxNode;
		Node targetNode = this.searchData(o);
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
			Node sucessor = this.sucessorOf(targetNode);
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
				root = sucessor;
			}

			else {

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
		BST bst = new BST(15);
		bst.insertNode(6);
		bst.insertNode(18);
		bst.insertNode(3);
		bst.insertNode(7);
		bst.insertNode(17);
		bst.insertNode(2);
		bst.insertNode(4);
		bst.insertNode(13);
		bst.insertNode(9);
//		// n.inOrder();

		bst.deleteNode(1);
		// System.out.println("---After Delete---");
		bst.inOrder();

	}
}
