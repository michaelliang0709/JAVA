package a4;

public class RedPlate extends PlateImpl implements Plate {
	
	private static Color color = Color.RED;
	private static double price = 1.0;
	
	public RedPlate(Sushi contents) throws PlatePriceException{
		super(price, color);
		if (contents != null && contents.getCost() > price ){
			throw new PlatePriceException();
		}
		if (contents != null) {
			this.setContents(contents);
		}
	}
}
