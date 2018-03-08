package Food;
import java.io.*;
import java.util.Arrays;

/**
 * Class for importing CSV files from the 2015 Health Canada data on food nutritional values, and 
 * storing these values in an array of Food ADT objects.
 * 
 * @author Illya Pilipenko <p>
 * Date: 2018 - 03 - 04
 */

public class ReadCSV {

	
	/**
	 * Total amount of recored nutrients; should potentially be moved to Food ADT
	 */
	private static int TOTAL_NUTRIENT_AMOUNTS = 153;
	
	
	/**
	 * Method for resizing an array of Food.
	 * @param a Array to resize
	 * @param size New size for array
	 * @return Returns new resized array, or the old one if the size is <= the old one.
	 */
	private static Food[] resize (Food[] a, int size) {
		if (size <= a.length)
			return a;
		Food[] b = new Food[size];
		for (int i = 0; i < a.length; i ++)
			b[i] = a[i];
		return b;
	}
	
	
	/**
	 * Converts the ingredients in the CSV row into an array of ingredient tags
	 * @param line The row in the CSV to read from
	 * @return A String array containing all of the food tags. Can sometimes consist of a single element.
	 */
	private static String[] ingredientsToTags (String line) {
		String[] quoteSplit = line.split("\"", 3);
		if (quoteSplit.length != 1)
			return quoteSplit[1].split(", ");
		else
			return new String[] {line.split(",", 6)[4]};
	}
	
	
	/**
	 * Reads the CSV files stored in the "data\Food Name.csv" file path, and uses them to create an array of Food objects, 
	 * which it returns.
	 * @return 
	 */
	public static Food[] readFile() {
		String foodNamePath = "data\\Food Name.csv";
		Food[] foods = new Food [1626];
		String[] nutrients = new String [TOTAL_NUTRIENT_AMOUNTS];
		
		try{
			BufferedReader read = new BufferedReader ( new FileReader(foodNamePath));
			int i = 0;
			read.readLine();
			for (String line = read.readLine(); line != null && line != ""; line = read.readLine()) {
				
				  //System.out.println(i);
				
				  //int id = Integer.parseInt(line.split(",", 3)[1]);
				  String[] ingredients = ingredientsToTags(line);
				  
				  if (i == foods.length) {
					  //foods = resize(foods, i*2);
					  break;
				  }
				  
				  foods[i] = new Food(i, ingredients, new double[] {1.1,2.2});
				  
				  /*if (id != i) {
					  System.out.println("The location ID and stored ID of the food item are mismatched.");
					  return new Food[] (new Food (i, Arrays.toString(ingredients), new double[] {1.1,2.2}));
				  }*/
				  
				  i++;
				} 
			read.close();
			return foods;
		}
		catch (IOException e) {
			System.out.println("Problem reading file.");
			return new Food[] {new Food (-1, new String[] {"Bad"}, new double[] {1})};
		}
	}
	
	public static void main(String[] args) {
		System.out.println(readFile()[1600].toString());
	}

}
