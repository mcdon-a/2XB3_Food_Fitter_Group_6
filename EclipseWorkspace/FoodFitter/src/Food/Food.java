package Food;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	private final String[] descriptor;			//stores an array of tags that describe the food
	private HashMap<Integer, Double> nutrVals;			//stores an array of nutritional values in respective order
	private int group;
	
	/**
	 * Constructor for Food Class
	 * @param descriptor an array of tags that represent the description of the food object
	 * @return instance of self
	 */
	public Food(String[] descriptor) {
		this.descriptor = descriptor;
		nutrVals = new HashMap<>();
	}
	
	/**
	 * @return the array of tags that represent the description of the food
	 */
	public String[] getDescriptor() {
		return this.descriptor;
	}
	
	/**
	 * @return the tags that represent the description as a single string 
	 */
	public String allDescriptors() {
		StringBuilder sb = new StringBuilder();
		if (descriptor.length > 0) {
			sb.append(descriptor[0]);
			for(int i = 1; i < descriptor.length; i++) {
				sb.append(", "+descriptor[i]);
			}
		}
		return sb.toString();
	}
	
	/**
	 * adds nutrition value to food object
	 * @param nutrID id of nutrition to add
	 * @param val value of nutrition to add
	 */
	public void addNutr(int nutrID, double val){
		nutrVals.put(nutrID,val);
	}
	
	/**
	 * @param nutrID id of nutrition value to get
	 * @return the value of the nutrition with the specified id
	 */
	public double getNutr(int nutrID){
		try {
			return nutrVals.get(nutrID);
		}catch (Exception e){
			return 0;
		}
	}
	
	/**
	 * @return an array list of all nutrition values
	 */
	public ArrayList<Integer> getNutrients() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.addAll(nutrVals.keySet());
		return temp;
	}
	
	/**
	 * assigns a food group to the food object
	 * @param groupID 
	 */
	public void addGroup(int groupID){
		group = groupID;
	}
	
	/**
	 * @return the food group of the food object
	 */
	public int getGroup(){
		return group;

	}
	
	/**
	 * @brief provides string representation of food object 
	 */
	public String toString() {
		String descriptor = "";
		for(int i = 0; i < this.descriptor.length; i++) {
			descriptor += this.descriptor[i];
			if (i < this.descriptor.length-1)
				descriptor += ", ";
		}
		
		return "(" + descriptor + ")";
	}
	
	/**
	 * @return get the calorie value of the food object
	 */
	public int getCal() {
		final int CALORIE_ID = 208;
		return (int)getNutr(CALORIE_ID);
	}
	
	
}
