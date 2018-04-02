package Food;

public class NutriName {
	private final int NutrientID;
	private final int NutrientCode;
	private final String NutrientSymbol;
	private final String NutrientUnit;
	private final String NutrientName;
	private final String NutrientNameF;
	private final String Tagname;
	private final int NutrientDecimals;
	
	
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
	public int getID() {
		return NutrientID;
	}
	public int getCode() {
		return NutrientCode;
	}
	public String getSymbol() {
		return NutrientSymbol;
	}
	public String getUnit() {
		return NutrientUnit;
	}
	public String getName() {
		return NutrientName;
	}
	public String getNameF() {
		return NutrientNameF;
	}
	public String getTagname() {
		return Tagname;
	}
	public int getDecimals() {
		return NutrientDecimals;
	}
}
