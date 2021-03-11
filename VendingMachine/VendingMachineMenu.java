import java.util.Scanner;
import java.io.IOException;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.io.*;
/**
   A menu from the vending machine.
*/
public class VendingMachineMenu
{    
   private Scanner in;
   private static Coin[] coins = { new Coin(0.05, "5 cent"),
                                   new Coin(0.10, "10 cent"),
                                   new Coin(0.50, "50 cent"),
                                   new Coin(1, "1 euro") };
	private CoinSet tempCoins = new CoinSet();
   /**
      Constructs a VendingMachineMenu object
   */
   public VendingMachineMenu()
   {
      in = new Scanner(System.in);
   }
   
   /**
      Runs the vending machine system.
      @param machine the vending machine
   */
   public void run(VendingMachine machine)
         throws IOException
   {
      boolean more = true;
      
	  //System.out.print("Would you like to use the G)UI or the C)ommand Line?");
	 // String interfaceOption = in.nextLine().toUpperCase();
	  //if(interfaceOption.equals("C"))
	  //{
		  while (more)
		  { 
	  boolean more1 = true;
			System.out.println("O)perator or U)ser?");
			String userType = in.nextLine().toUpperCase();
			String tryAgain;
			boolean fail = true;
			boolean loginAttempt = false;
			if(userType.equals("O"))
			{
					while(more1)
				{
						while(fail == true)
						{
							System.out.println("Username:");
							String username = in.nextLine().toUpperCase();
							System.out.println("Password:");
							String password = in.nextLine();
							login Login = new login();
							loginAttempt = Login.loginTry(username, password);
							if(loginAttempt == true)
								fail = false;
							else{
								System.out.println("Log in failed, try again? Y/N");
								tryAgain = in.nextLine().toUpperCase();
								if(tryAgain.equals("N"))
									fail = false;
							}
							
						}
						if(loginAttempt)
						{
							System.out.println("A)dd product  R)emove coins  Q)uit");
							String operatorChoice = in.nextLine().toUpperCase();
							if (operatorChoice.equals("A"))
						 {  
							System.out.println("Description:");
							String description = in.nextLine();
							System.out.println("Price:");
							Double price = in.nextDouble();
							System.out.println("Quantity:");
							String quantity = in.nextLine();
							in.nextLine();
							DataReader dataReader = new DataReader();
							dataReader.addProduct(new Product(description, price), quantity);
						 }
						 else if (operatorChoice.equals("R"))
						 {
							 DataReader dataReader = new DataReader();
							 dataReader.clearCoinList();
							 System.out.print("Removed Coins");
						 }
						 else if (operatorChoice.equals("Q"))
						 {
							 more1 = false;
						 }
						}
				}
			}
			if(userType.equals("U"))
			{
				boolean check = true;
				while(check)
				{
					System.out.println("S)how products  I)nsert coin  B)uy  Q)uit");
					String command = in.nextLine().toUpperCase();
					 if (command.equals("S"))
					 {
						DataReader dataReader = new DataReader();
						ArrayList<ArrayList<String>> stockArray = dataReader.StockReturn();
						for (int i = 0; i < stockArray.get(0).size(); i++)
						{
							System.out.println(stockArray.get(0).get(i));
						}
					 }
					 
					 if(command.equals("I"))
					 {
						char c = 'A';
						for(int i = 0; i < coins.length; i++)
						{
							System.out.println(c + ") " + coins[i].getDescription());
							c++;
						}
						System.out.println("Choice:");
						String choice1 = in.nextLine();
						int n = choice1.toUpperCase().charAt(0) - 'A';
						tempCoins.addCoinToSet(coins[n]);
					 }
					 
					 if(command.equals("B"))
					 {
						char c = 'A';
					    DataReader dataReader = new DataReader();
						ArrayList<ArrayList<String>> stockArray = dataReader.StockReturn();
						for (int i = 0; i < stockArray.get(0).size(); i++)
						{
							System.out.println(c + ")" + stockArray.get(0).get(i) + " - " + stockArray.get(1).get(i) );
							c++;
						}
						String choice1 = in.nextLine();
						int n = choice1.toUpperCase().charAt(0) - 'A';
						 if(Double.parseDouble(stockArray.get(1).get(n)) <= tempCoins.getTotal())
						{
							ArrayList<Coin> coinsArr;
							coinsArr = tempCoins.getCoinList();
							dataReader.clearCoins();
							ArrayList<ArrayList<String>> CoinArray = dataReader.CoinReturn();
							for(int i = 0; i < coinsArr.size(); i++)
							{
								for(int j = 0; j < CoinArray.size()+1; j++)
								{
									//System.out.println(String.valueOf(Integer.parseInt(CoinArray.get(2).get(j)) + 1));
									if(coinsArr.get(i).getDescription().equals(CoinArray.get(1).get(j))){
										CoinArray.get(2).set(j, String.valueOf(Integer.parseInt(CoinArray.get(2).get(j)) + 1));
										//System.out.println(String.valueOf(Integer.parseInt(CoinArray.get(2).get(j))));
									}
								}
							}
							
							stockArray.get(2).set(n, String.valueOf(Integer.parseInt(stockArray.get(2).get(n)) - 1));
							updateCoin(CoinArray);
							updateStock(stockArray);
							System.out.println("Bought " + stockArray.get(0).get(n));
							tempCoins.clearCoins();
						}
						else
							System.out.println("Not enough money");
					 } 
					 if(command.equals("Q"))
					 {
						check = false;	
					 }
			}
				 /*System.out.println("S)how products  I)nsert coin  B)uy  A)dd product  R)emove coins  Q)uit");
				 String command = in.nextLine().toUpperCase();

				 if (command.equals("S"))
				 {  /*
					getProductTypes() returns an array of products that doesn't contain duplicates
					
					for (Product p : machine.getProductTypes())
					   System.out.println(p);
				 }
				 else if (command.equals("I")) //allows one coin be inserted at a time
				 { 
					machine.addCoin((Coin) getChoice(coins));
				 }
				 else if (command.equals("R"))
				 {  
					System.out.println("Removed: " + machine.removeMoney());
				 }
				 else if (command.equals("B"))
				 {              
					try
					{
					   Product p = (Product) getChoice(machine.getProductTypes());
					   machine.buyProduct(p);
					   //System.out.println("Purchased: " + p);
					}
					catch (VendingException ex)
					{
					   System.out.println(ex.getMessage());
					}
				 }
				 else if (command.equals("A"))
				 {  
					System.out.println("Description:");
					String description = in.nextLine();
					System.out.println("Price:");
					double price = in.nextDouble();
					System.out.println("Quantity:");
					int quantity = in.nextInt();
					in.nextLine(); // read the new-line character
					machine.addProduct(new Product(description, price), quantity);
				 }
				 else if (command.equals("Q"))
				 { 
					more = false;
				 }*/
			  //}
	  
	  //else if(interfaceOption.equals("G"))
	 // {
		//  Application.launch(["test"]);
	  }
	}
}

   private Object getChoice(Object[] choices)
   {
      while (true)
      {
         char c = 'A';
         for (Object choice : choices)
         {
            System.out.println(c + ") " + choice); 
            c++;
         }
         String input = in.nextLine();
         int n = input.toUpperCase().charAt(0) - 'A';
         if (0 <= n && n < choices.length)
            return choices[n];
      }      
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
		FileWriter writer  = new FileWriter("coins.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			//System.out.println("test " + stockUpdate.get(0).size());
			for(int i = 0; i < coinUpdate.get(0).size(); i++)
			{
				//System.out.println(stockUpdate.get(0).get(i) + "," + stockUpdate.get(1).get(i) + "," + stockUpdate.get(2).get(i));
				userWriter.println(coinUpdate.get(0).get(i) + "," + coinUpdate.get(1).get(i) + "," + coinUpdate.get(2).get(i));
			}
			userWriter.close();
	}
   
}
