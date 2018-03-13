package Food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * Module Interface:
 * 
 * public void insertFood(Food food)   			insert a food into the prefix tree (inserts the TAGS into the tree, not the food itself)
 * public void insertTag(Food food,String tag)	insert a tag into the prefix tree
 * public Food[] getFood(String tag)			returns a list of foods based on the word that is searched
 * 
 * 
 * @author Thomas Armena
 *
 */

public class PrefixTree {
	//ADT for node
	private class Node {
		HashMap<Character, Node> children = new HashMap<Character, Node>();
		List<Food> foods = new ArrayList<>();
		char c;
		
		Node() {}
		Node(char c){this.c = c;}
		
		void insertFood(Food food) {
			this.foods.add(food);
		}
		
		Food[] getFoods() {
			Food[] get = new Food[this.foods.size()];
			get = this.foods.toArray(get);
			return get;
		}
		
	}
	
	private Node root;
	
	public PrefixTree (ArrayList<Food> foods) {
		root = new Node();
		this.load(foods);
	}
	

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
	
	
	
	public void insertFood(Food food) {
		for(int i = 0; i < food.getDescriptor().length;i++) {
			insertTag(food,food.getDescriptor()[i]);
		}
	}
	
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
	
	public Food[] getFood(String tag) {		
		if (searchNode(tag)!= null) return searchNode(tag).getFoods();
		System.out.println("Nothing found.");
		return this.root.getFoods();
	}
	
	private void load(ArrayList<Food> foods) {
		for(int i = 0; i < foods.size();i++) this.insertFood(foods.get(i));
	}
	
	private String simplifyString(String str) {
		str = str.toLowerCase();
		str = str.replaceAll(" ", "");
		if (str.charAt(str.length()-1) == 's') str = str.substring(0, str.length() - 1);
		return str;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creating a test food item
		/*
		String[] descriptor1 = {"sandwich", "peanut butter", "strawberry jelly" };
		String[] descriptor2 = {"sandwich", "peanut butter", "grape jelly" };
		String[] descriptor3 = {"sandwich", "tuna"};
		String[] descriptor4 = {"fries", "plain"};
		String[] descriptor5 = {"fried fish", "soy sauce" };
		String[] descriptor6 = {"sardines", "fried" };
		
		Food food1 = new Food(descriptor1);
		Food food2 = new Food(descriptor2);
		Food food3 = new Food(descriptor3);
		Food food4 = new Food(descriptor4);
		Food food5 = new Food(descriptor5);
		Food food6 = new Food(descriptor6);
		
		PrefixTree Tree = new PrefixTree();
		Tree.insertFood(food1);
		Tree.insertFood(food2);
		Tree.insertFood(food3);
		Tree.insertFood(food4);
		Tree.insertFood(food5);
		Tree.insertFood(food6);
		
		/*
		//SEARCH FOR NODES BY ENTERING STRING INSIDE THE CALL TO getFood BELOW
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Food[] foods = Tree.getFood("nuqd");   
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		for(int i = 0; i < foods.length;i ++) {
			System.out.println(foods[i]);
		}
		
		
		//Multiple tags test
		PrefixTree Tree2 = new PrefixTree();
		//*********************************
		Food[] foods1 = Tree.getFood("sandwich");   //First tag
		//*********************************
		for(int i = 0; i < foods1.length; i ++){
			Tree2.insertFood(foods1[i]);
		}
		//*********************************
		Food[] foods2 = Tree2.getFood("grape");  //Second tag
		//*********************************
		for(int i = 0; i < foods2.length;i ++) {
			System.out.println(foods2[i]);
		}
		*/
		//PrefixTree Tree = new PrefixTree();
		
		//for(int i = 0)
		
	}
}
