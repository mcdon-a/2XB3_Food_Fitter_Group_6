public class Wrapper {

    private ArrayList<Food> foods;

    private enum Nutrient {
        CALORIES, PROTEIN, CARBS, FIBRE
    }

    public void getFoods() {
        foods = ReadCSV.readFile();
    }

    public ArrayList<ArrayList<Meal>> getKnapsack(Nutrient n) {
        Knapsack knapsack = new Knapsack(foods);
        return knapsack.buckets;
    }
}