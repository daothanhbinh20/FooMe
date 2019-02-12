
//Class which describes a Dish object containing information about it
import java.util.ArrayList;

public class Dish {
	
	private String name;
	private float price;
	private ArrayList<String> ingredients;
	
	public Dish(String name, float price, ArrayList<String> ingredients) {
		this.name = name;
		this.price = price;
		this.ingredients = ingredients;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public ArrayList<String> getIngredients() {
		return this.ingredients;
	}
	
}
