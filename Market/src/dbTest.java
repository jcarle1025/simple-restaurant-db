import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbTest {
    public static void main(String[] args) {
        MarketDB rest = new MarketDB();
        System.out.println(rest.testConnection());      
        //rest.resetDB();
        rest.dropFoodTable();
        rest.addFoodItem(1, "ham", 5);
        rest.addFoodItem(2, "chicken", 4);
        rest.addFoodItem(3, "beef", 6);
        rest.addFoodItem(4,  "cheese",  3);
        rest.deleteFoodItem(2);
        rest.updateFoodPrice(3, 7);
        
        rest.dropStaffTable();
        rest.addEmployee(1,  "John",  "Snow",  88000);
        rest.addEmployee(2,  "Jack",  "Smith",  82000);
        rest.updateSalary(2,  75000);
        rest.deleteEmployee(1);
        Connection c = rest.getConn();
        Statement s;
        try{
            s = c.createStatement();
            ResultSet foodItems = s.executeQuery("select * from food");
            System.out.println("food Table:");
            while (foodItems.next()){
                System.out.println(foodItems.getInt("id") +"\t"+
                                   foodItems.getString("name")+"\t"+
                                   foodItems.getInt("price"));
            }
            foodItems.close();
            
            ResultSet searchResults = rest.findFood("I");
            while (searchResults.next()){
                    System.out.println(searchResults.getString("username"));
            }
                        
        } catch (Exception e){
            System.out.println("Error getting a menu resultSet"+e);
        }
        
        try{
            s = c.createStatement();
            ResultSet employees = s.executeQuery("select * from staff");
            System.out.println("\nStaff Table:");
            
            while (employees.next()){
                System.out.println(employees.getInt("id") +"\t"+
                                   employees.getString("firstName")+"\t"+
                                   employees.getString("lastName")+"\t"+
                                   employees.getInt("salary"));
            }
            employees.close();
        } catch (Exception e){
            System.out.println("Error error getting staff resultSet... "+e);
        }
    }
}