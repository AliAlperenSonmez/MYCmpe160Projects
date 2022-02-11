package elements;
/**
* SellingOrder is a subclass of the abstract class Order.
* 
* @author Ali Alperen Sönmez
* 
*/
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	
	/**
	 * <p>
	 * Constructor of the SellingOrder
	 * 
	 * @param traderID ID of the trader who gives the order
	 * @param amount of coins in the order
	 * @param price of the order
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * <p>
	 * Overriding comparable interface's compareTo method
	 * 
	 * @param o Other SellingOrder object
	 * @return int -1 or 1
	 */
	@Override
	public int compareTo(SellingOrder o) {
		if (this.getPrice()<o.getPrice()) {
			return -1;
		}
		else if (this.getPrice()==o.getPrice()) {
			if (this.getAmount()>o.getAmount()) {
				return -1;
			}
			else if (this.getAmount()==o.getAmount()) {
				return this.getTraderID()-o.getTraderID();
			}
			else {return 1;}
		}
		else {return 1;}
	}

}
