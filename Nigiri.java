package a4;

public class Nigiri implements Sushi{

	public enum NigiriType {TUNA, SALMON, EEL, CRAB, SHRIMP};
	private NigiriType type;
	private Ingredient [] ingredients = new Ingredient[2]; // Nigiri have one ingredient and rice
	private static double amount = 0.75;
	private Rice rice = new Rice(0.5);
	
	public Nigiri(NigiriType type){
		this.type = type;
	}

	@Override
	public Ingredient[] getIngredients() {
		// use switch to determine which ingredient Nigiri has besides rice
		switch (type) {
		case TUNA: Tuna tuna = new Tuna(amount); ingredients[0] = tuna; break;
		case SALMON: Salmon salmon = new Salmon(amount); ingredients[0] = salmon; break;
		case EEL: Eel eel = new Eel(amount); ingredients[0] = eel; break;
		case CRAB: Crab crab = new Crab(amount); ingredients[0] = crab; break;
		case SHRIMP: Shrimp shrimp = new Shrimp(amount); ingredients[0] = shrimp;
		}
		ingredients[1] = rice;
		return ingredients.clone();
	}

	@Override
	public double getCost() {
		double rice_price = rice.getCost();
		// use switch to determine the ingredient's type and cost, and plus the rice's cost
		switch (type) {
		case TUNA: Tuna tuna = new Tuna(amount); return tuna.getCost() + rice_price;
		case SALMON: Salmon salmon = new Salmon(amount); return salmon.getCost() + rice_price;
		case EEL: Eel eel = new Eel(amount); return eel.getCost() + rice_price;
		case CRAB: Crab crab = new Crab(amount); return crab.getCost() + rice_price;
		case SHRIMP: Shrimp shrimp = new Shrimp(amount); return shrimp.getCost() + rice_price;
		}
		return rice_price;
	}

	@Override
	public boolean hasRice() {
		return true;
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
