import java.io.Serializable;

public class Product implements Serializable{
	private int id;
	private String name;
	private int price;
	Category category;
	
	public Product(int id, String name, int price, Category category){
		System.out.println("new product ID "+id);
		this.name = name;
		this.price = price;
		this.category = category;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}