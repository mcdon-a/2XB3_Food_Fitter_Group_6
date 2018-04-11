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
	 * Reads the CSV files stored in the "data\FOOD GROUP.csv" file path,
	 * and initialises the HashMap to contain an instance of each food group as
	 * GroupName objects
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

	/**
	* Acceccor method to get a GroupName from the hashmap
	* @param key the key of the GroupName to get
	* @return The GroupName corresponding to the key
	*/
	public static GroupName get(int key) {
		return group_lookup.get(key);
	}
}
