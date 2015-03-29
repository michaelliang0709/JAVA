package a4;

public class Shrimp extends IngredientImpl {

	private static double price = 1.15;
	private double amount;
	
	public Shrimp(double amount) {
		super(amount);
		this.amount = amount;
	}

	public double getCost(){
		return amount * price;
	}
	
	@Override
	public boolean isRice(){
		return false;
	}
	
	@Override
	public boolean isShellfish(){
		return true;
	}
	
	@Override
	public boolean isVegetarian(){
		return false;
	}
	
	@Override
	public String getName(){
		return "shrimp";
	}
}
