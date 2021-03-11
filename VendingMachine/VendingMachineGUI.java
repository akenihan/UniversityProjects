import java.util.Scanner;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class VendingMachineGUI extends Application
{  
	//private DataReader dataReader = new DataReader();
	private ArrayList<ArrayList<String>> stockArray;
	private ArrayList<ArrayList<String>> CoinArray = new ArrayList<ArrayList<String>>();
	private ArrayList<Coin> coinsArr;
	private VendingMachine machine = new VendingMachine();
	private CoinSet tempCoins = new CoinSet();
	private static Coin[] coins = { new Coin(0.05, "5 cent"),
								    new Coin(0.10, "10 cent"),
								    new Coin(0.50, "50 cent"),
								    new Coin(1, "1 euro") };
									
	public VendingMachineGUI()
   {
   }	

	public void run(VendingMachine machine, String[] args)
         throws IOException
   {
	   launch(args);
	   this.machine = machine;
   }
   
   @Override // Override the start method in the Application class
	public void start(Stage primaryStage) throws IOException
	{
		//user and operator selection
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		final Label lb1 = new Label("test");
		Button operator = new Button("Operator");
		Button user = new Button("User");
		hBox.getChildren().add(operator);
		hBox.getChildren().add(user);
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(hBox);
		BorderPane.setAlignment(hBox, Pos.CENTER);
		Scene scene = new Scene(borderPane, 350, 150);
		
		//operator menu
		HBox operatorBox = new HBox();
		operatorBox.setSpacing(10);
		operatorBox.setAlignment(Pos.CENTER);
		Button addItem = new Button("Add Items");
		Button removeCoins = new Button("Remove Coins");
		Button Return = new Button("Return");
		operatorBox.getChildren().add(addItem);
		operatorBox.getChildren().add(removeCoins);
		operatorBox.getChildren().add(Return);
		BorderPane operatorPane = new BorderPane();
		operatorPane.setCenter(operatorBox);
		BorderPane.setAlignment(operatorBox, Pos.CENTER);
		Scene operatorScene = new Scene(operatorPane, 350, 150);
		
		//add item scene
		GridPane addItemPane = new GridPane();
		addItemPane.setPadding(new Insets(10, 10, 10, 10));
		addItemPane.setVgap(5);
		addItemPane.setHgap(5);
		final TextField description = new TextField();
		final Label descLabel = new Label("Description:");
		GridPane.setConstraints(descLabel, 0, 0);
		addItemPane.getChildren().add(descLabel);
		description.setPromptText("Enter Item Description");
		description.setPrefColumnCount(10);
		//description.getText();
		GridPane.setConstraints(description, 1, 0);
		addItemPane.getChildren().add(description);
		Button submit = new Button("Submit");
		addItemPane.getChildren().add(submit);
		GridPane.setConstraints(submit, 2, 0);
		Button Return1 = new Button("Return");
		addItemPane.getChildren().add(Return1);
		GridPane.setConstraints(Return1, 2, 1);
		final TextField price = new TextField();
		final Label priceLabel = new Label("Price:");
		GridPane.setConstraints(priceLabel, 0, 1);
		addItemPane.getChildren().add(priceLabel);
		price.setPromptText("Enter Item Price");
		price.setPrefColumnCount(10);
		//price.getText();
		GridPane.setConstraints(price, 1, 1);
		addItemPane.getChildren().add(price);
		final TextField quantity = new TextField();
		final Label quantityLabel = new Label("Quantity:");
		GridPane.setConstraints(quantityLabel, 0, 2);
		addItemPane.getChildren().add(quantityLabel);
		quantity.setPromptText("Enter Item Quantity");
		quantity.setPrefColumnCount(10);
		//quantity.getText();
		GridPane.setConstraints(quantity, 1, 2);
		addItemPane.getChildren().add(quantity);
		final Label output = new Label("");
		GridPane.setConstraints(output, 0, 3);
		addItemPane.getChildren().add(output);
		Scene addItemScene = new Scene(addItemPane, 350, 150);
		
		//user scene
		HBox userBox = new HBox();
		userBox.setSpacing(10);
		userBox.setAlignment(Pos.CENTER);
		Button showProducts = new Button("Show Products");
		Button insertCoins = new Button("Insert Coins");
		Button buyItem = new Button("Buy");
		Button Return2 = new Button("Return");
		userBox.getChildren().add(showProducts);
		userBox.getChildren().add(insertCoins);
		userBox.getChildren().add(buyItem);
		userBox.getChildren().add(Return2);
		BorderPane userPane = new BorderPane();
		userPane.setCenter(userBox);
		BorderPane.setAlignment(userBox, Pos.CENTER);
		Scene userScene = new Scene(userPane, 350, 150);
		
		//show products
		VBox productsBox = new VBox();
		productsBox.setSpacing(10);
		productsBox.setAlignment(Pos.CENTER);
		Button Return3 = new Button("Return");
		for (Product p : machine.getProductTypes())
		{
			productsBox.getChildren().add(new Label(p.toString()));
		}
		BorderPane productsPane = new BorderPane();
		productsPane.setCenter(productsBox);
		BorderPane.setAlignment(productsBox, Pos.CENTER);
		HBox buttonBox = new HBox();
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().add(Return3);
		productsPane.setBottom(buttonBox);
		BorderPane.setAlignment(buttonBox, Pos.BOTTOM_CENTER);
		Scene productsScene = new Scene(productsPane, 350, 300);
		
		//insertCoins
		VBox coinsBox = new VBox();
		coinsBox.setSpacing(10);
		coinsBox.setAlignment(Pos.CENTER);
		Button Return4 = new Button("Return");
		Button submit1 = new Button("Submit");
		BorderPane coinsPane = new BorderPane();
		final TextField choice = new TextField();
		choice.setPromptText("Item Choice");
		coinsPane.setCenter(coinsBox);
		BorderPane.setAlignment(coinsBox, Pos.CENTER);
		HBox buttonBox1 = new HBox();
		buttonBox1.setSpacing(10);
		buttonBox1.setAlignment(Pos.CENTER);
		buttonBox1.getChildren().add(submit1);
		buttonBox1.getChildren().add(Return4);
		coinsPane.setBottom(buttonBox1);
		BorderPane.setAlignment(buttonBox1, Pos.BOTTOM_CENTER);
		VBox totalBox = new VBox();
		final Label total = new Label(String.valueOf(tempCoins.getTotal()));
		totalBox.setAlignment(Pos.TOP_RIGHT);
		totalBox.getChildren().add(total);
		coinsPane.setTop(totalBox);
		BorderPane.setAlignment(totalBox, Pos.TOP_RIGHT);
		Scene coinsScene = new Scene(coinsPane, 450, 200);
	
		//buy item
		VBox buyBox = new VBox();
		buyBox.setSpacing(10);
		buyBox.setAlignment(Pos.CENTER);
		Button Return5 = new Button("Return");
		Button submit2 = new Button("Submit");
		final Label bought = new Label("");
		//Label message = new Message("");
		BorderPane buyPane = new BorderPane();
		final TextField choice1 = new TextField();
		choice1.setPromptText("Item Choice");
		buyPane.setCenter(buyBox);
		BorderPane.setAlignment(buyBox, Pos.CENTER);
		HBox buttonBox2 = new HBox();
		buttonBox2.setSpacing(10);
		buttonBox2.setAlignment(Pos.CENTER);
		buttonBox2.getChildren().add(submit2);
		buttonBox2.getChildren().add(Return5);
		buyPane.setBottom(buttonBox2);
		BorderPane.setAlignment(buttonBox2, Pos.BOTTOM_CENTER);
		VBox total1Box = new VBox();
		final Label total1 = new Label(String.valueOf(tempCoins.getTotal()));
		total1Box.setAlignment(Pos.TOP_RIGHT);
		total1Box.getChildren().add(total1);
		buyPane.setTop(total1Box);
		BorderPane.setAlignment(total1Box, Pos.TOP_RIGHT);
		Scene buyScene = new Scene(buyPane, 450, 300);
		
		//log in
		VBox loginBox = new VBox();
		loginBox.setSpacing(10);
		loginBox.setAlignment(Pos.CENTER);
		final TextField username = new TextField();
		final TextField password = new TextField();
		Button submit3 = new Button("Submit");
		Button Return6 = new Button("Return");
		username.setPromptText("Username");
		password.setPromptText("Password");
		loginBox.getChildren().add(new Label("Username: "));
		loginBox.getChildren().add(username);
		loginBox.getChildren().add(new Label("Password: "));
		loginBox.getChildren().add(password);
		HBox buttonBox3 = new HBox();
		buttonBox3.setSpacing(10);
		buttonBox3.setAlignment(Pos.CENTER);
		buttonBox3.getChildren().add(submit3);
		buttonBox3.getChildren().add(Return6);
		BorderPane loginPane = new BorderPane();
		loginPane.setCenter(loginBox);
		loginPane.setBottom(buttonBox3);
		Scene loginScene = new Scene(loginPane, 450, 200);
		
		primaryStage.setTitle("Vending Machine"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
		//operator button
		operator.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(loginScene);
					}
				});
				
		//user button	
		user.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(userScene);
			}
		});
		
		//add item button
		addItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(addItemScene);
					}
				});
				
		//remove coins button
		removeCoins.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try
				{
					coinRemove();
				}
				
				catch(IOException e)
				{
					
				}
			}
		});
		
		//add item submit
		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String descText = description.getText();
				double priceAmount = Double.parseDouble(price.getText());
				int quantityAmount = Integer.parseInt(quantity.getText());
				output.setText("Added " + quantity.getText() + " " + description.getText() + " at " + price.getText() + " euro.");
				Product product = new Product(descText, priceAmount);
				try
				{
					DataReader dataReader = new DataReader();
					dataReader.addProduct(product,"" + quantityAmount);
				}
				catch(IOException e)
				{
					
				}
			}
		});
		
		Return1.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(operatorScene);
					}
				});
				
		Return.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(scene);
			}
		});
		
		Return2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(scene);
			}
		});
		
		submit3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{
					login Login = new login();
					if(Login.loginTry(username.getText(), password.getText()))
						primaryStage.setScene(operatorScene);
					else
						loginBox.getChildren().add(new Label("Login failed please try again"));
				}
				catch(IOException e)
				{
					
				}
			}
		});
		
		showProducts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				//DataReader dataReader = new DataReader();
				productsBox.getChildren().clear();
				primaryStage.setScene(productsScene);
				try
				{
				
					stockArray = StocksReturn();
				}
				
				catch(IOException e)
				{
					
				}
				for (int i = 0; i < stockArray.get(0).size(); i++)
					{
						productsBox.getChildren().add(new Label(stockArray.get(0).get(i)));
					}
					
			}
		});
		
		Return3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(userScene);
			}
		});
		
		Return4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(userScene);
			}
		});
		
		Return5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(userScene);
			}
		});
		
		Return6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(scene);
			}
		});
		
		insertCoins.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(coinsScene);
				coinsBox.getChildren().clear();
				char c = 'A';
				for(int i = 0; i < coins.length; i++)
				{
					coinsBox.getChildren().add(new Label(c + ") " + coins[i].getDescription()));
					c++;
				}
				coinsBox.getChildren().add(choice);
			}
		});
		
		submit1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int n = choice.getText().toUpperCase().charAt(0) - 'A';
				if(n > coins.length)
					System.out.print("not an option");
				else
				{
				tempCoins.addCoinToSet(coins[n]);
				total.setText(Double.toString(tempCoins.getTotal()));
				}
			}
		});
	
		buyItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(buyScene);
				total1.setText(String.valueOf(tempCoins.getTotal()));
				buyBox.getChildren().clear();
				bought.setText("");
				//stockArray.clear();
				char c = 'A';
				try
				{
					stockArray = StocksReturn();
					//stockUpdate(stockArray);
				}
				
				catch(IOException e)
				{
					
				}
				//System.out.println(stockArray.get(0).size());
				for (int i = 0; i < stockArray.get(0).size(); i++)
					{
						buyBox.getChildren().add(new Label(c + ")" + stockArray.get(0).get(i) + " - " + stockArray.get(1).get(i)));
						c++;
					}
					//stockArray.clear();
				buyBox.getChildren().add(choice1);
				buyBox.getChildren().add(bought);
			}
		});
		
		//
		submit2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try
				{
					stockArray = StocksReturn();
				}
				catch(IOException e)
				{
					
				}
				int n = choice1.getText().toUpperCase().charAt(0) - 'A';
				int stock = Integer.parseInt(stockArray.get(2).get(n));
				if(Double.parseDouble(stockArray.get(1).get(n)) <= tempCoins.getTotal())
				{
					coinsArr = tempCoins.getCoinList();
					try
					{
							CoinArray = CoinsReturn();
							//System.out.print(CoinArray.get(0).size());
					}
					catch(IOException e)
					{
							
					}
					try{
					for(int i = 0; i < coinsArr.size(); i++)
					{
						for(int j = 0; j < CoinArray.size()+1; j++)
						{
							System.out.println(String.valueOf(Integer.parseInt(CoinArray.get(2).get(j)) + 1));
							if(coinsArr.get(i).getDescription().equals(CoinArray.get(1).get(j))){
								CoinArray.get(2).set(j, String.valueOf(Integer.parseInt(CoinArray.get(2).get(j)) + 1));
								System.out.println(String.valueOf(Integer.parseInt(CoinArray.get(2).get(j))));
							}
						}
					}
					
							updateCoin(CoinArray);
							tempCoins.clearCoins();
							CoinArray.clear();
							total.setText(String.valueOf(tempCoins.getTotal()));
							total1.setText(String.valueOf(tempCoins.getTotal()));
							stockArray.get(2).set(n, String.valueOf(Integer.parseInt(stockArray.get(2).get(n)) - 1));
							updateStock(stockArray);
							bought.setText("Bought " + stockArray.get(0).get(n));
					}
					catch(IOException e)
						{
					
						}
				}
				else 
					bought.setText("Please insert more coins if you wish to buy this item");
		}
		});
		
  }
  
  public ArrayList<ArrayList<String>> StocksReturn() throws IOException
		{
			DataReader dataReader = new DataReader();
			dataReader.clearStock();
			return dataReader.StockReturn();
		}
		
 public ArrayList<ArrayList<String>> CoinsReturn() throws IOException
		{
			DataReader dataReader = new DataReader();
			dataReader.clearCoins();
			return dataReader.CoinReturn();
		}
		
 /*public void stockUpdate(ArrayList<ArrayList<String>> stockList) throws IOException
 {
	DataReader dataReader = new DataReader();
	dataReader.updateStock(stockList);
 } */
	
