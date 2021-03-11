import java.util.ArrayList;

/**
   A set of coins.
*/


public class CoinSet
{  

	private double total;
	
   private ArrayList<Coin> set;

   /**
      Constructs a CoinSet object.
   */
   public CoinSet()
   {  
      set = new ArrayList<Coin>();
   }
   
   public void addCoinToSet(Coin coin)
   {
	   set.add(coin);
   }
   
   public void clearCoins()
   {
	   set.clear();
   }
   
   public ArrayList<Coin> getCoinList()
   {
	   return set;
   }
   
   public double getTotal()
   {
	   total = 0;
	   for(int i = 0; i < set.size(); i++)
		   total += set.get(i).getValue();
	   return total;
   }
   //ADD REMAINING CODE HERE
}