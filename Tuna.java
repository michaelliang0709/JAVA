package a4;

public class Tuna extends IngredientImpl {

	private static double price = 0.65;
	private double amount;
	
	public Tuna(double amount) {
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
		return "tuna";
	}
}