public void coinUpdate(ArrayList<ArrayList<String>> coinList) throws IOException
 {
	DataReader dataReader = new DataReader();
	dataReader.updateCoins(coinList);
 } 	

 public void coinAdd(Coin coin) throws IOException
 {
	 DataReader dataReader = new DataReader();
	 dataReader.addCoins(coin, "1");
 }
 
 public void coinRemove() throws IOException
 {
	 DataReader dataReader = new DataReader();
	 dataReader.clearCoinList();
 }
 
 public void updateStock(ArrayList<ArrayList<String>> stockUpdate) throws IOException
	{
		FileWriter writer  = new FileWriter("stock.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			//System.out.println("test " + stockUpdate.get(0).size());
			for(int i = 0; i < stockUpdate.get(0).size(); i++)
			{
				//System.out.println(stockUpdate.get(0).get(i) + "," + stockUpdate.get(1).get(i) + "," + stockUpdate.get(2).get(i));
				userWriter.println(stockUpdate.get(0).get(i) + "," + stockUpdate.get(1).get(i) + "," + stockUpdate.get(2).get(i));
			}
			userWriter.close();
	}
	
	public void updateCoin(ArrayList<ArrayList<String>> coinUpdate) throws IOException
	{
		//System.out.print(coinUpdate.get(1).size());
		FileWriter writer  = new FileWriter("coins.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			//System.out.println("test " + stockUpdate.get(0).size());
			for(int i = 0; i < coinUpdate.get(0).size(); i++)
			{
				System.out.println(coinUpdate.get(0).get(i) + "," + coinUpdate.get(1).get(i) + "," + coinUpdate.get(2).get(i));
				userWriter.println(coinUpdate.get(0).get(i) + "," + coinUpdate.get(1).get(i) + "," + coinUpdate.get(2).get(i));
			}
			userWriter.close();
	}
}