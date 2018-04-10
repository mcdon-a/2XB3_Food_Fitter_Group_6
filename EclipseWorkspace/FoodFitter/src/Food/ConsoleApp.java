package Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * A console interface based off of a Finite State Machine, with
 * values of state representing different states in the
 * FSM. Each state displays options to the user and uses
 * fitting, graphing, lookup, and sorting algorithms from
 * our implementation to provide the user with output for their
 * queries.
 * 
 * @author Wyatt Wismer
 *
 */

public class ConsoleApp {
	/// Application Variables
	private final static int SCREEN_HEIGHT = 12;
	private static int state = 0; // Application state
	private static Scanner scan = new Scanner(System.in); // User input
	/// Working variables
	private static Hashtable<String, Meal> meals;
	private static Hashtable<String, NutrientTarget> targets;
	private static String cur_name;
	private static Meal cur_meal;
	private static NutrientTarget cur_nt;
	// Data Structures
	private static ArrayList<Food> foods;
	private static Knapsack knap;
	private static PrefixTree tree;
	
	/**
	 * Setup structures and start FSM
	 * @param args N/A
	 */
	public static void main(String[] args) {
		build_data_structures();
		load_sample_meals();
		FSM();
	}
	
	/**
	 * Build all data structures
	 */
	private static void build_data_structures() {
		// Load in food
    	foods = ReadCSV.readFile();
    	// Build knapsack
    	knap = new Knapsack(foods);
    	// Build prefix tree
    	tree = new PrefixTree(foods);
    	// Load nutrientInfo
    	NutrientInfo.init_info();
    	// Intialize working variables
    	meals = new Hashtable<String, Meal>();
    	targets = new Hashtable<String, NutrientTarget>();
    	cur_name = null;
    	cur_meal = null;
    	cur_nt = null;
	}
	
	/**
	 * Create and load sample meals
	 */
	private static void load_sample_meals() {
		// Build first sample meal
		Meal m1 = new Meal();
		Food f1 = new Food(new String[] {"Carrots", "farm-sourced", "long"});
		Food f2 = new Food(new String[] {"Almonds", "Salted"});
		Food f3 = new Food(new String[] {"Eggs","White","farm-sourced"});
		f1.addNutr(208, 153);f2.addNutr(208, 232);f3.addNutr(208, 461);
		m1.add(f1);m1.add(f2);m1.add(f3);
		// Build second sample meal
		Meal m2 = new Meal();
		Food f4 = new Food(new String[] {"Asparagus", "Polish", "Vegetable"});
		Food f5 = new Food(new String[] {"Steak", "Meat", "farm-sourced"});
		Food f6 = new Food(new String[] {"Potatoes", "Vegetable", "Starch"});
		f4.addNutr(208, 134);f5.addNutr(208, 578);f6.addNutr(208, 307);
		m2.add(f4);m2.add(f5);m2.add(f6);
		// Insert sample meals
		meals.put("My favorite brunch", m1);
		meals.put("Fancy dinner", m2);
	}
	
