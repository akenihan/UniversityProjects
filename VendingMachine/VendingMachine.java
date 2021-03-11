import java.util.ArrayList;

/**
   A vending machine.
*/
public class VendingMachine
{  
   private ArrayList<Product> products;
   public CoinSet coins;
   public CoinSet currentCoins;
   private ArrayList<Product> p;
   public int unique;

   /**
      Constructs a VendingMachine object.
   */
   public VendingMachine()
   { 
      products = new ArrayList<Product>();
      coins = new CoinSet();
      currentCoins = new CoinSet();
	  p = new ArrayList<Product>();
   }
   
   public Product[] getProductTypes()
   {
	   /*boolean check = false;
	   p = new Product[products.size()];
	   for(int i =0; i < products.size(); i++)
	   {
		   for(int j = 0; j < unique; j++)
		   {
			   if(products.get(i) == p[j])
					check = true;
		   }
		   if(check != true)
		   {
			   p[unique] = products.get(i);
		   }
	   }
	   //p = products.toArray(new Product[products.size()]);;*/
	   return p.toArray(new Product[p.size()]);
   }
     
   public void addProduct(Product product, int quantity)
   {
	   boolean check1 = false;
	   for(int i = 0; i < p.size(); i++)
	   {
		   if(product.getDescription().matches(p.get(i).getDescription()))
		   {
				check1 = true;
		   } 
	   }
	   if (!check1)
			p.add(product);
	   
	   for(int i =0; i < quantity; i++)
		products.add(product);
   }
   
   public void addCoin(Coin coin)
   {
	   currentCoins.addCoinToSet(coin);
   }
   
   public String removeMoney()
   {
	   String total = Double.toString(currentCoins.getTotal()); 
	   currentCoins.clearCoins();
	   return total;
   }
   
   public void buyProduct(Product p)
   {
	   if(this.p.size() == 0)
	   {
		    System.out.println("There are no items in this vending machine");
	   }
	   else
	   {
		   /*for(int i =0; i < products.size(); i++)
		   { 
			//if (p.getDescription().matches(products.get(i).getDescription()))
			if (products.contains(p))
			{
				if(p.getPrice() <= currentCoins.getTotal())
				{
					products.remove(i);
					System.out.println("Purchased: " + p);
					for(int j = 0; j < currentCoins.getCoinList().size(); j++)
					{
						coins.addCoinToSet(currentCoins.getCoinList().get(j));
					}
					currentCoins.clearCoins();
					break;
				}
				else
				{
					System.out.printf("Your total is %f, the price of the selected product is %f, please add more coins\n", currentCoins.getTotal(), p.getPrice());
					break;
				}
			}
			else
			{
				System.out.print("Sorry, that item is out of stock");
			}
		   }*/
		   if (products.contains(p))
			{
				if(p.getPrice() <= currentCoins.getTotal())
				{
					products.remove(p);
					System.out.println("Purchased: " + p);
					for(int j = 0; j < currentCoins.getCoinList().size(); j++)
					{
						coins.addCoinToSet(currentCoins.getCoinList().get(j));
					}
					currentCoins.clearCoins();
				}
				else
				{
					System.out.printf("Your total is %f, the price of the selected product is %f, please add more coins\n", currentCoins.getTotal(), p.getPrice());
				}
			}
			else
			{
				System.out.print("Sorry, that item is out of stock\n");
			}
		   
		}
   }
   
   //ADD REMAINING CODE HERE
}