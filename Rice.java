package a4;

public class Rice extends IngredientImpl {

	private static double price = 0.25;
	private double amount;
	
	public Rice(double amount) {
		super(amount);
		this.amount = amount;
	}

	public double getCost(){
		return amount * price;
	}
	
	@Override
	public boolean isRice(){
		return true;
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
		return "rice";
	}
}
