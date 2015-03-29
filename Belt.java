package a7jedi;

import java.util.NoSuchElementException;
import java.util.Observable;
import a7jedi.PlateEvent.EventType;
import comp401.sushi.Plate;

public class Belt extends Observable {

	AgedPlateImpl[] belt;
	
	public Belt(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Illegal belt size");
		}
		belt = new AgedPlateImpl[size];
	}
	
	public int getSize() {
		return belt.length;
	}
	
	public Plate getPlateAtPosition(int position) {
		if (belt[correct_position(position)] != null){
			return belt[correct_position(position)].getWrappedPlate();
		}
		else {
			return null;
		}
	}
	
	public void setPlateAtPosition(Plate plate, int position) throws BeltPlateException {
		if (plate == null) {
			throw new IllegalArgumentException();
		}
		if (getPlateAtPosition(position) != null) {
			throw new BeltPlateException(position, plate, this);
		} else {
			belt[correct_position(position)] = new AgedPlateImpl(plate, 0);
			setChanged();
			notifyObservers(new PlateEvent(EventType.PLATE_PLACED, plate, correct_position(position)));
		}
	}

	public void clearPlateAtPosition(int position) {
		Plate plate = belt[correct_position(position)].getWrappedPlate();
		if (plate != null){
			setChanged();
			notifyObservers(new PlateEvent(EventType.PLATE_REMOVED, plate, correct_position(position)));
		}
		belt[correct_position(position)] = null;
	}
	
	public Plate removePlateAtPosition(int position) {
		Plate plate_at_position = getPlateAtPosition(position);
		if (plate_at_position == null) {
			throw new NoSuchElementException();
		}
		clearPlateAtPosition(position);
		return plate_at_position;
	}
	
	public int setPlateNearestToPosition(Plate plate, int position) throws BeltFullException {
		for (int offset=0; offset < getSize(); offset++) {
			try {
				setPlateAtPosition(plate, position+offset);
				return position+offset;
			} catch (BeltPlateException e) {
			}
		}
		throw new BeltFullException(this);
	}

	public void rotate() {
		AgedPlateImpl last_plate = belt[getSize()-1];
		for (int i = getSize() - 1; i > 0; i--) {
			belt[i] = belt[i-1];
		}
		belt[0] = last_plate;
		for (int i = getSize() - 1; i >= 0; i--) {
			if (getPlateAtPosition(i) != null){
				belt[i].aging();
			}
		}
		for(int i = 0; i < getSize(); i++) {
			if (belt[i] != null && belt[i].getContents() != null){
				if (belt[i].getContents().hasShellfish() 
						&& getAgeOfPlateAtPosition(i) >= getSize()){
					setChanged();
					notifyObservers(new PlateEvent(EventType.PLATE_SPOILED, getPlateAtPosition(i), i));
				}
				else if (!belt[i].getContents().hasShellfish() && !belt[i].getContents().isVegetarian() 
						&& getAgeOfPlateAtPosition(i) >= 2*getSize()){
					setChanged();
					notifyObservers(new PlateEvent(EventType.PLATE_SPOILED, getPlateAtPosition(i), i));
				}
				else if (belt[i].getContents().isVegetarian() && 
						getAgeOfPlateAtPosition(i) >= 3 * getSize()){
					setChanged();
					notifyObservers(new PlateEvent(EventType.PLATE_SPOILED, getPlateAtPosition(i), i));
				}
			}
		}
	}
	
	private int correct_position(int position) {
		if (position < 0) {
			return ((position%getSize())+getSize())%getSize();
		}
		return position%getSize();
	}
	
	public int getAgeOfPlateAtPosition(int position){
		if (belt[correct_position(position)] != null){
			return belt[correct_position(position)].getAgeOfPlateAtPosition();
		}
		else {
			return -1;
		}
	}
}
