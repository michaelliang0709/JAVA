package a7jedi;

import comp401.sushi.Plate;

public interface AgedPlate extends Plate{
	void aging();
	int getAgeOfPlateAtPosition();
	Plate getWrappedPlate();
}
