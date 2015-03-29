package a4;

public class GoldPlate extends PlateImpl implements Plate {
	
	private static Color color = Color.GOLD;
	
	public GoldPlate(Sushi contents, double price) throws PlatePriceException{
		super(price, color);
		if (price <= 5.0){
			throw new IllegalArgumentException();
		}
		if (contents != null && contents.getCost() > price ){
			throw new PlatePriceException();
		}
		if (contents != null) {
			this.setContents(contents);
		}
	}
}
