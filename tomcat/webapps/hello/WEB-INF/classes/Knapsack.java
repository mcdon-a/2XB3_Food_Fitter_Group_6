

import java.util.ArrayList;

public class Knapsack {
	private ArrayList<ArrayList<Meal>> buckets;
	private int BUCKET_SIZE = 5;
	private int MAX_CAL;

	private int max_cal(ArrayList<Food> foods){
		int mx = foods.get(0).getCal();
		for (Food x: foods)
			if (x.getCal() > mx)
				mx = x.getCal();
		return mx;
	}

	public ArrayList<Meal> mealsInRange(int l,int r) { // inclusive
		ArrayList<Meal> res = new ArrayList<Meal>();
		for (int i = l; i <= r; i++) {
			for (int j = 0; j < buckets.get(i).size(); j++) {
				Meal meal = buckets.get(i).get(j);
				res.add(meal);
			}
		}
		return res;
	}

	public Knapsack(ArrayList<Food> foods) {
		MAX_CAL = max_cal(foods);
		// set up buckets
		buckets = new ArrayList<ArrayList<Meal>>(MAX_CAL+1);
		for (int i = 0; i <= MAX_CAL; i++)
			buckets.add(new ArrayList<Meal>());
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
			for(int j = 0; j < buckets.get(i).size(); j++) {
				Meal meal = buckets.get(i).get(j);
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
