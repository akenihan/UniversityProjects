/**
   A product in a vending machine.
*/
public class Product
{  
   private String description;
   private double price;

   /**
      Constructs a Product object
      @param aDescription the description of the product
      @param aPrice the price of the product
   */
   public Product(String aDescription, double aPrice)
   {  
      description = aDescription;
      price = aPrice;
   }
   
   public String toString()
   {
	   return description + " - " + price + " Euro";
   }
   
   public String getDescription()
   {
	   return description;
   }
   
   public double getPrice()
   {
	   return price;
   }
	//ADD REMAINING CODE HERE
}
