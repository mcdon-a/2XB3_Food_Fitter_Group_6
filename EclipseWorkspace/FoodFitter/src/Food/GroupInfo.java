package Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Class for importing the information for food groups from the CSV files
 *
 * @author Illya Pilipenko
 *         <p>
 *         Date: 2018 - 03 - 28
 */
public class GroupInfo {
	/**
	 * The filepath to the FOOD GROUP.csv file.
	 */
	private static final String FOOD_GROUP_PATH = "data\\FOOD GROUP.csv";
	/**
	* The row width in the csv files
	*/
	private static final int ROW_WIDTH = 4;
	/**
	* The hashmap containing all of the GroupName objects
	*/
	private static HashMap<Integer, GroupName> group_lookup = new HashMap<Integer, GroupName>();

	/**
	 * Method that takes a food object and its food ID, and then adds all of the
	 * nutrient IDs and their corresponding amounts for said food from the Nutrient
	 * Amount.csv file.
	 *
	 * @param read
	 *            BufferedReader object that is reading from the Nutrient Amount.csv
	 *            file; assumed to start at the 2nd nutrient for the current food.
	 * @param firstLine
	 *            The 1st nutrient for the current food. This is needed since the
	 *            1st line of the next food's nutrients needs to be read to make
	 *            sure there are no more nutrients remaining for the current food.
	 * @param food
	 *            The Food object to which to add nutrients.
	 * @param foodID
	 *            The Food ID of the current Food object; used to check that the
	 *            nutrients being added are for the right food.
	 * @return returns This method returns the 1st line of the next food's nutrients
	 *         so that it may be passed to the next food's addNutrients pass.
	 */
	public static void init_info() {
		// Define buffered reader
		BufferedReader br = null;
		String line = "";
		String delim = ",";
		// Set up group parameter variables
		int key;
		String name;

		try {
			// Set up buffered reader
			br = new BufferedReader(new FileReader(FOOD_GROUP_PATH));
			// Discard first row
			br.readLine();
			// Read Food Group csv line by line
			while ((line = br.readLine()) != null) {
				// Split line by comma
				String[] info = line.split(delim);
				// Discard improper row
				if (info.length != ROW_WIDTH)
					continue;
				// Fill paramaters
				key = Integer.parseInt(info[0]);
				name = info[2];
				// Fill Groupname object with parameters
				GroupName nw = new GroupName(key, name);
				// Store nw in hashmap
				group_lookup.put(key, nw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static GroupName get(int key) {
		return group_lookup.get(key);
	}

	/*
	 * Main script with example usage
	 */
	public static void main(String[] args) {
		int groupChoice = 12;
		init_info();
		System.out.println(get(groupChoice).getName());
	}
}
