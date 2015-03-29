package a4;

public class Seaweed extends IngredientImpl {

	private static double price = 0.4;
	private double amount;
	
	public Seaweed(double amount) {
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
		return false;
	}
	
	@Override
	public boolean isVegetarian(){
		return true;
	}
	
	@Override
	public String getName(){
		return "seaweed";
	}
}
