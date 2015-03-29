package a7jedi;

import java.util.Observable;
import java.util.Observer;

import comp401.sushi.Ingredient;

import a7jedi.PlateEvent.EventType;

public class SpoilageCollector implements Observer{

	private double total_spoiled_cost;
	private double total_spoiled_shellfish;
	private double total_spoiled_seafood;
	private double total_spoiled_food;
	
	public SpoilageCollector(){
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Belt belt = (Belt) o;
		PlateEvent plateEvent = (PlateEvent) arg;
		if (plateEvent.getType() == EventType.PLATE_SPOILED){
			total_spoiled_cost += plateEvent.getPlate().getContents().getCost();
			for (Ingredient ingredient : plateEvent.getPlate().getContents().getIngredients()){
				if (ingredient.isShellfish()){
					total_spoiled_shellfish += ingredient.getAmount();
				}
				if (!ingredient.isRice() && !ingredient.isVegetarian()){
					total_spoiled_seafood += ingredient.getAmount();
				}
				total_spoiled_food += ingredient.getAmount();
			}
			belt.removePlateAtPosition(plateEvent.getPosition());
		}
	}
	
	public double getTotalSpoiledCost(){
		return total_spoiled_cost;
	}
	
	public double getTotalSpoiledShellfish(){
		return total_spoiled_shellfish;
	}
	
	public double getTotalSpoiledSeafood(){
		return total_spoiled_seafood;
	}
	
	public double getTotalSpoiledFood(){
		return total_spoiled_food;
	}
}
