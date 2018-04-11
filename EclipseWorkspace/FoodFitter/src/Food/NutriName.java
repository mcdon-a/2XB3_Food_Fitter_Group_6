package Food;

/**
 * Contains information about a given Nutrient Type as a NutriName ADT.
 * Provides interface to access all properties in ADT.
 * 
 * @author Wyatt Wismer
 *
 */

public class NutriName {
	/// Contains ID of nutrient
	private final int NutrientID;
	/// Contains code for nutrient
	private final int NutrientCode;
	/// Contains symbol for nutrient
	private final String NutrientSymbol;
	/// Contains unit nutrient is measured in
	private final String NutrientUnit;
	/// Contains common name of nutrient
	private final String NutrientName;
	/// Contains dditional name for nutrient
	private final String NutrientNameF;
	/// Contains shortened name for nutrient
	private final String Tagname;
	/// Number of decimal places in nutrient measurements
	private final int NutrientDecimals;
	
	/**
	 * Constructs a new NutriName object from supplied properties
	 * @param ID ID of nutrient
	 * @param code Code for nutrient
	 * @param symbol Symbol for nutrient
	 * @param unit Units of nutrient
	 * @param name Common name of nutrient
	 * @param nameF Additional name of nutrient
	 * @param tagname Shortened name of nutrient
	 * @param decimals Number of decimal places in nutrient measurements
	 */
	public NutriName(int ID, int code, String symbol, String unit, 
			String name,String nameF,String tagname,int decimals){
		NutrientID = ID;
		NutrientCode = code;
		NutrientSymbol = symbol;
		NutrientUnit = unit;
		NutrientName = name;
		NutrientNameF = nameF;
		Tagname = tagname;
		NutrientDecimals = decimals;
	}
	
	/**
	 * Get ID of nutrient
	 * @return ID of nutrient
	 */
	public int getID() {
		return NutrientID;
	}
	
	/**
	 * Get code of nutrient
	 * @return Code of nutrient
	 */
	public int getCode() {
		return NutrientCode;
	}
	
	/**
	 * Get symbol of nutrient
	 * @return Symbol of nutrient
	 */
	public String getSymbol() {
		return NutrientSymbol;
	}
	
	/**
	 * Get unit of nutrient
	 * @return Unit of nutrient
	 */
	public String getUnit() {
		return NutrientUnit;
	}
	
	/**
	 * Get name of nutrient
	 * @return Name of nutrient
	 */
	public String getName() {
		return NutrientName;
	}
	
	/**
	 * Get additional name of nutrient
	 * @return Additional name of nutrient
	 */
	public String getNameF() {
		return NutrientNameF;
	}
	
	/**
	 * Get shortened name of nutrient
	 * @return Shortened name of nutrient
	 */
	public String getTagname() {
		return Tagname;
	}
	
	/**
	 * Get number of decimal places in measurement
	 * @return Number of decimal places in measurement 
	 */
	public int getDecimals() {
		return NutrientDecimals;
	}
}
