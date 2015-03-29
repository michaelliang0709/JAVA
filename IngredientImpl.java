package a4;

abstract public class IngredientImpl implements Ingredient{

	private double amount;
	
	public IngredientImpl (double amount){
		this.amount = amount;
		if (this.amount <= 0) {
			throw new RuntimeException("Amount specified is not greater than 0.");
		}
	}
	
	public double getAmount(){
		return amount;
	}
}
