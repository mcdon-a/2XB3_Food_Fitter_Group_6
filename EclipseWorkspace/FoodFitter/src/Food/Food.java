package Food;

/**
 * The Food class is an ADT that represents a food item by its id, descriptor, and nutritional values.
 * It accepts 3 arguments; the id (int), the descriptor (String of tags separated by commas), 
 * and the nutritional values (
 * 
 * Note that the inputed descriptor is converted to an array of its tags
 * 
 * @author Thomas Armena
 *
 */

public class Food {
	
	private final int id;						//stores the food ID
	private final String[] descriptor;			//stores an array of tags that describe the food
	private final double[] nutrVals;			//stores an array of nutritional values in respective order
	
	//constructor
	public Food(int id, String[] descriptor, double[] nutrVals) {
		this.id = id;
		this.descriptor = descriptor;
		this.nutrVals = nutrVals;
	}
	
	//GETTER methods
	public int getID() {
		return this.id;
	}
	
	public String[] getDescriptor() {
		return this.descriptor;
	}
	
	public double[] nutrVals() {
		return this.nutrVals;
	}
	
	//String representation 
	public String toString() {
		String descriptor = "";
		for(int i = 0; i < this.descriptor.length; i++) {
			descriptor += this.descriptor[i];
			if (i < this.descriptor.length-1)
				descriptor += ", ";
		}
		String nutrVals = "";
		for(int i = 0; i < this.nutrVals.length; i++) {
			nutrVals += Double.toString(this.nutrVals[i]);
			if (i < this.nutrVals.length - 1)
				nutrVals += ", ";
		}
		return "{(Food id: "+ this.id + "), (Descriptor: " + descriptor + "),(Nutritional Values: " + nutrVals + ")}";
	}
	
	//Temporary main to test code
	public static void main(String[] args) {
		//Creating a test food item
		int id = 400078381;
		String[] descriptor = {"Fast foods", "sandwiches and burgers", "hamburger", "regular", "single patty", "plain"};
		double[] nutrVals = {1,2,3,4,5,6,7,8};
		Food food1 = new Food(id,descriptor,nutrVals);
		
		//Testing the outputs
		System.out.println(food1);
		System.out.println(food1.getDescriptor()[1]);
	}

}
