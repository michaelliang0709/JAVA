package a4;

public class GreenPlate extends PlateImpl implements Plate {
	
	private static Color color = Color.GREEN;
	private static double price = 2.0;
	
	public GreenPlate(Sushi contents) throws PlatePriceException{
		super(price, color);
		if (contents != null && contents.getCost() > price ){
			throw new PlatePriceException();
		}
		if (contents != null) {
			this.setContents(contents);
		}
	}
}
