package a4;

public class Salmon extends IngredientImpl {

	private static double price = 0.75;
	private double amount;
	
	public Salmon(double amount) {
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
		return false;
	}
	
	@Override
	public String getName(){
		return "salmon";
	}
}
