package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comp401.sushi.*;
import comp401.sushi.Nigiri.NigiriType;
import comp401.sushi.Plate.Color;
import comp401.sushi.Sashimi.SashimiType;

public class SushiWorkstationWidget extends JPanel implements ActionListener{

	private List<WorkstationListener> listeners;
	private JLabel gold_price_label;
	private JSlider gold_price_slider;
	private int color_index = 0;
	private Plate.Color color = Color.RED;
	private int gold_price = 10;
	private SashimiType sashimi_type = SashimiType.CRAB;
	private NigiriType nigiri_type = NigiriType.CRAB;
	private JSlider avocado_slider;
	private JSlider crab_slider;
	private JSlider eel_slider;
	private JSlider rice_slider;
	private JSlider salmon_slider;
	private JSlider seaweed_slider;
	private JSlider shrimp_slider;
	private JSlider tuna_slider;

	public SushiWorkstationWidget() {
		/* Do not change the following line*/
		listeners = new ArrayList<WorkstationListener>();
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());
		JPanel north_panel = new JPanel();
		north_panel.setLayout(new BorderLayout());
		JPanel label_panel = new JPanel();
		label_panel.setLayout(new GridLayout(10,1));
		JPanel value_panel = new JPanel();
		value_panel.setLayout(new GridLayout(10,1));
		JPanel south_panel = new JPanel();
		south_panel.setLayout(new BorderLayout());
		JPanel label_panel2 = new JPanel();
		label_panel2.setLayout(new GridLayout(2,1));
		JPanel value_panel2 = new JPanel();
		value_panel2.setLayout(new GridLayout(2,1));
		JPanel making_panel2 = new JPanel();
		making_panel2.setLayout(new GridLayout(2,1));
		
		String[] colors = {"Red", "Green", "blue", "Gold"};
		JComboBox<String> color_box = new JComboBox<>(colors);
		color_box.setName("color_box");
		
