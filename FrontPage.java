import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrontPage extends JFrame {
	JComboBox<String> budget;
	JComboBox<String> category;
	Container contentPane;
	
	class GenerateMenuPage implements ActionListener {
		String budgetOption = budget.getSelectedItem().toString();
		String categoryOption = category.getSelectedItem().toString();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				FileParser fileParser = new FileParser(categoryOption, budgetOption);
				MenuPage menuPage = new MenuPage(fileParser);
				
				contentPane.removeAll();
				contentPane.add(menuPage.getContentPane());
				contentPane.validate();
				contentPane.repaint();
			}
			catch(IOException e1) {
				System.err.println("File not found");
			}
		}
	}
	
	public FrontPage() {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		JPanel navigationContainer = new JPanel();
		navigationContainer.setLayout(new FlowLayout());
		navigationContainer.setBackground(new Color(235, 235, 224));
		
		Box componentForNavContainer = Box.createHorizontalBox();
		ImageIcon foomee = new ImageIcon(getClass().getResource("FooMe.jpg"));
		JLabel icon = new JLabel(foomee);
		JButton login = new JButton("Login ");
		JButton signUp = new JButton("Signup");
		
		login.setFocusPainted(false);
		login.setBorderPainted(false);
		login.setForeground(Color.white);
		login.setBackground(new Color(36, 35, 35));
		
		signUp.setForeground(Color.white);
		signUp.setBackground(new Color(36, 35, 35));
		signUp.setFocusPainted(false);
		signUp.setBorderPainted(false);
		
		JPanel authenticationContainer = new JPanel();
		authenticationContainer.setLayout(new FlowLayout());
		authenticationContainer.add(login);
		authenticationContainer.add(signUp);
		
		componentForNavContainer.add(icon);
		componentForNavContainer.add(Box.createHorizontalStrut(1163));
		componentForNavContainer.add(signUp);
		componentForNavContainer.add(Box.createRigidArea(new Dimension(8,0)));
		componentForNavContainer.add(login);
		
		
		navigationContainer.add(componentForNavContainer);
		contentPane.add(navigationContainer, BorderLayout.NORTH);
		
		JPanel centerContainer = new JPanel();
		ImageIcon image = new ImageIcon(getClass().getResource("FoodMenu.jpg"));
		JLabel imageContainer2 = new JLabel(image);
		centerContainer.add(imageContainer2);
		centerContainer.setBackground(new Color(36, 35, 35));
		contentPane.add(centerContainer, BorderLayout.CENTER);
		
		
		String[] budgetListing = {"Less than 25£", "25£ to infinity"};
		budget = new JComboBox(budgetListing);
		budget.getEditor().getEditorComponent().setBackground(new Color(36, 35, 35));

		String[] categoryListing = {"Basic", "Cheap", "Events", "Healthy", "Vegetarian"};
		category =  new JComboBox(categoryListing);
		
		JButton ok = new JButton("Select Menu");
		ok.addActionListener(new GenerateMenuPage());
		
		JPanel footerContainer = new JPanel();
		footerContainer.setLayout(new GridLayout(2,1));
		
		JPanel footer1Component = new JPanel();
		footer1Component.setLayout(new FlowLayout());
		footer1Component.add(budget);
		footer1Component.add(category);
		
		JPanel footer2Component = new JPanel();
		footer2Component.setLayout(new FlowLayout());
		footer2Component.add(ok);
		
		footerContainer.add(footer1Component);
		footerContainer.add(footer2Component);
		
		contentPane.add(footerContainer, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		FrontPage front = new FrontPage();
	}
}
