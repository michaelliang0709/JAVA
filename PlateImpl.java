package a4;

public class PlateImpl implements Plate{
	
	private double price;
	private Color color;
	private Sushi sushi;
	
	public PlateImpl(double price, Plate.Color color){
		this.price  = price;
		this.color = color;
	}

	@Override
	public Sushi getContents() {
		return sushi;
	}

	@Override
	public Sushi removeContents() {
		if (this.getContents() == null){
			return null;
		}
		else {
			Sushi prior_sushi = sushi;
			sushi = null;
			return prior_sushi;
		}
	}

	@Override
	public void setContents(Sushi s) throws PlatePriceException {
		if (s == null){
			throw new IllegalArgumentException();
		}
		else if (price <= s.getCost()){
			throw new PlatePriceException();
		}
		else {
			sushi = s;
		}
	}

	@Override
	public boolean hasContents() {
		if (this.getContents() != null)
		return true;
		else {
			return false;
		}
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public double getProfit() {
		if (this.getContents() == null){
			return 0.0;
		}
		else {
			return price - sushi.getCost();
		}
	}
}
