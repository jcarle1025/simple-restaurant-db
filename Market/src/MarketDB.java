import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MarketDB {
        private String databaseName;
        private String jdbcUrl;
        private String userName;
        private String password;
        private Connection conn;

    public MarketDB() {
        databaseName="restaurantDB";
        jdbcUrl = "jdbc:derby:" + databaseName +";create=true";
        userName = "user";
        password = "pass";
    }
    
    public MarketDB(String databaseName, String userName, String password) {
        jdbcUrl = "jdbc:derby:" + databaseName +";create=true";
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Connection getConn() {
        try {
          conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Error in getConn" + e);
        }
        
        return conn;
    }
    
    public boolean testConnection() {

        try {
          conn = DriverManager.getConnection(jdbcUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("Error establishing connection" + e);
            return false;
        }
        return true;
    }

    public void resetDB(){
	    this.dropStaffTable();
	    this.dropFoodTable();
	    this.dropDrinkTable();
	    this.createStaffTable();
	    this.createFoodTable();
	    this.createDrinkTable();
	}

	public void createFoodTable() {
    	Statement statement;
        testConnection();

        try {
            statement = conn.createStatement();
            statement.execute("CREATE TABLE food (" + 
            "	id INT PRIMARY KEY," + 
            "	name VARCHAR(30)," + 
            "	price INT" + 
            "	)");   

            System.out.println("Food table created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table food..." + e);
        }       
    }
    
    public void createDrinkTable(){
		Statement statement;
        testConnection();
		
		try {
			statement = conn.createStatement();
			statement.execute("CREATE TABLE drink (" + 
		            "	id INT PRIMARY KEY," + 
		            "	name VARCHAR(30)," + 
		            "	price INT" + 
		            "	)");
		    System.out.println("Drink table created successfully");
	    } catch (SQLException e) {
	        System.out.println("Error creating table drink..." + e);
	    } 
	}

	public void createStaffTable() {
        Statement statement;
        testConnection();
        
            try {
                statement = conn.createStatement();
                statement.execute("CREATE TABLE staff (" + 
                "	id INT PRIMARY KEY," + 
                "	firstName VARCHAR(30)," + 
                "	lastName VARCHAR(30)," + 
                "	salary INT" + 
                "	)");   
                System.out.println("Staff table created successfully");
                
            } catch (SQLException e) {
                System.out.println("Error creating table staff..." + e);
            }
    }

	public void dropFoodTable() { 
        Statement statement;
        testConnection();

            try {
                statement = conn.createStatement();
                statement.execute("DROP TABLE food");   
                System.out.println("Food table dropped successfully");
            } catch (SQLException e) {
                System.out.println("Error dropping table food..." + e);
            }
            
    }
    
    public void dropDrinkTable(){
    	Statement statement;
        testConnection();

        try {
            statement = conn.createStatement();
            statement.execute("DROP TABLE drink");   
            System.out.println("Drink table dropped successfully");
        } catch (SQLException e) {
            System.out.println("Error dropping table drink..." + e);
        }
    }
    
    public void dropStaffTable() {
        Statement statement;
        testConnection();

            try {
                statement = conn.createStatement();
                statement.execute("DROP TABLE staff");   
                System.out.println("Staff table dropped successfully");
            } catch (SQLException e) {
                System.out.println("Error dropping table staff..." + e);
            }
    }
    
    public void addFoodItem(int id, String name, int price){
        Statement statement;
        testConnection();

        try {
            statement = conn.createStatement();
            statement.execute("INSERT INTO food VALUES ("+id+", '"+ name+"', "+price+")");
        } catch (SQLException e) {
            System.out.println("Error inserting food item "+name+": "+e);
        }
    }
    
    public void addDrinkItem(int id, String name, int price){
		  Statement statement;
	        testConnection();
	
	      try {
	          statement = conn.createStatement();
	          statement.execute("INSERT INTO drink VALUES ("+id+", '"+ name+"', "+price+")");
	         
	      } catch (SQLException e) {
	          System.out.println("Error inserting drink item "+name+": "+e);
	      }
	}

	public void addEmployee(int id, String firstName, String lastName, int salary){
	    Statement statement;
        testConnection();
	
	    try {
	        statement = conn.createStatement();
	        statement.execute("INSERT INTO staff VALUES ("+id+", '"+ firstName+"', '"+lastName+"', "+salary+")");
	     
	    } catch (SQLException e) {
	        System.out.println("Error inserting employee "+firstName+" "+lastName+": "+e);
	    }
	}

	public void deleteFoodItem(int id){
        Statement statement;
        testConnection();
        try {
            statement = conn.createStatement();
            statement.execute("DELETE FROM food where id = "+id);
        } catch(SQLException e){
            System.out.println("Error deleting food "+id + ": "+e);
        }
    }
    
    public void deleteDrinkItem(int id){
    	Statement statement;
    	testConnection();
        try {
            statement = conn.createStatement();
            statement.execute("DELETE FROM drink where id = "+id);
        } catch(SQLException e){
            System.out.println("Error deleting drink "+id + ": "+e);
        }
    }
    
    public void deleteEmployee(int id){
	    Statement statement;
	    testConnection();
	    try {
	        statement = conn.createStatement();
	        statement.execute("DELETE FROM staff where id = "+id);
	    } catch(SQLException e){
	        System.out.println("Error deleting employee "+id + ": "+e);
	    }
	}

	public void updateFoodPrice(int id, int newPrice){
        Statement statement;
        testConnection();
        try{
            statement = conn.createStatement();
            statement.execute("UPDATE food set price="+newPrice+" where id="+id);
        } catch(SQLException e){
            System.out.println("Error updating food item "+id+"'s price: " +e);
        }
    }
    
    public void updateDrinkPrice(int id, int newPrice){
    	Statement statement;
        testConnection();
        try{
            statement = conn.createStatement();
            statement.execute("UPDATE drink set price="+newPrice+" where id="+id);
        } catch(SQLException e){
            System.out.println("Error updating drink item "+id+"'s price: " +e);
        }
    }
    
    public void updateSalary(int id, int newSalary){
	    Statement statement;
	    testConnection();
	    try{
	        statement = conn.createStatement();
	        statement.execute("UPDATE staff set salary="+newSalary+" where id="+id);
	    } catch(SQLException e){
	        System.out.println("Error updating employee "+id+"'s salary: " +e);
	    }
	}

	public ResultSet findFood(String search){
        Statement statement;
        testConnection();
        ResultSet results = null;
 
        try{
            statement = conn.createStatement();
            results = statement.executeQuery("select * from food where name like '%"+search.toLowerCase()+"%'");
        } catch(SQLException e){
              System.out.println("Error finding food item "+search+": "+e);      
        }

        return results;
    }
    
    public ResultSet findDrink(String search){
    	Statement statement;
        testConnection();
        ResultSet results = null;
 
        try{
            statement = conn.createStatement();
            results = statement.executeQuery("select * from drink where name like '%"+search.toLowerCase()+"%'");
        } catch(SQLException e){
              System.out.println("Error finding drink item "+search+": "+e);      
        }

        return results;
    }
    
    public ResultSet findEmployeeByLastName(String search){
        Statement statement;
        testConnection();
        ResultSet results = null;
 
        try{
            statement = conn.createStatement();
            results = statement.executeQuery("select * from staff where lastName like '%"+search.toLowerCase()+"%';");
        } catch(SQLException e){
              System.out.println("Error finding employee "+search+":"+e);      
        }

        return results;
    }
    
    public ResultSet findEmployeeByFirstName(String search){
        Statement statement;
        testConnection();
        ResultSet results = null;
 
        try{
            statement = conn.createStatement();
            results = statement.executeQuery("select * from staff where firstName like '%"+search.toLowerCase()+"%';");
        } catch(SQLException e){
              System.out.println("Error finding employee "+search+":"+e);      
        }

        return results;
    }
    
    public String printFood(){
    	String fs = "";
    	Statement s;
        testConnection();
    	try{
            s = conn.createStatement();
            ResultSet foodItems = s.executeQuery("select * from food");
            fs += "Food Table:\n";
            while (foodItems.next()){
                fs += foodItems.getInt("id") +".  "+foodItems.getString("name").toUpperCase()+"\t\t"+
                	  foodItems.getInt("price")+"\n";
            }
            foodItems.close();
                        
        } catch (Exception e){
            System.out.println("Error getting food resultSet"+e);
        }
    	return fs;
    }
    
    public String printDrink(){
    	Statement s;
        testConnection();
    	String ds = "";
    	try{
            s = conn.createStatement();
            ResultSet foodItems = s.executeQuery("select * from drink");
            ds += "Drink Table:\n";
            while (foodItems.next()){
                ds += foodItems.getInt("id") +".  "+foodItems.getString("name").toUpperCase()+"\t\t"+
                      foodItems.getInt("price")+"\n";
            }
            foodItems.close();
                        
        } catch (Exception e){
            System.out.println("Error getting drink resultSet"+e);
        }
    	return ds;
    }
    
    public String printStaff(){
    	Statement s;
        testConnection();
    	String st = "";
    	try{
            s = conn.createStatement();
            ResultSet foodItems = s.executeQuery("select * from staff");
            st += "Staff Table:\n";
            while (foodItems.next()){
                st += foodItems.getInt("id") +".  "+foodItems.getString("firstName").toUpperCase()+
                	" "+foodItems.getString("lastName").toUpperCase()+"\t\t"+foodItems.getInt("salary")+"\n";
            }
            foodItems.close();
                        
        } catch (Exception e){
            System.out.println("Error getting employee resultSet"+e);
        }
    	return st;
    }
}