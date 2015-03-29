package a4;

public class BluePlate extends PlateImpl implements Plate {
	
	private static Color color = Color.BLUE;
	private static double price = 4.0;
	
	public BluePlate(Sushi contents) throws PlatePriceException{
		super(price, color);
		if (contents != null && contents.getCost() > price ){
			throw new PlatePriceException();
		}
		if (contents != null) {
			this.setContents(contents);
		}
	}
}
