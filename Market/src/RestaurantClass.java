import java.util.ArrayList;

public class RestaurantClass implements Restaurant{
	private ArrayList<Food> foods;
	private ArrayList<Drink> drinks;
	private ArrayList<Employee> staff;
	private ArrayList<Product> menu;
	private MarketDB myMarketDB;
	
	public RestaurantClass(){
		this.foods = new ArrayList<Food>();
		this.drinks = new ArrayList<Drink>();
		this.staff = new ArrayList<Employee>();
		this.menu = new ArrayList<Product>();
		this.myMarketDB = new MarketDB();
	}
	
	public RestaurantClass(ArrayList<Food> foods, ArrayList<Drink> drinks, ArrayList<Employee> staff){
		this.foods = foods;
		this.drinks = drinks;
		this.staff = staff;
		this.menu.addAll(foods);
		this.menu.addAll(drinks);
		this.myMarketDB = new MarketDB();	
	}

	public ArrayList<Food> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}
	
	public ArrayList<Drink> getDrinkds() {
		return drinks;
	}

	public void setDrinks(ArrayList<Drink> drinks) {
		this.drinks = drinks;
	}
	
	public ArrayList<Employee> getStaff() {
		return staff;
	}

	public void setStaff(ArrayList<Employee> staff) {
		this.staff = staff;
	}	
	
	public int numFoods(){
		return foods.size();
	}
	public int numDrinks(){
		return drinks.size();
	}
	
	public int numItems(){
		return menu.size();
	}
	
	public int numEmployees(){
		return staff.size();
	}
	
	public void addFood(Food f){
		foods.add(f);
		menu.add(f);
		System.out.println(numFoods());
	}
	
	public void addDrink(Drink d){
		drinks.add(d);
		menu.add(d);
		System.out.println(numDrinks());
	}
	
	public void addEmployee(Employee e){
		staff.add(e);
	}
	
	public String staffList(){
		String staffString = "\tEMPLOYEES\n====================\n";
		for (Employee e : staff)
			staffString += e.getFullName()+"\t\t"+e.getSalary()+"\n";
		return staffString;
	}
	
	public String printMenu(){
		String menuString = "RESTAURANT MENU\n=================\n";
		String drinkString = "DRINKS:\n";
		String foodString = "FOOD:\n";
		for(Product p : menu){
			if (p.getCategory().equals(Category.FOOD))
				foodString += p.getId()+". "+p.getName()+"\t"+p.getPrice()+"\n";
			else 
				drinkString += p.getId()+". "+p.getName()+"\t"+p.getPrice()+"\n";
		}
		
		menuString += foodString + "\n"+ drinkString;
		return menuString;
	}
	
	public String printFoods(){
		String breakfasts = "BREAKFAST:\n-";
		String lunches = "\nLUNCH:\n-";
		String dinners = "\nDINNER:\n-";
		for (Food f : foods){
			if (f.getMeal().equals(Meal.BREAKFAST))
				breakfasts +=f.getName()+"  ($"+f.getPrice()+") - ";
			else if (f.getMeal().equals(Meal.LUNCH))
				lunches += f.getName()+"  ($"+f.getPrice()+") - ";
			else
				dinners += f.getName()+"  ($"+f.getPrice()+") - ";
		}
		return breakfasts+lunches+dinners;
	}
	
	public String printDrinks(){
		String sodas = "SODAS:\n - ";
		String alcohol = "\nALCOHOL:\n - ";
		String juice = "\nJUICE:\n - ";
		
		for (Drink d : drinks){
			if (d.getType().equals(DrinkType.SODA))
				sodas += d.getName()+"  ($"+d.getPrice()+") - ";
			else if (d.getType().equals(DrinkType.ALCOHOL))
				alcohol +=d.getName()+"  ($"+d.getPrice()+") - ";
			else
				juice += d.getName()+"  ($"+d.getPrice()+") - ";
		}
		return juice+sodas+alcohol;
	}
	
	public String printStaff(){
		String emp = "Employees:\n";
		for (Employee e : staff){
			emp += e.getId()+". "+e.getFullName() +"\t("+e.getTitle()+")\t"+e.getSalary()+"\n";
		}
		return emp;
	}
	
	public String removeFoodItem(String fName){
		for(Food a : foods){
			if (a.getName().equalsIgnoreCase(fName)){
				foods.remove(a);
				menu.remove(a);
				myMarketDB.deleteFoodItem(a.getId());
				return a.getName()+" was successfully removed";
			}
		}
		return"No match found";
	}
	
	public String removeDrink(String dName){
		for(Drink a : drinks){
			if (a.getName().equalsIgnoreCase(dName)){
				foods.remove(a);
				menu.remove(a);
				myMarketDB.deleteDrinkItem(a.getId());
				return a.getName()+" was successfully removed";
			}
		}
		return"No match found";
	}
	
	public String removeEmployee(String firstName, String lastName){
		for(Employee a : staff){
			if (a.getFirstName().equalsIgnoreCase(firstName) && a.getLastName().equalsIgnoreCase(lastName)){
				staff.remove(a);
				myMarketDB.deleteEmployee(a.getId());
				return a.getFullName()+" was successfully removed";
			}
		}
		return"No match found";
	}
	
	public void clearMenu(){
		foods.clear();
		drinks.clear();
		staff.clear();
		myMarketDB.dropFoodTable();
		myMarketDB.dropDrinkTable();
	}
	
	public void clearFoods(){
		foods.clear();
	}
	
	public void clearDrinks(){
		drinks.clear();
	}
	
	public void clearStaff(){
		staff.clear();
	}
}