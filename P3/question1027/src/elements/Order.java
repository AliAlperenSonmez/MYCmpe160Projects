package elements;

/**
 * Trader class is the abstract class of orders.
 * 
 * @author Ali Alperen Sönmez
 * 
 */
public abstract class Order{
	/**
	 * amount of the coins in the order
	 */
	private double amount;
	
	/**
	 * price of the order
	 */
	private final double price;
	
	/**
	 * ID of the trader who gives the order
	 */
	private int traderID;
	
	/**
	 * <p>
	 * Constructor of the Order
	 * 
	 * @param traderID ID of the trader who gives the order
	 * @param amount of coins in the order
	 * @param price of the order
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * <p>
	 * Getter for the price
	 * 
	 * @return Double price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * <p>
	 * Getter for the amount
	 * 
	 * @return Double amount
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * <p>
	 * Getter for the traderID
	 * 
	 * @return int traderID
	 */
	public int getTraderID() {
		return traderID;
	}
	
}