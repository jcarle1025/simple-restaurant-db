import java.io.Serializable;
import java.util.ArrayList;

public class Food extends Product implements Serializable{
	private Meal meal;
	private String name;
	private int price;
	private int id;
	
	public Food(int id, String name, int price, Meal meal){
		super(id, name, price, Category.FOOD);
		System.out.println("new food with id"+id);
		this.name = name;
		this.price = price;
		this.meal = meal;
		this.id = id;
	}

	public Meal getMeal() {
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}
}