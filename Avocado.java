package a4;

public class Avocado extends IngredientImpl {

	private static double price = 0.8;
	private double amount;
	
	public Avocado(double amount) {
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
		return false;
	}
	
	@Override
	public boolean isVegetarian(){
		return true;
	}
	
	@Override
	public String getName(){
		return "avocado";
	}
}