	/**
	 * Start finite state machine
	 */
	private static void FSM() {
		String i; // for input
		int c; // for counting
		int pos; // position in item lists
		final int ELE_LIMIT = 8; // max number of elements to display
		
		// Main looping mechanism for FSM
		while (state!=-1) {
			// Each case represents a different state of the system
			switch(state) {
				case 0: // Main Menu
					// Display options
					pHeader("FOOD FITTER");
					pSubHeader("By Wyatt W, Thomas A, Illya P, Alexie M, Sophia T");
					choose();
					pOptionMany("Make/view meals",
								"Exit");
					// Get user choice
					i = inp().trim();
					// Determine new state
					if (e(i,"1")) state = 1;
					else if (e(i,"2")) state = -1;
					else state = 999;
					break;
				case 1: // Meals overview
					// Display options
					pHeader("MEALS");
					pnl();
					choose();
					c = 1;
					ArrayList<String> names = new ArrayList<String>(meals.keySet());
					for(String name : names) {
						pOption(c++, "View -> "+name);
					}
					pOption("+","Add Meal");
					pOption(Arrays.asList(new String[] {"-","#"}),"Delete meal");
					pOption("b","Go back");
					// Get user input
					i = inp().trim();
					// Determine next state
					if (e(i,"b")) {
						state = 0;
					}else if (e(i,"+")) {
						p("Enter name of new meal: ");
						i = inp().trim();
						cur_name = i;
						cur_meal = new Meal();
						meals.put(cur_name, cur_meal);
						state = 2;
					} else if (i.charAt(0)=='-') {
						int ind = Integer.parseInt(i.substring(1));
						cur_name = names.get(ind-1);
						meals.remove(cur_name);
						// State stays the same
					} else {
						int ind = Integer.parseInt(i);
						cur_name = names.get(ind-1);
						cur_meal = meals.get(cur_name);
						if (targets.containsKey(cur_name))
							cur_nt = targets.get(cur_name);
						else
							cur_nt = null;
						state = 2;
					}
					break;
				case 2: // Meal options
					// Display options
					pHeader(cur_name.toUpperCase());
					choose();
					pOptionMany("Set nutrient targets","View meal");
					pOption("b","Go back");
					// Get input
					i = inp().trim();
					// Determine next state
					if (e(i,"b")) {
						state = 1;
					} else {
						int j = Integer.parseInt(i);
						switch(j) {
							case 1:
								state = 5;
								break;
							case 2:
								state = 4;
								break;
							default:
								state = 999;
								break;
						}
					}
					break;
				case 3: // Add Nutrient Targets
					while(true) { // loop until user exits
						// Display options
						pHeader("Add Nutrient Target");
						pnl();
						///   Getting possible nutrients
						p("Enter name or prefix of target nutrient(or bb to go back): ");
						i = inp().trim();
						if (e(i,"bb")) {
							state = 5;
							break;
						}
						ArrayList<NutriName> options = new ArrayList<NutriName>();;
						ArrayList<Integer> keys = NutrientInfo.getKeys();
						for(Integer key : keys) {
							NutriName nut_name = NutrientInfo.get(key);
							// Check if i is prefix of name
							if (nut_name.getName().toLowerCase().startsWith(i.toLowerCase())) {
								options.add(nut_name);
							}
						}
						///   Selecting one nutrient
						c = 1;
						for(NutriName nut : options) {
							pOption(c++,nut.getName());
						}
						if (options.size()==0) {
							p("No matching nutrient found");
							p("Press ENTER to continue...");
							waitEnter();
							break; // Try new input if none found
						}
						choose();
						// Get user input
						i = inp().trim();
						// Add nutrient to target
						int j = Integer.parseInt(i);
						NutriName cur_nut = options.get(j-1);
						p("Set target amount of nutrient("+cur_nut.getUnit()+"):");
						i = inp().trim();
						double amt = Double.parseDouble(i);
						cur_nt.addNutrient(cur_nut.getID(), amt);
						p("Nutrient Added");
						p("Press ENTER to continue...");
						waitEnter();
					}
					break;
				case 4: // View Meal
					// Display options
					pHeader(cur_name.toUpperCase()+" - Meal view");
					pSubHeader("Foods in meal are:");
					
					ArrayList<Food> mfoods = cur_meal.getFoods();
					c = 1;
					for (Food food: mfoods) {
						pOption(c++,Integer.toString(food.getCal())+" cal\t | "+food.allDescriptors());
					}
					pnl();
					pOption("+","Add Food");
					pOption(Arrays.asList(new String[] {"-","#"}),"Delete Food");
					if (cur_nt != null) pOption("f","FoodFit");
					else p("- Create Nutrient Target to enable foodfitting -");
					pOption("b", "Go back");
					// Get user input
					i = inp().trim();
					// Determine next state
					if (e(i,"b")) {
						state = 2;
					}else if (e(i,"f")) {
						cur_meal = Fitter.fitNutrTarFromMeal(knap, cur_nt, cur_meal);
						// State stays the same
					}else if (e(i,"+")) {
						state = 6;
					} else if (i.charAt(0)=='-') {
						int ind = Integer.parseInt(i.substring(1));
						cur_meal.remove(ind-1);
						// State stays the same
					} else {
						state = 999;
					}
					break;
				case 5: // Set Nutrient Targets
					// Check if Nutrient Target already exists
					if (targets.containsKey(cur_name)) {
						cur_nt = targets.get(cur_name);
					} else {
						pHeader("CALORIE SETUP");
						pSubHeader("Target number of calories:");
						i = inp().trim();
						int j = Integer.parseInt(i);
						cur_nt = new NutrientTarget(j);
						targets.put(cur_name, cur_nt);
					}
					// Display options
					pHeader(cur_name.toUpperCase()+" - Nutrient Targets");
					pSubHeader("Calories: "+cur_nt.getCal());
					ArrayList<Integer> IDs = cur_nt.getNutrients();
					c = 1;
					for(Integer id: IDs) {
						NutriName nut = NutrientInfo.get(id);
						String disp = Double.toString(cur_nt.getAmt(id))+nut.getUnit()+" - "+nut.getName();
						pOption(c++, disp);
					}
					pnl();
					pOption("+","Add Nutrient Target");
					pOption(Arrays.asList(new String[] {"-","#"}),"Delete Nutrient Target");
					pOption(Arrays.asList(new String[] {"c","#"}),"Change calories");
					pOption("b", "Go back");
					// Get user input
					i = inp().trim();
					// Determine next state
					if (e(i,"b")) {
						state = 2;
					}else if (i.charAt(0)=='c') {
						int cals = Integer.parseInt(i.substring(1).trim());
						cur_nt.setCal(cals);
						// State stays the same
					}else if (e(i,"+")) {
						state = 3;
					} else if (i.charAt(0)=='-') {
						int ind = Integer.parseInt(i.substring(1));
						int rID = IDs.get(ind-1);
						cur_nt.removeNutrient(rID);
						// State stays the same
					} else {
						state = 999;
					}
					
					break;
				case 6: // food adding options
					// Display options
					pHeader(cur_name.toUpperCase()+" - Add Food");
					pnl();
					choose();
					pOptionMany("Add by tag",
								"Add by nutrient");
					pOption("b", "Go back");
					// Get user input
					i = inp().trim();
					// Determine next state
					if (e(i,"b")) {
						state = 4;
					} else {
						int j = Integer.parseInt(i);
						if (j == 1) {
							state = 7;
						} else if (j == 2) {
							state = 8;
						} else {
							state = 999;
						}
					}
					break;
				case 7: // Add food by tag
					// Display options
					pHeader(cur_name.toUpperCase()+" - Add food by tag");
					pSubHeader("Enter name or prefix of tag you would like to search by(or bb to go back): ");
					// Get prefix
					i = inp().trim();
					if(e(i,"bb")) {
						state = 6;
						break;
					}
					// Lookup prefix
					Food[] matches = tree.getFood(i);
					pos = 0;
					// Create page system
					while (true) {
						// Display current page
						pHeader("Foods Found:");
						pSubHeader("Page ("+Integer.toString(1+pos/ELE_LIMIT)+"/"+Integer.toString(1+matches.length/ELE_LIMIT)+")");
						choose();
						c = 1;
						
						for(int j = pos; j<pos+ELE_LIMIT && j<matches.length; j++) {
							Food food = matches[j];
							pOption(c++, Integer.toString(food.getCal())+" cal\t | "+food.allDescriptors());
						}
						if (pos+ELE_LIMIT < matches.length) pOption("n", "Next page");
						if (pos > 0) pOption("p", "Prev page");
						pOption("b", "Go back");
						// Get user input
						i = inp().trim();
						// Determine next state
						if (e(i,"b")) {
							state = 6;
							break;
						} else if (e(i,"n")) {
							pos += ELE_LIMIT;
						} else if (e(i,"p")) {
							pos -= ELE_LIMIT;
						} else {
							int j = Integer.parseInt(i);
							int ind = pos + j - 1;
							cur_meal.add(matches[ind]);
							p("Food Added");
							p("Press ENTER to continue...");
							waitEnter();
							break;
						}
					}
					break;
				case 8: // Add by nutrient
					while(true) { // loop until user exits
						pHeader(cur_name.toUpperCase()+" - Add food by nutrient);");
						pnl();
						///   Getting possible nutrients
						p("Enter name or prefix of target nutrient(or bb to go back): ");
						i = inp().trim();
						if (e(i,"bb")) {
							state = 6;
							break;
						}
						ArrayList<NutriName> options = new ArrayList<NutriName>();;
						ArrayList<Integer> keys = NutrientInfo.getKeys();
						for(Integer key : keys) {
							NutriName nut_name = NutrientInfo.get(key);
							// Check if i is prefix of name
							if (nut_name.getName().toLowerCase().startsWith(i.toLowerCase())) {
								options.add(nut_name);
							}
						}
						///   Selecting one nutrient
						c = 1;
						for(NutriName nut : options) {
							pOption(c++,nut.getName());
						}
						if (options.size()==0) {
							p("No matching nutrient found");
							p("Press ENTER to continue...");
							waitEnter();
							break; // Try new input if none found
						}
						choose();
						// Get user input
						i = inp().trim();
						int k = Integer.parseInt(i);
						NutriName cur_nut = options.get(k-1);
						int id = cur_nut.getID();
						Food[] ordered = foods.toArray(new Food[foods.size()]);
						TimSort.sortMerge(ordered, id);
						// Request minimum value
						p("Enter minimum value("+cur_nut.getUnit()+"):");
						int mn = Integer.parseInt(inp().trim());
						// Request maximum value
						p("Enter maximum value("+cur_nut.getUnit()+"):");
						int mx = Integer.parseInt(inp().trim());
						// Find start/end indices
						int s = 0, e = ordered.length-1;
						while (s < ordered.length && ordered[s].getNutr(id) < mn) s++;
						while (e >= 0 && ordered[e].getNutr(id)>mx) e--;
						
						// Create page system
						pos = s;
						while (true) {
							// Display page
							pHeader("Foods Found:");
							pSubHeader("Page ("+Integer.toString(1+(pos-s+1)/ELE_LIMIT)+"/"+Integer.toString(1+(e-s+1)/ELE_LIMIT)+")");
							
							choose();
							p("Amount of "+NutrientInfo.get(id).getName());
							c = 1;
							
							for(int j = pos; j<pos+ELE_LIMIT && j<=e; j++) {
								Food food = ordered[j];
								pOption(c++, Double.toString(food.getNutr(id))+NutrientInfo.get(id).getUnit()+"\t | "+food.allDescriptors());
							}
							if (pos+ELE_LIMIT <= e) pOption("n", "Next page");
							if (pos > s) pOption("p", "Prev page");
							pOption("b", "Go back");
							// Get user input
							i = inp().trim();
							// Determine next state
							if (e(i,"b")) {
								state = 6;
								break;
							} else if (e(i,"n")) {
								pos += ELE_LIMIT;
							} else if (e(i,"p")) {
								pos -= ELE_LIMIT;
							} else {
								int j = Integer.parseInt(i);
								int ind = pos + j - 1;
								cur_meal.add(ordered[ind]);
								p("Food Added");
								p("Press ENTER to continue...");
								waitEnter();
								break;
							}
						}
						
					}
					break;
				default:
					// Entering this state signifies undefined behavior
					p("Undefined Behavior");
					p(" Press ENTER to exit..");
					// Exit system
					waitEnter();
					state = -1;
					break;
			}
		}
	}
	
