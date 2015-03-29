package a7jedi;

import java.util.Observable;
import java.util.Observer;

import a7jedi.PlateEvent.EventType;

public class ProfitCounter implements Observer{
	
	private double total_profit;
	private int plate_count;
	
	public ProfitCounter(){
	}

	public void update(Observable o, Object arg) {
		PlateEvent plateEvent = (PlateEvent) arg;
		if (plateEvent.getType() == EventType.PLATE_PLACED){
			total_profit += plateEvent.getPlate().getProfit();
			plate_count++;
		}
		else if (plateEvent.getType() == EventType.PLATE_REMOVED){
			total_profit -= plateEvent.getPlate().getProfit();
			plate_count--;
		}
	}
	
	public double getTotalBeltProfit(){
		return total_profit;
	}
	
	public double getAverageBeltProfit(){
		if (plate_count == 0){
			return 0.0;
		}
		else {
			return total_profit / plate_count;
		}
	}
}
