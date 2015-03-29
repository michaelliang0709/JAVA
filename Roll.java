package a4;

import java.util.ArrayList;

public class Roll implements Sushi{

	private Ingredient [] roll_ingredients;
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	private boolean isRepeated = false;
	private boolean hasSeaweed = false;
	
	public Roll(Ingredient[] roll_ingredients){
		this.roll_ingredients = roll_ingredients.clone();
		//  check whether each ingredient in the array passed to it is null
		for (Ingredient in : this.roll_ingredients){
			if (in == null){
				throw new RuntimeException("Null values are included!");
			}
		}
		// if the roll has no ingredients, it will just have seaweed as the wrapper
		if (this.roll_ingredients.length == 0){
			Ingredient [] empty_roll = new Ingredient[]{new Seaweed(0.1)};
			this.roll_ingredients = empty_roll;
		}
		ingredients.add(this.roll_ingredients[0]);
		// compare if the ingredients in the Array are repeated in the ArrayList
		for (int i = 1; i < this.roll_ingredients.length; i++){
			isRepeated = false;
			for (int j = 0; j <ingredients.size() && !isRepeated; j++){
				// if the ingredient is repeated, combine the two repeated instances
				if (this.roll_ingredients[i].getName().equals(ingredients.get(j).getName())){
					double amount = this.roll_ingredients[i].getAmount() + ingredients.get(j).getAmount();
					// remove the old item from the ArrayList and add the new combined item to it
					ingredients.remove(j);
					switch (this.roll_ingredients[i].getName()) {
					case "tuna": Tuna tuna = new Tuna(amount); ingredients.add(tuna); break;
					case "salmon": Salmon salmon = new Salmon(amount); ingredients.add(salmon); break;
					case "eel": Eel eel = new Eel(amount); ingredients.add(eel); break;
					case "crab": Crab crab = new Crab(amount); ingredients.add(crab); break;
					case "shrimp": Shrimp shrimp = new Shrimp(amount); ingredients.add(shrimp); break;
					case "avocado": Avocado avocado = new Avocado(amount); ingredients.add(avocado); break;
					case "rice": Rice rice = new Rice(amount); ingredients.add(rice); break;
					case "seaweed": Seaweed seaweed = new Seaweed(amount); ingredients.add(seaweed);
					}
					isRepeated = true;
				}
				// if the ingredient is not in the ArrayList and is not repeated, add it to the ArrayList
				if (j == (ingredients.size()-1) && !isRepeated){
					ingredients.add(this.roll_ingredients[i]);
					isRepeated = true;
				}
			}
		}
		for (int i = 0; i < ingredients.size(); i++){
			if (ingredients.get(i).getName().equals("seaweed")){
				// if the amount of seaweed is less than 0.1, create a new seaweed with 0.1 ounces
				if (ingredients.get(i).getAmount() - 0.1 < -0.0001){
					ingredients.remove(i);
					ingredients.add(i, new Seaweed(0.1));
					hasSeaweed = true; break;
				}
				// if it contains seaweed, b2=true
				hasSeaweed = true;
			}
		}
		// if the roll does not contain seaweed, add 0.1 ounces of seaweed to it
		if (!hasSeaweed){
			ingredients.add(new Seaweed(0.1));
		}
		this.roll_ingredients = ingredients.toArray(new Ingredient[0]);
	}

	@Override
	public Ingredient[] getIngredients() {
		return roll_ingredients.clone();
	}

	@Override
	public double getCost() {
		double cost = 0.0;
		for (Ingredient ingredient : roll_ingredients){
			cost += ingredient.getCost();
		}
		return cost;
	}

	@Override
	public boolean hasRice() {
		for (Ingredient in : roll_ingredients){
			switch (in.getName()) {
			case "rice": return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasShellfish() {
		for (Ingredient in : roll_ingredients){
			switch (in.getName()) {
			case "crab": return true;
			case "shrimp": return true;
			}
		}
		return false;
	}

	@Override
	public boolean isVegetarian() {
		for (Ingredient in : roll_ingredients){
			// if it has meat, it is not vegetarian
			switch (in.getName()) {
			case "tuna": return false;
			case "salmon": return false;
			case "eel": return false;
			case "crab": return false;
			case "shrimp": return false;
			}
		}
		return true;
	}
}
