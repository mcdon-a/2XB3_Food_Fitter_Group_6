package Food;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class GroupInfo {
	private static final String NUTRI_NAME_PATH = "data\\NUTRIENT NAME.csv";
	private static final int ROW_WIDTH = 8;
	private static HashMap<Integer, NutriName> nutrient_lookup = new HashMap<Integer, NutriName>();
	
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
				String[] info = line.split(delim);
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
				NutriName nw = new NutriName(code,symbol,unit,name,
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
	public static NutriName get(int key) {
		return nutrient_lookup.get(key);
	}
	/*
	 * Main script with example usage
	 */
	public static void main(String[] args) {
		init_info();
		System.out.println(get(211).getName());
	}
}
