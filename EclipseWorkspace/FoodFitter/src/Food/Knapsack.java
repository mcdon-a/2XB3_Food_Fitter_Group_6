package Food;

import java.util.ArrayList;

public class Knapsack {
	ArrayList<ArrayList<Meal>> buckets;
	int BUCKET_SIZE = 5;
	int MAX_CAL;
	int max_cal(ArrayList<Food> foods){
		int mx = foods.get(0).getCal();
		for (Food x: foods)
			if (x.getCal() > mx)
				mx = x.getCal();
		return mx;
	}

	public Knapsack(ArrayList<Food> foods) {
		MAX_CAL = max_cal(foods);
		buckets = new ArrayList<ArrayList<Meal>>(MAX_CAL+1);
		fill_buckets(foods);
	}

	public void fill_buckets(ArrayList<Food> foods) {
		// Insert single foods
		for (Food food: foods) {
			if (food.getCal()>MAX_CAL) break;
			if (buckets.get(food.getCal()).size() == BUCKET_SIZE) continue;
			buckets.get(food.getCal()).add(new Meal(food));
		}
		// Fill buckets using knapsack approach
		for(int i = 1; i <= MAX_CAL; i++) {
			for (Meal meal: buckets.get(i)) {
				for (Food food: foods) {
					int new_cal = i+food.getCal();
					if (new_cal>MAX_CAL) break;
					if (buckets.get(new_cal).size() == BUCKET_SIZE)
						continue;
					Meal new_meal = meal.copy();
					new_meal.add(food);
					buckets.get(new_cal).add(new_meal);
				}
			}
		}
	}



}
