package a7jedi;

import java.util.Observable;
import java.util.Observer;

import a7jedi.PlateEvent.EventType;

public class PlateCounter implements Observer{
	
	private int red_count;
	private int green_count;
	private int blue_count;
	private int gold_count;
	
	public PlateCounter(){
	}
	
	public void update(Observable o, Object arg) {
		PlateEvent plateEvent = (PlateEvent) arg;
		if (plateEvent.getType() == EventType.PLATE_PLACED){
			switch (plateEvent.getPlate().getColor()) {
			case RED: red_count++; break;
			case GREEN: green_count++; break;
			case BLUE: blue_count++; break;
			case GOLD: gold_count++;
			}
		}
		else if (plateEvent.getType() == EventType.PLATE_REMOVED){
			switch (plateEvent.getPlate().getColor()) {
			case RED: red_count--; break;
			case GREEN: green_count--; break;
			case BLUE: blue_count--; break;
			case GOLD: gold_count--;
			}
		}
	}
	
	public int getRedPlateCount(){
		return red_count;
	}
	
	public int getGreenPlateCount(){
		return green_count;
	}
	
	public int getBluePlateCount(){
		return blue_count;
	}
	
	public int getGoldPlateCount(){
		return gold_count;
	}
}
