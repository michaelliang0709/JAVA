package a4;

public class Sashimi implements Sushi{

	public enum SashimiType {TUNA, SALMON, EEL, CRAB, SHRIMP};
	private SashimiType type;
	Ingredient [] ingredients = new Ingredient[1];  // Sashimi only has one ingredient
	private static double amount = 0.75;
	
	public Sashimi(SashimiType type){
		this.type = type;
	}
	
	@Override
	public Ingredient[] getIngredients() {
		// use switch to determine which ingredient Sashimi has
		switch (type) {
		case TUNA: Tuna tuna = new Tuna(amount); ingredients[0] = tuna; break;
		case SALMON: Salmon salmon = new Salmon(amount); ingredients[0] = salmon; break;
		case EEL: Eel eel = new Eel(amount); ingredients[0] = eel; break;
		case CRAB: Crab crab = new Crab(amount); ingredients[0] = crab; break;
		case SHRIMP: Shrimp shrimp = new Shrimp(amount); ingredients[0] = shrimp;
		}
		return ingredients.clone();
	}

	@Override
	public double getCost() {
		// use switch to determine the ingredient's type and return its cost
		switch (type) {
		case TUNA: Tuna tuna = new Tuna(amount); return tuna.getCost();
		case SALMON: Salmon salmon = new Salmon(amount); return salmon.getCost();
		case EEL: Eel eel = new Eel(amount); return eel.getCost();
		case CRAB: Crab crab = new Crab(amount); return crab.getCost();
		case SHRIMP: Shrimp shrimp = new Shrimp(amount); return shrimp.getCost();
		}
		return 0.0;
	}

	@Override
	public boolean hasRice() {
		return false;
	}

	@Override
	public boolean hasShellfish() {
		switch (type) {
		case TUNA: Tuna tuna = new Tuna(amount); return tuna.isShellfish();
		case SALMON: Salmon salmon = new Salmon(amount); return salmon.isShellfish();
		case EEL: Eel eel = new Eel(amount); return eel.isShellfish();
		case CRAB: Crab crab = new Crab(amount); return crab.isShellfish();
		case SHRIMP: Shrimp shrimp = new Shrimp(amount); return shrimp.isShellfish();
		}
		return false;
	}

	@Override
	public boolean isVegetarian() {
		return false;
	}
}
