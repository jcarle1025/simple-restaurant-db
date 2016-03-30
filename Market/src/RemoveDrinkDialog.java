import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RemoveDrinkDialog extends JDialog implements ActionListener {
	private JLabel label;
	private JTextField textField;
	private JTextArea allDrinksTextArea;
	private JButton okButton;
	private JButton doneButton;
	private JPanel top;
	private JPanel buttons;
	private RestaurantClass myMarket;
	
	public RemoveDrinkDialog(RestaurantClass myMarket){
		this.myMarket = myMarket;
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Remove Drink");
		label = new JLabel("Drink Name:");
		textField = new JTextField();
		allDrinksTextArea = new JTextArea();
		
		okButton = new JButton("Remove");
		okButton.addActionListener(this);
	   	doneButton = new JButton("Done");
	   	doneButton.addActionListener(this);
	   
	   	top = new JPanel();
	   	top.setLayout(new GridLayout(4, 1));
	   	top.add(label);
	   	top.add(textField);
	   
	   	getContentPane().setLayout(new GridLayout(3, 2));//Rows, Columns
	   	getContentPane().add(top);
		getContentPane().add(allDrinksTextArea, null);
	   
	   	buttons = new JPanel();
	   	buttons.setLayout(new FlowLayout());
	   	buttons.add(okButton);
	   	buttons.add(doneButton);
	   	getContentPane().add(buttons);
	   	getRootPane().setDefaultButton(okButton);
	   
	   	this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton){
			String d = textField.getText();
			allDrinksTextArea.setText(myMarket.removeDrink(d));
			textField.setText("");
		}
		
		else if (e.getSource() == doneButton){
			dispose();
		}
	}
}