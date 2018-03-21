

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for importing CSV files from the 2015 Health Canada data on food
 * nutritional values, and storing these values in an array of Food ADT objects.
 *
 * @author Illya Pilipenko
 *         <p>
 *         Date: 2018 - 03 - 04
 */

public class ReadCSV {

	/**
	 * The filepath to the Food Name.csv file.
	 */
	private static final String FOOD_NAME_PATH = "data\\Food Name.csv";
	/**
	 * The filepath to the Nutrient Amount.csv file.
	 */
	private static final String NUTRIENT_AMOUNT_PATH = "data\\Nutrient Amount.csv";

	/*
	 * /** Method for resizing an array of Food.
	 *
	 * @param a Array to resize
	 *
	 * @param size New size for array
	 *
	 * @return Returns new resized array, or the old one if the size is <= the old
	 * one.
	 *
	 * private static Food[] resize (Food[] a, int size) { if (size <= a.length)
	 * return a; Food[] b = new Food[size]; for (int i = 0; i < a.length; i ++) b[i]
	 * = a[i]; return b; }
	 */

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
	private static String addNutrients(BufferedReader read, String firstLine, Food food, int foodID) {
		int ID = Integer.parseInt(firstLine.split(",", 3)[1]);
		double val = Double.parseDouble(firstLine.split(",", 4)[2]);
		food.addNutr(ID, val);

		try {
			String line;
			for (line = read.readLine(); line != null && Integer.parseInt(line.split(",", 2)[0]) == foodID; line = read.readLine()) {
				ID = Integer.parseInt(line.split(",", 3)[1]);
				val = Double.parseDouble(line.split(",", 4)[2]);
				food.addNutr(ID, val);
			}
			return line;
		}

		catch (IOException e) {
			System.out.println("Problem reading nutrient amount file.");
			return "-1,-1,-1, ";
		}
	}

	/**
	 * Converts the ingredients in the CSV row into an array of ingredient tags
	 *
	 * @param line
	 *            The row in the CSV to read from
	 * @return A String array containing all of the food tags. Can sometimes consist
	 *         of a single element.
	 */
	private static String[] ingredientsToTags(String line) {
		String[] quoteSplit = line.split("\"", 3);
		if (quoteSplit.length != 1)
			return quoteSplit[1].split(", ");
		else
			return new String[] { line.split(",", 6)[4] };
	}

	/**
	 * Reads the CSV files stored in the "data\Food Name.csv" file path, and uses
	 * them to create an array of Food objects, which it returns. It also adds the
	 * nutrient IDs and amount values from the data\Nutrient Amount.csv file path to
	 * the corresponding Food objects.
	 *
	 * @return returns a pointer to the ArrayList of created Food objects.
	 */
	public static ArrayList<Food> readFile() {
		ArrayList<Food> foods = new ArrayList<Food>();

		try {
			BufferedReader readFood = new BufferedReader(new FileReader(FOOD_NAME_PATH));
			BufferedReader readNutrients = new BufferedReader(new FileReader(NUTRIENT_AMOUNT_PATH));
			readNutrients.readLine();
			readFood.readLine();
			String nextNutrients = readNutrients.readLine();

			for (String line = readFood.readLine(); line != null && line != ""; line = readFood.readLine()) {
				int foodID = Integer.parseInt(line.split(",", 3)[1]);
				String[] ingredients = ingredientsToTags(line);

				foods.add(new Food(ingredients));
				nextNutrients = addNutrients(readNutrients, nextNutrients, foods.get(foods.size() - 1), foodID);
			}
			readFood.close();
			return foods;
		} catch (IOException e) {
			System.out.println("Problem reading food name file.");
			return new ArrayList<Food>();
		}
	}

	public static void main(String[] args) {
		readFile();
		System.out.println(readFile().get(5689).toString());
		//System.out.println(readFile().get(2000).getNutr(306));
	}

}
