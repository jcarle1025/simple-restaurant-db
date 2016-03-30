import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DrinkDialog extends JDialog implements ActionListener {
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JLabel drinkTypeLabel;
	private JComboBox<DrinkType> drinkComboBox;
	private JLabel priceLabel;
	private JSlider priceSlider;
	private JTextArea allDrinksTextArea;
	private JScrollPane scroller;
	private JButton saveButton;
	private JButton clearButton;
	private JButton removeButton;
	private JButton doneButton;
	private JPanel top;
	private JPanel buttons;
	private RestaurantClass myMarket;
	private MarketDB myMarketDB;
	
	public DrinkDialog(RestaurantClass myMarket, MarketDB myMarketDB){
		this.myMarket = myMarket;
		this.myMarketDB = myMarketDB;
		this.setBounds(100, 100, 450, 350);
		this.setTitle("Manage Drink Items");
		nameLabel = new JLabel("Drink Name:");
		nameTextField = new JTextField();

		drinkTypeLabel = new JLabel("Type: ");
		drinkComboBox = new JComboBox<DrinkType>();
		drinkComboBox.addItem(DrinkType.JUICE);
		drinkComboBox.addItem(DrinkType.SODA);
		drinkComboBox.addItem(DrinkType.ALCOHOL);
		allDrinksTextArea = new JTextArea();
		
		priceLabel = new JLabel("Price: ");
		priceSlider = new JSlider();
		priceSlider.setPaintLabels(true);
		priceSlider.setMinorTickSpacing(1);
		priceSlider.setSnapToTicks(true);
		priceSlider.setPaintTicks(true);
		priceSlider.setMinimum(0);
		priceSlider.setMaximum(20);
		
		saveButton = new JButton("Add");
		saveButton.addActionListener(this);
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
	   	removeButton = new JButton("Remove");
	   	removeButton.addActionListener(this);
	   	doneButton = new JButton("Done");
	   	doneButton.addActionListener(this);
	   
	   	scroller = new JScrollPane();
	   
	   	top = new JPanel();
	   	top.setLayout(new GridLayout(4, 1));
	   	top.add(nameLabel);
	   	top.add(nameTextField);
	   	top.add(priceLabel);
	   	top.add(priceSlider);
	   	top.add(drinkTypeLabel);
	   	top.add(drinkComboBox);
	   
	   	getContentPane().setLayout(new GridLayout(3, 2));//Rows, Columns
	   	getContentPane().add(top);
	   
	   	buttons = new JPanel();
	   	buttons.setLayout(new FlowLayout());
	   	buttons.add(saveButton);
	   	buttons.add(clearButton);
	   	buttons.add(removeButton);
	   	buttons.add(doneButton);
	   	getContentPane().add(buttons);
	   	getRootPane().setDefaultButton(saveButton);
	   
	   	scroller.getViewport().add(allDrinksTextArea, null);
	   	getContentPane().add(scroller);
	   
	   	this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton){
			String n = nameTextField.getText();
			int p = priceSlider.getValue();
			DrinkType t = (DrinkType) drinkComboBox.getSelectedItem();
			
			Drink d = new Drink(myMarket.numItems()+1,n,p,t);
			myMarket.addDrink(d);
			
			myMarketDB.addDrinkItem(d.getId(), d.getName(), d.getPrice());
			allDrinksTextArea.setText(myMarket.printDrinks());
			nameTextField.setText("");
		}
		
		else if (e.getSource() == clearButton){
			myMarket.clearDrinks();
			myMarketDB.dropDrinkTable();
			myMarketDB.createDrinkTable();
			allDrinksTextArea.setText("Menu has been cleared.");
		}
		
		else if (e.getSource() == removeButton){
			new RemoveDrinkDialog(myMarket);
			allDrinksTextArea.setText("");
		}
		
		else if (e.getSource() == doneButton) {
			dispose();
		}
	}
}