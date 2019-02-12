import java.awt.*;
import javax.swing.*;

import java.io.File;
import java.util.List;


import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuPage extends JPanel
{	
	
	public String getImageName(String dishName) {
		String text = dishName.toLowerCase().replace(" ", "-");
		return text + ".jpg";
	}
	
	public MenuPage(FileParser p)
	{
		//Navigation Panel *********************
		JPanel navigationContainer = new JPanel();
		navigationContainer.setLayout(new FlowLayout());
		navigationContainer.setBackground(new Color(235, 235, 224));
		
		Box componentForNavContainer = Box.createHorizontalBox();
		ImageIcon foomee = new ImageIcon(getClass().getResource("FooMe.jpg"));
		JLabel icon = new JLabel(foomee);
		JButton logout = new JButton("Logout ");
		JLabel menuTitle = new JLabel("Menu");
		menuTitle.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		logout.setFocusPainted(false);
		logout.setBorderPainted(false);
		logout.setForeground(Color.white);
		logout.setBackground(new Color(36, 35, 35));
	
		JPanel authenticationContainer = new JPanel();
		authenticationContainer.setLayout(new FlowLayout());
		
		
		componentForNavContainer.add(icon);
		componentForNavContainer.add(Box.createHorizontalStrut(582));
		componentForNavContainer.add(menuTitle);
		componentForNavContainer.add(Box.createHorizontalStrut(582));
		componentForNavContainer.add(logout);
		
		
		navigationContainer.add(componentForNavContainer);
		JPanel navigationPanel = new JPanel();
		navigationPanel.setLayout(new FlowLayout());
		navigationPanel.setBackground(new Color(235, 235, 224));
		navigationPanel.add(navigationContainer);
		
		
		
		//CentralPanel
		
		JPanel centralPanel = new JPanel();
		centralPanel.setLayout(new GridLayout(2,1));
		centralPanel.setBackground(new Color(36, 35, 35));
		
		JPanel centralPanel1 = new JPanel();
		centralPanel1.setLayout(new GridLayout(1,7));
		centralPanel1.setBackground(new Color(36, 35, 35));
		ArrayList<Dish> dishList = p.getDishList();
		System.out.println(dishList);
		String imageFilePath = "src";
		File file = new File(imageFilePath);
		String[] fileList = file.list();
		List<String> list = Arrays.asList(fileList);
		if (list != null) {
			System.out.println(list);
			for(Dish dish: dishList) {
				System.out.println(this.getImageName(dish.getName()));
				if (list.contains(this.getImageName(dish.getName()))) {
					System.out.println("HI");
					ImageIcon foodIcon = new ImageIcon(imageFilePath + "\\" + this.getImageName(dish.getName()));
					JLabel label = new JLabel(foodIcon);
					centralPanel1.add(label);
				}
			}
			
			centralPanel.add(centralPanel1);
		}
		
		ArrayList<Dish> listOfDish = p.getDishList();
		ArrayList<String> dishInformation = new ArrayList<>();
		for (Dish currentDish : listOfDish)
		{
			String line1 = currentDish.getName();
			String line2 = Float.toString(currentDish.getPrice());
			String dishInfo = "Name: " + line1 + ".\n" + "Price: " + line2 + ".\n" + "Ingredients: " + ".\n";
			ArrayList<String> listOfIngredient = currentDish.getIngredients();
			
			for( String currentIngredient : listOfIngredient)
			{ 
				dishInfo = dishInfo + "- " + currentIngredient + ".\n";
			}
			dishInformation.add(dishInfo);
		}
		
		JPanel centralPanel2 = new JPanel();
		centralPanel2.setBackground(new Color(36, 35,35));
		GridLayout gridLayout = new GridLayout();
		gridLayout.setHgap(10);
		centralPanel2.setLayout(gridLayout);
		for(String currentInfo : dishInformation)
		{
			JTextArea textArea = new JTextArea(currentInfo);
			textArea.setBackground(new Color(36, 35, 35));
			textArea.setForeground(Color.white);
			textArea.setEditable(false);
			centralPanel2.add(textArea);
		}
		
		centralPanel.add(centralPanel2);
		//ContactInfo Panel************************
		JPanel contactInfoPanel = new JPanel();
		contactInfoPanel.setLayout(new GridLayout(1, 2));
		JPanel leftSide = new JPanel();
		JLabel contactInfo =  new JLabel("Contact");
		JLabel emailAddress = new JLabel("Email Address: ********");
		JLabel phoneNo = new JLabel("Phone no: *********");
		JLabel houseAddress = new JLabel("Address: **********");
		GridLayout grid = new GridLayout(4,1);
		leftSide.setLayout(grid);
		grid.setHgap(5);
		leftSide.add(contactInfo);
		leftSide.add(emailAddress);
		leftSide.add(phoneNo);
		leftSide.add(houseAddress);
		
		JButton back = new JButton("Back");

		Box footer = Box.createHorizontalBox();
		footer.add(leftSide);
		footer.createRigidArea(new Dimension(200,0));
		footer.add(back);
		contactInfoPanel.add(footer);
		
		//Second Panel ***********************
		this.setLayout(new BorderLayout());
		this.add(navigationPanel, BorderLayout.NORTH);
		this.add(centralPanel, BorderLayout.CENTER);
		this.add(contactInfoPanel, BorderLayout.SOUTH);	
	}
	
	public JPanel getContentPane() {
		return this;
	}

	public static void main(String[] args)
	{	
		String cwd = System.getProperty("user.dir");
		System.out.println(cwd);
	}

}
