package a4;

public class Crab extends IngredientImpl {

	private static double price = 1.5;
	private double amount;
	
	public Crab(double amount) {
		super(amount);
		this.amount = amount;
	}

	@Override
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
		return "crab";
	}
}
