import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class DataReader
{
	private File input1;									
	private File input2;
	private Scanner in1;
	private Scanner in2;
	private String [] lineFromText;
	public static ArrayList<String> Description = new ArrayList<String>();
	public static ArrayList<String> Price = new ArrayList<String>();
	public static ArrayList<String> Value = new ArrayList<String>();
	public static ArrayList<String> Name = new ArrayList<String>();
	public static ArrayList<String> AmountOfStock = new ArrayList<String>();
	public static ArrayList<String> Quantity = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> CoinOutput = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> StockOutput = new ArrayList<ArrayList<String>>();
	
	public DataReader() throws IOException
	{
		input1 = new File("Stock.txt");
		input2 = new File("Coins.txt");
		in1 = new Scanner(input1);
		in2 = new Scanner(input2);
	}
	
	public ArrayList<ArrayList<String>> CoinReturn()
	{
		CoinOutput.clear();
		Value.clear();
		Name.clear();
		Quantity.clear();
		while(in2.hasNext())
			{
				lineFromText = (in2.nextLine().split(","));
				Value.add(lineFromText[0]);
				Name.add(lineFromText[1]);
				Quantity.add(lineFromText[2]);
			}
			
			CoinOutput.add(Value);
			CoinOutput.add(Name);
			CoinOutput.add(Quantity);
			in2.close();
			return CoinOutput;
		
	}
	
	public ArrayList<ArrayList<String>> StockReturn()
	{
		StockOutput.clear();
		Description.clear();
		Price.clear();
		AmountOfStock.clear();
		while(in1.hasNext())
			{
				lineFromText = (in1.nextLine().split(","));						
				Description.add(lineFromText[0]);				
				Price.add(lineFromText[1]);	
				AmountOfStock.add(lineFromText[2]);
			}
			StockOutput.add(Description);
			StockOutput.add(Price);
			StockOutput.add(AmountOfStock);
			//System.out.println(StockOutput.get(0).size());
			//in1.close();
			return StockOutput;
		
	}
	
	public void addCoins(Coin coin, String quantity) throws IOException
	{
			ArrayList<ArrayList<String>> coinArray = CoinReturn();
			FileWriter writer  = new FileWriter("coins.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			for(int i = 0; i < coinArray.size(); i++)
			{
				if(coinArray.get(1).get(i).equals(coin.getDescription()))
					coinArray.get(2).set(i, "" + (Integer.parseInt(coinArray.get(2).get(i)) + Integer.parseInt(quantity)));
			}
			for(int i = 0; i < coinArray.size(); i++)
			{
				userWriter.println(coinArray.get(0).get(i) + "," + coinArray.get(1).get(i) + "," + coinArray.get(2).get(i));
			}
			userWriter.close();
	}
	
	public void addProduct(Product product, String quantity) throws IOException
	{
			ArrayList<ArrayList<String>> stockArray = StockReturn();
			FileWriter writer1  = new FileWriter("stock.txt");
			PrintWriter userWriter1  = new PrintWriter(writer1);
			boolean exists = false;
			for(int i = 0; i < stockArray.size(); i++)
			{
				//System.out.print(stockArray.get(0).get(i));
				if(stockArray.get(0).get(i).equals(product.getDescription()))
				{
					stockArray.get(2).set(i, quantity);
					exists = true;
				}
			}
			if(!exists)
			{
				stockArray.get(0).add(product.getDescription());
				//System.out.println(product.getDescription());
				stockArray.get(1).add("" + product.getPrice());
				//System.out.println("" + product.getPrice());
				stockArray.get(2).add(String.valueOf(quantity));
				//System.out.println(String.valueOf(quantity));
			}
			
			for(int i = 0; i < stockArray.get(0).size(); i++)
			{
				userWriter1.println(stockArray.get(0).get(i) + "," + stockArray.get(1).get(i) + "," + stockArray.get(2).get(i));
			}
			userWriter1.close();
			
	}
	
	public void updateStock(ArrayList<String> names, ArrayList<String> values, ArrayList<String> quantitites) throws IOException
	{
			StockOutput.clear();
			Description.clear();
			Price.clear();
			AmountOfStock.clear();
			ArrayList<ArrayList<String>> stockUpdate = new ArrayList<ArrayList<String>>();
			stockUpdate.add(names);
			stockUpdate.add(values);
			stockUpdate.add(quantitites);
			FileWriter writer  = new FileWriter("stock.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			System.out.println("test " + stockUpdate.get(0).size());
			for(int i = 0; i < stockUpdate.size(); i++)
			{
				System.out.println(stockUpdate.get(0).get(i) + "," + stockUpdate.get(1).get(i) + "," + stockUpdate.get(2).get(i));
				userWriter.println(stockUpdate.get(0).get(i) + "," + stockUpdate.get(1).get(i) + "," + stockUpdate.get(2).get(i));
			}
			userWriter.close();
	}
	
	public void updateCoins(ArrayList<ArrayList<String>> coinList) throws IOException
	{
			FileWriter writer  = new FileWriter("coins.txt");
			PrintWriter userWriter  = new PrintWriter(writer);
			for(int i = 0; i < coinList.size(); i++)
			{
				userWriter.println();
			}
			userWriter.close();
	}
	
	public void clearCoinList() throws IOException
	{
		FileWriter writer  = new FileWriter("coins.txt");
		PrintWriter userWriter  = new PrintWriter(writer);
		userWriter.println("0.05,5 cent,0");
		userWriter.println("0.10,10 cent,0");
		userWriter.println("0.50,50 cent,0");
		userWriter.print("1.00,1 euro,0");
		userWriter.close();
	}
	
	public void clearCoins()
	{
		CoinOutput.clear();
	}
	
	public void clearStock()
	{
		StockOutput.clear();
	}
}