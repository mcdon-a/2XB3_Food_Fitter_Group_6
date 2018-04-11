package Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Provides an interface for looking up inforation about nutrients from their ID.
 * Loads in information from the Nutrient Name csv file.
 * 
 * @author Wyatt Wismer
 *
 */

public class NutrientInfo {
	/// Location of Nutrient Name data file
	private static final String NUTRI_NAME_PATH = "data\\NUTRIENT NAME.csv";
	/// Number of items in row
	private static final int ROW_WIDTH = 8;
	/// Hashmap for looking up nutrients
	private static HashMap<Integer, NutriName> nutrient_lookup = new HashMap<Integer, NutriName>();
	
	/**
	 * Read nutrient name file into data HashMap
	 */
	public static void init_info() {
		// Define buffered reader
		BufferedReader br = null;
		String line = "";
		String delim = ",";
		int key;
		// Set up nutrient parameter variables
		int code;
		String symbol;
		String unit;
		String name;
		String nameF;
		String tagname;
		int decimals;
		
		try {
			// Set up buffered reader
			br = new BufferedReader(new FileReader(NUTRI_NAME_PATH));
			// Discard first row
			br.readLine();
			// Read Nutrient Name csv line by line
			while((line = br.readLine()) != null) {
				// Split line by comma
				String[] info = SplitWithQuotes(line);
				// Discard improper row
				if (info.length != ROW_WIDTH) continue;
				// Fill paramaters
				key = Integer.parseInt(info[0]);
				code = Integer.parseInt(info[1]);
				symbol = info[2];
				unit = info[3];
				name = info[4];
				nameF = info[5];
				tagname = info[6];
				decimals = Integer.parseInt(info[7]);
				// Fill Nutriname object with parameters
				NutriName nw = new NutriName(key, code,symbol,unit,name,
											nameF,tagname,decimals);
				// Store nw in hashmap
				nutrient_lookup.put(key, nw);
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
	 * Get NutriName object with supplied ID key
	 * @param key ID of NutriName object to lookup
	 * @return NutriName object with ID matching supplied key
	 */
	public static NutriName get(int key) {
		return nutrient_lookup.get(key);
	}
	
	/**
	 * Get all Nutrient IDs in hash map
	 * @return All keys of hash map as an ArrayList
	 */
	public static ArrayList<Integer> getKeys(){
		return new ArrayList<Integer>(nutrient_lookup.keySet());
	}
	
	/**
	 * Modified split that accounts for quotes in line (ignoring commas within quotes)
	 * @param line Line containing items seperates by commas (along with quotes)
	 * @return Array of strings containing all items
	 */
	private static String[] SplitWithQuotes(String line) {
		/// Keeps track of if current char is inside of quotes
		boolean inQuotes = false;
		// Create ArrayList to hold result
		ArrayList<String> res = new ArrayList<String>();
		// Initialize string builder
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<line.length(); i++) {
			if (line.charAt(i)=='"') {
				inQuotes = !inQuotes; // flip inQuotes state
			} else if(line.charAt(i) == ',' && !inQuotes) { // Save new string
				String temp = sb.toString();
				res.add(temp);
				sb = new StringBuilder(); // Reset string builder
			} else {                 // Grow string
					sb.append(line.charAt(i));
			}
		}
		// Deal with last element
		if(sb.length()>0) {
			String temp = sb.toString();
			res.add(temp);
		}
		return res.toArray(new String[res.size()]);
	}
	
	/**
	 * Example usage
	 */
	public static void main(String[] args) {
		init_info();
		System.out.println(get(211).getName());
	}
	
}
