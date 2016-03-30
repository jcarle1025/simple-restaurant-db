import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EmployeeDialog extends JDialog implements ActionListener{
	private JLabel lastNameLabel = new JLabel("Employee Last Name:");
    private JTextField lastNameTextField = new JTextField();
    private JLabel firstNameLabel = new JLabel("Employee First Name:");
    private JTextField firstNameTextField = new JTextField();
    private JTextField titleText = new JTextField();
    private final JLabel salaryLabel = new JLabel("Salary:");
    private JLabel titleLabel = new JLabel("Job Title:");
    private final JTextField salaryText = new JTextField();
    private JButton saveButton = new JButton("Add");
    private JButton clearButton = new JButton("Clear");
    private JButton removeButton = new JButton("Remove");
    private JButton doneButton = new JButton("Done");
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea allEmployeesTextArea = new JTextArea();
    
    private RestaurantClass myMarket;
    private MarketDB myMarketDB;

    public EmployeeDialog(RestaurantClass myMarket, MarketDB myMarketDB) {
        this(null, "Manage Employees", false);
        this.myMarket = myMarket;
        this.myMarketDB = myMarketDB;
    }

    public EmployeeDialog(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(416, 395));
        this.getContentPane().setLayout( null );
        
        lastNameLabel.setBounds(new Rectangle(30, 19, 150, 15));
        lastNameTextField.setBounds(new Rectangle(30, 36, 136, 20));
        this.getContentPane().add(lastNameTextField, null);
        this.getContentPane().add(lastNameLabel, null);
        
        firstNameLabel.setBounds(new Rectangle(30, 77, 150, 15));
        firstNameTextField.setBounds(new Rectangle(30, 94, 136, 20));
        this.getContentPane().add(firstNameTextField, null);
        this.getContentPane().add(firstNameLabel, null);

        titleLabel.setBounds(new Rectangle(220, 19, 143, 15));
        this.getContentPane().add(titleLabel);

        titleText.setBounds(new Rectangle(220, 36, 136, 20));
        this.getContentPane().add(titleText);

        salaryLabel.setBounds(new Rectangle(220, 77, 150, 15));   
        this.getContentPane().add(salaryLabel);
        
        salaryText.setBounds(new Rectangle(220, 94, 136, 20));
        this.getContentPane().add(salaryText);
        
        saveButton.setBounds(new Rectangle(30, 137, 107, 21));
        saveButton.addActionListener(this);
        this.getContentPane().add(saveButton, null);
        
        clearButton.setBounds(new Rectangle(149, 137, 107, 21));
        clearButton.addActionListener(this);
        this.getContentPane().add(clearButton, null);
        
        removeButton.setBounds(new Rectangle(268, 137, 107, 21));
        removeButton.addActionListener(this);
        this.getContentPane().add(removeButton, null);
        
        doneButton.setBounds(new Rectangle(290, 346, 107, 21));
        doneButton.addActionListener(this);
        this.getContentPane().add(doneButton, null);
	   	getRootPane().setDefaultButton(saveButton);
        
        jScrollPane1.setBounds(new Rectangle(21, 170, 376, 164));
        this.getContentPane().add(jScrollPane1, null);
        
        allEmployeesTextArea.setBounds(21, 172, 376, 162);
        getContentPane().add(allEmployeesTextArea);

        this.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton){
			String f = firstNameTextField.getText();
			String l = lastNameTextField.getText();
			String salS = salaryText.getText();
			salS = salS.replace(",", "");
			salS = salS.replace(".", "");
			salS = salS.replace("$", "");
			int s = Integer.valueOf(salS);
			String t = titleText.getText();
			
			Employee a = new Employee(myMarket.numEmployees()+1, f, l, s, t);
			myMarket.addEmployee(a);
			
			myMarketDB.addEmployee(a.getId(), a.getFirstName(), a.getLastName(), a.getSalary());
			allEmployeesTextArea.setText(myMarket.printStaff());
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			titleText.setText("");
			salaryText.setText("");
		}
		
		else if (e.getSource() == clearButton){
			myMarket.clearStaff();
			myMarketDB.dropStaffTable();
			myMarketDB.createStaffTable();
			allEmployeesTextArea.setText("Staff has been cleared.");
		}
		
		else if (e.getSource() == removeButton){
			new RemoveEmployeeDialog(myMarket, myMarketDB);
			allEmployeesTextArea.setText("");
		}
		
		else if (e.getSource() == doneButton){
			dispose();
		}
	}
}