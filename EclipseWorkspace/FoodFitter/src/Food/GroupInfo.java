package Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class GroupInfo {
	private static final String FOOD_GROUP_PATH = "data\\FOOD GROUP.csv";
	private static final int ROW_WIDTH = 4;
	private static HashMap<Integer, GroupName> group_lookup = new HashMap<Integer, GroupName>();
	
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
			while((line = br.readLine()) != null) {
				// Split line by comma
				String[] info = line.split(delim);
				// Discard improper row
				if (info.length != ROW_WIDTH) continue;
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
		init_info();
		System.out.println(get(12).getName());
	}
}
