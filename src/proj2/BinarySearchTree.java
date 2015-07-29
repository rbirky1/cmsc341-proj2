package proj2;

/**
 * @name BinarySearchTree
 * @author Rachael Birky
 * @Section 01
 * @date 03.10.2014
 * 
 * @description A binary tree (each node has at most two children)
 * 		in which items are sorted so that items of lesser value
 * 		are stored to the left and items of greater value are stored
 * 		to the right. If the first value, it is the root.
 * 		This class supports insertion, printing the root or tree,
 * 		and finding the common ancestor.
 *
 * @param <AnyType> Uses objects of Any Type
 */
public class BinarySearchTree<AnyType extends Comparable <? super AnyType>> {

	private BinaryNode<AnyType> root;
	private int numNodes = 0;
	
	/**
	 * @name BinarySearchTree
	 * @description Constructor that creates a tree with a null root
	 */
	public BinarySearchTree(){
		
		root = null;
		
	}
	
	/**
	 * @name insert
	 * @description Bootstrap method
	 * 		Inserts the given item of Any Type
	 * 		into the tree according to the rules.
	 * 		Calls the private method and passes the root
	 * 		of the current tree with the given object
	 * @param x: the new item to be inserted
	 */
	public void insert(AnyType x){
		
		root = insert(x, root);
		
	}
	
	/**
	 * @name insert
	 * @description Private method for inserting a given
	 * 		object into the current tree according to the rules.
	 * 		Uses recursion to follow the rules and find
	 * 		a null pointer to which to assign the new node
	 * 
	 * @param x: the given node to be inserted
	 * @param t: the node used to traverse the tree
	 * @return BinaryNode<AnyType> the new node inserted into the
	 * 		tree with null left child and right child
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t){
		if (t==null){
			numNodes++;
			return new BinaryNode<AnyType>(x, null, null);
		}
		
		int compare = x.compareTo(t.element);
		
		if (compare < 0)
			t.left = insert(x, t.left);
		else if (compare > 0)
			t.right = insert(x, t.right);
		else
			if (t.element instanceof Node) t.incrementFrequency(); //Duplicate

		return t;
	}
	
	/**
	 * @name printRoot
	 * @description Bootstrap method
	 * 		Uses the toString method of the object type
	 * 		to print the root of the current BST to the screen
	 */
	public void printRoot(){
		printRoot(root);
	}
	
	/**
	 * @name printRoot 
	 * @description Uses the toString method of the object type
	 * 		to print the root of the current BST to the screen
	 * 		Also prints the number of nodes, if any, in the tree
	 * @param t: the binary node to print and access number of children
	 */
	private void printRoot(BinaryNode<AnyType> t){
		
		if (root!=null)
			System.out.printf("This tree starts with " + root + " and has %d nodes\n", numNodes);
		else
			System.out.println("This tree has no nodes");
		
	}
	
	
	/**
	 * @name printTree
	 * @description Bootstrap method
	 * 		Prints the in-order succession of the tree
	 * 		Gives the root to the private method
	 * 		as a starting point
	 */
	public void printTree(){
		
		System.out.println();
		printTree(root);
		
	}
	
	/**
	 * @name printTree
	 * @description Prints the in-order succession of the tree
	 * @param t: the node at which to start,
	 * 			generally the root of the tree
	 */
	private void printTree(BinaryNode<AnyType> t){
		
		if (t.left!=null) printTree(t.left);
		if (t!=null) System.out.println(t);
		if (t.right!=null) printTree(t.right);
		
	}
	
	/**
	 * @name findNode
	 * @description Given an object of AnyType, find it in the tree and
	 * 			assign the correct frequency from the tree Node
	 * @param aRoot: Starting node of the recursion
	 * @param x: the object for which to search
	 * @return x: the object with the correct frequency value
	 */
	private AnyType findNode(BinaryNode<AnyType> aRoot, AnyType x){
		//Types checked before calling function
		Node rootNode = (Node) aRoot.getElement();
		Node xNode = (Node) x;
		
		if (rootNode.compareTo(x) == 0){
			xNode.setFrequency(rootNode.getFrequency());
		}
		//Recurse
		else{
			if(aRoot.left!=null)
				findNode(aRoot.left, x);
			if (aRoot.right!=null)
				findNode(aRoot.right, x);
		}
		return x;
			
	}
	
	/**
	 * @name findCommonAncestor
	 * @description Bootstrap method
	 * 		Finds the lowest node under which both given nodes fall
	 * 		and prints the result to the screen
	 * 		Passes the given objects and the root of the tree to the
	 * 		private method to traverse and compare
	 * @param x: first given node
	 * @param y: second given node
	 */
	public void findCommonAncestor(AnyType x, AnyType y){
		AnyType commonAncestor = this.findCommonAncestor(this.root, x, y);
		
		//Get correct frequencies of given objects, if nodes
		if (root.element instanceof Node){
			if (x instanceof Node)
				x = findNode(root, x);
			if (y instanceof Node)
				y = findNode(root, y);
		}

		System.out.println("\nThe result of the common ancestor is:\n"
				+ x + " and " + y + "\n\thave the common ancestor " + commonAncestor);
	}
	
	
	/**
	 * @name findCommonAncestor
	 * @description Finds the lowest node under which both given nodes fall
	 * 		and prints the result to the screen
	 * 		Recursively compares the root to both objects using the objects' compareTo()
	 * 		method and uses the resulting integer to determine which subtree to search
	 * 
	 * @param aRoot the current root
	 * @param x: first given object
	 * @param y: second given object
	 * @return AnyType The ancestor object
	 */
	private AnyType findCommonAncestor(BinaryNode<AnyType> aRoot, AnyType x, AnyType y){
		
		int compareX = aRoot.getElement().compareTo(x);
		int compareY = aRoot.getElement().compareTo(y);
		
		//LCA doesn't exist
		if (aRoot == null || compareX == 0 || compareY == 0)
			return null;
		
		//LCA is root
		if ((compareX < 0 && compareY > 0) || (compareX > 0 && compareY < 0))
			return aRoot.getElement();
			
		//LCA on left
		if (compareX > 0 && compareY > 0)
			return findCommonAncestor(aRoot.getLeft(), x, y);
		
		//LCA on right
		if (compareX < 0 && compareY < 0)
			return findCommonAncestor(aRoot.getRight(), x, y);
			
		else return null;
	}
	
	
	/**
	 * @name findAll
	 * @description Bootstrap method 
	 * 		Prints all the nodes that start with the string
	 * 		contained in the given sample Node
	 * 
	 * @param sample: The given node, containing a String for comparison
	 */
	public void findAll(Node sample){
		
		findAll(this.root, sample);
		
	}
	
	
	/**
	 * @name findAll
	 * @description Prints all the nodes that start with the string
	 * 		in the given sample Node
	 * @param aRoot: The node of the tree at which to start 
	 * @param sample: The given node, containing a String for comparison
	 */
	private void findAll(BinaryNode<AnyType> aRoot, Node sample){
		if(aRoot == null)
			System.out.println("No Nodes");
		
		Node aRootNode = (Node) aRoot.element;
		
		if(sample.startsWith(aRootNode))
			System.out.println(aRootNode);
		
		if(aRoot.left!=null)
			findAll(aRoot.left, sample);
		if(aRoot.right!=null)
			findAll(aRoot.right, sample);
		

	}
	
	public String toString(){
		return this.toString();
	}
	
}