		gold_price_slider = new JSlider(5, 15);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer(5), new JLabel("5.00") );
		labelTable.put( new Integer(15), new JLabel("15.00") );
		gold_price_slider.setLabelTable( labelTable );
		gold_price_slider.setPaintTicks(true);
		gold_price_slider.setMajorTickSpacing(2);
		gold_price_slider.setPaintLabels(true);
		gold_price_slider.setVisible(false);
		
		avocado_slider = new JSlider(0, 100);
		avocado_slider.setValue(0);
		crab_slider = new JSlider(0, 100);
		crab_slider.setValue(0);
		eel_slider = new JSlider(0, 100);
		eel_slider.setValue(0);
		rice_slider = new JSlider(0, 100);
		rice_slider.setValue(0);
		salmon_slider = new JSlider(0, 100);
		salmon_slider.setValue(0);
		seaweed_slider = new JSlider(0, 100);
		seaweed_slider.setValue(0);
		shrimp_slider = new JSlider(0, 100);
		shrimp_slider.setValue(0);
		tuna_slider = new JSlider(0, 100);
		tuna_slider.setValue(0);
		
		String[] ingredients = {"Crab", "Eel", "Salmon", "Shrimp", "Tuna"};
		JComboBox<String> sashimi_box = new JComboBox<>(ingredients);
		sashimi_box.setName("sashimi_box");
		JComboBox<String> nigiri_box = new JComboBox<>(ingredients);
		nigiri_box.setName("nigiri_box");
		
		label_panel.add(new JLabel("Plate Color: "));
		gold_price_label = new JLabel("Gold Price: ");
		label_panel.add(gold_price_label);
		gold_price_label.setVisible(false);
		label_panel.add(new JLabel("Avocado: "));
		label_panel.add(new JLabel("Crab: "));
		label_panel.add(new JLabel("Eel: "));
		label_panel.add(new JLabel("Rice: "));
		label_panel.add(new JLabel("Salmon: "));
		label_panel.add(new JLabel("Seaweed: "));
		label_panel.add(new JLabel("Shrimp: "));
		label_panel.add(new JLabel("Tuna: "));
		label_panel2.add(new JLabel("Sashimi Type: "));
		label_panel2.add(new JLabel("Nigiri Type: "));
		
		value_panel.add(color_box);
		value_panel.add(gold_price_slider);
		value_panel.add(avocado_slider);
		value_panel.add(crab_slider);
		value_panel.add(eel_slider);
		value_panel.add(rice_slider);
		value_panel.add(salmon_slider);
		value_panel.add(seaweed_slider);
		value_panel.add(shrimp_slider);
		value_panel.add(tuna_slider);
		value_panel2.add(sashimi_box);
		value_panel2.add(nigiri_box);
		
		JButton make_roll = new JButton("Make Roll");
		JButton make_sashimi = new JButton("Make Sashimi");
		making_panel2.add(make_sashimi);
		JButton make_nigiri = new JButton("Make Nigiri");
		making_panel2.add(make_nigiri);
		
		north_panel.add(label_panel, BorderLayout.WEST);
		north_panel.add(value_panel, BorderLayout.EAST);
		north_panel.add(make_roll, BorderLayout.SOUTH);
		south_panel.add(label_panel2, BorderLayout.WEST);
		south_panel.add(value_panel2, BorderLayout.CENTER);
		south_panel.add(making_panel2, BorderLayout.EAST);
		
		main_panel.add(north_panel, BorderLayout.NORTH);
		main_panel.add(south_panel, BorderLayout.SOUTH);
		add(main_panel);
		
		color_box.addActionListener(new ComboBoxListener());
		gold_price_slider.addChangeListener(new SliderListener());
		sashimi_box.addActionListener(new ComboBoxListener());
		nigiri_box.addActionListener(new ComboBoxListener());
		make_roll.addActionListener(this);
		make_sashimi.addActionListener(this);
		make_nigiri.addActionListener(this);
	}

	/* Do not change the following three methods:
	 * addWorkstationListener
	 * removeWorkstationListener
	 * publicPlateToListeners
	 */
	
	public void addWorkstationListener(WorkstationListener l) {
		listeners.add(l);
	}

	public void removeWorkstationListener(WorkstationListener l) {
		listeners.remove(l);
	}
	
	private void publishPlateToListeners(Plate p) {
		for (WorkstationListener l : listeners) {
			l.handleMadePlate(p);
		}
	}
	
	private void generatePlate(Color color, Sushi sushi){
		switch (color) {
		case GOLD:
			try {
				GoldPlate plate = new GoldPlate(sushi, gold_price);
				publishPlateToListeners(plate);
			} catch (PlatePriceException e1) {
			}
			break;
		case RED:
			try {
				RedPlate plate = new RedPlate(sushi);
				publishPlateToListeners(plate);
			} catch (PlatePriceException e1) {
			}
			break;
		case GREEN:
			try {
				GreenPlate plate = new GreenPlate(sushi);
				publishPlateToListeners(plate);
			} catch (PlatePriceException e1) {
			}
			break;
		case BLUE:
			try {
				BluePlate plate = new BluePlate(sushi);
				publishPlateToListeners(plate);
			} catch (PlatePriceException e1) {
			}
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		String text = button.getText();
		switch (text) {
		case "Make Sashimi":
			if (sashimi_type != null){
				Sushi sushi = new Sashimi(sashimi_type);
				if (color == Color.GOLD){
					if (gold_price >= 5){
						generatePlate(color, sushi);
					}
				}
				else { generatePlate(color, sushi);}
			}
			break;
		case "Make Nigiri":
			if (nigiri_type != null){
				Sushi sushi2 = new Nigiri(nigiri_type);
				if (color == Color.GOLD){
					if (gold_price >= 5){
						generatePlate(color, sushi2);
					}
				}
				else { generatePlate(color, sushi2);}
			}
			break;
		case "Make Roll":
			double[] ingredient_amount = new double[8];
			ingredient_amount[0] = (double)avocado_slider.getValue() / 100;
			ingredient_amount[1] = (double)crab_slider.getValue() / 100;
			ingredient_amount[2] = (double)eel_slider.getValue() / 100;
			ingredient_amount[3] = (double)rice_slider.getValue() / 100;
			ingredient_amount[4] = (double)salmon_slider.getValue() / 100;
			ingredient_amount[5] = (double)seaweed_slider.getValue() / 100;
			ingredient_amount[6] = (double)shrimp_slider.getValue() / 100;
			ingredient_amount[7] = (double)tuna_slider.getValue() / 100;
			ArrayList<Ingredient> roll_ingredient = new ArrayList<>();
			if (ingredient_amount[0] > 0){
				roll_ingredient.add(new Avocado(ingredient_amount[0]));
			}
			if (ingredient_amount[1] > 0){
				roll_ingredient.add(new Crab(ingredient_amount[1]));
			}
			if (ingredient_amount[2] > 0){
				roll_ingredient.add(new Eel(ingredient_amount[2]));
			}
			if (ingredient_amount[3] > 0){
				roll_ingredient.add(new Rice(ingredient_amount[3]));
			}
			if (ingredient_amount[4] > 0){
				roll_ingredient.add(new Salmon(ingredient_amount[4]));
			}
			if (ingredient_amount[5] > 0){
				roll_ingredient.add(new Seaweed(ingredient_amount[5]));
			}
			if (ingredient_amount[6] > 0){
				roll_ingredient.add(new Shrimp(ingredient_amount[6]));
			}
			if (ingredient_amount[7] > 0){
				roll_ingredient.add(new Tuna(ingredient_amount[7]));
			}
			Ingredient[] roll_ingredients = roll_ingredient.toArray(new Ingredient[0]);
			if (roll_ingredients.length > 0){
				Roll roll = new Roll(roll_ingredients);
				generatePlate(color, roll);
			}
			break;
		}
	}
	
	class ComboBoxListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb = (JComboBox)e.getSource();
			switch (cb.getName()){
			case "color_box": 
				color_index = cb.getSelectedIndex();
				if (color_index == 3){
					gold_price_slider.setVisible(true);
					gold_price_label.setVisible(true);
				}
				else {
					gold_price_slider.setVisible(false);
					gold_price_label.setVisible(false);
				}
				switch (color_index) {
				case 0: color = Color.RED; break;
				case 1: color = Color.GREEN; break;
				case 2: color = Color.BLUE; break;
				case 3: color = Color.GOLD; break;
				}
				break;
			case "sashimi_box":
				String type = (String)cb.getSelectedItem();
				switch (type) {
				case "Tuna": sashimi_type = SashimiType.TUNA; break;
				case "Salmon": sashimi_type = SashimiType.SALMON; break;
				case "Eel": sashimi_type = SashimiType.EEL; break;
				case "Crab": sashimi_type = SashimiType.CRAB; break;
				case "Shrimp": sashimi_type = SashimiType.SHRIMP; break;
				default: sashimi_type = null; break;
				}
				break;
			case "nigiri_box":
				String type2 = (String)cb.getSelectedItem();
				switch (type2) {
				case "Tuna": nigiri_type = NigiriType.TUNA; break;
				case "Salmon": nigiri_type = NigiriType.SALMON; break;
				case "Eel": nigiri_type = NigiriType.EEL; break;
				case "Crab": nigiri_type = NigiriType.CRAB; break;
				case "Shrimp": nigiri_type = NigiriType.SHRIMP; break;
				default: nigiri_type = null; break;
				}
				break;
			}
		}
	}
	
	class SliderListener implements ChangeListener {
		@Override
	    public void stateChanged(ChangeEvent e) {
	        JSlider js = (JSlider)e.getSource();
	        if (!js.getValueIsAdjusting()) {
	            gold_price = js.getValue();
	        }
	    }
	}
}
