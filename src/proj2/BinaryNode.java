package proj2;

/**
 * @name BinaryNode<AnyType> 
 * @author Rachael Birky
 * @Section 01
 * @date 03.10.2014
 *
 * @description A class that represents a Binary Node,
 * 		a node with pointers to left and right children.
 * 		Holds an object of AnyType, which for this project,
 * 		is a Node type, and has a method to increment its
 * 		frequency in a tree in case of duplicates entries 
 * 		
 */
public class BinaryNode<AnyType> {

	AnyType element;
	BinaryNode<AnyType> left;
	BinaryNode<AnyType> right;
	
	/**
	 * @name BinaryNode
	 * @description Constructor that creates a new node with the given element
	 * 		and left and right BinaryNode children
	 * @param aElement: the given element to be held in the new node
	 * @param aLeft: the left child of the new node
	 * @param aRight: the right child of the new node
	 */
	BinaryNode(AnyType aElement, BinaryNode<AnyType> aLeft, BinaryNode<AnyType> aRight){
		this.element = aElement;
		this.left = aLeft;
		this.right = aRight;
	}
	
	/**
	 * @name BinaryNode
	 * @description Constructor that creates a new node with null children
	 * @param aElement: the element to be held in the new node
	 */
	BinaryNode(AnyType aElement){
		this(aElement, null, null);
	}
	
	/**
	 * @name getElement
	 * @description Returns the element held in the current BinaryNode
	 * @return AnyType the object held in the current node
	 */
	public AnyType getElement(){
		return this.element;
	}
	
	/**
	 * @name getLeft
	 * @description Returns the left BinaryNode child of the current node
	 * @return BinaryNode<AnyType> the left child
	 */
	public BinaryNode<AnyType> getLeft(){
		return this.left;
	}
	
	/**
	 * @name getRight
	 * @description Returns the left BinaryNode child of the current node
	 * @return BinaryNode<AnyType> the right child
	 */
	public BinaryNode<AnyType> getRight(){
		return this.right;
	}
	
	/**
	 * @name incrementFrequency
	 * @description  Accesses the Node object's method
	 * 		to increment the frequency of this Node in the tree
	 * 		in case of duplicates
	 */
	public void incrementFrequency(){
		//Downcast to access incrementFrequency method of a Node
		//(This method will only be used on Node type Objects)
		Node thisNode = (Node) this.element;
		thisNode.incrementFrequency();
	}
	
	public String toString(){
		return element.toString();
	}
}
