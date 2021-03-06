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

public class RemoveEmployeeDialog extends JDialog implements ActionListener {
	private JLabel label;
	private JTextField textField;
	private JLabel label2;
	private JTextField textField2;
	private JTextArea employeesTextArea;
	private JButton okButton;
	private JButton doneButton;
	private JPanel top;
	private JPanel buttons;
	private RestaurantClass myMarket;
	private MarketDB myMarketDB;
	
	public RemoveEmployeeDialog(RestaurantClass myMarket, MarketDB myMarketDB){
		this.myMarket = myMarket;
		this.myMarketDB = myMarketDB;
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Remove Employee");
		label = new JLabel("First Name:");
		textField = new JTextField();
		label2 = new JLabel("Last Name:");
		textField2 = new JTextField();
		employeesTextArea = new JTextArea();
		
		okButton = new JButton("Remove");
		okButton.addActionListener(this);
	   	doneButton = new JButton("Done");
	   	doneButton.addActionListener(this);
	   
	   	top = new JPanel();
	   	top.setLayout(new GridLayout(4, 1));
	   	top.add(label);
	   	top.add(textField);
	   	top.add(label2);
	   	top.add(textField2);
	   
	   	getContentPane().setLayout(new GridLayout(3, 2));//Rows, Columns
	   	getContentPane().add(top);
		getContentPane().add(employeesTextArea, null);
	   
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
			String first = textField.getText();
			String last = textField2.getText();
			employeesTextArea.setText(myMarket.removeEmployee(first, last));
			textField.setText("");
			textField2.setText("");
		}
		
		else if (e.getSource() == doneButton){
			dispose();
		}
	}
}