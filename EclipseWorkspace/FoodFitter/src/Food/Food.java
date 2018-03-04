package Food;

public class Food {
	
	private final int id;
	private final String[] descriptor;
	private final double[] nutrVals;
	
	public Food(int id, String descriptor, double[] nutrVals) {
		this.id = id;
		this.descriptor = descriptor.split(", ");
		this.nutrVals = nutrVals;
	}
	
	public String toString() {
		String descriptor = "";
		for(int i = 0; i < this.descriptor.length; i++) {
			descriptor += this.descriptor[i];
			if (i < this.descriptor.length-1)
				descriptor += ", ";
		}
		String natrVals = "";
		for(int i = 0; i < this.nutrVals.length; i++) {
			natrVals += ", ";
		}
		return "{ (Food id: "+ this.id + "), (Descriptor: " + descriptor + "),(Nutritional Values: " + natrVals + ")}";
	}
	
	public static void main(String[] args) {
		int id = 400078381;
		String descriptor = "Fast foods, sandwiches and burgers, hamburger, regular, single patty, plain";
		double[] nutrVals = {1,2,3,4,5,6,7,8};
		Food food1 = new Food(id,descriptor,nutrVals);
		System.out.println(food1);
	}

}
