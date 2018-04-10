package Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The PrefixTree class is a data structure that allows for faster tag searches
 * and auto completion of search results based on an partially searched string.
 * 
 * Used https://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/ 
 * as reference for an example prefix tree implementation
 * 
 * @author Thomas Armena
 *
 */

public class PrefixTree {
	
	/**
	 * Class to represent a node in a prefix tree
	 */
	private class Node {
		HashMap<Character, Node> children = new HashMap<Character, Node>();
		List<Food> foods = new ArrayList<>();
		char c;
		
		Node() {}
		Node(char c){this.c = c;}
		
		/**
		 * store food inside a node
		 * @param food Food object to be stored into node
		 */
		void insertFood(Food food) {
			this.foods.add(food);
		}
		
		/**
		 * @return an array of foods stored inside the node
		 */
		Food[] getFoods() {
			Food[] get = new Food[this.foods.size()];
			get = this.foods.toArray(get);
			return get;
		}
		
	}
	
	private Node root;
	
	/**
	 * Constructor for PrefixTree class
	 * @param foods Array list of foods whose tags will be stored into the prefix tree
	 */
	public PrefixTree (ArrayList<Food> foods) {
		root = new Node();
		this.load(foods);
	}
	
	/**
	 * Insert a tag into the prefix tree
	 * @param food Food that the tag comes from
	 * @param tag String to be added to prefix tree
	 */
	public void insertTag(Food food,String tag) {
		
		tag = simplifyString(tag);
		HashMap<Character, Node> children = this.root.children;

		for(int  i=0; i < tag.length(); i++) {
			char c = tag.charAt(i);
			
			Node t;
			if(children.containsKey(c)) {
				t = children.get(c);
			} else {
				t = new Node(c);
				children.put(c, t);
			}
			
			children = t.children;
			t.insertFood(food);
			
		}
	}
	
	/**
	 * Inserts all the tags of a food into the prefix tree
	 * @param food Food object to be inserted
	 */
	public void insertFood(Food food) {
		for(int i = 0; i < food.getDescriptor().length;i++) {
			insertTag(food,food.getDescriptor()[i]);
		}
	}
	
	/**
	 * Search for a node inside the prefix tree
	 * @param str String of tag to search for 
	 * @return Node of searched string 
	 */
	private Node searchNode(String str) {
		str = simplifyString(str);
		str = str.replaceAll(" ", "");
		Map<Character, Node> children = root.children;
		Node t = null;
		for(int i=0; i <str.length();i++) {
			char c = str.charAt(i);
			if(children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}
		return t;
	}
	
	/**
	 * @param tag String to search for
	 * @return array of possible foods that the current prefix tree node contains
	 */
	public Food[] getFood(String tag) {		
		if (searchNode(tag)!= null) return searchNode(tag).getFoods();
		System.out.println("Nothing found.");
		return this.root.getFoods();
	}
	
	/**
	 * loads an array of foods into the prefix tree
	 * @param foods Array of foods to load into prefix tree
	 */
	private void load(ArrayList<Food> foods) {
		for(int i = 0; i < foods.size();i++) this.insertFood(foods.get(i));
	}
	
	/**
	 * Simplifies strings to allow for broader search results
	 * @param str String to simplify
	 * @return Simplified version of the string
	 */
	private String simplifyString(String str) {
		str = str.toLowerCase();
		str = str.replaceAll(" ", "");
		if (str.charAt(str.length()-1) == 's') str = str.substring(0, str.length() - 1);
		return str;
	}
}