	/**
	 * Makes new screen, and outputs text with dashed line underneath
	 * @param header Text to be outputted
	 */
	private static void pHeader(String header) {
		cls();
		System.out.println(header);
		String under = "";
		for(int i = 0; i < header.length(); i++) under += "-";
		System.out.println(under);
	}
	
	/**
	 * Outputs a subheader(text with newline afterwards)
	 * @param subheader Text to be outputed
	 */
	private static void pSubHeader(String subheader) {
		System.out.println(subheader);
		pnl();
	}
	
	/**
	 * Outputs a menu options in the form:
	 * (key) item
	 * 
	 * @param key Item to be outputted in parantheses
	 * @param item Item to be outputted after key
	 */
	private static void pOption(String key, String item) {
		System.out.println("("+key+") "+item);
	}
	
	/**
	 * Wrapper for regular pOption allowing key to be integer
	 * @param key Item to be outputted in parantheses
	 * @param item Item to be outputted after key
	 */
	private static void pOption(int key, String item) {
		pOption(Integer.toString(key),item);
	}
	
	/**
	 * Prints a list of menu options from an arbitrary number of items, with an enumerated key
	 * @param items Arbitrary number of string items to create menu options for
	 */
	private static void pOptionMany(String... items) {
		int i = 1;
		for (String item : items) {
			pOption(i++, item);
		}
	}
	
