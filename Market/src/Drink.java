import java.io.Serializable;

public class Drink extends Product implements Serializable{
	private String name;
	private int price;
	private DrinkType type;
	private int id;
	
	public Drink(int id, String name, int price, DrinkType type){
		super(id, name, price, Category.DRINK);
		this.name = name;
		this.price = price;
		this.type = type;
		this.id = id;
	}

	public DrinkType getType() {
		return type;
	}

	public void setType(DrinkType type) {
		this.type = type;
	}
}
