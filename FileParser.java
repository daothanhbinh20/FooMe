// Class to parse through external configuration file and extract information to create Dish objects
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FileParser {
	
	//Declaring reader and hashmap 
	private BufferedReader reader;
	private HashMap<String, ArrayList<Object>> configMap = new HashMap<String, ArrayList<Object>>();
	private ArrayList<Dish> dishList;
	
	public FileParser(String category, String budget) throws FileNotFoundException, IOException {
		try {
			//String configFilePath = "bin\\" + category + "\\" + budget + "\\config.txt";
			String configFilePath = "C:\\Users\\admin\\Desktop\\FooMe\\FooMe\\src\\config1.txt";
			File inFile = new File(configFilePath);
			//System.out.println(inFile.exists());
			reader = new BufferedReader(new FileReader(inFile));
			this.readFile();
			this.dishList = this.createDishes();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Reads contents of text file and adds them into hashmap
	private void readFile() {
		String currentLine;
		
		try {
			while ((currentLine = reader.readLine()) != null) {
				if (currentLine.length() > 1) {
					ArrayList<String> parts = new ArrayList<String>();
					for (String splitVal : currentLine.split(": ")) {
						parts.add(splitVal);
					}
					
					if (parts.get(0).equals("Ingredients")) {
						ArrayList<String> ingList = new ArrayList<String>();
						for (String ingPart : parts.get(1).split(", ")) {
							ingList.add(ingPart);
						}
						this.addToMap(ingList);
					}
					else {
						this.addToMap(parts.get(0), parts.get(1));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Adds a key-value pair into the hashmap
	private void addToMap(String key, String value) {
		ArrayList<Object> keyList = configMap.get(key);
		
		//If key doesn't exist, it creates a new key
		if (keyList == null) {
			keyList = new ArrayList<Object>();
			keyList.add(value);
			configMap.put(key, keyList);
		}
		else {
			keyList.add(value);
		}
	}
	
	//OVerloaded method for ingredients which are stored in arraylists
	private void addToMap(ArrayList<String> ingList) {
		ArrayList<Object> keyList = configMap.get("Ingredients");
		
		if (keyList == null) {
			keyList = new ArrayList<Object>();
			keyList.add(ingList);
			configMap.put("Ingredients", keyList);
		}
		else {
			keyList.add(ingList);
		}
	}

	public ArrayList<Dish> createDishes() {
		ArrayList<Dish> dishList = new ArrayList<Dish>();
		
		for (int i=0 ; i < 7 ; i++) {
			String name = String.valueOf(configMap.get("Name").get(i));
			float price = Float.valueOf((String) configMap.get("Price").get(i));
			ArrayList<String> ingredients = ((ArrayList<String>) configMap.get("Ingredients").get(i));
			dishList.add(new Dish(name, price, ingredients));
		}
		
		return dishList;
	}
	
	public ArrayList<Dish> getDishList() {
		return this.dishList;
	}
	
	public String getImageName(String dishName) {
		String text = dishName.toLowerCase().replace(" ", "-");
		return text.substring(0, text.length()-1) + ".jpg";
	}
	
	public static void main(String[] args) throws IOException, FileNotFoundException {
		String cwd = System.getProperty("user.dir");
		System.out.println(cwd);
		FileParser p = new FileParser("Vegetarian", "Less than 25");
		ArrayList<Dish> testDishList = p.getDishList();
		for (int i=0 ; i < p.dishList.size(); i++) {
			System.out.println("Dish " + (i+1));
			System.out.println("Name: " + testDishList.get(i).getName());
			System.out.println("Price: " + testDishList.get(i).getPrice());
			System.out.print("Ingredients: ");
			for (String ingName : testDishList.get(i).getIngredients()) {
				System.out.print(ingName + "\n");
			}
			System.out.println();
		}
		String imageFilePath = "bin\\Vegetarian\\Less than 25\\";
		File file = new File(imageFilePath);
		String[] fileList = file.list();
		List<String> list = Arrays.asList(fileList);
		if (list != null) {
			System.out.println(list);
			JFrame window = new JFrame("Test images");
			window.setLayout(new FlowLayout());
			for (Dish dish : testDishList) {
				System.out.println(p.getImageName(dish.getName()));
				if (list.contains(p.getImageName(dish.getName()))) {
					System.out.print("It is working");
					ImageIcon icon = new ImageIcon(imageFilePath + p.getImageName(dish.getName()));
					JLabel label = new JLabel(icon);
					window.add(label);
				}
			}
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.pack();
			window.setVisible(true);
		}
	}
	
}
