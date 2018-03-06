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
 * @author tobya
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
	
	public PrefixTree () {
		root = new Node();
	}
	

	public void insertTag(Food food,String tag) {
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
		Node t = searchNode(tag);
				
		if (searchNode(tag).getFoods() != null) {
			return searchNode(tag).getFoods();
		} else {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creating a test food item
				String descriptor1 = "Fast foods, sandwiches and burgers, hamburger, regular, single patty, plain";
				String descriptor2 = "Fast foods, sandwiches and burgers, hamburger, regular, single patty, plain";
				String descriptor3 = "Fast foods, Fries, plain";
				String descriptor4 = "Fast foods, Fried onions";
				String descriptor5 = "Fast foods, Salad";
				String descriptor6 = "Fast foods, nuts";
				double[] nutrVals = {1,2,3,4,5,6,7,8};
				Food food1 = new Food(1,descriptor1,nutrVals);
				Food food2 = new Food(2,descriptor2,nutrVals);
				Food food3 = new Food(3,descriptor3,nutrVals);
				Food food4 = new Food(4,descriptor4,nutrVals);
				Food food5 = new Food(5,descriptor5,nutrVals);
				Food food6 = new Food(6,descriptor6,nutrVals);
				
				PrefixTree Tree = new PrefixTree();
				Tree.insertFood(food1);
				Tree.insertFood(food2);
				Tree.insertFood(food3);
				Tree.insertFood(food4);
				Tree.insertFood(food5);
				Tree.insertFood(food6);
				
				//SEARCH FOR NODES BY ENTERING STRING INSIDE THE CALL TO getFood BELOW
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				Food[] foods = Tree.getFood("plain");
				//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				
				for(int i = 0; i < foods.length;i ++) {
					System.out.println(foods[i]);
				}
	}
}