	/**
	 * Create menu option with multiple key items at front.
	 * Follows the form:
	 * (keys[0])(keys[1])...(keys[keys.size()-1]) item
	 * @param keys Text to put in parantheses at front
	 * @param item Text to put after keys
	 */
	private static void pOption(Iterable<String> keys, String item) {
		String res = "";
		for (String key: keys) 
			res += "("+key+")";
		res += " ";
		res += item;
		System.out.println(res);
	}
	
	/**
	 * Shorthand for printing newline
	 */
	private static void pnl() {
		System.out.println("");
	}
	
	/**
	 * Shorthand for asking user to choose option
	 */
	private static void choose() {
		System.out.println("Choose an option below: ");
	}
	
	/**
	 * Shorthand for regular printing
	 * @param text Text to print
	 */
	private static void p(String text) {
		System.out.println(text);
	}
	
	/**
	 * Shorthand to get line of input from user
	 * @return
	 */
	private static String inp() {
		return scan.nextLine();
	}
	
	/**
	 * Shorthand to check that strings are equal
	 * @param a First string
	 * @param b Second string
	 * @return True if strings are equivalent, else False
	 */
	private static boolean e(String a, String b) {
		return a.equals(b);
	}
	
	/**
	 * Shorthand to wait until the user presses enter key
	 */
	private static void waitEnter() {
		inp(); // Waits for any input
	}
	
	/**
	 * Shorthand to clear the screen
	 */
	private static void cls() {  
		// Clear screen with newlines
		for(int i = 0; i < SCREEN_HEIGHT/2; i++)
			p("\n");
	}  
	
}
