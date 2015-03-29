package a7jedi;

import comp401.sushi.Plate;
import comp401.sushi.PlatePriceException;
import comp401.sushi.Sushi;

public class AgedPlateImpl implements AgedPlate{
	
	private Plate plate;
	private int age;
	
	public AgedPlateImpl(Plate plate, int age){
		this.plate = plate;
		this.age = age;
	}

	public void aging() {
		age += 1;
	}

	public int getAgeOfPlateAtPosition() {
		return age;
	}

	public Plate getWrappedPlate() {
		return plate;
	}
	
	@Override
	public Sushi getContents() {
		return plate.getContents();
	}

	@Override
	public Sushi removeContents() {
		return plate.removeContents();
	}

	@Override
	public void setContents(Sushi s) throws PlatePriceException {
		setContents(s);
	}

	@Override
	public boolean hasContents() {
		return plate.hasContents();
	}

	@Override
	public double getPrice() {
		return plate.getPrice();
	}

	@Override
	public Color getColor() {
		return plate.getColor();
	}

	@Override
	public double getProfit() {
		return plate.getProfit();
	}
}
