package elements;
/**
* A Transaction object keeps a transaction that consist of one buying and one selling orders.
* 
* @author Ali Alperen Sönmez
* 
*/

public class Transaction {
	
	/**
	 * SellingOrder of the transaction
	 */
	private SellingOrder sellingOrder;
	
	/**
	 * BuyingOrder of the transaction
	 */
	private BuyingOrder buyingOrder;

	/**
	 * <p>
	 * Constructor of the Transaction
	 * 
	 * @param sellingOrder
	 * @param buyingOrder
	 */
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.buyingOrder = buyingOrder;
		this.sellingOrder = sellingOrder;
	}
	
	/**
	 * <p>
	 * Getter for the buyingOrder
	 * 
	 * @return Wallet wallet
	 */
	public BuyingOrder getBuyingOrder() {
		return buyingOrder;
	}
	
	/**
	 * <p>
	 * Getter for the sellingOrder
	 * 
	 * @return Wallet wallet
	 */
	public SellingOrder getSellingOrder() {
		return sellingOrder;
	}
}
