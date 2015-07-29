package proj2;

import java.io.*;
import java.util.*;

/**
 * @name HashedBST
 * @author Rachael Birky
 * @Section 01
 * @date 03.10.2014
 *
 * @description A class representing a hashed table of
 * 		Binary Search Trees containing sorted Node objects.
 * 		Used to fill the trees from an input file,
 * 		print the results of the hash,
 * 		and find all Nodes with given criteria
 * 		e.g. "starts with ..."
 * 		
 * 
 * @param <T> Holds Objects of Any Type
 */
public class HashedBSTs <AnyType> {

	Scanner infile;
	private ArrayList<BinarySearchTree<Node>> table;
	
	/**
	 * @name HasedBST
	 * 
	 * @description Constructor that creates a new
	 * 		hashed table of the given size
	 * 
	 * @param size: user given size of the hashed table
	 */
	public HashedBSTs(int size){
		table = new ArrayList<BinarySearchTree<Node>>(size);
		for (int i=0; i<size; i++){
			BinarySearchTree<Node> aTree = new BinarySearchTree<Node>();
			table.add(aTree);
		}
	}
	
	
	/**
	 * @name fileReader
	 * 
	 * @description Reads the user given file, removes punctuation,
	 * 		and tokenizes using whitespace
	 * 
	 * @param filename: the text file given by the user
	 */
	public void fileReader(String filename){

		//Get file
		try {
			infile = new Scanner(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
			System.exit(0);
		}
		
		//Remove punctuation and split into tokens by whitespace
		while(infile.hasNextLine()){
			String[] line = infile.nextLine().replaceAll("[\\./]", "").replaceAll("[^a-zA-Z\u0020\n]", "").split("[^a-zA-Z]");
			
			//For each token, determine placement in table
			for (String token : line){
				
				if (token.length()>0){
					//Manipulate ASCII values to make letters contiguous
					int charIndex = (int) token.charAt(0);
					if (charIndex > 90){
						charIndex-=32;
					}
					
					//Manipulate ASCII values to match table registers
					charIndex-=65;
					
					//Add word to tree
					table.get(charIndex).insert(new Node(token));
				}
			}
		}
		
	}
	
	
	/**
	 * @name printHashCountResults
	 * 
	 * @description Prints the first item of each Binary
	 * 		Search Tree created in the table.
	 * 		Uses the BinarySearchTree printRoot() method
	 * 		to access the first items.
	 */
	public void printHashCountResults() {
		
		System.out.println();
		
		for (BinarySearchTree<Node> bst : table)
			bst.printRoot();
		
	}

	
	/**
	 * @name retrieveHashedBSTat
	 * 
	 * @param i: the index of the BST in the hashed table
	 * 		to access and return
	 * 
	 * @return BinarySearchTree<Node>: The desired BST
	 */
	public BinarySearchTree<Node> retreiveHashedBSTat(int i) {
		
		return table.get(i);
		
	}



	/**
	 * @name findAll
	 * @description Scans the given tree and prints all the nodes
	 * 		that start with the String in the given Node
	 * @param retrieved: a binary search tree to scan for the String
	 * @param sample: the given Node, containing a String for comparison
	 */
	public void findAll(BinarySearchTree<Node> retrieved, Node sample) {
		
		System.out.println("\nWords that start with \'" + sample.getWord() + "\':");
		
		retrieved.findAll(sample);
		
	}
	
}
