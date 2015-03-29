package a5jedi;

import comp401.sushi.Plate;
import comp401.sushi.Plate.Color;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BeltIterator implements Iterator<Plate>{
	
	private Belt belt;
	private int start_position;
	private int next_position;
	private boolean next_has_been_called = false;
	private double max_price;
	private Color color_filter;
	private boolean has_price_filter = false;
	private boolean has_color_filter = false;
	
	public BeltIterator(Belt belt, int start_position){
		this.belt = belt;
		int circular_position;
		int size = this.belt.getSize();
		if (start_position >= 0){
			circular_position = start_position % size;
		}
		else {
			circular_position = ((start_position % size) + size) % size;
		}
		this.start_position = circular_position;
		next_position = this.start_position;
	}
	
	public BeltIterator(Belt belt, int start_position, double max_price){
		this(belt,start_position);
		this.max_price = max_price;
		has_price_filter = true;
	}
	
	public BeltIterator(Belt belt, int start_position, Plate.Color color_filter){
		this(belt,start_position);
		this.color_filter = color_filter;
		has_color_filter = true;
	}
	
	public boolean hasNext(){
		// loop from current position to the end first
		for (int i = start_position; i < belt.getSize(); i++){
			if (belt.getPlateAtPosition(i) != null){
				return true;
			}
		}
		// then loop from 0 to current position - 1
		for (int i = 0; i < start_position; i++){
			if (belt.getPlateAtPosition(i) != null){
				return true;
			}
		}
		return false;
	}
	
	public Plate next(){
		next_has_been_called = true;
		while (this.hasNext()){
			// if the next position is within the size of belt
			if (next_position < belt.getSize()){
				Plate plate = belt.getPlateAtPosition(next_position);
				next_position++;
				double price = plate.getPrice();
				Color color = plate.getColor();
				// if there is no filter, return the next plate
				if (!has_price_filter && !has_color_filter){
					return plate;
				}
				// return the plate that matches the filter, if not, move to the next one
				if (has_price_filter && price <= max_price){
					return plate;
				}
				else if (has_color_filter && color == color_filter){
					return plate;
				}
				else {
					continue;
				}
			}
			// if not, treat the belt as if it were circular
			else {
				next_position = next_position % belt.getSize();
				Plate plate = belt.getPlateAtPosition(next_position);
				next_position++;
				if (plate == null){continue;}
				double price = plate.getPrice();
				Color color = plate.getColor();
				// if there is no filter, return the next plate
				if (!has_price_filter && !has_color_filter){
					return plate;
				}
				// return the plate that matches the filter, if not, move to the next one
				if (has_price_filter && price <= max_price){
					return plate;
				}
				else if (has_color_filter && color == color_filter){
					return plate;
				}
				else {
					continue;
				}
			}
		}
		throw new NoSuchElementException();
	}
	
	public void remove(){
		if (!next_has_been_called){
			throw new IllegalStateException();
		}
		next_has_been_called = false;
		belt.removePlateAtPosition(next_position - 1);
	}
}
